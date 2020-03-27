import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class LoanRepayment
{
	private static final String path = "";
	private static final String filename = "loan";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			String line = br.readLine();
			br.close();

			String[] params = line.split(" ");
			long N = Long.parseLong(params[0]);
			long K = Long.parseLong(params[1]);
			long M = Long.parseLong(params[2]);
			long low = 1, high = N / M;
			while (high - low > 1)
			{
				long mid = (high + low) / 2;
				if (isSolution(mid, N, K, M))
				{
					low = mid;
				} else
				{
					high = mid;
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			bw.write(Long.toString(low));
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static final boolean isSolution(long x, long N, long K, long M)
	{
		double p = (1.0 * K * x + x + 1 + Math.pow(-1.0, K + 1.0) * Math.pow(x, -K)) / Math.pow(x + 1, 2);
		if (p < 1)
			return false;
		long G = N / x;
		for (long i = 2; i <= K; i++)
		{
			long pay = (N - G) / x;
			G += pay;
			if (pay <= M)
			{
				if (G + (K - i) * M >= N)
				{
					return true;
				} else
				{
					return false;
				}
			}
		}
		return false;
	}
}
