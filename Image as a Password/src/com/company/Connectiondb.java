package com.company;

import java.sql.*;
import java.io.*;
public  class Connectiondb {

    public void connection(){
        //Calling Registration class
        Registration r = new Registration();
        try {
            /*
            Storing the user details into the mysql database table.
             */
            String path = r.pathdb;
            Class.forName("com.mysql.jdbc.Driver");
            Connection connectiondb = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp?characterEncoding=latin1", "root", "Mnrjr@14");
            PreparedStatement preparedStatement = connectiondb.prepareStatement("insert into user_reg values(?,?,?,?,?,?)");
            preparedStatement.setString(1,r.maildb);
            preparedStatement.setString(2,r.firstNamedb);
            preparedStatement.setString(3,r.lastNamedb);
            preparedStatement.setString(4,r.dobdb);
            preparedStatement.setString(5,r.mobileNumberdb);
            FileInputStream passwd = new FileInputStream(r.pathdb);
            preparedStatement.setBinaryStream(6,passwd,passwd.available());
            preparedStatement.executeUpdate();
            connectiondb.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
