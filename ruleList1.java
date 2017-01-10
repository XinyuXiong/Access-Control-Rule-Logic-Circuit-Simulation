import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ruleList1 {


	private ArrayList<rule1> myRuleList1=new ArrayList<rule1>();  //store rules
	
	

	public void addRule(String ruleName, ArrayList<String> subject, String action, ArrayList<String> object, String type, ArrayList<String>environmentCondition)  
	{
		int size1 = subject.size();
		int size2 = object.size();
		//when the rule list is empty, you can always add new rule anyway
		if(myRuleList1.size()==0){


			for(int i=0;i<size1;i++){
				for(int j=0;j<size2;j++)
				{
					rule1 newRule = new rule1(ruleName, subject.get(i), action, object.get(j), type,environmentCondition);
					myRuleList1.add(newRule);
				}


			}   

			System.out.println("Rule["+ruleName+"] has been added. \n"); ////////////////////////////////////////////////////////////
		}
		//if the rule list is not empty, need to check if the new adding rule is good or not
		else
		{	
			boolean check = true;
			System.out.println("Checking the new adding rule....");

			//checking new adding is ok or not
			for(int i=0;i<size1;i++){
				for(int j=0;j<size2;j++)
				{
					rule1 newRule = new rule1(ruleName, subject.get(i), action, object.get(j), type,environmentCondition);
					if(isDuplicate(newRule)||isConflict(newRule)){
						check = false;

						break;
					}


				}

			}	

			if(!check)
			{
				System.out.println("You new adding rule ["+ruleName+"] is Duplicate or Conflict with the exsiting rules. Your new adding rule has been denied.");
				System.out.println();
			}

			if(check)
			{
				for(int i=0;i<size1;i++){
					for(int j=0;j<size2;j++)
					{
						rule1 newRule = new rule1(ruleName, subject.get(i), action, object.get(j), type,environmentCondition);
						myRuleList1.add(newRule);
					}
				}
				System.out.println("Rule["+ruleName+"] has been added. \n");
			}

			/////////////////////////////////////////////////////////////






		}//else
	}//add rule


	public void deleteRule(String ruleName)
	{
		
		
		for(int i=0;i<myRuleList1.size();i++)
		{
			if(myRuleList1.get(i).getRuleName().equals(ruleName)){

				myRuleList1.remove(i);
				i--;
			}
		}
		System.out.println("Rule["+ruleName+"] has been deleted. \n"); 
	}


	public int getSize()
	{
		return myRuleList1.size();
	}
	
	public ArrayList<rule1> getArrayList()
	{
		return myRuleList1;
	}


	public void showRuleList()
	{

		int size = myRuleList1.size();
		System.out.println("Current Rule List :");

		for(int i=0,j=0;i<size&&j<size;i++,j++)
		{

			System.out.print("[SubRule"+(j+1)+"]");
			myRuleList1.get(i).showRules();
			System.out.println();
		}
	

	}
	
	public void deleteSubrule() throws IOException
	{
		try{
		Scanner br = new Scanner(System.in);
		System.out.println();
		System.out.println("What sub rule do you want to delete ?\n");
		int num = br.nextInt();
		
		if(num>myRuleList1.size())
		{
			System.out.println("Can't find this sub rule in the rule list.");
		}
		
		else{
		myRuleList1.remove(num-1);
		System.out.println("Delete success.");
		}
		}
		
		catch(InputMismatchException exception)
		{
			System.out.println("Please enter an integer.");
		}
	}
	
	//save the rule list and write all the rules to a .txt file
	public void writeRuleList(String txtFile) throws IOException
	{
		int size = myRuleList1.size();

		for(int i=0;i<size;i++)
		{
			myRuleList1.get(i).writeRules("tempTxt.txt");
		
		}
		
		FileReader fr = new FileReader("tempTxt.txt");
		BufferedReader bufr = new BufferedReader(fr);
		FileWriter fw = new FileWriter(txtFile,false);
		BufferedWriter bufw = new BufferedWriter(fw);
		
		String line = null;
		while((line = bufr.readLine())!= null)
		{
			bufw.write(line);
			bufw.newLine();
		}
		
		myRuleList1.get(0).getFile().deleteOnExit();
		bufr.close();
		bufw.close();
		
	}
	
	public void readRuleList() throws IOException
	{
		boolean temp = true;
		while(temp){
		Scanner br = new Scanner(System.in);
		System.out.println("\nWould you like to use a previous project? (y/n)");
		String project = null;

		project = br.next().toLowerCase();

		if (project.equals("y"))
		{
			
			
			System.out.println("Please type the name of the project (add '.txt' at the end without spaces): ");
			String fileName = br.next();

			try {
				Scanner reader = new Scanner (new File (fileName));
				while (reader.hasNext())
				{
				
					String line = reader.nextLine();
					
					String[] elements = line.split(",");
			
					ArrayList<String>enList = new ArrayList();
					for(int i=5;i<elements.length;i++)
					{
						enList.add(elements[i]);
					}
					
				
					rule1 newRule = new rule1(elements[0], elements[1], elements[2], elements[3], elements[4],enList);
					myRuleList1.add(newRule);
		
				}
		
				System.out.println("Loaded success.");
				temp = false;
			}
			
			
			catch (FileNotFoundException e)
			{
				System.out.println("File "+fileName+ " was not found!");
			}


			
		}
		
		else if(project.equals("n"))
		{
			break;
		}
		
		else
		{
			System.out.println("Please enter y for yes or n for no.");
			temp = true;
		}
		
		
	}
		
		
		
		
		
	}

	public boolean isDuplicate(rule1 newRule) //Check the new adding rule if it is a duplicate rule from the releList
	{                                        //If subject,action,object and type are all the same, return true
		for(int i=0;i<myRuleList1.size();i++){
			if(newRule.getSubject().equals(myRuleList1.get(i).getSubject())&&newRule.getObject().equals(myRuleList1.get(i).getObject())&&newRule.getAction().equals(myRuleList1.get(i).getAction())&&newRule.getType().equals(myRuleList1.get(i).getType()))

				return true;
		}
		return false;
	}

	public boolean isConflict(rule1 newRule) //Check the new adding rule if it is a conflict rule from the releList
	{                                        //If subject,action,object are all the same,but the type is different, return true
		for(int i=0;i<myRuleList1.size();i++){
			if(newRule.getSubject().equals(myRuleList1.get(i).getSubject())&&newRule.getObject().equals(myRuleList1.get(i).getObject())&&newRule.getAction().equals(myRuleList1.get(i).getAction())&&!newRule.getType().equals(myRuleList1.get(i).getType()))

				return true;
		}
		return false;
	}
	
	public void addInheritance(String newSubject,String newRuleName,String oldSubject)
	{
		boolean canInhe = false;
		int size = myRuleList1.size();
		for(int i=0; i<size;i++)
		{
			if(myRuleList1.get(i).getSubject().equals(oldSubject))
			{
				rule1 newRule = new rule1(newRuleName, newSubject, myRuleList1.get(i).getAction(), myRuleList1.get(i).getObject(), myRuleList1.get(i).getType(),myRuleList1.get(i).getEnvironmentCondition());
				myRuleList1.add(newRule);
				canInhe = true;	
			}
			
			
		}
		
		if(canInhe)
		System.out.println("\nInherited successful.");
		else
		System.out.println("Can't find the target subject");
	}
	
	



}
