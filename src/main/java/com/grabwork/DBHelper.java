package com.grabwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class DBHelper {
	
	public Connection connect(){
		Connection con = null;
		try{  
				Class.forName("com.mysql.jdbc.Driver");  
				con =DriverManager.getConnection("jdbc:mysql://localhost/grabwork?useUnicode=yes&characterEncoding=UTF-8","root","");   
//				Statement stmt=con.createStatement();  
//				ResultSet rs=stmt.executeQuery("select * from emp");  
//				while(rs.next())  
//				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//				con.close();  
			}catch(Exception e){ System.out.println(e);
			}  
		return con;		
	}
	public User getUserForLogin(Connection con,String name, String pass){
		User user = null;
		try {
			String sql = "SELECT * FROM USER WHERE NAME = '"+name+"' AND PASS = '"+pass+"'";
			System.out.println(sql);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getInt(10), rs.getDouble(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return user;
		}
		return user;
	}
	public 	List<WorkDetail> getNearWork(Connection con,String ward,String district,String city,String workName){
		List<WorkDetail> works = new ArrayList<WorkDetail>();
		
		String where = "";
		if(!workName.equals(""))
			where = "AND w.work_name like '%"+workName+"'";
		try {
			String sql = "SELECT wd.work_detail_id,w.WORK_ID,w.WORK_NAME,wd.WORK_DETAIL_PRICE,p.PLACE_ID,wd.WORK_DETAIL_DES,wd.WORK_DETAIL_TIME,p.place_numberOfHouse,p.place_street,p.place_ward,p.place_county,p.place_city FROM WORK w,WORK_DETAIL wd, PLACE p WHERE wd.PLACE_ID = p.PLACE_ID AND wd.work_id = w.work_id AND wd.work_detail_status = 0 AND NOT EXISTS (SELECT wr.work_detail_id FROM WORK_RECIEVER wr WHERE wr.work_reciever_status = 1 and wd.work_detail_id = wr.work_detail_id) AND p.PLACE_WARD = '"+ward+"' AND p.place_county='"+district+"' AND p.place_city='"+city+"' "+where;
			System.out.println(sql);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				WorkDetail work = new WorkDetail(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getDouble(4),rs.getInt(5), rs.getString(6), rs.getString(7),new Place(rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
				works.add(work);
			}
		} catch (SQLException e) {
			
			return null;
		}
		return works;
	}
	public int insertUser(Connection con, User user){
		String sql = "INSERT INTO USER VALUES(NULL,'"+user.getName()+"','"+user.getPass()+"','"+user.getAddress()+"','"+user.getPhone()+"'"
		+","+user.getCardId()+",'"+user.getIcon()+"',"+user.getLat()+","+user.getLng()+","+user.getRoleId()+","+user.getTotal()+")";
		System.out.println(sql);
		int result = 0;
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			return result = 0;
		}		
		return result;
	}
	public String getWork(Connection con,String workDetailID,String userId){
		String status = "Thất bại";
		String sql = "INSERT INTO WORK_RECIEVER VALUES("+workDetailID+","+userId+",0)";
		System.out.println(sql);
		int result = 0;
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
			if(result != 0)
				return status = "Thành công";
		}catch(SQLIntegrityConstraintViolationException ex){
			return status = "Việc này bạn đã nhận rồi";
		}
		catch (SQLException e) {
			return status;
		}		
		return status;
	}
	public List<Place> getPlace(Connection con){
		List<Place> places = new ArrayList<Place>();
		
		try {
			String sql = "SELECT place_ward,place_county,place_city from place";
			System.out.println(sql);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Place place = new Place("","", rs.getString(1), rs.getString(2),rs.getString(3));
				places.add(place);
			}
		} catch (SQLException e) {
			
			return null;
		}
		return places;
	}
	public void disconnect(Connection con){
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
