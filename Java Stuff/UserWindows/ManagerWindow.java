package UserWindows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class ManagerWindow extends JFrame implements ActionListener {
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

    ManagerWindow()
    {

    }


    public void actionPerformed(ActionEvent e) {

    }
}
