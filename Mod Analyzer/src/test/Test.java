package test;

public class Test 
{
	public Test(int n, int m)
	{
		System.out.println(test(n, m));
	}
	
	public int test(int n, int m)
	{
		if (n<=1 || m<=1)
		{
			return 2;
		}
		else
		{
			return test(n-1, m) + test(n, m-2);
		}
	}
	
	public static void main(String args[])
	{
		new Test(5, 4);
	}
}
