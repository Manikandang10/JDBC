import java.util.Scanner;
import java.sql.*;
public class Employee 
{
	String name,dept;
	int id,salary;
	Scanner s=new Scanner(System.in);
	void store() 
	{
		int choice = 0;
		do {
			try {
				System.out.print("Enter the Employee ID :");
				id=s.nextInt();
				System.out.print("Enter the Employee Name :");
				name=s.next();
				System.out.print("Enter the Department :");
				dept=s.next();
				System.out.print("Enter Employee Salary :");
				salary=s.nextInt();
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
				PreparedStatement st=con.prepareStatement("insert into employeedata values(?,?,?,?)"); 
				
				st.setInt(1, id);
				st.setString(2,name);
				st.setString(3, dept);
				st.setInt(4, salary);
				st.executeUpdate();
				
				con.close();
				
				System.out.println("Data Saved");
				System.out.println("ADD one more Data Press 1 :");
				choice=s.nextInt();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		while(choice==1);
	}
	void find()
	{
		try
		{
			System.out.println("Enter the Employee Id :");
			int id=s.nextInt();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
			PreparedStatement st=con.prepareStatement("select * from employeedata where id=?"); 
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			int c=0;
			while(rs.next())
			{
				System.out.println("Employee Id :"+rs.getInt(1));
				System.out.println("Employee Name :"+rs.getString(2));
				System.out.println("Employee Department :"+rs.getString(3));
				System.out.println("Employee Salary :"+rs.getInt(4));
				System.out.println(".............................");
				
				c=1;
			}
			if(c==0)
			{
				System.out.println("Invalid Employee Id..");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	void show() {
		try
		{
			System.out.println("Enter the Employee Department :");
			String dept=s.next();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
			PreparedStatement st=con.prepareStatement("select * from employeedata where Department=?");
			st.setString(1, dept);
			
			ResultSet rs=st.executeQuery();
			
			int c=0;
			while(rs.next())
			{
				System.out.println("Employee Id :"+rs.getInt(1));
				System.out.println("Employee Name :"+rs.getString(2));
				System.out.println("Employee Department :"+rs.getString(3));
				System.out.println("Employee Salary :"+rs.getInt(4));
				System.out.println(".............................");
				c=1;
			}
			if(c==0)
			{
				System.out.println("Department NOT Found...");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void findSalary()
	{
		try {
			System.out.println("Enter From Salary :");
			int s1=s.nextInt();
			System.out.println("Enter To Salary :");
			int s2=s.nextInt();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
			PreparedStatement st=con.prepareStatement("select * from employeedata where salary>=? and salary<=?"); 
			st.setInt(1, s1);
			st.setInt(2, s2);
			
			ResultSet rs=st.executeQuery();
			int c=0;
			while(rs.next())
			{
				System.out.println();
				System.out.println("Employee Id :"+rs.getInt(1));
				System.out.println("Employee Name :"+rs.getString(2));
				System.out.println("Employee Department :"+rs.getString(3));
				System.out.println("Employee Salary :"+rs.getInt(4));
				System.out.println(".............................");
				c=1;
			}
			if(c==0)
			{
				System.out.println("Salary  NOT Found...");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void delete()
	{
		try
		{
			System.out.println("Enter Employee ID :");
			int s1=s.nextInt();
			System.out.println("Enter Update Salary :");
			int s2=s.nextInt();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javademodb","root","1234");
			PreparedStatement st=con.prepareStatement("Update employeedata set salary=? where id=? and salary<?"); 
			st.setInt(1, s2);
			st.setInt(2, s1);
			st.setInt(3, s2);
			int res=st.executeUpdate();
			
			if(res<s1) {
			ResultSet rs=st.executeQuery();
			int c=0;
			while(rs.next())
			{
				System.out.println();
				System.out.println("Employee Id :"+rs.getInt(1));
				System.out.println("Employee Name :"+rs.getString(2));
				System.out.println("Employee Department :"+rs.getString(3));
				System.out.println("Employee Salary :"+rs.getInt(4));
				System.out.println(".............................");
				c=1;
			}
			if(c==0)
			{
				System.out.println("Salary  NOT Found...");
			}  
			}
			else
			{
				throw new ArithmeticException("Salary is Less Than to before Salary..");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void main(String[] args) 
	{
		Employee employee=new Employee();
		Scanner sc=new Scanner(System.in);
		employee.delete();
		System.out.println("1.Find Employee Using ID\n2.Show Details Using Department\n3.Find Salary between\nEnter any 1 Option:");
		
		
		int a=sc.nextInt();
		
		
		if(a==1) {
			employee.find();
		}
		else if(a==2) {
			employee.show();
		}
		else if(a==3)
		{
		employee.findSalary();
		}
		else
		{
			
		}

	}

}
