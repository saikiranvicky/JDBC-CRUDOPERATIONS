package com.src.studentinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentInfo {
	static Connection con = null;
	static Statement stmt = null;
	static PreparedStatement pst = null;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StudentData sd = new StudentData();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/student","db_username","db_password");  
			stmt = con.createStatement();
			System.out.println("View the student details:");
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM studentinfo");
			while(rs1.next()) {
				System.out.println(rs1.getInt(1) +" "+rs1.getString(2)+" "+rs1.getInt(3)+" "+rs1.getInt(4));
			}
			System.out.println("Operations to be performed : ");
			System.out.println("1. Add Students.");
			System.out.println("2. View Students.");
			System.out.println("3. Delete Students.");
			System.out.println("4. Update Students.");
			int option = scan.nextInt();
			switch(option) {
			case 1:
				pst = con.prepareStatement("insert into studentinfo(name,age,greScore)" + "values(?,?,?)");
				System.out.println("Enter how many students to be inserted in the studentinfo table:");
				int num_students = scan.nextInt();
				for(int i = 0; i < num_students; i++) {
					System.out.println("Enter student's name : ");
					String s_name = scan.next();
					sd.setName(s_name);
					System.out.println("Enter student's age : ");
					int s_age = scan.nextInt();
					sd.setAge(s_age);
					System.out.println("Enter student's gre score");
					int s_score = scan.nextInt();
					sd.setGre_score(s_score);	
					pst.setString(1, s_name);
					pst.setInt(2, s_age);
					pst.setInt(3, s_score);
					pst.execute();
				}
				System.out.println(num_students +" rows have been inserted into studentinfo");
				break;
			case 2:
				System.out.println("View the student details:");
				ResultSet rs = stmt.executeQuery("SELECT * FROM studentinfo");
				while(rs.next()) {
					System.out.println(rs.getInt(1) +" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
				}
				break;
			case 3:
				System.out.println("Enter the id of the student to be deleted : ");
				int s_id = scan.nextInt();
				pst = con.prepareStatement("DELETE FROM studentinfo WHERE id = ? ");
				pst.setInt(1, s_id);
				pst.execute();
				System.out.println("List of remaining students:");
				ResultSet rs2 = stmt.executeQuery("SELECT * FROM studentinfo");
				while(rs2.next()) {
					System.out.println(rs2.getInt(1) +" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getInt(4));
				}
				break;
			case 4:
				System.out.println("enter the new name of the student:");
				String stud_name = scan.next();
				System.out.println("Enter the id of the student that you want to update a field.");
				int stud_id = scan.nextInt();
				pst = con.prepareStatement("UPDATE studentinfo SET name = ? WHERE id = ?");
				pst.setString(1, stud_name);
				pst.setInt(2, stud_id);
				pst.executeUpdate();
				System.out.println("View the student details:");
				ResultSet rs3 = stmt.executeQuery("SELECT * FROM studentinfo");
				while(rs3.next()) {
					System.out.println(rs3.getInt(1) +" "+rs3.getString(2)+" "+rs3.getInt(3)+" "+rs3.getInt(4));
				}
				break;
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			scan.close();
			if(con!=null) {
				try {
					con.close();
					stmt.close();
					pst.close();
				} catch (SQLException e) {
				}
			}
		
		}
	}

}
