package com.param;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.param.JdbcConnection;

public class DatabaseMenu {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(System.in);
		Connection conn=null;
		conn=JdbcConnection.getJdbcConnection();
		Statement st=conn.createStatement();
		
		int option=0;
		int sid = 0,sage=0,sphone=0;
		String sfname ="",slname="",semail="",spassword="",saddress="";
		
		//String sqlQuery1="insert into students(`sid`,`sfname`,`slname`,`semail`,`spassword`,`sage`,`sphone`,`saddress`)values(13,'Cybil','Trelawny','st33@gmail.com','st33rel',32,12345678,'Hogwarts castle,Crystal Ball'),(14,'Dido','Dad','dd44@gmail.com','dd444412',37,98765432,'Godzilla Country')";
		
		String sqlQuery2="select sid,sfname,slname,semail,spassword,sage,sphone,saddress from students";
		String sqlQuery3="update students set sphone=82821177 where sid=12";
		String sqlQuery4="delete from students where sid=13";
		
		System.out.println("Enter option to execute DatabaseAction:: 1=>Create||2=>Read||3=>Update||4=>Delete");
		option=sc.nextInt();
		
		try {
			
			if(conn!=null) {
				switch(option) {
				//1. Create Operation
				case 1:	System.out.println("Insert values into the database:");
						ResultSet rs=st.executeQuery("select max(sid) from students");
						while(rs.next()) {
						sid=rs.getInt(1)+1;
						System.out.println("Student id:"+sid);
						}
						System.out.println("Student 1st name:");
						sfname=sc.next();
						sfname="'"+sfname+"'";
						System.out.println("Student last name:");
						slname=sc.next();
						slname="'"+slname+"'";
						System.out.println("Student email:");
						semail=sc.next();
						semail="'"+semail+"@gmail.com'";
						System.out.println("Student password:");
						spassword=sc.next();
						spassword="'"+spassword+"'";
						System.out.println("Student age:");
						sage=sc.nextInt();
						System.out.println("Student phone:");
						sphone=sc.nextInt();
						System.out.println("Student address:");
						saddress=sc.next();
						saddress="'"+saddress+"'";
						String sqlQuery=String.format("insert into students(`sid`,`sfname`,`slname`,`semail`,`spassword`,`sage`,`sphone`,`saddress`)values(%d,%s,%s,%s,%s,%d,%d,%s)",sid,sfname,slname,semail,spassword,sage,sphone,saddress);
						System.out.println(sqlQuery);
						String sqlQuery1="insert into students(`sid`,`sfname`,`slname`,`semail`,`spassword`,`sage`,`sphone`,`saddress`)values("+sid+","+sfname+","+slname+","+semail+","+spassword+","+sage+","+sphone+","+saddress+")";
						int noOfRows=st.executeUpdate(sqlQuery1);
						System.out.println("No. of rows affected="+noOfRows);
						break;
						
				//2.Read Operation		
				case 2: ResultSet rs1=st.executeQuery(sqlQuery2);
						System.out.println("SID\tSFNAME\tSLNAME\tSEMAIL\t\tSPASS\t\tSAGE\tSPHONE\t\tSADDRESS");
						while(rs1.next()) {
							System.out.print(rs1.getInt(1)+"\t");
							System.out.print(rs1.getString(2)+"\t");
							System.out.print(rs1.getString(3)+"\t");
							System.out.print(rs1.getString(4)+"\t");
							System.out.print(rs1.getString(5)+"\t");
							System.out.print(rs1.getInt(6)+"\t");
							System.out.print(rs1.getInt(7)+"\t");
							System.out.println(rs1.getString(8));
							System.out.println();
						}
						break;
					
				//3.Update Operation
				case 3:	int rowcount1=st.executeUpdate(sqlQuery3);
						System.out.println("No. of rows affected"+rowcount1);
						ResultSet rs2=st.executeQuery(sqlQuery2);
						System.out.println("SID\tSFNAME\tSLNAME\tSEMAIL\t\tSPASS\t\tSAGE\tSPHONE\t\tSADDRESS");
						while(rs2.next()) {
							System.out.print(rs2.getInt(1)+"\t");
							System.out.print(rs2.getString(2)+"\t");
							System.out.print(rs2.getString(3)+"\t");
							System.out.print(rs2.getString(4)+"\t");
							System.out.print(rs2.getString(5)+"\t");
							System.out.print(rs2.getInt(6)+"\t");
							System.out.print(rs2.getInt(7)+"\t");
							System.out.print(rs2.getString(8));
							System.out.println();
						}
						break;
				
				//4.Delete Operation		
				case 4: int rowCount2=st.executeUpdate(sqlQuery4);
						System.out.println("No. of rows affected"+rowCount2);
						ResultSet rs3=st.executeQuery(sqlQuery2);
						System.out.println("SID\tSFNAME\tSLNAME\tSEMAIL\t\tSPASS\t\tSAGE\tSPHONE\t\tSADDRESS");
						while(rs3.next()) {
							System.out.print(rs3.getInt(1)+"\t");
							System.out.print(rs3.getString(2)+"\t");
							System.out.print(rs3.getString(3)+"\t");
							System.out.print(rs3.getString(4)+"\t");
							System.out.print(rs3.getString(5)+"\t");
							System.out.print(rs3.getInt(6)+"\t");
							System.out.print(rs3.getInt(7)+"\t");
							System.out.print(rs3.getString(8));
							System.out.println();
						}
						break;
						
				default:System.out.println("Wrong input||Exit and Try again");
						break;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			st.close();
			conn.close();
		}

	}

}
