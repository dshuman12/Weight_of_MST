import java.io.File;  // Import the File class
import java.io.IOException;
import java.io.FileWriter;

public class Test {

  public static int factorial(int n) {
    int res = 1, i;
    for (i=2; i<=n; i++)
      res *= i;
    return res;
  }
  
  //unused
  public static double getThreshold(int dim, int n) {
    return Math.pow(factorial(dim/2)/(n * Math.pow(Math.PI, dim/2.0)), 1.0/dim) * 4;
  }

  public static void main(String[] args) {
    //Write to File
    int dim = 1;

    String filename = "ourtest_" + dim + ".txt";
    try {
      File myObj = new File(filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    String filename_theirtest = "theirtest";
    for (int i = 1; i <= 4; i++) {
      theirTest(i, filename_theirtest + "_" + i);
    }
  }

   
    public static void theirTest(int dim, String filename) {
    //change filename to include the batch # (i.e. for each new run, increase n by 1)
      long startTime = System.nanoTime();

    String batch = "131072_finalreplit";
    String filename_full = filename + "_" + batch + ".txt";
    try {
      File myObj = new File(filename_full);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    try {
      FileWriter myWriter = new FileWriter(filename_full);

      int[] sizes = new int[]{128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
      int tries = 5;
      double[] avgWeights = new double[sizes.length];

      for (int size : sizes) {
        double threshold = getThreshold(dim, size);
        double[] results = new double[tries];

        for (int i = 0; i < tries; i ++) {
          V[] vertices = V.randVertices(dim, size);           
          double mstWeight = Main.primsAttempt2(size, vertices, dim, threshold);
          System.out.println("size=" + size + " MST Weight:" + mstWeight);
          myWriter.write("size=" + size + " MST Weight:" + mstWeight + "\r\n");
          results[i] = mstWeight; 
        }

        double sum = 0;
        for (double r : results) {
          sum += r;
        }     
        long endTime = System.nanoTime(); 
        long millisElapsed = (endTime - startTime) / 1000000; 
        // https://www.techiedelight.com/measure-elapsed-time-execution-time-java/

        System.out.println("avg weight for size " + size + ": " + (sum/tries));
        myWriter.write("avg weight for size " + size + ": " + (sum/tries) + "\r\n");
        System.out.println("Execution time, 5 runs in milliseconds: " + millisElapsed);
        myWriter.write("Execution time, 5 runs in milliseconds: " + millisElapsed + "\r\n");
      }
    myWriter.close();

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

   public static void ourTest(int dim, String filename) {
      try {
        FileWriter myWriter = new FileWriter(filename);
      int[] sizes = new int[]{20, 50, 100, 200, 400, 600, 1000, 1200, 1600};
      int tries = 3;
      double[] avgWeights = new double[sizes.length];

      for (int size : sizes) {
        double threshold = getThreshold(dim, size);
        double[] results = new double[tries];

        for (int i = 0; i < tries; i ++) {
          V[] vertices = V.randVertices(dim, size); 
          double mstWeight = Main.primsAttempt2(size, vertices, dim, threshold);
          System.out.println("size=" + size + " MST Weight:" + mstWeight);
          try {
            myWriter.write("size=" + size + " MST Weight:" + mstWeight + "\r\n");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          
          results[i] = mstWeight; 
        }

        double sum = 0;
        for (double r : results) {
          sum += r;
        }       
        System.out.println("avg weight for size " + size + ": " + (sum/tries));
        try {
          myWriter.write("avg weight for size " + size + ": " + (sum/tries) + "\r\n");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      }
      try {
        myWriter.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
}