package logic;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;             
    private DirectedEdge[] edgeTo;        
    private boolean[] onStack;            
    private Stack<DirectedEdge> cycle;    

    public DirectedCycle(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adjacent(v)) {
            int w = e.to();

            if (cycle != null) return;

            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();

                DirectedEdge edge = e;
                while (edge.from() != w) {
                    cycle.push(edge);
                    edge = edgeTo[edge.from()];
                }
                cycle.push(edge);

                return;
            }
        }
        onStack[v] = false;
    }
	
	public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}