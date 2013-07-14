package com.dhairya.apidft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.logging.Logger;

public class CustomerDao {

Logger log = Logger.getLogger(CustomerDao.class);
private Connection connect = null;
private Statement statement = null;
private ResultSet resultSet = null;


//Get list of customers
public List<CustomerModel> readDatabase() throws Exception{

List<CustomerModel> list = new ArrayList<CustomerModel>();

try{
log.info("Loading MySql driver");
Class.forName("com.mysql.jdbc.Driver");

log.info("Setting up connection with db");
connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/apidft","root","cisco");
log.info("Database connection successful");
statement = connect.createStatement();

resultSet= statement.executeQuery("select * from customer");

while(resultSet.next()){
list.add(processRow(resultSet));
}
}

catch(Exception e){
log.error("Database connection fail");
}

finally{
connect.close();
}
return list;
}

private CustomerModel processRow(ResultSet rs) throws SQLException {
CustomerModel cust = new CustomerModel();

cust.setId(rs.getInt("ID"));
cust.setName(rs.getString("NAME"));
cust.setAddress(rs.getString("ADDRESS"));

return cust;
}


//Add a Customer
public void updateDatabase() throws Exception{

try{
log.info("Loading MySql driver");
Class.forName("com.mysql.jdbc.Driver");

log.info("Setting up connection with db");
connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/apidft","root","cisco");


statement = connect.createStatement();
String updater = "INSERT INTO customer " +
                "VALUES ('4','Dhairya Sanghvi', 'Bombay, India')";
   statement.executeUpdate(updater);
 System.out.println("Inserted Successfully!");
 connect.close();

}

catch(Exception e){
e.printStackTrace();
}

finally{
connect.close();
}

}


//Get customer by ID
public CustomerModel findById(int id) throws Exception {
    String sql = "SELECT * FROM customer WHERE ID = ?";
    CustomerModel customer = null;
      
       try {
        
        log.info("Loading MySql driver");
    Class.forName("com.mysql.jdbc.Driver");

    log.info("Setting up connection with db");
    connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/apidft","root","cisco");

    PreparedStatement stmt = connect.prepareStatement(sql);
           stmt.setInt(1, id);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               customer = processRow(rs);
           }
           
       } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException(e);
} finally {
connect.close();
}
       return customer;
   }

}

