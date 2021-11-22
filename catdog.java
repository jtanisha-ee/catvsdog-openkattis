import java.util.Scanner;

public class catdog {
    private static int[][] bpgraph;
    private static int[] matching;
    private static int n;
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
    
    public int fordfulkerson(int [][] bpgraph, int source, int sink) {
        int numNodes = bpgraph.length;
        int [] seen = new int[numNodes];
        for(int max_flow = 0; ; ) {

            int flow = dfs(bpgraph, seen, source, sink, Integer.MAX_VALUE);
            seenNode++;
            max_flow += flow;
            if (flow == 0) {
                return max_flow;
            }
        }
    }
    public static int dfs(int[][] bpgraph, int[] seen, int node, int sink, int flow) {
      // Found sink node, return flow thus far
      if (node == sink) return flow;
  
      int[] weight = bpgraph[node];
      seen[node] = seenNode;
  
      for (int i = 0; i < weight.length; i++) {
        if (seen[i] != seenNode && weight[i] > 0) {
  
          if (weight[i] < flow) flow = bpg[i];
          int dfsFlow = dfs(bpgraph, seen, i, sink, flow);
  
          if (dfsFlow > 0) {
            bpgraph[node][i] -= dfsFlow;
            bpgraph[i][node] += dfsFlow;
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
        Scanner input = new Scanner();
        int nCases = input.getInt();
		for (int ctr = 0; ctr < nTests; ctr++) {
			int nCats = input.getInt();
			int nDogs = input.getInt();
			int numVoters = input.getInt();

			N = nVoters;
        int[][];
        ArrayList<Voter> voters = new ArrayList<>(numVoters); //numvertices


    }
    
}
