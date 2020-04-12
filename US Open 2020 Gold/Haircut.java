import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Haircut
{
	private static final String path = "";
	private static final String filename = "haircut";
	private static Map<Long, Long> inverts = new HashMap<Long, Long>();
	private static long N = 0;

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			N = Integer.parseInt(br.readLine());
			long[] hairValues = Stream.of(br.readLine().split(" ")).mapToLong(Long::valueOf).toArray();
			long[] hairCopy = Arrays.copyOf(hairValues, hairValues.length);
			mergeSort(hairValues, hairCopy, 0, hairValues.length - 1);
			long count = 0;
			bw.write("" + 0);
			for (int i = 1; i < N; i++)
			{
				long temp = i - 1;
				if (inverts.containsKey(temp))
				{
					count += inverts.get(temp);
				}
				bw.write("\n" + count);
			}
			bw.close();
			br.close();
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static void mergingAlgorithm(long[] hairValues, long[] hairCopy, int low, int mid, int high)
	{
		int a = low;
		int b = low;
		int c = mid + 1;
		while (b <= mid && c <= high)
		{
			if (hairValues[b] <= hairValues[c])
			{
				hairCopy[a++] = hairValues[b++];
			} else
			{
				long length = mid - b + 1;
				if (inverts.putIfAbsent(hairValues[c], length) != null)
				{
					inverts.put(hairValues[c], length + inverts.get(hairValues[c]));
				}
				hairCopy[a++] = hairValues[c++];
			}
		}
		while (b <= mid)
		{
			hairCopy[a++] = hairValues[b++];
		}
		for (b = low; b <= high; b++)
		{
			hairValues[b] = hairCopy[b];
		}
	}

	public static void mergeSort(long[] hairValues, long[] hairCopy, int low, int high)
	{
		if (high == low)
		{
			return;
		}
		int mid = (low + ((high - low) >> 1));
		mergeSort(hairValues, hairCopy, low, mid);
		mergeSort(hairValues, hairCopy, mid + 1, high);
		mergingAlgorithm(hairValues, hairCopy, low, mid, high);
	}
}
