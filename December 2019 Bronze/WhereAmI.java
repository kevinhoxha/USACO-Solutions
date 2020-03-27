package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class WhereAmI
{
	private static final String path = "";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + "whereami.in"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + "whereami.out"));
			int n = Integer.parseInt(br.readLine());
			String mailboxes = br.readLine();
			br.close();
			int answer = 0;
			for (int i = 1; i <= n; i++)
			{
				ArrayList<String> substrings = new ArrayList<>();
				for (int k = 0; k < n - i + 1; k++)
				{
					if (substrings.contains(mailboxes.substring(k, k + i)))
					{
						break;
					} else
					{
						substrings.add(mailboxes.substring(k, k + i));
					}
				}
				if (substrings.size() == n - i + 1)
				{
					answer = i;
					break;
				}
			}
			bw.write(Integer.toString(answer));
			bw.close();
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
