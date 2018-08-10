package logic;

import java.util.List;
import java.util.ArrayList;

public class Digraph {
    
    private int V;                
    private int E;                 
    private List<DirectedEdge>[] adjacent;          

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adjacent = (List<DirectedEdge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++)
            adjacent[v] = new ArrayList<DirectedEdge>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adjacent[v].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adjacent(int v) {
        return adjacent[v];
    }
}
