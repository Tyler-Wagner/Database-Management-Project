/*
Author: Tyler Wagner
Date Created: ?
Date Modified: 11/28/22
Modified by: Khled Singleton
 */
package WindowPackage;

import javax.swing.*; //imports Swing package which creates form and button
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.event.*; //imports Event package which listens for button press
import WindowPackage.searchWindow;
public class loginWindow implements ActionListener { //notice implements ActionListener
    public JFrame frame = new JFrame();
    JButton Login;
    JLabel Username;
    JLabel Password;
    JLabel incorrect;
    JTextField user;
    JTextField pass;
    JPanel panel;
    HashMap<String, String> managers;
    HashMap<String, String> employees;

    public static void main (String[] args) {
        loginWindow gui = new loginWindow();
        gui.initWindow();

    }

    public void initWindow(){
         //creates a Java Frame called frame
        Login = new JButton("Login"); //creates a Button called button
        Login.addActionListener(this); //listens for button press
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ends program when JFrame closed

        Username = new JLabel("Username");
        Password = new JLabel("Password");

        user = new JTextField(10);
        pass = new JTextField(10);
        panel = new JPanel();

        panel.add(Username);
        panel.add(user);
        panel.add(Password);
        panel.add(pass);
        panel.add(Login);

        frame.getContentPane().add(BorderLayout.CENTER, panel);


        frame.setSize(250,250); //pixel size of frame in width then height

        frame.setVisible(true); //if false then frame will be invisible
        managers = new HashMap<String, String>();
        managers.put("Manager", "Manager1");
        employees = new HashMap<String, String>();
        employees.put("Employee1", "Password1");
        employees.put("Employee2", "Password2");
        employees.put("Employee3", "Password3");
        employees.put("Employee4", "Password4");


        

    }

    public void actionPerformed(ActionEvent event){ //if button is pressed then this changes button text
        String username = user.getText();
        String password = pass.getText();
        boolean correctUser, correctPass = false; // default false to make sure that you cant just log in
        

        //Checking to see if the username and password are correct, default is root
        if (managers.containsKey(username)) {
            //User is in managers hashmap
            String targetPass = managers.get(username); //gets password for user
            if (password.equals(targetPass)) {
                System.out.printf("Logged in as %s\n", username);
                frame.setVisible(false);
                searchWindow sw = new searchWindow();
                sw.searchWindow();
                //given admin rights
            }
            else {
                System.out.printf("Incorrect username or password\n");
            }
        }
        else if (employees.containsKey(username)) {
            //user is in employees hashmap
            String targetPass = employees.get(username); //gets password for user
            if (password.equals(targetPass)) {
                System.out.printf("Logged in as %s\n", username);
                frame.setVisible(false);
                searchWindow sw = new searchWindow();
                sw.searchWindow();
                //given user rights
            }
            else {
                System.out.printf("Incorrect username or password\n");
            }
        }
        else {
            //username was not found in either hashmap; account does not exist.
        }
        
        
        
    }

    

}

