import java.util.*;

public class SortingAcronyms {

	static int count;
	static List<String> Standard_input = new ArrayList<String>();
	static List<String> Standard_output = new ArrayList<String>();

    public static void main(String[] args) 
    {
        Input_function();
        Loop_input();
        Process();
        Output_process();
    }

    public static void Input_function()
    {
    	Scanner sc = new Scanner(System.in);
    	count = sc.nextInt();
    }

    public static void Loop_input()
    {
    	Scanner sc = new Scanner(System.in);
    	String buffer;
    	int i;
    	for(i = 0 ; i < count ; i++)
    	{
    		buffer = sc.nextLine();
    		Standard_input.add(buffer);
    	}
    }

    public static void Process()
    {
    	int i,j,position=0;
    	for(i = 0 ; i < count ; i++)
    	{
    		String buffer = new String();
    		char[] operator = Standard_input.get(i).toCharArray();
    		for(j = 0 ; j < operator.length ; j++)
    		{
    			if(Character.isUpperCase(operator[j]))
    			{
    				buffer = buffer + operator[j];
    			}
    		}
    		Standard_output.add(buffer);
    	}
    }

    public static void Output_process()
    {
    	Collections.sort(Standard_output, new Custom_comparator());
    	for(String output : Standard_output)
    		System.out.println(output.toString());
    }
}

class Custom_comparator implements Comparator<String>
{
	@Override
    public int compare(String firstItem, String secondItem) 
    {
        Integer first = Integer.valueOf(firstItem.length());
        Integer second = Integer.valueOf(secondItem.length());
       	if(second != first)
       		return second.compareTo(first);
        else
        	if(secondItem.compareTo(firstItem) > 0)
        		return -1;
        	else return 1;
    }
}