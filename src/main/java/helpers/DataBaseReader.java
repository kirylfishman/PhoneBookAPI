package helpers;

import models.ContactModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseReader {
    static String url = "jdbc:mysql://localhost:3306/phonebook";
       static String user = "root";
       static String password = "user";
       public static List<ContactModel> readAllContactFromDatabase() throws SQLException {
           List <ContactModel>contacts = new ArrayList<>();

           Connection connection = DriverManager.getConnection(url,user,password);
           System.out.println("Connection successful!");
           String query = "Select * from contacts";

       Statement statement = connection.createStatement();

       ResultSet resultSet = statement.executeQuery(query);
       while (resultSet.next()){
           ContactModel contactModel = new ContactModel();

           contactModel.setId(resultSet.getString("id"));
           contactModel.setName(resultSet.getString("name"));
           contactModel.setLastName(resultSet.getString("lastname"));
           contactModel.setEmail(resultSet.getString("email"));
           contactModel.setPhone(resultSet.getString("phone"));
           contactModel.setAddress(resultSet.getString("address"));
           contactModel.setDescription(resultSet.getString("description"));
contacts.add(contactModel);
       }
       statement.close();
       connection.close();
       return contacts;
       }
       public static void main(String[] args) throws SQLException {
List<ContactModel> cont = readAllContactFromDatabase();
for (ContactModel contactModel: cont){
    System.out.println("Record: " + contactModel.toString());
}
    }

}
