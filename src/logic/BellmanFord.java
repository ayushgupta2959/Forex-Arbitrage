package logic;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BellmanFord {
    private double[] distTo;               
    private DirectedEdge[] edgeTo;         
    private boolean[] onQueue;             
    private Queue<Integer> queue;          
    private int cost;                      
    private Iterable<DirectedEdge> cycle;  

    public BellmanFord(Digraph G, int s) {
        distTo  = new double[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        queue = new LinkedList<Integer>();
        queue.add(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.remove();
            onQueue[v] = false;
            relax(G, v);
        }
    }

    private void relax(Digraph G, int v) {
        for (DirectedEdge e : G.adjacent(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    queue.add(w);
                    onQueue[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return; 
            }
        }
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        Digraph spt = new Digraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        DirectedCycle finder = new DirectedCycle(spt);
        cycle = finder.cycle();
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
}
