
import java.util.*;

public class Main {
 
  public static double primsAttempt2(int n, V[] vertices, int dim) {
    int verticesLeft = vertices.length;
    // index corresponding to vertices
    double[] dist = new double[n]; 
    Arrays.fill(dist, Double.MAX_VALUE);
    dist[0] = 0; // tracks previous distances
    //vertices in MST
    Boolean[] S = new Boolean[n];
    Arrays.fill(S, false);

    while (verticesLeft > 0) { 
      // FIND SMALLEST!!
      int minIndex = -1;
      double min = Double.MAX_VALUE;  
      //Loop through the array  
      for (int i = 0; i < dist.length; i++) {  
      //Compare elements of array with min  
        if(dist[i] < min && !S[i])  {
          min = dist[i];  
          minIndex = i;
        }
      }  
      V v = vertices[minIndex];
      S[minIndex] = true;
      verticesLeft--;
      // check each edge from v -> w
      for(int j = 0; j < n; j++){
        // Possible edge, inserting edges, 
        // only look at vertices not in set
        V w = vertices[j];
          if (!S[j]) {
            double weight;
            // calculate euc distance  
            if (dim > 1) {
              weight = v.findWeight(w, dim);
            }
            else {
              weight = Math.random();
            }
            if(dist[j] > weight) {
              dist[j] = weight;
            }                
          }
      }
    }
     
    return getWeight(dist);
  }

    public static double primsAttempt(int n, V[] vertices, int dim, double threshold) {
      // initialize values
      double[] dist = new double[n];
      Arrays.fill(dist, Double.MAX_VALUE); // the weights are all > 0
      dist[0] = 0;
      V[] prev = new V[n];
      Arrays.fill(prev, null);
      
      GHeap2 heap = new GHeap2(new V[] {vertices[0]}, n);

      //vertices in MST
      List<V> S = new ArrayList<V>();
      while (heap.length() > 0) { 
        V v = heap.extractMin();
        S.add(v);
      // put old code down below if we need
      // check each edge from v -> w
        for(int j = 0; j < n; j++){
          // Possible edge
          // only look at vertices not in set
          V w = vertices[j];

            if (!S.contains(w)) {
              double weight;
              // calculate euc distance  
              if (dim > 1) {
                weight = v.findWeight(w, dim);
                if (weight > threshold) {
                  continue;
                }
              }
              else {
                weight = Math.random(); //Math.pow(Math.random(), 2);//
                // threshold implementation
                // if the weight is greater than threshold, there is a high likelihood that another edge will be smaller. 
                if (weight > threshold) {
                  continue;
                }
              }
              if(dist[j] > weight) {
                dist[j] = weight;
                prev[j] = v;
                w.setDist(weight);
                heap.update(w);
              }                
            }
        }
      }
      
      String toReturn = "[" + dist[0];
      for (int i = 1; i < dist.length; i++) {
        toReturn += (", " + dist[i]);
      }
      toReturn += "]";
      System.out.println("DIST: " + toReturn);
      
      return getWeight(dist);
    }
  
    // This gets the weight of the MST
    public static double getWeight(double[] dist) {    
      double sum = 0;
      for (double distance : dist) {
        if (distance == Double.MAX_VALUE) {
          continue;
        }
        sum += distance;
      }
      return sum;
    }
} 