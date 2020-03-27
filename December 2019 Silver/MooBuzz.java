import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MooBuzz
{
	private static final String path = "";
	private static final String filename = "moobuzz";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			int n = Integer.parseInt(br.readLine());
			br.close();
			long num = 0;
			while (n > 8)
			{
				n -= 8;
				num += 15;
			}

			if (n == 1)
			{
				num += 1;
			} else if (n == 2)
			{
				num += 2;
			} else if (n == 3)
			{
				num += 4;
			} else if (n == 4)
			{
				num += 7;
			} else if (n == 5)
			{
				num += 8;
			} else if (n == 6)
			{
				num += 11;
			} else if (n == 7)
			{
				num += 13;
			} else if (n == 8)
			{
				num += 14;
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			bw.write(Long.toString(num));
			bw.close();
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
