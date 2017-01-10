import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class main1 {
	
	

	public static void main(String[] args) throws IOException {



		boolean loop = true; //To manipulate the loop
		char choice='0';

		ruleList1 myRuleList=new ruleList1();
		Scanner br=new Scanner(System.in);
		System.out.println("Welcome to the SLC Simulation. The main functionality of this simulation is to add rules and perform inheritance.\n"
				+ "When a rule is inserted, the simulation checks for a fault in real time by checking if there is a conflict or duplicate in the opposite list.");

		myRuleList.readRuleList();

		System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
				+ " or Q to quit the program.");

		choice = br.next().charAt(0);	

		while (loop){

			switch (choice){

			//********************************************** case A *****************************************************
			case 'A':
			case 'a': //if (choice == 'A' || choice == 'a')
			{	
				 ArrayList<String> subjectArray = new ArrayList(); //store subjects
				 ArrayList<String> objectArray = new ArrayList(); //store objects
				 ArrayList<String> environmentCondition = new ArrayList(); //store environment conditions
				 String action;
				 String type;
				 String ruleName;
				//Get the rule name from user
				System.out.println("Please enter rule name.");
				ruleName = br.next();

			

				//Get subjects from user
				while(true){

					String temp;
					System.out.println("Please enter your subjects, type 'q' when you finish.");
					temp = br.next();
					if(temp.toUpperCase().equals("Q"))
					{
						System.out.println("Subjects have been saved.\n");
						break;
					}
					else{

						subjectArray.add(temp);

					}
				}

				//Get action from user
				System.out.println("Please enter your action.");
				action = br.next();

				//Get objects from user
				while(true){

					String temp;
					System.out.println("Please enter your objects, type 'q' when you finish.");
					temp = br.next();
					if(temp.toUpperCase().equals("Q"))
					{
						System.out.println("Objects have been saved.\n");
						break;
					}
					else{

						objectArray.add(temp);

					}
				}


				//get environment condition from user

				while(true){

					String temp;
					System.out.println("Please enter your environment conditions, type 'q' when you finish.");
					temp = br.next();
					if(temp.toUpperCase().equals("Q"))
					{
						System.out.println("Environment conditions have been saved.\n");
						break;
					}
					else{

						environmentCondition.add(temp);

					}
				}



				//get type from user
				while(true)
				{	
					String temp;
					System.out.println("Enter deny or grant: ");
					temp = br.next().toLowerCase();
					if ( (temp.equals("grant")) || (temp.equals("deny")) )
					{ 
						type = temp;
						break; 
					}

					else
					{
						System.out.println("Error. Type inserted is not valid."); 
					}

				}



				myRuleList.addRule(ruleName, subjectArray, action, objectArray, type, environmentCondition);
				System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
						+ " or Q to quit the program.");
				choice = br.next().charAt(0);	 
				break;


			}  


			//***************************************** end for case A ***********************************************************



			//******************************************** case V ****************************************************************

			case 'V':
			case 'v': //else if (choice == 'V' || choice == 'v')
			{
				if(myRuleList.getSize()==0)
				{
					System.out.println("The rule list is empty");
					System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
							+ " or Q to quit the program.");
					choice = br.next().charAt(0);
					break;
				}
				else{

					myRuleList.showRuleList();



				}


				System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
						+ " or Q to quit the program.");
				choice = br.next().charAt(0);
				break;



			}


			//end of case v *************************************************************************************************************


			case 'D':
			case 'd': 
			{

				if (myRuleList.getSize()==0 )
				{
					System.out.println("The rule list is empty, you can't delete rule right now.");
					System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
							+ " or Q to quit the program.");
					choice = br.next().charAt(0);
					break;
				}
				else{
					myRuleList.showRuleList();
					System.out.println(" \nEnter ruleName of rule to be deleted: "+"(Rules in line of inheritance (same action, object, and type) will be deleted as well)");
					String ruleName = br.next().toLowerCase();
					myRuleList.deleteRule(ruleName);
					System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
							+ " or Q to quit the program.");
					choice = br.next().charAt(0);
					break;
				}
			} 



			//end of case d************************************************************************************************


			case 'Q':
			case 'q': 
			{
				try{

					System.out.println("\nDo you want to save your file? (y/n)");
					String answer= br.next().toLowerCase();
	
	
	
					if(answer.equals("n"))
					{
						loop = false;
						break;
					}
	
					else if(answer.equals("y"))
					{
						System.out.println("\nPlease enter your file name. (.txt)");
						String fileName=br.next();
	
						myRuleList.writeRuleList(fileName);
	
						System.out.println("\nYour file has benn saved");
	
						loop = false;
	
						break;
					}
					else
					{
						System.out.println("\nPlease enter y for yes and n for no");
						loop = true;	
						break;
					}
				}
				
				catch(FileNotFoundException exception)
				{
					System.out.println("You can't save a blank file !");
					System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
							+ " or Q to quit the program.");
					choice = br.next().charAt(0);	 
					break;
				}

			}
			
			case 'I':
			case 'i':
			{
				

				if (myRuleList.getSize()==0 )
				{
					System.out.println("Inheritance can not occur. There are no rules currently in the system.");
					System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
							+ " or Q to quit the program.");
					choice = br.next().charAt(0);
					break;
				}

				//Inherited subject--------------------------------------------

				System.out.println("\nPlease enter a rule name. ");
				String ruleName = br.next().toLowerCase();
				
				System.out.println("\nInsert the name of the beneficiary subject (can be a new subject or one previously added).");
				String newSubject = br.next().toLowerCase();        //new subject
				
				System.out.println("\nInsert name of subject to be inherited.");
				String oldSubject = br.next().toLowerCase();           //subject to be inherited

				
				
				myRuleList.addInheritance(newSubject, ruleName, oldSubject);




				System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
						+ " or Q to quit the program.");
				choice = br.next().charAt(0);
				break;	
			}
			
			//delete subRule
			case 'S':
			case 's': 
			{
				myRuleList.showRuleList();
				if(myRuleList.getSize()==0)
				{
					System.out.println("The rule list is empty.\n");
					System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
							+ " or Q to quit the program.");
					choice = br.next().charAt(0);
					break;
				}
				
				else{
					
				myRuleList.deleteSubrule();
				System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
						+ " or Q to quit the program.");
				choice = br.next().charAt(0);
				break; }
			} 
			
			default:
			{
				System.out.println("Choice is invalid.");
				System.out.println("\nEnter A to add a rule, V to view rules, D to delete a rule, S to delete a sub rule, I to perform inheritance "	
						+ " or Q to quit the program.");
				choice = br.next().charAt(0);	

			}


			}//switch

		} //while
		br.close();
		System.out.println("Thank you for using this program.");
	} //main
}  //class