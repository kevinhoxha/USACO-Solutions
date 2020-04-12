import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FavoriteColors
{
	private static final String path = "";
	private static final String filename = "fcolor";
	private static int N = 0;
	private static final Map<Integer, Set<Integer>> positions = new HashMap<>();

	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
		BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
		String line = br.readLine();
		N = Integer.parseInt(line.split(" ")[0]);
		for (int i = 0; i < N; i++)
		{
			positions.put(i + 1, new HashSet<Integer>());
		}
		while ((line = br.readLine()) != null)
		{
			int a = Integer.parseInt(line.split(" ")[0]);
			int b = Integer.parseInt(line.split(" ")[1]);
			positions.get(b).add(a);
		}
		br.close();

		int[] colors = new int[N];
		for (int i = 0; i < colors.length; i++)
		{
			colors[i] = i + 1;
		}
		int[] prevColors = Arrays.copyOf(colors, colors.length);
		while (true)
		{
			for (Entry<Integer, Set<Integer>> entry : positions.entrySet())
			{
				if (entry.getValue().size() > 1)
				{
					transform(colors, entry.getValue());
				}
			}
			if (Arrays.equals(colors, prevColors))
			{
				break;
			} else
			{
				prevColors = Arrays.copyOf(colors, colors.length);
			}
		}

		// for (int i = 0; i < colors.length; i++)
		// {
		// System.out.println(colors[i]);
		// }
		for (int i = 0; i < colors.length; i++)
		{
			bw.write("" + colors[i] + "\n");
		}
		bw.close();
	}

	private static void transform(int[] colors, Set<Integer> value)
	{
		if (value == null || value.iterator() == null)
		{
			return;
		}
		int min = value.iterator().next();
		for (Integer v : value)
		{
			min = Math.min(min, v.intValue());
		}
		for (int i = 0; i < colors.length; i++)
		{
			if (value.contains(i + 1) && min != i + 1 && colors[i] != min)
			{
				for (int j = i + 1; j < colors.length; j++)
				{
					if (colors[j] > colors[i])
						colors[j]--;
				}
				colors[i] = min;
			}
		}
		for (Integer v : value)
		{
			if (v.intValue() == min)
				continue;
			positions.get(min).addAll(positions.get(v.intValue()));
			positions.get(v.intValue()).clear();
		}
	}
}