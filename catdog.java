import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class catdog {

    private int bpgraph [][]; // adjacency matrix
    int catlovers, doglovers;
    int [][] g;
    int [] matching;
    boolean [] used;
    
    public catdog(int v) { // v = voters. we draw an edge if 2 voters are incompatible
        bpgraph = new int[v][v];
        bpgraph[0][0] = 1; //initliaze source and sink to 1
        bpgraph[v][v] = 1;
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
      
      if (source == sink) return flow;
  
      int[] weight = g[source];
      seen[source] = seenNode;
  
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

    private static boolean[][] graph;
	private static int[] matches;
	private static int dim;

    public static void main(String [] args ) {
        Scanner input = new Scanner(System.in);
        int nCases = input.nextInt();
        System.out.println("nCases " + nCases);
		for (int ctr = 0; ctr < nCases; ctr++) {
			int nCats = input.nextInt();
			int nDogs = input.nextInt();
			int numVoters = input.nextInt();
            System.out.println("ncats " + nCats + "nDogs" +nDogs);
            catdog graph = new catdog(numVoters+2);
            
            dim = numVoters;
            matches = new boolean[dim][dim];
		    ArrayList<Voter> voters = new ArrayList<>(numVoters); //numvertices

            for (int i = 1; i <=numVoters; i++) {
                String keep_str = input.next();
                String remove_str = input.next();
                String pet = keep_str.substring(0,1);
                int keep = Integer.parseInt(keep_str.substring(1));
				int remove = Integer.parseInt(remove_str.substring(1));
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
                    if (v1.keep == v2.remove && v1 != v2) {
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
                    graph.addEdge(v.index, numVoters+2);
                }
            } 
            
            for (Voter v : voters) {
				for (Voter v1 : voters) {
					if (v.keep == v1.leave && v != p1) {
						map[v.index][v1.index] = true;
						map[v1.index][v.index] = true;
					} else if (v1.keep == v.leave && p1 != p2) {
						map[v.index][v1.index] = true;
						map[v1.index][v.index] = true;
					}
				}
			} 

			matches = new int[dim];
			for (int i = 0; i < dim; i++)
				matches[i] = -1;

			boolean[] seen;
			int count = 0;
			for (int i = 0; i <dim; i++) {
				seen = new boolean[dim];
				if (voters.get(i).keep < 0)  //i = voter index
					if (maxMatch(i, seen))
						count++;
			}
            
        }
        input.close();
    }    
    private static boolean maxMatch(int p, boolean[] seen) {
		for (int adj = 0; adj < N; adj++) {
			if (map[p][adj] && adj != p) {
				if (matching[adj] < 0) {
					matching[adj] = p;
					return true;
				}
			}
		}
		for (int adj = 0; adj < dim; adj++) {
			if (graph[p][adj] && !seen[adj] && adj != p) {
				seen[adj] = true;
				if (match(matches[adj], seen)) {
					matches[adj] = p;
					return true;
				}
			}
		}

		return false;

	}
}


