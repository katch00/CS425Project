
import java.sql.*;
import java.util.*;
public class connection
{
	public static Connection conn;
	public static void main(String [] args) throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome. Please enter your username:");
		String username = sc.nextLine();
		System.out.println("Please enter you password:");
		String psswrd = sc.nextLine();
		
		try
		{
			//Class.forName ("org.postgresql.Driver"); // load server
			conn = DriverManager.getConnection( "jdbc:postgresql://localhost/project", username, psswrd);
			
		}
		catch (SQLException sqle)
		{
			System.out.println("SQLException : " + sqle);// handle exceptions
		}
		
		PreparedStatement insertCust = conn.prepareStatement("insert into customer values(?, ?, ?);");
		PreparedStatement insertEmp = conn.prepareStatement("insert into employee values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
		PreparedStatement insertModel = conn.prepareStatement("insert into model values(?, ?, ?, ?, ?);");
		PreparedStatement insertOrder = conn.prepareStatement("insert into orderTable values(?,?,?,?,?,?);");
		//select statement
		PreparedStatement selectState = conn.prepareStatement("select ? from ? where ?;");
		//deletion values
		PreparedStatement deleteRowE = conn.prepareStatement("delete from employee where employeeid=?;");
		PreparedStatement deleteRowC = conn.prepareStatement("delete from customer where customerid=?;");
		PreparedStatement deleteRowM = conn.prepareStatement("delete from model where modelnumber=?;");
		PreparedStatement deleteRowO = conn.prepareStatement("delete from orderTable where ordernumber=?;");
		//update employee statements
		PreparedStatement updateSal = conn.prepareStatement("update employee set salary = ? where employeeid=?;");
		PreparedStatement updateName = conn.prepareStatement("update employee set name = ? where employeeid=?;");
		PreparedStatement updateAdd= conn.prepareStatement("update employee set address = ? where employeeid=?;");
		PreparedStatement updatePriv = conn.prepareStatement("update employee set privilege = ? where employeeid=?;");
		//update model statements
		PreparedStatement updateMName = conn.prepareStatement("update model set modelname = ? where modelnumber = ?");
		PreparedStatement updateSalePrice = conn.prepareStatement("update model set saleprice = ? where modelnumber =?");
		PreparedStatement updatequant = conn.prepareStatement("update model set totalNumber = ? where modelnumber =?");
		PreparedStatement updateCost = conn.prepareStatement("update model set totalcost = ? where modelnumber = ?");
		//update customer
		PreparedStatement updateCFName = conn.prepareStatement("update customer set firstName = ? where customerID = ?");
		PreparedStatement updateCLName = conn.prepareStatement("update customer set lastname = ? where customerID = ?");
		//update order
		PreparedStatement updateQuantity = conn.prepareStatement("update order set quantity = ? where ordernumber = ?");
		PreparedStatement updatesaleVal = conn.prepareStatement("update order set salvevalue = ? where ordernumber = ?");
		
		
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
			
			if((choice == 1) || (choice == 2) || (choice == 3))
			{
				sc.nextLine();
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
					sc.nextLine();
					insertEmp.setString(2, sc.nextLine());
					insertEmp.setString(3, sc.nextLine());
					insertEmp.setInt(4, sc.nextInt());
					insertEmp.setInt(5, sc.nextInt());
					sc.nextLine();
					insertEmp.setString(6, sc.nextLine());
					insertEmp.setString(7, sc.nextLine());
					insertEmp.setString(8, sc.nextLine());
					insertEmp.setString(9, sc.nextLine());
					insertEmp.executeUpdate();
					break;
				case "customer":
					System.out.println("Please enter the customer ID (integer), First name, and Last name:");
					insertCust.setInt(1, sc.nextInt());
					sc.nextLine();
					insertCust.setString(2, sc.nextLine());
					insertCust.setString(3, sc.nextLine());
					insertCust.executeUpdate();
					break;
				case "model":
					System.out.println("Please enter the model Number, Model Name, sale Price, totatl amount, and total cost");
					insertModel.setInt(1, sc.nextInt());
					sc.nextLine();
					insertModel.setString(2, sc.nextLine());
					insertModel.setDouble(3, sc.nextDouble());
					insertModel.setInt(4, sc.nextInt());
					insertModel.setInt(5, sc.nextInt());
					insertModel.executeUpdate();
					break;
				case "order":
					System.out.println("Please enter the order number, employeeid, customerid, and sale value, modelnumber, and quantity:");
					insertOrder.setInt(1, sc.nextInt());
					insertOrder.setInt(2, sc.nextInt());
					insertOrder.setInt(3,  sc.nextInt());
					insertOrder.setDouble(4, sc.nextDouble());
					insertOrder.setInt(5, sc.nextInt());
					insertOrder.setInt(6, sc.nextInt());
					insertOrder.executeUpdate();
					break;
				}
			}
			if(choice == 2)
			{
				switch(table) {
					case "employee":
						System.out.println("Enter employee id");
						deleteRowE.setInt(1,sc.nextInt());
						deleteRowE.executeUpdate();
						break;
					case "customer":
						System.out.println("Enter customer id");
						deleteRowC.setInt(1,sc.nextInt());
						deleteRowC.executeUpdate();
						break;
					case "model":
						System.out.println("Enter modelnumber");
						deleteRowM.setInt(1,sc.nextInt());
						deleteRowM.executeUpdate();
						break;
					case "order":
						System.out.println("Enter ordernuber");
						deleteRowO.setInt(1,sc.nextInt());
						deleteRowO.executeUpdate();
						break;
				}
			}
			if(choice == 3)
			{
				
				switch(table) {
				case "employee":
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
					break;
				case "customer":
					System.out.println("Please enter the customer ID of who you would like to update:");
					int cid = sc.nextInt();
					System.out.println("Please choose what you wish to do:");
					System.out.println("1: Update First Name");
					System.out.println("2: Update Last Name");
					int choiceC = sc.nextInt();
					System.out.println();
					switch(choiceC)
					{
						case 1:
							System.out.println("What is their new First Name?");
							updateCFName.setString(1, sc.nextLine());
							updateCFName.setInt(2, cid);
							updateCFName.executeUpdate();
							break;
						case 2:
							System.out.println("What is their new last Name?");
							updateCLName.setString(1, sc.nextLine());
							updateCLName.setInt(2, cid);
							updateCLName.executeUpdate();
							break;
					}
					break;
				case "model":
					System.out.println("Please enter the model number of what you would like to update:");
					int modelid = sc.nextInt();
					System.out.println("Please choose what you wish to do:");
					System.out.println("1: Update the model name");
					System.out.println("2: Update quantity");
					System.out.println("3: Update sale price");
					System.out.println("4: Update total cost");
					int choiceMod = sc.nextInt();
					System.out.println();
					switch(choiceMod)
					{
						case 1:
							System.out.println("What is the new name?");
							updateMName.setString(1, sc.nextLine());
							updateMName.setInt(2, modelid);
							updateMName.executeUpdate();
							break;
						case 2:
							System.out.println("What is the new sale price?");
							updateSalePrice.setInt(1, sc.nextInt());
							updateSalePrice.setInt(2, modelid);
							updateSalePrice.executeUpdate();
							break;
						case 3:
							System.out.println("What is the quantity?");
							updatequant.setInt(1, sc.nextInt());
							updatequant.setInt(2, modelid);
							updatequant.executeUpdate();
							break;
						case 4:
							System.out.println("What is the new cost?");
							updateMName.setInt(1, sc.nextInt());
							updateMName.setInt(2, modelid);
							updateMName.executeUpdate();
							break;
					}
					break;
				case "order":
					System.out.println("Please enter the order number of what you would like to update:");
					int orderid = sc.nextInt();
					System.out.println("Please choose what you wish to do:");
					System.out.println("1: Update quanity");
					System.out.println("2: Update sale value");
					int choiceOrd = sc.nextInt();
					System.out.println();
					switch(choiceOrd)
					{
						case 1:
							System.out.println("What is the new quantity?");
							updateQuantity.setInt(1, sc.nextInt());
							updateQuantity.setInt(2, orderid);
							updateQuantity.executeUpdate();
							break;
						case 2:
							System.out.println("What is the new sale value?");
							updatesaleVal.setInt(1, sc.nextInt());
							updatesaleVal.setInt(2, orderid);
							updatesaleVal.executeUpdate();
							break;
					}
					break;
				
					}
				}
				
