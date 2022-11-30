
/*
Author: Tyler Wagner
Date Created: ?
Date Modified: 11/30/22
Modified by: Tyler Wagner
 */

package UserWindows;

import DatabasePackage.mySQLCon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Vector;


public class employeeWindow extends JFrame implements ActionListener {
    //Declaring all Variables
    JFrame frame;
    JLabel label1, label2, label3;
    JTextField searchBar;
    JButton b1, b2;
    ResultSet rs, rs1;
    Statement st, st1;
    String ids;
    static JTable table;
    String[] columnNames = {"gameName", "gameSystem", "releaseDate"};
    String from;
    Connection con;
    PreparedStatement pst;

    public employeeWindow() {
        label1 = new JLabel("Fetching Information");
        label2 = new JLabel("Search Title: ");
        b1 = new JButton("Search");
        b2 = new JButton("View All");
        searchBar = new JTextField();
        label1.setBounds(125, 50, 350, 40);
        label2.setBounds(50, 110, 100, 20);
        searchBar.setBounds(150,110,175,20);
        b1.setBounds(150, 150, 150, 20);
        b2.setBounds(150,205,150,20);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setTitle("Fetching Game Info from Database");
        setLayout(null);
        setVisible(true);
        setSize(400,250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(label1);
        add(label2);
        add(searchBar);
        add(b1);
        add(b2);



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameschema","root", "root");
            con = (Connection) new mySQLCon();
            st = con.createStatement();
            rs = st.executeQuery("Search gameName from game");
            Vector v = new Vector();

            while(rs.next())
            {
                ids = rs.getString(1);
                v.add(ids);
            }
            st.close();
            rs.close();

        }catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == b1)
        {
            showTableData();
        }
        else if (event.getSource() == b2)
        {
            showAll();
        }
    }



    public void showTableData()
    {
        frame = new JFrame("Database Search Result");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable();
        table.setModel(model);
        model.setColumnIdentifiers(columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        from = (String) searchBar.getText();
        String gameName = "";
        String gameSystem = "";
        String releaseDate = "";

        try{
            pst = con.prepareStatement("select * from game where gameName='" + from + "'");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            if (rs.next())
            {
                gameName = rs.getString("gameName");
                gameSystem = rs.getString("gameSystem");
                releaseDate = rs.getString("releaseDate");
                model.addRow(new Object[]{gameName, gameSystem, releaseDate});
                i++;
            }
            if (i < 1)
            {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1)
            {
                System.out.println(i +" Record Found");
            }
            else
            {
                System.out.println(i + " Records Found");
            }

        }catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }

        frame.add(scroll);
        frame.setVisible(true);
        frame.setSize(500,500);
    }


    //method to show 
    public void showAll()
    {
        frame = new JFrame("Database Search Result");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable();
        table.setModel(model);
        model.setColumnIdentifiers(columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        from = (String) searchBar.getText();
        String gameName = "";
        String gameSystem = "";
        String releaseDate = "";

        try{
            pst = con.prepareStatement("select * from game");
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                gameName = rs.getString("gameName");
                gameSystem = rs.getString("gameSystem");
                releaseDate = rs.getString("releaseDate");
                model.addRow(new Object[]{gameName, gameSystem, releaseDate});
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        frame.add(scroll);
        frame.setVisible(true);
        frame.setSize(500,500);
    }

}
