package UserWindows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class managerWindow extends JFrame implements ActionListener {
    JFrame frame, frame2;
    JLabel label1, label2, label3, label4, label5;
    JButton button1, button2, button3, button4, button5;
    JTextField tf1, tf2, tf3, tf4, tf5;
    Connection con;
    Statement st, pst1, pst2, pst3;


    public managerWindow()
    {
        frame = new JFrame("Manager Portal");
        button1 = new JButton("Search Store");
        button2 = new JButton("View all");
        button3 = new JButton("Update Tables");

        button1.setBounds(25,100, 100,20);
        button2.setBounds(150, 100, 100, 20);
        button3.setBounds(275,100,100,20);


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setVisible(true);
        setLayout(null);
        setSize(400,200);
        add(button3);
        add(button2);
        add(button1);
    }

    public void updateTables()
    {
        frame2 = new JFrame("Table Updates");
        label1 = new JLabel("Enter the name of the game");
        label1.setBounds(50,50,200,25);
        label2 = new JLabel("Enter the release date");
        label2.setBounds(50,100,200,25);
        label3 = new JLabel("Enter the system");
        label3.setBounds(50,150, 200,25);
        label4 = new JLabel("Enter the gameID");
        label4.setBounds(50,200,200,25);

        tf1 = new JTextField();
        tf1.setBounds(250,50,100,25);
        tf2 = new JTextField();
        tf2.setBounds(250,100,100,25);
        tf3 = new JTextField();
        tf3.setBounds(250,150,100,25);
        tf4 = new JTextField();
        tf4.setBounds(250,200,100,25);

        button4 = new JButton("INSERT");
        button4.setBounds(150,400,150,25);
        button4.addActionListener(this);

        frame2.setLayout(null);
        frame2.setSize(500,500);
        frame2.setVisible(true);
        frame2.add(label1);
        frame2.add(tf1);
        frame2.add(label2);
        frame2.add(tf2);
        frame2.add(label3);
        frame2.add(tf3);
        frame2.add(label4);
        frame2.add(tf4);
        frame2.add(button4);




    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1)
        {
            new employeeWindow();
            frame.setVisible(false);
        }
        else if (e.getSource() == button2)
        {
            new managerViewAll();
            frame.setVisible(false);

        }
        else if (e.getSource() == button3){
            updateTables();
            frame.setVisible(false);
        }
        else if (e.getSource() == button4)
        {

            System.out.println("pressed button");
            String gameID = tf4.getText();
            String gameName = tf1.getText();
            String gameSystem = tf3.getText();
            String releaseDate = tf2.getText();


            try
            {
                System.out.println("pressed button");
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameschema","root", "root");
                st = con.createStatement();

                String inTable = "INSERT INTO game VALUES(" + gameID + ",'"+gameName+"','" +gameSystem +"','"+releaseDate+"')";
                st.executeUpdate(inTable);


            }catch (Exception exception)
            {
                System.out.println(exception.getMessage());

            }
        }


    }
}
