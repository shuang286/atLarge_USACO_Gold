import java.util.*;
import java.io.*;

/*

In this tree problem, we go from the leaves (dead ends) of the entire barn tree inwards, mapping every barn
with a number using recursion. I was able to assign the least number to every internal node by starting from
every leaf, and branching inwards from every leaf. That number represents the least number of barns it would
take to reach the leaves (distance from the leaf).

Then we give every barn/node another number, the number of barns away from where the cow Bessie initially starts
(distance from Bessie's original position). To count how many farmers are needed to effectively catch Bessie,
I compare the two numbers (the distance from the nearest leaf and the distance from Bessie's initial position).
If a barn's distance from the nearest leaf is either the same or one less than the distance from Bessie's original
position, then I know we need a farmer, adding one to the final count. This "the same or one less than" condition
is ensured by (b >= c.f) and "return" in method dfs2 (depth-first search, cow Bessie moves from the original
position).

*/
class Path {
	public int s;
	public int d;
	public Path(int s_in,int d_in){
		s = s_in;
		d = d_in;
	}
}

class Barn{
	public HashMap<Integer, Path> nbrmap;
	public int k;
	public Barn(int k_in) {
		nbrmap = new HashMap<Integer, Path>();
		k = k_in;
		f = Integer.MAX_VALUE;
	}
	public int f;

}

public class AtLarge {
	static Barn[] barn = null;
	static int n = 0;
	static int count = 0;
	public static void dfs1(Barn prev, Barn c, int len) {
		c.f = Math.min(c.f, len);
		int f = c.f + 1;
		for (int nbr: c.nbrmap.keySet()) {
			Barn h = barn[nbr];
			if (h == prev) continue;
			if (f < h.f){
				dfs1(c, h, f);
			}
		}
	}
	public static void dfs2(Barn prev, Barn c, int b) {
		if (b >= c.f){
			count ++;
			return;
		}
		for (int nbr: c.nbrmap.keySet()) {
			Barn h = barn[nbr];
			if (h == prev) continue;
			dfs2(c,h,b+1);
		}
		
	}
	public static void main (String args[]) throws FileNotFoundException {
		Scanner scan = new Scanner( new File("atlarge.in"));
		n = scan.nextInt();
		int startpos = scan.nextInt();
		barn = new Barn[n+1];
		for (int k = 1; k<=n; k++){
			barn[k]= new Barn(k);
		}
		for (int i = 0; i<n-1; i++){
			int s = scan.nextInt();
			int d = scan.nextInt();
			Path e = new Path(s,d);
			barn[s].nbrmap.put(d, e);
			barn[d].nbrmap.put(s, e);
		}
		//for (int k = 1; k<=n; k++){
			//System.out.print("barn " + k + ": ");
			//Barn h = barn[k];
			//for (int nbr: h.nbrmap.keySet()){
				//System.out.print(nbr + " ");
			//}
			//System.out.println();
		//}
		for (int k = 1; k<=n; k++){
			Barn h = barn[k];
			if (h.nbrmap.size() == 1) {
				dfs1(null, h, 0);
			}
		}
		//for (int k = 1; k<=n; k++){
			//Barn h = barn[k];
			//System.out.println(h.k+" f="+h.f);
		//}
		count = 0;
		dfs2(null, barn[startpos], 0);
		scan.close();
        PrintWriter writer = new PrintWriter("atlarge.out");
  		writer.println(count);
  		writer.close();
	}
//		scan.close();
//		PrintWriter writer = new PrintWriter("shuffle.out");
//		writer.println(positions.size());
//		writer.close();
	
}
