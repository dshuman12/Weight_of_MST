import java.lang.Math; 

public class V implements Comparable<V>  {
  double[] coord;
  //distance to this vertex, if any, index in heap. retired
  double dist;
  int heapIndex;

  public int getHeapInd() {
    return this.heapIndex;
  }

  public void setHeapInd(int x) {
    this.heapIndex = x;
  }
  public double[] getCoord() {
    return this.coord;
  }

  public double getDist() {
    return this.dist;
  }

  public double setDist(double d) {
    this.dist = d;
    return d;
  }

  public int compareTo(V other) {
    return Double.compare(getDist(), other.getDist());
  }
  public V(double[] coord) {
    this.coord = coord;
    this.dist = 0;
    //initialized as not in heap
    this.heapIndex = -1;
  }

    public V(double[] coord, double d) {
    this.coord = coord;
    this.dist = d;
  }

  public static V randVertex(int dim) {
    // if dim is 0, it's just an empty object
    double[] coord = new double[dim];

    for (int i = 0; i < dim; i++) {
      coord[i] = Math.random();
    }
    return new V(coord);
  }

  // make list of random vertices
  public static V[] randVertices(int dim, int size) {
    V[] lst = new V[size];
    for (int i = 0; i < size; i++) {
      lst[i] = randVertex(dim);
    }
    return lst;
  }

    /**
    ** For our one case, generate + store weight to vertex a.
    **/

    public double makeRandWeight() {
      //square to get smaller later
      double v = Math.random();//Math.pow(Math.random(), 2);
      this.dist = v;
      return v;
    }
  /**
  generates Euclidean Distance + sets weight to b, from vertex a.
  **/

    public double findWeight(V b, int dim) {
    // we always use arrays of same length (aka same dimension)
    double[] pt1 = this.getCoord();
    double[] pt2 = b.getCoord();
    double squared = 0;
    for (int i = 0; i < dim; i++) {
      squared += Math.pow(Math.abs(pt1[i] - pt2[i]), 2);
    }
    //return squared;
    double w = Math.sqrt(squared);
    return w;
    
  }

  public String toString() {
    String s = "vertex (";
    int size = this.coord.length;
    for (int i = 0; i < size; i++) {
      s += this.coord[i] + ", ";
    }
    s += ")/: " + this.dist;
    s += "/ ind: " + this.heapIndex;
    return s;
  }

}