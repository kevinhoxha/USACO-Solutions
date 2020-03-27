import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class SwapitySwapitySwap
{
	private static final String path = "";
	private static final String filename = "swap";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);
			int k = Integer.parseInt(line[2]);
			long[] cows = new long[n];
			long[][] steps = new long[m][2];
			for (int i = 1; i <= n; i++)
			{
				cows[i - 1] = i;
			}
			for (int i = 0; i < m; i++)
			{
				String[] step = br.readLine().split(" ");
				steps[i][0] = Integer.parseInt(step[0]);
				steps[i][1] = Integer.parseInt(step[1]);
			}
			for (int i = 0; i < m; i++)
			{
				reverseArray(cows, (int) steps[i][0], (int) steps[i][1]);
			}
			long[] master = cows;

			ArrayList<long[]> orders = new ArrayList<>();
			orders.add(master);
			int iterations = highestPower(k);
			for (int i = 0; i < Math.log(iterations) / Math.log(2); i++)
			{
				long[] temp = new long[n];
				for (int j = 0; j < n; j++)
				{
					temp[j] = cows[(int) (master[j] - 1)];
				}
				orders.add(temp);
				cows = temp;
				master = temp;
			}
			k -= iterations;
			while (k != 0)
			{
				int iterations2 = (int) (Math.log(highestPower(k)) / Math.log(2));
				long[] order = orders.get(iterations2);
				long[] temp = new long[n];
				for (int i = 0; i < n; i++)
				{
					temp[i] = cows[(int) (order[i] - 1)];
				}
				cows = temp;
				k -= highestPower(k);
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));

			for (int i = 0; i < n; i++)
			{
				bw.write(Long.toString(cows[i]) + "\n");
			}
			bw.close();
			br.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void reverseArray(long arr[], int start, int end)
	{
		start -= 1;
		int len = end - start;
		if (len <= 0)
			return;

		int len2 = len >> 1;
		long temp;

		for (int i = 0; i < len2; ++i)
		{
			temp = arr[start + i];
			arr[start + i] = arr[end - i - 1];
			arr[end - i - 1] = temp;
		}
	}

	public static int highestPower(int n)
	{
		int temp = (int) (Math.log(n) / Math.log(2));
		return (int) Math.pow(2, temp);
	}
}
