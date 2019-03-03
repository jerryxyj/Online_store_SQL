package cs425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cs425.Store_info;
import java.util.Scanner;
public class project {
	public static void main(String[] args) throws ClassNotFoundException {	
		Scanner sc = new Scanner(System.in);
		boolean key = true;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl","gli32","YCsBUo3...uK3Z17");
			Statement stmt = conn.createStatement();
			do{
				Store_info.menu_list();
				int option = sc.nextInt();
				if(option==1){
					Store_info.database_list();
					int database = sc.nextInt();
					switch(database){
					case 3:{
						sc.nextLine();
						System.out.println("Enter customerID: ");
						String customerID = sc.nextLine();
						System.out.println("Enter email: ");
						String email = sc.nextLine();
						System.out.println("Enter First name: ");
						String firstname = sc.nextLine();
						System.out.println("Enter middle name: ");
						String middlename = sc.nextLine();
						System.out.println("Enter last name: ");
						String lastname = sc.nextLine();
						try{
							PreparedStatement pStmt = conn.prepareStatement("insert into customer values(?,?,?,?,?)");
							pStmt.setString(1, customerID);
							pStmt.setString(2, email);
							pStmt.setString(3,firstname);
							pStmt.setString(4,middlename);
							pStmt.setString(5,lastname);
							pStmt.executeUpdate();
							
						}catch(SQLException sqle)
						{
							System.out.println("Counld not insert tuple"+sqle);
						}
					}
						
					}
				}
				else if(option==2){
					Store_info.database_list();
					int database = sc.nextInt();
					switch(database){
					case 3:{
						sc.nextLine();
						System.out.println("Enter the customerID");
						String customerid = sc.nextLine();
						System.out.println("Enter new first name");
						String firstname = sc.nextLine();
						System.out.println("Enter new middle name");
						String midddlename = sc.nextLine();
						System.out.println("Enter new last name");
						String lastname = sc.nextLine();
						System.out.println("Enter new email");
						String email = sc.nextLine();
						try{
							PreparedStatement pStmt = conn.prepareStatement("update customer set firstname =?,middlename=?,lastname=?,email=?where customerid=?");
							pStmt.setString(1, firstname);
							pStmt.setString(2, midddlename);
							pStmt.setString(3, lastname);
							pStmt.setString(4, email);
							pStmt.setString(5,customerid);
							pStmt.executeUpdate();
						}
						catch(SQLException sqle)
						{
							System.out.println("Counld not update info"+sqle);
						}
					}
					}
				}
				else if(option==3){
					Store_info.database_list();
					int database = sc.nextInt();
					switch(database){
					case 3:{
						ResultSet rest = stmt.executeQuery("select customerID,email,firstname,middlename,lastname from customer");
						while(rest.next()){
							System.out.println(rest.getString("customerID")+" "+rest.getString("Firstname")+" "+rest.getString("middlename")+" "+rest.getString("lastname")+" "+rest.getString("email"));
						}
					}
					}
				}
				else if(option==4){
					key = false;
				}
				else{
					System.out.println("That is not a valid option");
					continue;
				}
				
				
				
			}while(key==true);
			stmt.close();
			conn.close();
		}
		catch(SQLException sqle){
			System.out.println("SQLException:"+sqle);
		}
		


	}

}