			if(choice == 4)
			{
				System.out.println("What would you like to view?");
				System.out.println("1: Total Sales in Order");
				System.out.println("2: Order Information");
				System.out.println("3: Customer Orders");
				System.out.println("4: Expense Report");
				Statement stmt = conn.createStatement();
				int choice4 = sc.nextInt();
				switch(choice4)
				{
					case 1:
						//total revenue from sale, employee number, customer id
						//select saleValue, eid, custid from order;
						ResultSet orderSales = stmt.executeQuery("select saleValue, employeeidref, customerid from customer, orderTable");
						System.out.println("salevalue   employeeid   customerid ");
						while(orderSales.next())
						{
							
							System.out.println(orderSales.getInt("saleValue") + "                " + orderSales.getInt("employeeidref") + "             " + orderSales.getInt("customerid")); 
						}
						break;
					case 2:
						//customer, models bought, quantity
						//select customerID, modelNumber, quantity from order;
						ResultSet ordInfo = stmt.executeQuery("select customerID, modelNumber1, quantity from customer, orderTable");
						System.out.println("customerid    modelNumber    quantity");
						while(ordInfo.next())
						{
							System.out.println(ordInfo.getInt("customeridref") + "              " + ordInfo.getInt("modelNumber1")+ "            " + ordInfo.getInt("quantity"));
						}
						break;
					case 3:
						//each order, the parts, available inventory
						//select Order.orderNumer, Order.modelNumber, Model.quantity from Order, Model where Order.modelNumber == Model.ModelNumber; 
						ResultSet cusOrds = stmt.executeQuery("select Ordertable.orderNumber, Ordertable.modelNumber1, Model.totalNumber from Ordertable, Model where Ordertable.modelNumber1 = Model.ModelNumber");
						System.out.println(("ordernumber   modelNumber  totalNumber"));
						while(cusOrds.next())
						{
							System.out.println(cusOrds.getInt("OrderNumber") + "             " + cusOrds.getInt("ModelNumber1") + "             " + cusOrds.getInt("totalNumber"));
						}
						break;
					case 4:
						//expense report, employee salary, bonus expense, part cost
						// select sum(Employee.salary), sum(Model.cost * Model.quantity) from Employee, Model;
						ResultSet totals = stmt.executeQuery("select sum(Employee.salary), sum(Model.totalCost * Model.totalNumber) from Employee, Model");
						System.out.println("total salary      total part cost");
				
						while(totals.next())
						{
							System.out.println(totals.getInt(1) + "            " + totals.getInt(2));
						}
						break;
				}
			}
		
			
			System.out.println("Would you like to make another choice?");
			System.out.println("1: Yes");
			System.out.println("2: No");
			int choice3 = sc.nextInt();
			if(choice3 == 2)
			{
				flag = false;
				System.out.println("Have a good day.");
			}
		}
	}
}

