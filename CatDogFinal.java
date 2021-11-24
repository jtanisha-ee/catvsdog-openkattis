import java.util.Scanner;
import java.util.ArrayList;

public class CatDogFinal {
       // on a Bipartite graph, the value of max flow = size of maximum matching set of edges

    private int bpgraph [][]; // adjacency matrix
    
    
    public CatDogFinal(int v) { // v = voters. we draw an edge if 2 voters are incompatible
        bpgraph = new int[v][v];
    }

    public void addEdge(int from, int to ) {
        this.bpgraph[from][to] = 1; 
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
    
    static int seenNode = 1;
    public int fordfulkerson(CatDogFinal a, int source, int sink) {
        int [][] g = a.bpgraph;
        int numNodes = g.length;
        System.out.println("reached here");
        int [] seen = new int[numNodes];
        for(int max_flow = 0; ; ) {

            int flow = dfs(g, seen, source, sink, Integer.MAX_VALUE);
            seenNode++;
            max_flow += flow;
            
            if (flow == 0) {
                System.out.println("max flow = " + flow);
                return max_flow;  
            }
        }
    }
    public static int dfs(int[][] g, int[] seen, int source, int sink, int flow) {
      
      if (source == sink) return flow;
  
      int[] weight = g[source];
      seen[source] = seenNode;
        System.out.println("flow = " + flow);
      for (int i = 0; i < weight.length; i++) {
        if (seen[i] != seenNode && weight[i] > 0) {
  
          if (weight[i] < flow) flow = weight[i];
          int dfs_flow = dfs(g, seen, i, sink, flow);
  
          if (dfs_flow > 0) {
            g[source][i] -= dfs_flow;
            g[i][source] += dfs_flow;
            return dfs_flow;
          }
        }
      }
  
      return 0;
    } 



    public static void main(String [] args ) {
        int [] maxmatches;
        Scanner input = new Scanner(System.in);
        String n = input.nextLine();
        int nCases = Integer.parseInt(n);
        maxmatches = new int [nCases];
		for (int ctr = 0; ctr < nCases; ctr++) {
			String line2 = input.nextLine();
			String[] values = line2.split(" ");
            
            int numVoters = Integer.parseInt(values[2]);
            CatDogFinal graph = new CatDogFinal(numVoters+2);
            
            
		    ArrayList<Voter> voters = new ArrayList<>(numVoters); //numvertices before bpgraph
            int catlovers = 0;
            int doglovers = 0;
            for (int i = 0; i <numVoters; i++) {
                String kr = input.nextLine();
                String[] vals = kr.split(" ");
                int keep = Integer.parseInt(vals[0].substring(1));
                int remove = Integer.parseInt(vals[1].substring(1));
                String pet = vals[0].substring(0,1);
                
                boolean catLover = pet.equals("C");
                
                if (catLover) {
                    voters.add(new Voter(i, -keep, remove)); //positive = cat, neg = dog
                    catlovers++;
                } else {
                    voters.add(new Voter(i, keep, -remove));
                    doglovers++;
                }  
            }
            //draw edge when 2 voters are incompatible
            for (Voter v1 : voters) {
                for (Voter v2 : voters) {
                    if (v1.keep == v2.remove && v1 != v2) {
                        graph.addEdge(v1.index, v2.index);
                    } else if (v2.keep == v1.remove && v1 != v2) {
                        graph.addEdge(v2.index, v1.index);
                    }
                }
            } 
            //draw edge from source to cat lover and from dog lover to sink.
            //effectively convert graph into a bipartite graph, for max flow computation
            for (Voter v : voters) {
                if (v.keep > 0) {
                    graph.addEdge(0,v.index);
                } else {
                    graph.addEdge(v.index, numVoters);
                }
            } 
            //end draw graph
            //int [][] g, int source, int sink
            int s = numVoters;
            int t = numVoters+1;
            int maximal_match = graph.fordfulkerson(graph, s, t);
            System.out.println("mm = " +  maximal_match);
            maxmatches[ctr] = maximal_match;
                
        }
        input.close();
        for (int i = 0; i < maxmatches.length; i++) {
            System.out.println(maxmatches[i]);
        }
    }    
}
