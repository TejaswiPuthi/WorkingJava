package main.java.api;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import com.howtodoinjava.logic.OnlineOfflineLogic;
//import com.howtodoinjava.pojo.Response;

//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//
@Path("Order")
public class OrderService {
	
	@Path("/Check")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String checkMethod() {
		return "Hello World";
	}
//	
//	
	Result response = new Result();
	@Path("/Online")
	@Produces("application/json")
	@POST
	public Result onlineOrder(@FormParam("CustomerDeliveryInformation") String customerDelivery) {
		
	System.out.println("tejasw");
	System.out.println(customerDelivery.getClass());
	//check if the customer is valid customer
	OnlineOfflineLogic logic = new OnlineOfflineLogic();
	Result result = new Result();
	System.out.println("before try block");
	try {
		System.out.println("in try block before");
		logic.repeat(customerDelivery);
//		logic = new OnlineOfflineLogic(customerDelivery);
		System.out.println("in try block after");
	}catch(Exception e) {
		System.out.println("in catch");
		System.out.println("exception "+e);
	}
	finally {
		System.out.println("finally block");
	}
		System.out.println("after try block");
		if(logic.ifValidCustomer()) {
			//get current time 
			System.out.println("valid");
			Date date = new Date();   
			Calendar calendar = GregorianCalendar.getInstance(); 
			calendar.setTime(date);
			int current_hour = calendar.get(Calendar.HOUR_OF_DAY);
			if(current_hour > 8 && current_hour <10) {
				if(logic.ifValidCoupon()) {
					int order_id = logic.addCustomerOrder(date);
					
					result.setCode(200);
					result.setMessage("Order ID" + order_id);
					return result;
				}
			}
			else {
				result.setCode(401);
				result.setMessage("Invalid coupon code");
				return result;
			}
			
		}
		
		result.setCode(401);
		result.setMessage("Customer not registered");
		return result;
		
	}

}
