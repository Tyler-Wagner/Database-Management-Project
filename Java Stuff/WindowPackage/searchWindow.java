
/*
Author: Tyler Wagner
Date Created: ?
Date Modified: 11/28/22
Modified by: Tyler Wagner
 */

package WindowPackage;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Vector;


public class searchWindow implements ActionListener {
    //Declaring all Variables
    JFrame frame;
    JLabel label1, label2, label3;
    JTextField searchBar;
    JButton b1;
    ResultSet rs, rs1;
    Statement st, st1;
    String ids;
    static JTable table;
    String[] columnNames = {"gameName", "gameSystem", "releaseDate"};
    String from;
    Connection con;
    PreparedStatement pst;

    public void  DisplayGameData() throws ClassNotFoundException {
        label1 = new JLabel("Fetching Information");
        label2 = new JLabel("Search Title: ");
        b1 = new JButton("Search");
        searchBar = new JTextField();
        b1.addActionListener(this);
        frame.setTitle("Fetching Game Info from Database");
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(label1);
        frame.add(label2);
        frame.add(searchBar);
        frame.add(b1);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameschema","root", "root");
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
    }

    public void showTableData()
    {
        frame = new JFrame("Database Search Result");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable();
        table.setModel(model);
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

}
