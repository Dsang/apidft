package com.dhairya.apidft;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.logging.Logger;

import com.dhairya.apidft.CustomerDao;
import com.dhairya.apidft.CustomerService;
import com.dhairya.apidft.CustomerModel;

@Produces("application/xml")
@Path("customers")
public class CustomerService {
	Logger log = Logger.getLogger(CustomerService.class);
	 
	 CustomerDao CustData = new CustomerDao();  //instantiate CustomerDao class

 @GET
 public List<CustomerModel> getCustomers() throws Exception{
return CustData.readDatabase();
}
 
 @GET
 @Path("add")
 @Produces("text/plain")
 public String addCustomer() throws Exception{
	 CustData.updateDatabase();
	 return "Customer added successfully!";
 }

 
 @GET
 @Path("{id}")
 public CustomerModel getCustomerByID(@PathParam("id") String id) throws Exception {
	 log.info("findById " + id);
	 return CustData.findById(Integer.parseInt(id));	 
}

}
