import java.util.*;
import java.sql.*;
public class CrudOperation 
{
	Scanner scanner=new  Scanner(System.in);
	void insert() 
	{
		String Name,Course,Date,Gender;		
		int regno,Fees;
		
			try
			{
				System.out.print("Enter the Reg.No :");
				regno=scanner.nextInt();
				System.out.print("Enter the Name :");
				Name=scanner.next();
				System.out.print("Enter the DOB yyyy-mm-dd :");
				Date=scanner.next();
				System.out.print("Enter the Gender :");				
				Gender=scanner.next();		
				System.out.print("Enter the Course :");
				Course=scanner.next();
				System.out.print("Enter the Fees :");
				Fees=scanner.nextInt();
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
				PreparedStatement st=con.prepareStatement("insert into studentinfo values(?,?,?,?,?,?)");
				
				st.setInt(1, regno);
				st.setString(2,Name);
				st.setString(3, Date);
				st.setString(4, Gender);
				st.setString(5, Course);
				st.setInt(6, Fees);
				int c=st.executeUpdate();
				if(c==1)
				{
					System.out.println("Data Saved........");
				}
				else
				{
					System.out.println("Data Not Saved......");
				}
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}
	void read()
	{
		int regno;
		try
		{
			System.out.print("Enter Student Register Number :");
			regno=scanner.nextInt();
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
			PreparedStatement st=con.prepareStatement("select * from Studentinfo where RegNo=?");
			st.setInt(1, regno);
			
			
			ResultSet rs=st.executeQuery();
			
			int c=0;
			
			while(rs.next())
			{
				System.out.println("Employee Regno :"+rs.getInt(1));
				System.out.println("Employee Name :"+rs.getString(2));
				System.out.println("Employee DOB :"+rs.getString(3));
				System.out.println("Employee Gender :"+rs.getString(4));
				System.out.println("Employee Course :"+rs.getString(5));
				System.out.println("Employee Fees :"+rs.getInt(6));
				System.out.println(".............................");
				c=1;
			}
			if(c==0)
			{
				System.out.print("Student RegNo Not Found....");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	void update()
	{
		int regno,fees;
		String Course;
		
		try
		{
			System.out.print("Enter Student RegNo :");
			regno=scanner.nextInt();
			System.out.print("1.Update Course\n2.Upadate Fees\nChoose One");
			int n=scanner.nextInt();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
			if(n==1)
			{
				System.out.print("Enter The Course :");
				Course=scanner.next();
				PreparedStatement st=con.prepareStatement("Update Studentinfo set Course=? where regno=? "); 
				st.setString(1, Course);
				st.setInt(2, regno);
				int c=st.executeUpdate();
				if(c==1)
				{
					System.out.println("Data Updated........");
				}
				else
				{
					System.out.println("Data Not Updated......");
				}
				
				
			}
			else if(n==2)
			{
				System.out.print("Enter The Fees :");
				fees=scanner.nextInt();
				PreparedStatement st=con.prepareStatement("Update Studentinfo set Fees=? where regno=? "); 
				st.setInt(1,fees);
				st.setInt(2, regno);
				int c=st.executeUpdate();
				if(c==1)
				{
					System.out.println("Data Updated........");
				}
				else
				{
					System.out.println("Data Not Updated......");
				}
			}
			else
			{
				System.out.println("Enter valid choice....");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	void delete()
	{
		int regno;
		
		try
		{
			System.out.print("Enter the Student RegisterNumber :");
			regno=scanner.nextInt();
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
			PreparedStatement st=con.prepareStatement("delete from studentinfo where regno=?");
			
			st.setInt(1, regno);
			int c=st.executeUpdate();
			if(c==1)
			{
				System.out.println("Data deleted Successfully........");
			}
			else
			{
				System.out.println("Regno Not Found......");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
		
}

public class StudentInfo {

	public static void main(String[] args) {
		CrudOperation crud=new CrudOperation();
		int choice;
		System.out.println("1.Create\n2.Read\n3.Update\n4.Delete :");
		do {
		System.out.print("Choose One :");
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		switch(n)
		{
		case 1:
			crud.insert();
			break;		
		case 2:
			crud.read();
			break;
		case 3:
			crud.update();
			break;
		case 4:
			crud.delete();
			break;
		default:
			System.out.println("Invalid Choice.....");
		}
		System.out.print("Do you Wish To Continue enter 1..");
		choice=s.nextInt();
		}
		while(choice==1);

	}

}
