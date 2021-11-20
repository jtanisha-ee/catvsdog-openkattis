package kattis;

import java.awt.List;
import java.util.ArrayList;

//Cat vs. Dog
public class CatVsDog {
	private static class Person {

		int index;
		int keep;
		int leave;

		public Person(int index, int keep, int leave) {
			this.index = index;
			this.keep = keep;
			this.leave = leave;
		}

		public String toString() {
			return "[Index: " + index + " keep " + keep + " leave " + leave + "]";
		}
	}

	private static boolean[][] map;
	private static int[] matching;
	private static int N;

	public static void main(String[] args) {

		Kattio io = new Kattio(System.in);
		int nTests = io.getInt();
		for (int t = 0; t < nTests; t++) {
			int nCats = io.getInt();
			int nDogs = io.getInt();
			int nVoters = io.getInt();

			N = nVoters;
			map = new boolean[N][N];
			ArrayList<Person> people = new ArrayList<>(nVoters);

			// Reading of votes started
			for (int v = 0; v < nVoters; v++) {
				String s1 = io.getWord();
				String s2 = io.getWord();

				int keep = Integer.parseInt(s1.substring(1));
				int leave = Integer.parseInt(s2.substring(1));
				String animal = s1.substring(0, 1);
				boolean dogLover = animal.equals("D");

				// Positive number represent dog, negative represent cat
				if (dogLover)
					people.add(new Person(v, keep, -leave));
				else
					people.add(new Person(v, -keep, leave));
			} // Reading of votes end

			// Make map started
			for (Person p1 : people) {
				for (Person p2 : people) {
					if (p1.keep == p2.leave && p1 != p2) {
						map[p1.index][p2.index] = true;
						map[p2.index][p1.index] = true;
					} else if (p2.keep == p1.leave && p1 != p2) {
						map[p1.index][p2.index] = true;
						map[p2.index][p1.index] = true;
					}
				}
			} // Make map end

			matching = new int[N];
			for (int i = 0; i < N; i++)
				matching[i] = -1;

			boolean[] checked;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				checked = new boolean[N];
				if (people.get(i).keep < 0)
					if (match(i, checked))
						cnt++;
			}

			// for (Person p : people)
			// System.out.println(p.toString());
			//
			// for (int i = 0; i < N; i++) {
			// System.out.println();
			// for (int j = 0; j < N; j++)
			// System.out.print(map[i][j]);
			// }

			// System.out.println("N: " + N + " cnt: " + cnt);
			System.out.println((N - cnt));
			// System.out.println(cnt);
		}

	}

	private static boolean match(int p, boolean[] checked) {
		for (int adj = 0; adj < N; adj++) {
			if (map[p][adj] && adj != p) {
				if (matching[adj] < 0) {
					matching[adj] = p;
					return true;
				}
			}
		}
		for (int adj = 0; adj < N; adj++) {
			if (map[p][adj] && !checked[adj] && adj != p) {
				checked[adj] = true;
				if (match(matching[adj], checked)) {
					matching[adj] = p;
					return true;
				}
			}
		}

		return false;

	}
}
