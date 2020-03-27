import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MilkVisits
{
	private static final String path = "";
	private static final String filename = "milkvisits";

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path + filename + ".in"));
			String line = br.readLine();
			String[] inputs = line.split(" ");
			int N = Integer.parseInt(inputs[0]);
			Farm[] farms = new Farm[N];
			String milkTypes = line = br.readLine();
			for (int i = 0; i < farms.length; i++)
			{
				farms[i] = new Farm(i + 1, milkTypes.charAt(i));
			}

			String ret = "";
			while ((line = br.readLine()) != null)
			{
				String[] params = line.split(" ");
				if (params.length == 2)
				{
					int f1 = Integer.parseInt(params[0]);
					int f2 = Integer.parseInt(params[1]);
					farms[f1 - 1].addNextFarm(farms[f2 - 1]);
					farms[f2 - 1].setPrevFarm(farms[f1 - 1]);
				} else if (params.length == 3)
				{
					int start = Integer.parseInt(params[0]);
					int end = Integer.parseInt(params[1]);
					char prefMilk = params[2].charAt(0);
					if (farms[start - 1].milk == prefMilk || farms[end - 1].milk == prefMilk)
					{
						ret = ret + "1";
					} else if (start == end)
					{
						ret = ret + "0";
					} else
					{
						List<Farm> path = pathToEnd(farms[start - 1], farms[end - 1]);
						if (path.isEmpty())
						{
							path.add(farms[start - 1]);
							Farm previous = farms[start - 1].prevFarm;
							while (previous != null)
							{
								path.addAll(pathToEnd(previous, farms[end - 1]));
								previous = previous.prevFarm;
							}
						}
						boolean found = false;
						for (Farm farm : path)
						{
							if (farm.milk == prefMilk)
							{
								ret = ret + "1";
								found = true;
								break;
							}
						}
						if (!found)
							ret = ret + "0";

						// System.out.println(start + "," + end + "-->" + path);
					}

				}
			}

			br.close();

			System.out.println(ret);
			BufferedWriter bw = new BufferedWriter(new FileWriter(path + filename + ".out"));
			bw.write(ret);
			bw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static List<Farm> pathToEnd(Farm start, Farm end)
	{
		List<Farm> ret = new ArrayList<>();
		if (start.index == end.index)
		{
			ret.add(start);
		} else
		{
			for (Farm farm : start.nextFarms)
			{
				List<Farm> pathToEnd = pathToEnd(farm, end);
				if (pathToEnd.size() > 0 && pathToEnd.get(0).index == end.index)
				{
					pathToEnd.add(start);
				}
				ret.addAll(pathToEnd);
			}
		}
		return ret;
	}

	private static class Farm
	{
		public List<Farm> nextFarms = new ArrayList<MilkVisits.Farm>();
		public Farm prevFarm;
		private char milk;
		private int index;

		public Farm(int index, char milk)
		{
			this.index = index;
			this.milk = milk;
		}

		public void addNextFarm(Farm n)
		{
			this.nextFarms.add(n);
		}

		public void setPrevFarm(Farm n)
		{
			this.prevFarm = n;
		}

		@Override
		public String toString()
		{
			return index + "" + milk;
		}
	}
}
