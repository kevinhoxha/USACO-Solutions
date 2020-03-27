package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CowGymnastics
{
	private static final String path = "";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + "gymnastics.in"));
			Map<String, List<String>> map = new HashMap<>();
			String line = br.readLine();
			String[] parts = line.split(" ");
			int k = Integer.parseInt(parts[0]);

			boolean firstLine = true;
			for (int i = 0; i < k; i++)
			{
				line = br.readLine();
				parts = line.split(" ");
				if (firstLine)
				{
					for (int j = 0; j < parts.length; j++)
					{
						List<String> l = new ArrayList<String>();
						Collections.addAll(l, Arrays.copyOfRange(parts, j + 1, parts.length));
						map.put(parts[j], l);
					}
					firstLine = false;
					continue;
				}
				for (int p = 0; p < parts.length - 1; p++)
				{
					for (int q = p + 1; q < parts.length; q++)
					{
						map.get(parts[q]).remove(parts[p]);
					}
				}
			}
			br.close();
			// System.out.println(map);
			int tot = 0;
			for (List<String> v : map.values())
			{
				tot += v.size();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(path + "gymnastics.out"));
			bw.write(String.valueOf(tot));
			bw.close();
			// System.out.println(tot);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
