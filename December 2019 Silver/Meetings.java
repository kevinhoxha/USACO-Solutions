import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Meetings
{
	private static final String path = "";
	private static final String filename = "meetings";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));

			String[] info = br.readLine().split(" ");
			int n = Integer.valueOf(info[0]);
			int l = Integer.valueOf(info[1]);

			ArrayList<Cow> cows = new ArrayList<Cow>();

			double totalW = 0;
			int numMeetings = 0;

			for (int i = 0; i < n; i++)
			{
				String[] temp = br.readLine().split(" ");
				cows.add(new Cow(Double.valueOf(temp[0]), Double.valueOf(temp[1]), Double.valueOf(temp[2])));
				totalW += Integer.valueOf(temp[0]);
			}

			br.close();

			double weightBarn = 0;
			double increment;

			Collections.sort(cows);

			while (weightBarn < .5 * totalW)
			{
				increment = Double.MAX_VALUE;
				for (int i = 0; i < cows.size(); i++)
				{
					if (cows.get(i).v == -1 && cows.get(i).x < increment && cows.get(i).x != 0)
					{
						increment = cows.get(i).x;
					} else if ((cows.get(i).v == 1 && l - cows.get(i).x < increment) && l - cows.get(i).x != 0)
					{
						increment = l - cows.get(i).x;
					}

					if (i < cows.size() - 1)
					{
						if (cows.get(i).v == 1 && cows.get(i + 1).v == -1 && cows.get(i).x < cows.get(i + 1).x
								&& (cows.get(i + 1).x - cows.get(i).x) / 2 < increment
								&& cows.get(i + 1).x - cows.get(i).x != 0)
						{
							increment = (cows.get(i + 1).x - cows.get(i).x) / 2;
						} else if (cows.get(i + 1).v == 1 && cows.get(i).v == -1 && cows.get(i + 1).x < cows.get(i).x
								&& (cows.get(i).x - cows.get(i + 1).x) / 2 < increment
								&& cows.get(i).x - cows.get(i + 1).x != 0)
						{
							increment = (cows.get(i).x - cows.get(i + 1).x) / 2;
						}
					}
				}

				for (int i = 0; i < cows.size(); i++)
				{
					Cow temp = cows.get(i);
					temp.x += temp.v * increment;

					if (temp.x == 0 || temp.x == l)
					{
						weightBarn += temp.w;
						cows.remove(i);
						i--;
					}
				}

				Collections.sort(cows);

				for (int i = 0; i < cows.size() - 1; i++)
				{
					Cow current = cows.get(i);
					Cow next = cows.get(i + 1);

					if (current.x == next.x)
					{
						Cow temp1 = current;
						temp1.v *= -1;
						cows.set(i, temp1);

						Cow temp2 = next;
						temp2.v *= -1;
						cows.set(i + 1, temp2);

						numMeetings++;
					}
				}

				// System.out.println("");
			}

			// System.out.println(numMeetings);
			bw.write(Integer.toString(numMeetings));
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static class Cow implements Comparable<Cow>
	{
		public double w;
		public double x;
		public double v;

		public Cow(double w, double x, double v)
		{
			this.w = w;
			this.x = x;
			this.v = v;
		}

		@Override
		public int compareTo(Cow arg0)
		{
			if (this.x > arg0.x)
			{
				return 1;
			}
			if (this.x < arg0.x)
			{
				return -1;
			} else
			{
				return 0;
			}
		}

		public String toString()
		{
			return "" + w + " " + x + " " + v;
		}

	}
}
