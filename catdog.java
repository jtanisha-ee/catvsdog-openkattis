import java.util.Scanner;
import java.io.*;

public class catdog {

    private int bpgraph [][]; // adjacency matrix
    public catdog(int v) { // v = voters. we draw an edge if 2 voters are incompatible
        bpgraph = new int[v][v];
    }

    public void addEdge(int to, int from ) {
        
        this.bpgraph[to][from] = 1;
        this.bpgraph[from][to] = 1; 
    }

    //initialize source and sink to 1
    public int [][] createBPgraph(int [][] g) {
        g[0][0] = 1;
        g[g.length][g.length] = 1;
        return g;
    } 
    

    private static class Voter {
        int index;
        int keep;
        int remove;

        public Voter(int index, int keep, int remove) {
            this.index = index;
            this.keep = keep;
            this.remove= remove;
        }

        
    }
    
    //TODO: implement ford fulkerson to find max flow
    public int fordfulkerson(int [][] g, int source, int sink) {
        int numNodes = g.length;
        int [] seen = new int[numNodes];
        for(int max_flow = 0; ; ) {

            int flow = dfs(g, seen, source, sink, Integer.MAX_VALUE);
            seenNode++;
            max_flow += flow;
            if (flow == 0) {
                return max_flow;
            }
        }
    }
    public static int dfs(int[][] g, int[] seen, int source, int sink, int flow) {
      // Found sink node, return flow thus far
      if (source == sink) return flow;
  
      int[] weight = g[source];
      seen[source] = seenNode;
  
      for (int i = 0; i < weight.length; i++) {
        if (seen[i] != seenNode && weight[i] > 0) {
  
          if (weight[i] < flow) flow = bpg[i];
          int dfsFlow = dfs(g, seen, i, sink, flow);
  
          if (dfsFlow > 0) {
            g[source][i] -= dfsFlow;
            g[i][source] += dfsFlow;
            return dfsFlow;
          }
        }
      }
  
      return 0;
    } //node = voter


    //TODO: implement maximal matching here
    public int maxmatching() {
        int maxvoters = 0;

        return maxvoters;

    }

    public static void main(String [] args ) {
        Scanner input = new Scanner(System.in);
        int nCases = input.getInt();
		for (int ctr = 0; ctr < nTests; ctr++) {
			int nCats = input.getInt();
			int nDogs = input.getInt();
			int numVoters = input.getInt();
            catdog graph = new catdog(numVoters+2);
            int sourceNodeCapacity = 1;
            int sinkNodeCapacity = 1;
            
		    ArrayList<Voter> voters = new ArrayList<>(numVoters); //numvertices

            for (int i = 1; i <=numVoters; i++) {
                String keep_str = input.next();
                String remove_str = input.next();
                String pet = s1.substring(0,1);
                int keep = Integer.parseInt(s1.substring(1));
				int remove = Integer.parseInt(s2.substring(1));
                boolean catLover = pet.equals("C");
                
                if (catLover) {
                    voters.add(new Voter(i, -keep, remove)); //positive = cat, neg = dog
                } else {
                    voters.add(new Voter(i, keep, -remove));
                }  
            }
            //draw edge when 2 voters are incompatible
            for (Voter v1 : voters) {
                for (Voter v2 : voters) {
                    if (v1.keep == v2.leave && v1 != v2) {
                        graph.addEdge(v1.index, v2.index);
                    } else if (v2.keep == v1.remove && v1 != v2) {
                        graph.addEdge(v2.index, v1.index);
                    }
                }
            } 
            //draw edge from source to cat lover and from dog lover to sink.
            for (Voter v : voters) {
                if (v.keep > 0) {
                    graph.addEdge(0,v.index);
                } else {
                    graph.addEdge(v.index,g.length);
                }
            } 
            //end draw graph
            createBPgraph(graph);
            
        }
    

    }    
}
