import java.util.*;

public class FloatingPrime 
{

	static List<Double> number = new ArrayList<Double>();

	public static void main(String args[])
	{
		Input_number();
		Process();
	}

	public static void Input_number()
	{
		Scanner sc = new Scanner(System.in);
		String converter;
		do{
			converter = sc.nextLine();
			Double buffer = Double.parseDouble(converter);
			number.add(buffer);
		} while(!converter.equals("0.0"));
		number.remove( number.size() - 1);
	}

	public static void Process()
	{
		for(int i = 0 ; i < number.size() ; i++)
		{
			int operator;
			operator = (int)Floatingpoint_move(number.get(i), 1);
			if(!Prime_check(operator))
			{
				operator = (int)Floatingpoint_move(number.get(i), 2);
				if(!Prime_check(operator))
				{
					operator = (int)Floatingpoint_move(number.get(i), 3);
					if(!Prime_check(operator))
						System.out.println("FALSE");
					else
						System.out.println("TRUE");
				}
				else
					System.out.println("TRUE");
			}
			else
				System.out.println("TRUE");
		}
	}

	public static double Floatingpoint_move(double floating_move, int position)
	{
		return floating_move = floating_move * Math.pow(10,position);
	}

	public static boolean Prime_check(int prime_checker)
	{
		int i = 2;
		boolean flag = false;
		while(i <= prime_checker/2)
		{
			if(prime_checker % i == 0)
			{
				flag = true;
				break;
			}
			++i;
		}
		if(!flag)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}