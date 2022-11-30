/*
Author: Tyler Wagner
Date Created: 11/18/22
Last Modified: 11/23/22
Modified By: Tyler Wagner
 */

package DatabasePackage;


import javax.naming.*;
import javax.sql.*;
import java.sql.*;


public class mySQLCon {
    Connection con;
    public Connection mySQLCon() {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameschema","root","root");
            System.out.println("Connection was created successfully");
            Statement statement  = con.createStatement();

            //mySQLCon server = new mySQLCon();

            con.close();

        }
        catch (Exception e)
        {
            System.out.println("Error: " +e.getMessage());
        }
        return con;
    }





}
