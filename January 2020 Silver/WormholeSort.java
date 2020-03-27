import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class WormholeSort
{
	private static final String path = "";
	private static final String filename = "wormsort";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			String s = br.readLine();
			int n = Integer.parseInt(s.split(" ")[0]);
			int m = Integer.parseInt(s.split(" ")[1]);
			String[] pos = br.readLine().split(" ");
			int[] positions = new int[pos.length];
			for (int i = 0; i < pos.length; i++)
			{
				positions[i] = Integer.parseInt(pos[i]);
			}
			int[][] wormholes = new int[m][4];
			for (int i = 0; i < m; i++)
			{
				wormholes[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			sortbyColumn(wormholes, 2);
			int ret = 0;
			ArrayList<HashSet<Integer>> accessible = new ArrayList<HashSet<Integer>>();
			for (int i = 0; i < n; i++)
			{
				HashSet<Integer> temp = new HashSet<Integer>();
				accessible.add(temp);
			}
			for (int i = 0; i < n; i++)
			{
				accessible.get(positions[i] - 1).add(i + 1);
			}
			boolean solved = false;
			if (isSorted(positions))
			{
				ret = -1;
			} else
			{
				for (int i = 0; i < wormholes.length; i++)
				{
					ret = wormholes[i][2];
					for (int k = i; k >= 0; k--)
					{
						accessible.get(wormholes[k][1] - 1).addAll(accessible.get(wormholes[k][0] - 1));
						accessible.get(wormholes[k][0] - 1).addAll(accessible.get(wormholes[k][1] - 1));
					}
					for (int k = 0; k < accessible.size(); k++)
					{
						if (!accessible.get(k).contains(k + 1))
						{
							break;
						} else if (k == accessible.size() - 1)
						{
							solved = true;
						}
					}
					if (solved)
					{
						break;
					}

				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			br.close();
			bw.write(Integer.toString(ret));
			bw.close();
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static boolean isSorted(int[] positions)
	{
		for (int i = 1; i < positions.length; i++)
		{
			if (positions[i] <= positions[i - 1])
			{
				return false;
			}
		}
		return true;
	}

	public static void sortbyColumn(int arr[][], int col)
	{
		// Using built-in sort function Arrays.sort
		Arrays.sort(arr, new Comparator<int[]>()
		{

			@Override
			// Compare values according to columns
			public int compare(final int[] entry1, final int[] entry2)
			{

				// To sort in descending order revert
				// the '>' Operator
				if (entry1[col] > entry2[col])
					return -1;
				else
					return 1;
			}
		}); // End of function call sort().
	}
}
