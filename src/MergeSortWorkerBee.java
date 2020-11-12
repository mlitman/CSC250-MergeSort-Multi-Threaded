
public class MergeSortWorkerBee extends Thread
{
	private int[] ar;
	private int begin;
	private int end;
	
	public MergeSortWorkerBee(int[] ar, int begin, int end)
	{
		this.ar = ar;
		this.begin = begin;
		this.end = end;
	}
	
	public void run()
	{
		System.out.println("*** " + this.getId() + " is starting between " + begin + " and " + end);
		
		//is this a one list?
		if(begin != end)
		{
			int begin1 = begin;
			int end1 = (begin + end)/2;
			int begin2 = end1 + 1;
			int end2 = end;
			
			MergeSortWorkerBee leftHalf = new MergeSortWorkerBee(ar, begin1, end1);
			MergeSortWorkerBee rightHalf = new MergeSortWorkerBee(ar, begin2, end2);
			leftHalf.start();
			rightHalf.start();
			try
			{
				leftHalf.join();
				rightHalf.join();
				
				//then merge - we can't start the merge until the 2 threads above are done
				//we know that leftHalf is sorted and rightHalf is sorted
				int[] temp = new int[end - begin + 1];
				int pos1 = begin1;
				int pos2 = begin2;
				
				for(int i = 0; i < temp.length; i++)
				{
					if(pos1 <= end1 && pos2 <= end2)
					{
						if(ar[pos1] < ar[pos2])
						{
							temp[i] = ar[pos1];
							pos1++;
						}
						else
						{
							temp[i] = ar[pos2];
							pos2++;
						}
					}
					else if(pos1 <= end1)
					{
						temp[i] = ar[pos1];
						pos1++;
					}
					else
					{
						temp[i] = ar[pos2];
						pos2++;
					}
				}
				
				//copy the contents of temp back into ar from begin to end
				for(int tempPos = 0, arPos = begin; tempPos < temp.length; tempPos++, arPos++)
				{
					ar[arPos] = temp[tempPos];
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
