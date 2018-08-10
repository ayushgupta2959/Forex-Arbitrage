package logic;
//haha
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Arbitrage {

    private Arbitrage() { }

    public static void main(String[] args) {

    	Scanner sc1 = new Scanner(System.in);
    	System.out.println("Enter file path");
    	String fileName = sc1.next();
    	Scanner sc = null;
    	Digraph G = null;
    	String[] name = null;
    	try {
    		sc = new Scanner(new FileReader(fileName));
    		int V = sc.nextInt();
    		name = new String[V];

    		G = new Digraph(V);
    		for (int v = 0; v < V; v++) {
    			name[v] = sc.next();
    			System.out.print(name[v] + " ");//<-
    			for (int w = 0; w < V; w++) {
    				double rate = sc.nextDouble();
    				System.out.print( rate + " ");
    				//System.out.print(-Math.log(rate) + " ");
    				DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
    				G.addEdge(e);
    			}
    			System.out.println();
    		}
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	double mincost = 0.0;
    	Stack<DirectedEdge> minResult = new Stack<DirectedEdge>();
       for(int q=0;q<G.V();q++) {	
    	   double summin = 0.0;
	       BellmanFord bf = new BellmanFord(G, q);
	       Stack<DirectedEdge> printResult = null;
	        if (bf.hasNegativeCycle()) {
	            printResult = new Stack<DirectedEdge>();
	            for (DirectedEdge e : bf.negativeCycle()) {
	            	printResult.push(e);
	            	summin += e.weight();
	            }
	        }
	        if(summin<mincost) {
	        	mincost = summin;
	        	minResult = printResult;
	        }
       }
	   if(mincost!= 0.0)
		   {int n=minResult.size();
		   double stake = 1000.0;
	            for (int i=0;i<n;i++) {
	            	DirectedEdge e = minResult.pop();
	            	System.out.print( stake+" "+ name[e.from()]);
	                stake *= Math.exp(-e.weight());
	                System.out.println("= "+ stake+" "+ name[e.to()]);
	            }
	        }
	        else {
	            System.out.println("No arbitrage opportunity");
	        }
       }
}








