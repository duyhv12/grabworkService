package com.grabwork;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("services")
public class GrabWorkService{
	private DBHelper dbHelper;
	private Connection con;
	
	public GrabWorkService(){
		dbHelper = new DBHelper();
		con = dbHelper.connect();
	}
	
	@Path("login")
	@GET
	@Produces("application/json")
	public String checkLogin(@MatrixParam("user")String username,@MatrixParam("pass")String password ){
		User user = dbHelper.getUserForLogin(con, username, password);
		String result = "";
		result += "  {\n";
		result += "   \"id\":"+user.getId()+",\n";
		result += "   \"name\":\""+user.getName()+"\",\n";
		result += "   \"icon\":\""+user.getIcon()+"\",\n";
		result += "   \"status\":"+true+"\n";
		result += "  }\n";
		return result;
	}
	@Path("register")
	@POST
	@Produces("application/json")
	public String registerUser(@FormParam("param1")String name,@FormParam("param2")String pass,@FormParam("param3")String address,@FormParam("param4")String phone,@FormParam("param5")long card_id,@FormParam("param6")String icon,
			@FormParam("param7")double lat, @FormParam("param8")double lng,@FormParam("param9")int role_id,@FormParam("param10")double total){
		
		User user = new User(1, name, pass, address, phone, card_id, icon, lat, lng, role_id, total);
		
		int success = dbHelper.insertUser(con, user);
		String status[] = {"Thành công", "Thất bại"};
		if(success == 0)
			return "{'status':'"+status[1]+"'}";
		else 
			return "{'status':'"+status[0]+"'}";
	}
	@Path("findnearwork")
	@GET
	@Produces("application/json")
	public String findWork(@MatrixParam("param1")String ward,@MatrixParam("param2")String district,@MatrixParam("param3")String city,@MatrixParam("param4")String workName){
		String result = "";
		boolean last = false;
		List<WorkDetail>works = dbHelper.getNearWork(con, ward,district,city,workName);
		if(works.size() != 0){
			result += "{\n";
			result += "\" result\" : [ \n";
			for(int i = 0 ; i < works.size(); i++){
				Place p = works.get(i).getPlace();
				String address = p.getNumberOfHouse() + "," + p.getStreet() + "," + p.getWard() + "," + p.getCountry() + "," + p.getCity(); 
				result += "  {\n";
				result += "   \"work_detail_id\":\""+works.get(i).getWorkDetailId()+"\",\n";
				result += "   \"work_id\":\""+works.get(i).getWorkId()+"\",\n";
				result += "   \"work_name\":\""+works.get(i).getWorkName()+"\",\n";
				result += "   \"work_price\":\""+works.get(i).getWorkPrice()+"\",\n";
				result += "   \"place_id\":\""+works.get(i).getPlaceId()+"\",\n";
				result += "   \"work_des\":\""+works.get(i).getWorkDes()+"\",\n";
				result += "   \"work_time\":\""+works.get(i).getWorkTime()+"\",\n";
				result += "   \"address\":\""+address+"\"\n";
				if(!last && works.size() >= 2){
					result += "  },\n";
					last = true;
				}
				else
					result += "  }\n";
			}
			result += " ]\n";
			result += "}";
		}
		return result;
	}
	@Path("getplace")
	@GET
	@Produces("application/json")
	public String getPlaces(){
		String result = "";
		boolean last = false;
		List<Place>places = dbHelper.getPlace(con);
		if(places.size() != 0){
			result += "{\n";
			result += "\" result\" : [ \n";
			for(int i = 0 ; i < places.size(); i++){
//				String address = places.get(i).getNumberOfHouse() + "," + p.getStreet() + "," + p.getWard() + "," + p.getCountry() + "," + p.getCity(); 
				result += "  {\n";
				result += "   \"ward\":\""+places.get(i).getWard()+"\",\n";
				result += "   \"district\":\""+places.get(i).getCountry()+"\",\n";
				result += "   \"city\":\""+places.get(i).getCity()+"\"\n";
	
				if(!last && places.size() == 1){
					result += "  }\n";
					last = true;
				}else{
					
					if(places.size()-1 > i)
						result += "  },\n";
					else
						result += "  }\n";
					
				}
				
			}
			result += " ]\n";
			result += "}";
		}
		return result;
	}
	@Path("getwork")
	@GET
	@Produces("application/json")
	public String getWorkFromId(@MatrixParam("param1")String workDetailId,@MatrixParam("param2")String userId){
		String status = dbHelper.getWork(con, workDetailId, userId);
		return "{\"status\":\""+status+"\"}";
	
	}
}
