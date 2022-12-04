package com.param;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStCrudTest {

	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		Scanner sc=new Scanner(System.in);
		Statement st=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		int instid=0,inst_phone=0;
		boolean numrow=false;
		String inst_name="",inst_address="";
		String doop;
		int option=0;
		try {
			conn=JdbcConnection.getJdbcConnection();
			st=conn.createStatement();
			System.out.println("Enter choice:[1.=>insert ||2.update ||3.select ||4. delete]::");
			option=sc.nextInt();
			switch(option) {
				case 1:		//1. insert operation with prepared Statement
						pstmt1=conn.prepareStatement("insert into institutes(`instid`,`inst_name`,inst_address`,`inst_phone`,`DOOP`)values(?,?,?,?,?)");
						ResultSet rs=st.executeQuery("select max(instid)from institutes");
						while(rs.next()) {
							instid=rs.getInt(1)+1;
							System.out.println("Institute ID:"+instid);
						}
						System.out.println("Institute ID:"+instid);
						System.out.println("Enter Name of Institute:");
						inst_name=sc.next();
						System.out.println("Enter address of Institute:");
						inst_address=sc.next();
						System.out.println("Enter phone no. of Institute:");
						inst_phone=sc.nextInt();
						System.out.println("Enter the Opening date of Institute:");
						doop=sc.next();
						pstmt1.setInt(1, instid);
						pstmt1.setString(2, inst_name);
						pstmt1.setString(3, inst_address);
						pstmt1.setInt(4,inst_phone);
						pstmt1.setString(5, doop);
						int numrow1=pstmt1.executeUpdate();
						System.out.println("No. of rows affected are="+numrow1);
						
						System.out.println("Data inserted are: ");
						System.out.println("INSTID\tINST_NAME\tINSTins_ADDRESS\tINST_PHONE\tINST_DOOP");
						ResultSet rs1=st.executeQuery("select * from institutes");
						while(rs1.next()) {
							System.out.print(rs1.getInt(1)+"\t");
							System.out.println(rs1.getString(2)+"\t");
							System.out.println(rs1.getString(3)+"\t\t");
							System.out.println(rs1.getInt(4)+"\t");
							System.out.println(rs1.getString(5)+"\t");
							System.out.println();
						}
						break;
						
				case 2:	//2. update operation with prepared Statement
						
						
						instid=0;
						inst_phone=0;
						pstmt2=conn.prepareStatement("update institutes set inst_phone=? where instid=?");
						System.out.println("Enter new phone no.:");
						inst_phone=sc.nextInt();
						System.out.println("Enter institute ID:");
						instid=sc.nextInt();
						pstmt2.setInt(1, inst_phone);
						pstmt2.setInt(2, instid);
						numrow=false;
						numrow=pstmt2.execute();
						
						rs1=null;
						if(numrow==true) {
							rs1=pstmt2.executeQuery("select * from institutes");
							while(rs1.next()) {
								System.out.print(rs1.getInt(1)+"\t");
								System.out.println(rs1.getString(2)+"\t");
								System.out.println(rs1.getString(3)+"\t\t");
								System.out.println(rs1.getInt(4)+"\t");
								System.out.println(rs1.getString(5)+"\t");
								System.out.println();
							}
						}
						break;
						
				case 3:	//3. select operation with prepared Statement
						
						instid=0;
						pstmt3=conn.prepareStatement("select `inst_name`,`inst_address` from institutes where instid=?");
						System.out.println("Enter institute ID to select record:");
						instid=sc.nextInt();
						pstmt3.setInt(1,instid);
						numrow=false;
						numrow=pstmt3.execute();
						rs1=null;
						if(numrow==true) {
							rs1=pstmt3.executeQuery("select * from institutes");
							while(rs1.next()) {
								System.out.print(rs1.getInt(1)+"\t");
								System.out.println(rs1.getString(2)+"\t");
								System.out.println(rs1.getString(3)+"\t\t");
								System.out.println(rs1.getInt(4)+"\t");
								System.out.println(rs1.getString(5)+"\t");
								System.out.println();
							}
						}
						break;
						
				case 4:		//4. delete operation with prepared Statement
						
						instid=0;
						pstmt4=conn.prepareStatement("delete from institutes where instid=?");
						System.out.println("Enter institute ID to delete record:");
						instid=sc.nextInt();
						pstmt4.setInt(1,instid);
						numrow=false;
						numrow=pstmt4.execute();
						rs1=null;
						if(numrow==true) {
							rs1=pstmt4.executeQuery("select * from institutes");
							while(rs1.next()) {
								System.out.print(rs1.getInt(1)+"\t");
								System.out.println(rs1.getString(2)+"\t");
								System.out.println(rs1.getString(3)+"\t\t");
								System.out.println(rs1.getInt(4)+"\t");
								System.out.println(rs1.getString(5)+"\t");
								System.out.println();
							}
						}
							break;
				default: System.out.println("Wrong choice||Try again");
						break;
			}	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			
			st.close();
			sc.close();
			conn.close();
		}

	}

}
