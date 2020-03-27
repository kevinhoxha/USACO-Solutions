import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Triangles
{
	private static final String path = "";
	private static final String filename = "triangles";
	private static final long MOD = 1000000007L;

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			String line = br.readLine();
			int N = Integer.parseInt(line);
			P[] points = new P[N];
			Map<Long, List<P>> ys = new HashMap<>();
			Map<Long, List<P>> xs = new HashMap<>();
			int i = 0;
			while ((line = br.readLine()) != null)
			{
				String[] split = line.split(" ");
				long x = Long.parseLong(split[0]);
				long y = Long.parseLong(split[1]);
				points[i] = new P(x, y);

				List<P> vl = xs.get(x);
				if (vl == null)
					xs.put(x, new ArrayList<>(N));
				xs.get(x).add(points[i]);

				List<P> hl = ys.get(y);
				if (hl == null)
					ys.put(y, new ArrayList<>(N));
				ys.get(y).add(points[i]);

				i++;
			}
			br.close();
			long area = 0;
			for (P p : points)
			{
				List<P> xNeighbors = xs.get(p.x);
				List<P> yNeighbors = ys.get(p.y);
				if (xNeighbors.size() > 1 && yNeighbors.size() > 1)
				{
					for (P p2 : xNeighbors)
					{
						p.yDist = p.yDist + Math.abs(p.y - p2.y);
					}
					p.yDist = p.yDist;
					if (p.yDist != 0)
					{
						for (P p2 : yNeighbors)
						{
							p.xDist = p.xDist + Math.abs(p.x - p2.x);
						}
						p.xDist = p.xDist;
						area += (p.xDist * p.yDist) % MOD;
					}
				}
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			bw.write(Long.toString(area % MOD));
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static class P
	{
		public long x;
		public long y;
		public long xDist = 0;
		public long yDist = 0;

		public P(long x, long y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString()
		{
			return "(" + x + "," + y + ")=(" + xDist + "," + yDist + ")";
		}
	}
}
