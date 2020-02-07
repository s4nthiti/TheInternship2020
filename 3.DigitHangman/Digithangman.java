import java.util.*;

public class Digithangman
{
	static int max_point;
	static int current_point = 0;
	static String raw_hangman;
	static List<String> split_hangman = new ArrayList<String>();
	static List<String> answer = new ArrayList<String>();
	static List<String> answer_input = new ArrayList<String>();

	public static void main(String args[])
	{
		Input_Hangman();
		Print_separate();
		Answer_process();
	}

	public static void Input_Hangman()
	{
		Scanner sc = new Scanner(System.in);
		raw_hangman = sc.nextLine();
		split_hangman = Arrays.asList(raw_hangman.split("\\s"));
		max_point = split_hangman.size();
		for(int i = 0; i < split_hangman.size() ; i++)
		{
			answer.add("_");
		}
		for(int i = 0; i < 5 ; i++)
		{
			answer_input.add(sc.nextLine());
		}
	}

	public static void Print_separate()
	{
		int i;
		for(i = 0 ; i < answer.size() ; i++)
		{
			System.out.print(answer.get(i) + " ");
		}
		System.out.println();
	}

	public static void Answer_process()
	{
		int i;
		for(i = 0 ; i < 5 ; i++)
		{
			if(split_hangman.contains(answer_input.get(i)))
			{
				Replace_blank(answer_input.get(i));
				Print_separate();
			}
			else
			{
				answer.add(answer_input.get(i));
				Print_separate();
			}
		}
		System.out.println(current_point);
	}

	public static void Replace_blank(String operator)
	{
		int i = 0;
		while(split_hangman.contains(operator))
		{
			if(split_hangman.get(i).equals(operator))
			{
				current_point++;
				split_hangman.set(i, "_");
				answer.set(i, operator);
			}
			i++;
		}
	}
}