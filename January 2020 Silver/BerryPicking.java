import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BerryPicking
{
	private static final String path = "";
	private static final String filename = "berries";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			String line = br.readLine();
			String[] params = line.split(" ");
			int N = Integer.parseInt(params[0]);
			int K = Integer.parseInt(params[1]);

			line = br.readLine();
			params = line.split(" ");
			int[] trees = new int[params.length];
			for (int i = 0; i < params.length; i++)
			{
				trees[i] = Integer.parseInt(params[i]);
			}
			br.close();

// code solution here
			int[] baskets = new int[K];
			if (K < N)
			{
				Arrays.sort(trees);
				baskets = Arrays.copyOfRange(trees, N - K, N);
			} else
			{
				for (int i = 0; i < trees.length; i++)
				{
					baskets[i] = trees[i];
				}
				Arrays.sort(baskets);
			}

			int total = lowSum(baskets);
			List<int[]> allSplits = getAllSplits(baskets);
			for (int[] is : allSplits)
			{
				int s = lowSum(is);
				if (s > total)
				{
					total = s;
				}
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			bw.write(Integer.toString(total));
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static final List<int[]> getAllSplits(int[] baskets)
	{
		List<int[]> ret = new ArrayList<>();
		if (baskets != null)
		{
			int[][] tmp = splitTheHighest(baskets);
			if (tmp != null)
			{
				for (int i = 0; i < tmp.length; i++)
				{
					ret.add(tmp[i]);
					ret.addAll(getAllSplits(tmp[i]));
				}
			}
		}
		return ret;
	}

	private static final int lowSum(int[] in)
	{
		int sum = 0;
		for (int i = 0; i < in.length / 2; i++)
		{
			sum += in[i];
		}
		return sum;
	}

	private static final int[][] splitTheHighest(int[] baskets)
	{
		int highestDiv = 1;
		for (int i = 0; i < baskets.length - 1; i++)
		{
			if (baskets[i] < baskets[baskets.length - 1] / (i + 2))
			{
				highestDiv++;
			}
		}
		if (highestDiv == 1)
			return null;
		int[][] ret = new int[highestDiv - 1][baskets.length];

		for (int i = 2; i <= highestDiv; i++)
		{
			int[] tmp1 = divide(baskets[baskets.length - 1], i);
			int[] tmp2 = Arrays.copyOf(baskets, baskets.length - 1);
			int[] tmp = join(tmp1, tmp2);
			Arrays.sort(tmp);
			ret[i - 2] = Arrays.copyOfRange(tmp, tmp.length - baskets.length, tmp.length);
		}

		return ret;
	}

	private static final int[] divide(int N, int d)
	{
		int[] ret = new int[d];
		int remain = N % d;
		for (int i = 0; i < ret.length; i++)
		{
			if (remain > 0)
			{
				ret[i] = N / d + 1;
				remain--;
			} else
			{
				ret[i] = N / d;
			}
		}
		return ret;
	}

	private static final int[] join(int[] arr1, int[] arr2)
	{
		int[] ret = new int[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length; i++)
		{
			ret[i] = arr1[i];
		}
		for (int i = 0; i < arr2.length; i++)
		{
			ret[arr1.length + i] = arr2[i];
		}
		return ret;
	}
}