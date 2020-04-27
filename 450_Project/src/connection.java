import java.sql.*;
import java.util.*;
public class connection
{
	public static Connection conn;
	public static void main(String [] args) throws ClassNotFoundException, SQLException
	{
		try
		{
			//Class.forName ("org.postgresql.Driver"); // load server
			conn = DriverManager.getConnection( "jdbc:postgresql://[24.14.236.184]:5432/project", "postgres", "postgres");
			
		}
		catch (SQLException sqle)
		{
			System.out.println("SQLException : " + sqle);// handle exceptions
		}
		
		PreparedStatement insertCust = conn.prepareStatement("insert into customer values(?, ?, ?);");
		PreparedStatement insertEmp = conn.prepareStatement("insert into employee values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
		PreparedStatement insertModel = conn.prepareStatement("insert into model values(?, ?, ?, ?, ?);");
		PreparedStatement insertOrder = conn.prepareStatement("insert into order values(?,?,?,?);");
		PreparedStatement selectState = conn.prepareStatement("select ? from ? where ?");
		PreparedStatement deleteRow = conn.prepareStatement("delete from ? where ?");
		PreparedStatement updateSal = conn.prepareStatement("update employee set salary = ? where eid=?;");
		PreparedStatement updateName = conn.prepareStatement("update employee set name = ? where eid=?;");
		PreparedStatement updateAdd= conn.prepareStatement("update employee set address = ? where eid=?;");
		PreparedStatement updatePriv = conn.prepareStatement("update employee set privilege = ? where eid=?;");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome. Please enter your username:");
		String username = sc.nextLine();
		System.out.println("Please enter you password:");
		String psswrd = sc.nextLine();

		boolean flag = true;
		String table = null;
		
		while(flag)
		{
			System.out.println("What would you like to do?");
			System.out.println("1: Insert data");
			System.out.println("2: Delete data");
			System.out.println("3: Update table");
			System.out.println("4: Reporting and Analytics");
			int choice = sc.nextInt();
			
			if(choice == 1 || choice == 2)
			{
				System.out.println("What table would you like to choose?");
				table = sc.nextLine();
			}
			if(choice == 1)
			{
				
				switch(table)
				{
				case "employee":
					System.out.println("Please enter all nine values for the employee:");
					insertEmp.setInt(1, sc.nextInt());
					insertEmp.setString(2, sc.nextLine());
					insertEmp.setString(3, sc.nextLine());
					insertEmp.setInt(4, sc.nextInt());
					insertEmp.setInt(5, sc.nextInt());
					insertEmp.setString(6, sc.nextLine());
					insertEmp.setString(7, sc.nextLine());
					insertEmp.setString(8, sc.nextLine());
					insertEmp.setString(9, sc.nextLine());
					insertEmp.executeUpdate();
					
					break;
				case "customer":
					System.out.println("Please enter the customer ID (integer), First name, and Last name:");
					insertCust.setInt(1, sc.nextInt());
					insertCust.setString(2, sc.nextLine());
					insertCust.setString(3, sc.nextLine());
					insertCust.executeUpdate();
					break;
				case "model":
					System.out.println("Please enter the model Number, Model Name, sale Price, totatl amount, and total cost");
					insertModel.setInt(1, sc.nextInt());
					insertModel.setString(2, sc.nextLine());
					insertModel.setDouble(3, sc.nextDouble());
					insertModel.setInt(4, sc.nextInt());
					insertModel.setInt(5, sc.nextInt());
					insertModel.executeUpdate();
					break;
				case "order":
					System.out.println("Please enter the order number, EID, CID, and sale value:");
					insertOrder.setInt(1, sc.nextInt());
					insertOrder.setInt(2, sc.nextInt());
					insertOrder.setInt(3,  sc.nextInt());
					insertOrder.setDouble(4, sc.nextDouble());
					insertOrder.executeUpdate();
					break;
				}
			}
			if(choice == 2)
			{
				deleteRow.setString(1, table);
				System.out.println("Please enter your where clause");
				deleteRow.setString(2, sc.nextLine());
				deleteRow.executeUpdate();
			}
			if(choice == 3)
			{
				System.out.println("Please enter the employee ID of you you would like to update:");
				int eid = sc.nextInt();
				System.out.println("Please choose what you wish to do:");
				System.out.println("1: Update Salary");
				System.out.println("2: Update Name");
				System.out.println("3: Update Address");
				System.out.println("4: Update privilege");
				int choice2 = sc.nextInt();
				System.out.println();
				switch(choice2)
				{
					case 1:
						System.out.println("What is the new salary?");
						updateSal.setInt(1, sc.nextInt());
						updateSal.setInt(2, eid);
						updateSal.executeUpdate();
						break;
					case 2:
						System.out.println("What is the new Name?");
						updateName.setString(1, sc.nextLine());
						updateName.setInt(2, eid);
						updateName.executeUpdate();
						break;
					case 3:
						System.out.println("What is the new address?");
						updateAdd.setString(1, sc.nextLine());
						updateAdd.setInt(2, eid);
						updateAdd.executeUpdate();
						break;
					case 4:
						System.out.println("What is the new privilege?");
						updatePriv.setString(1, sc.nextLine());
						updatePriv.setInt(2, eid);
						updatePriv.executeUpdate();
						break;
				}
				if(choice == 4)
				{
					Statement stmt = conn.createStatement();
					System.out.println("What would you like to view?");
					System.out.println("1: Total Sales in Order");
					System.out.println("2: Order Information");
					System.out.println("3: Customer Orders");
					System.out.println("4: Expense Report");
					int choice4 = sc.nextInt();
					switch(choice4)
					{
						case 1:
							//total revenue from sale, employee number, customer id
							//select saleValue, eid, custid from order;
							ResultSet orderSales = stmt.executeQuery("select saleValue, eid, custid from order");
							while(orderSales.next())
							{
								System.out.println(orderSales.getInt("saleValue") + " " + orderSales.getInt("eid") + " " + orderSales.getInt("custid")); 
							}
							break;
						case 2:
							//customer, models bought, quantity
							//select customerID, saleValue, modelNumber, quantity from order;
							ResultSet ordInfo = stmt.executeQuery("select customerID, saleValue, modelNumber, quantity from order");
							while(ordInfo.next())
							{
								System.out.println(ordInfo.getInt("customerid") + " " + ordInfo.getInt("saleValue") + " " + ordInfo.getInt("modelNumber")+ " " + ordInfo.getInt("quantity"));
							}
							break;
						case 3:
							//each order, the parts, available inventory
							//select Order.orderNumer, Order.modelNumber, Model.quantity from Order, Model where Order.modelNumber == Model.ModelNumber; 
							ResultSet cusOrds = stmt.executeQuery("select Order.orderNumer, Order.modelNumber, Model.quantity from Order, Model where Order.modelNumber == Model.ModelNumber");
							while(cusOrds.next())
							{
								System.out.println(cusOrds.getInt("Oder Number") + " " + cusOrds.getInt("Model Number") + " " + cusOrds.getInt("Quantity"));
							}
							break;
						case 4:
							//expense report, employee salary, bonus expense, part cost
							// select sum(Employee.salary), sum(Model.cost * Model.quantity) from Employee, Model;
							ResultSet totals = stmt.executeQuery("select sum(Employee.salary), sum(Model.cost * Model.quantity) from Employee, Model");
							while(totals.next())
							{
								System.out.println(totals.getInt("Total Salry Cost") + " " + totals.getInt("Total Parts Cost"));
							}
							break;
					}
				}
				else
				{
					System.out.println("Invalid Input, please try again:");
				}
				System.out.println("Would you like to make another choice?");
				System.out.println("1: Yes");
				System.out.println("2: No");
				if(choice == 2)
				{
					flag = false;
					System.out.println("Have a good day.");
				}
				
				
			}
		}
	}
}


