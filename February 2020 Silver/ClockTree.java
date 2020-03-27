import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ClockTree
{
	private static final String path = "";
	private static final String filename = "clocktree";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[] clock = new int[N];
			for (int i = 0; i < N; i++)
			{
				clock[i] = Integer.parseInt(st.nextToken());
			}
			Graph<Integer> g = new Graph<>();
			for (int i = 0; i < N - 1; i++)
			{
				st = new StringTokenizer(br.readLine());
				g.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true);
			}
			br.close();

			int countOfGoodNodes = 0;
			for (int i = 1; i <= N; i++)
			{
				int tot = dfs(i, -1, g, clock);
				if ((clock[i - 1] + tot) % 12 == 0 || (clock[i - 1] + tot - 1) % 12 == 0)
				{
					countOfGoodNodes++;
				}
			}

			// System.out.println(countOfGoodNodes);
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			bw.write(Integer.toString(countOfGoodNodes));
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static int dfs(int node, int parent, Graph<Integer> g, int[] clock)
	{
		List<Integer> children = g.getNext(node);
		int tot = 0;
		for (Integer child : children)
		{
			if (child == parent)
				continue;
			if (g.getNext(child).size() == 1)
			{
				tot += 12 - clock[child - 1];
			} else
			{
				int cs = dfs(child, node, g, clock);
				int a = (cs + clock[child - 1]) % 12;
				tot = tot + 12 - (a == 0 ? 12 : a);
			}
		}
		return tot;
	}

	private static class Graph<T>
	{
		private Map<T, List<T>> map = new HashMap<>();

		public void addVertex(T s)
		{
			map.put(s, new LinkedList<T>());
		}

		public void addEdge(T source, T destination, boolean bidirectional)
		{
			if (!map.containsKey(source))
				addVertex(source);

			if (!map.containsKey(destination))
				addVertex(destination);

			map.get(source).add(destination);
			if (bidirectional == true)
			{
				map.get(destination).add(source);
			}
		}

		public List<T> getNext(T s)
		{
			return map.get(s);
		}
	}
}
