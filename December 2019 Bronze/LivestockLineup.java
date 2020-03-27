package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LivestockLineup
{
	private static final String path = "";
	private static final List<String> COWS = Arrays.asList("Bessie", "Buttercup", "Belinda", "Beatrice", "Bella",
			"Blue", "Betsy", "Sue");
	private static final List<String> ALL_PERMS = allCombos(new int[]
	{ 0, 1, 2, 3, 4, 5, 6, 7 });

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + "lineup.in"));
			boolean firstline = true;
			String line = null;
			List<Constraint> constraints = new ArrayList<>();
			while ((line = br.readLine()) != null)
			{
				if (firstline)
				{
					firstline = false;
					continue;
				}
				String[] names = line.split(" must be milked beside ");
				String i1 = String.valueOf(COWS.indexOf(names[0]));
				String i2 = String.valueOf(COWS.indexOf(names[1]));
				constraints.add(new Constraint(i1 + i2, i2 + i1));
			}
			br.close();

			Set<String> validPerms = new TreeSet<>();
			for (String perm : ALL_PERMS)
			{
				boolean valid = true;
				for (Constraint constraint : constraints)
				{
					if (!perm.contains(constraint.o1) && !perm.contains(constraint.o2))
					{
						valid = false;
						break;
					}
				}
				if (valid)
				{
					StringBuilder sb = new StringBuilder();
					for (char c : perm.toCharArray())
					{
						sb.append(COWS.get(Integer.parseInt(String.valueOf(c)))).append("\n");
					}
					validPerms.add(sb.substring(0, sb.length() - 1));
				}
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(path + "lineup.out"));
			bw.write(validPerms.iterator().next());
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static List<String> allCombos(int[] data)
	{
		if (data.length == 1)
		{
			return Arrays.asList(String.valueOf(data[0]));
		} else if (data.length == 2)
		{
			String s1 = String.valueOf(data[0]) + String.valueOf(data[1]);
			String s2 = String.valueOf(data[1]) + String.valueOf(data[0]);
			return Arrays.asList(s1, s2);
		} else
		{
			int[] subdata = Arrays.copyOfRange(data, 1, data.length);
			List<String> subret = allCombos(subdata);
			List<String> ret = new ArrayList<>();
			for (String str : subret)
			{
				for (int i = 0; i <= str.length(); i++)
				{
					ret.add(str.substring(0, i) + String.valueOf(data[0]) + str.substring(i, str.length()));
				}
			}
			return ret;
		}
	}

	private static class Constraint
	{
		public Constraint(String o1, String o2)
		{
			this.o1 = o1;
			this.o2 = o2;
		}

		public String o1;
		public String o2;
	}
}
