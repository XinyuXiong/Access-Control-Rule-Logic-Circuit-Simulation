import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class rule1 {
	private String ruleName; //name of rule
	private String subject;
	private String action;
	private String object;   
	private String type;     //grant or deny
	private ArrayList<String> environmentCondition = new ArrayList() ;
	private ArrayList<String> beInherited;
	private ArrayList<String> inheritedBy;
	private File file = new File("tempTxt.txt");
	  //avoid user input same ruleName, so always add a index after the ruleName
	
	//get methods
	public String getRuleName ()   //get ruleName
	{
		return ruleName;
	}
	
	public String getSubject()
	{
		return subject;
	}

	public String getAction()
	{
		return action;
	}

	public String getObject ()
	{
		return object;
	}

	
	
	public String getType ()
	{
		return type;
	}
	
	public ArrayList<String> getBeInherited()
	{
		return beInherited;
	}
	
	public ArrayList<String> getInheritedBy()
	{
		return inheritedBy;
	}
		
	public ArrayList<String> getEnvironmentCondition()
	{
		return environmentCondition;
	}
	
	//set methods
	public void setBeInherited(ArrayList <String>beInherited)
	{
		this.beInherited = beInherited;
	}
	
	public void setInheritedBy(ArrayList <String>inheritedBy)
	{
		this.inheritedBy=inheritedBy;
	}
	
	//constructor  no environment condition
	rule1(String ruleName,String subject,String action,String object,String type)
	{
		this.subject = subject;
		this.action = action;
		this.object = object;
		this.ruleName = ruleName;
		this.type = type;
		this.beInherited = null;
		this.inheritedBy = null;
	//System.out.println(this.ruleName + " has been added."+"\n");
		
		
	}
	
	//constructor with environment condition
	rule1(String ruleName,String subject,String action,String object,String type,ArrayList <String>environmentCondition)
	{
		this.subject = subject;
		this.action = action;
		this.object = object;
		this.ruleName = ruleName;
		this.type = type;
		this.beInherited = null;
		this.inheritedBy = null;
		this.environmentCondition = environmentCondition;	
		
	}
	
	public void showRules(){ 
		
		System.out.print("Rule name :"+ this.ruleName +","+" Subject: " + this.subject +",");
		System.out.print(" Action: " + this.action + ",");
		System.out.print(" Object: " + this.object + ",");
		System.out.print(" Type: " + this.type );
		int size = environmentCondition.size();
		
		if(size!=0)
		System.out.print(", Environment Conditions : ");
		for(int i=0;i<size;i++)
		{
			System.out.print(environmentCondition.get(i)+",");
		}
		//System.out.println("Added through inheritance or inherited from another subject: " + this.inheritance);
		
	}
	
	//write rules to a .txt file
	public void writeRules(String txtFile) throws IOException{
		
		
		PrintWriter outputFile = new PrintWriter(new FileWriter (file,true),true);
	
		outputFile.print( this.ruleName +"," + this.subject +",");
		outputFile.print(  this.action + ",");
		outputFile.print( this.object + ",");
		outputFile.print( this.type + "," );
		int size = environmentCondition.size();
	
		
		
		for(int i=0;i<size;i++)
		{
			outputFile.print(environmentCondition.get(i)+",");
		}
		//System.out.println("Added through inheritance or inherited from another subject: " + this.inheritance);
		outputFile.println() ;
		outputFile.close();
	
		
	}
	
	public File getFile()
	{
		return file;
	}
	
	public void deleteFile()
	{
		file.deleteOnExit();	
	}	


	
	
}
