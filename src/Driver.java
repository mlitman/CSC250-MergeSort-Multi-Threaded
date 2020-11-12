import java.util.Random;

public class Driver {

	public static void main(String[] args) 
	{
		Random r = new Random();
		
		int[] ar = new int[20];
		for(int i = 0; i < ar.length; i++)
		{
			ar[i] = r.nextInt(1000);
		}
		
		Driver.printArray(ar);
		MergeSortWorkerBee firstWorker = new MergeSortWorkerBee(ar, 0, ar.length-1);
		firstWorker.start();
		try
		{
			firstWorker.join();
			Driver.printArray(ar);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void printArray(int[] ar)
	{
		for(int i = 0; i < ar.length; i++)
		{
			System.out.print(ar[i] + " ");
		}
		System.out.println("");
	}

}
