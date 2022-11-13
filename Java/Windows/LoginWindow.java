package Windows;/*
    Use: Creates the inital Login Window made for the project

    Author: Tyler Wagner
    Date Created: 10/17/18

    TODO:
    Create a way for people to create a new account or login to an existing one.
    If I want to get fancy create a way for people to rest passwords
 */

import javax.swing.*; //imports Swing package which creates form and button

import java.awt.BorderLayout;
import java.awt.event.*; //imports Event package which listens for button press

public class LoginWindow implements ActionListener { //notice implements ActionListener
    public JFrame frame = new JFrame();
    JButton Login;
    JLabel Username;
    JLabel Password;
    JLabel incorrect;
    JTextField user;
    JTextField pass;
    JPanel panel;


    public static void main (String[] args) {
        LoginWindow gui = new LoginWindow();
        gui.press();

    }

    public void press(){
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

    }

    public void actionPerformed(ActionEvent event){ //if button is pressed then this changes button text
        String username = user.getText();
        String password = pass.getText();
        boolean correctUser, correctPass = false; // default false to make sure that you cant just log in
        

        //Checking to see if the username and password are correct, default is root
        if(username.equals("Root"))
        {
            System.out.println("passed");
        }
        else
        {
            incorrect = new JLabel("Incorrect Username");
        }
        if(password.equals("root"))
        {
            System.out.println("passed");
            //incorrect = new JLabel("Logging you in");

            //calling the new frame
            frame.setVisible(false);
            searchWindow sw = new searchWindow();
            sw.searchWindow();
        }
        else
        {    
            incorrect = new JLabel("Incorrect Password");
        }
        
        
    }

    

}