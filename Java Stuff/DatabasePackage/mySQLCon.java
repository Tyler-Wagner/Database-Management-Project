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
    public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameschema","root","root");
            System.out.println("Connection was created successfully");
            Statement statement  = con.createStatement();

            mySQLCon server = new mySQLCon();
            server.insertValue(statement);

            con.close();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void popDatabase()
    {
        //This method will only run once when the program is started
        //This will really only need to be ran once. I will update the todo list for this


    }

    public void deleteAll()
    {
        //This method will only run when the database needs to be deleted.
    }

    public void insertValue(Statement statement) throws SQLException {

        try {
            statement.executeUpdate("INSERT INTO game "+ "VALUES (1001, 'God of War', 'PlayStation 5', '2022')");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteValue()
    {

    }



}
