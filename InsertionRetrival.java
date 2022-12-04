package com.param;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
//Question 2
public class InsertionRetrival {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc=new Scanner(System.in);
		Connection conn=null;
		String sqlQuery1="";
		PreparedStatement pstmt=null;
		try {
			conn=JdbcConnection.getJdbcConnection();
			Statement st=conn.createStatement();
			int empid=0;
			String name="",address="",gender="";
			//Insertion Operation
			System.out.println("Insert values into the database:");
			ResultSet rs=st.executeQuery("select max(empid) from employees");
			while(rs.next()) {
			empid=rs.getInt(1)+1;
			System.out.println("Employee id:"+empid);
			}
			System.out.println("Employee name:");
			name=sc.next();
			name="'"+name+"'";
			System.out.println("Employee address:");
			address=sc.next();
			address="'"+address+"'";
			System.out.println("Employee gender:");
			gender=sc.next();
			gender="'"+gender+"'";
			System.out.println("Enter Date of Birth:[in format:dd-MM-yyyy]");
			String dob=sc.next();
			System.out.println("Enter Date of Join:[in format:MM-dd-yyyy]");
			String doj=sc.next();
			System.out.println("Enter Date of Maturity in the format:yyyy-MM-dd");
			String dom=sc.next();
			
			sqlQuery1="insert into employees(`empid`,`name`,`address`,`gender`,`DOB`,`DOJ`,`DOM`) values(?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sqlQuery1);
			pstmt.setInt(1, empid);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, gender);
			pstmt.setString(5, dob);
			pstmt.setString(6, doj);
			pstmt.setString(7, dom);
			int noOfRows=pstmt.executeUpdate();
			System.out.println("No. of rows affected="+noOfRows);
			
			
			String sqlQuery2="select `empid`,`name`,`address`,`gender`,`DOB`,`DOJ`,`DOM` from employees";

			ResultSet rs2=st.executeQuery(sqlQuery2);
			System.out.println("EMPID\tNAME\tADDRESS\t\tGENDER\t\tDOB\t\tDOJ\t\tDOM");
			System.out.println("==========================================================================");
			while(rs2.next()) {
				System.out.print(rs2.getInt(1)+"\t");
				System.out.print(rs2.getString(2)+"\t");
				System.out.print(rs2.getString(3)+"\t\t");
				System.out.print(rs2.getString(4)+"\t\t");
				/*java.util.Date udate1=rs2.getDate(5);
				SimpleDateFormat sdf1=new SimpleDateFormat("dd-mm-YYYY");
				String d1=sdf1.format(udate1);*/
				System.out.print(rs2.getString(5)+"\t");
				/*java.util.Date udate2=rs2.getDate(6);
				SimpleDateFormat sdf2=new SimpleDateFormat("mm-dd-YYYY");
				String d2=sdf2.format(udate2);*/
				System.out.print(rs2.getString(6)+"\t");
				
				System.out.print(rs2.getString(7)+"\t");
				System.out.println();
			
			}
				
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			pstmt.close();
			conn.close();
		}
	}

}
