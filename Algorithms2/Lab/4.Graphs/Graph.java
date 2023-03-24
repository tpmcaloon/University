import java.util.*;

public class Graph {
  public ArrayList<Vertex> vlist;

  public Graph() {
    vlist = new ArrayList<Vertex>();
  }

  public void addVertex(String name) {
    vlist.add(new Vertex(name));
  }

  public Vertex getVertex(String name) {
    if (!vlist.isEmpty()) {
      for (Vertex ver: vlist) {
        if (ver.name.equals(name)) {
          return ver;
        }
      }
    }
    return null;
  }

  private void addEdge(Edge edge) {
    try {
      Vertex from = getVertex(edge.from.name);
      for (Edge added: from.adjlist) {
        if (edge.to.name.equals(added.to.name)) {
          return;
        }
      }
      Vertex to = getVertex(edge.to.name);
      from.adjlist.add(new Edge(from, to, edge.weight));
      to.adjlist.add(new Edge(to, from, edge.weight));
    } catch (Exception e) {}
  }

  public void addEdge(String from, String to, int weight) {
    Vertex vFrom = getVertex(from);
    if (null == vFrom) {
      vFrom = new Vertex(from);
      vlist.add(vFrom);
    }
    Vertex vTo = getVertex(to);
    if (null == vTo) {
      vTo = new Vertex(to);
      vlist.add(vTo);
    }
    Edge edge = new Edge(vFrom, vTo, weight);
    vFrom.adjlist.add(edge);
    if (!from.equals(to)) {
      edge = new Edge(vTo, vFrom, weight);
      vTo.adjlist.add(edge);
    }
  }

  public Edge getEdge(String from, String to) {
    Vertex vFrom = getVertex(from);
    Vertex vTo = getVertex(to);
    if (null != vFrom && null != vTo && !vFrom.adjlist.isEmpty() && !vTo.adjlist.isEmpty()) {
      for (Edge edge: vFrom.adjlist) {
        if (edge.to.equals(vTo)) {
          return edge;
        }
      }
    }
    return null;
  }

  // Returns the cost of the minimum spanning tree
  public int MSTCost() {
    Graph graph = MST();
    if (null != graph) {
      int result = 0;
      for(Vertex vertex : graph.vlist) {
        for (Edge edge : vertex.adjlist) {
          result += edge.weight;
        }
      }
      return result/2;
    }
    return -1;
  }

  // Return the minimum spanning tree of the original graph.
  public Graph MST() {
    if (vlist.isEmpty()) 
      return null;
    
    try {
      dijkstra(vlist.get(0));
      Graph result = new Graph();
      for (Vertex vertex : vlist) {
        result.addVertex(vertex.name);
      }
      for (Vertex vertex : vlist) {
        if (vertex.path.size() <= 0) 
          continue;
        if (vertex.path.size() == 1) {
          Vertex from = vertex.path.get(0);
          result.addEdge(getEdge(from.name, vertex.name));
          continue;
        }
        for (int i = 0; i < vertex.path.size()-1; i++) {
          Vertex from = vertex.path.get(i);
          Vertex to = vertex.path.get(i+1);
          result.addEdge(getEdge(from.name, to.name));
        }
        Vertex from = vertex.path.get(vertex.path.size()-1);
        result.addEdge(getEdge(from.name, vertex.name));
      }
      return result;
    } catch(Exception e) {}
    
    return null;
  }

  // returns the cost of the shortest path between v1 and v2
  public int SPCost(String from, String to) {
    Graph graph = SP(from, to);
    if (null != graph) {
      return graph.MSTCost();
    }
    return 0;
  }

  // returns a graph containing the route (sequence of
  // vertices) of the shortest path from v1 to v2
  public Graph SP(String from, String to) {
    try {
      Vertex vFrom = getVertex(from);
      Vertex vTo = getVertex(to);
      dijkstra(vFrom);
      Graph result = new Graph();
      result.addVertex(vTo.name);
      for(Vertex vertex : vTo.path) {
        result.addVertex(vertex.name);
      }
      if (vTo.path.size() == 1) {
        vFrom = vTo.path.get(0);
        result.addEdge(getEdge(vFrom.name, vTo.name));
      }
      for (int i = 0; i < vTo.path.size()-1; i++) {
        vFrom = vTo.path.get(i);
        Vertex target = vTo.path.get(i+1);
        result.addEdge(getEdge(vFrom.name, target.name));
      }
      vFrom = vTo.path.get(vTo.path.size()-1);
      result.addEdge(getEdge(vFrom.name, vTo.name));
      return result;
    } catch(Exception e) {}
    
    return null;
  }
  
  private void reset() {
    if (vlist.isEmpty()) {
      return;
    }
    for (Vertex vertex : vlist) {
      vertex.minDistance = Integer.MAX_VALUE;
      vertex.path.clear();
    }
  }

  private void dijkstra(Vertex source) {
    reset();
    source.minDistance = 0;
    PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
    queue.add(source);

    while (!queue.isEmpty()) {
      Vertex u = queue.poll();
      for (Edge neighbour: u.adjlist) {
        int newDist = u.minDistance + neighbour.weight;

        if (neighbour.to.minDistance <= newDist) {
          continue;
        }
        // Remove the node from the queue to update the distance value.
        queue.remove(neighbour.to);
        neighbour.to.minDistance = newDist;
        
        // Take the path visited till now and add the new node.s
        neighbour.to.path = new ArrayList<Vertex>(u.path);
        neighbour.to.path.add(u);
        
        // Reenter the node with new distance.
        queue.add(neighbour.to);
      }
    }
  }
}
