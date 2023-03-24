import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {
  public String name;
  public ArrayList<Edge> adjlist;
  public ArrayList<Vertex> path;
  public int minDistance = Integer.MAX_VALUE;

  public int compareTo(Vertex other) {
    return Integer.compare(minDistance, other.minDistance);
  }

  public Vertex(String _name) {
    name = _name;
    adjlist = new ArrayList<Edge>();
    path = new ArrayList<Vertex>();
  }
}
