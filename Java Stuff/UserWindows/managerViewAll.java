package UserWindows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class managerViewAll {

    JFrame frame;
    JLabel label1, label2, label3;
    JTextField searchBar;
    JButton b1, b2;
    ResultSet rs;
    Statement st;
    String ids;
    static JTable table;
    String[] columnNames = {"GameID" , "Game Name", "System", "Release Date", "Address", "Quantity"};
    String from;
    Connection con;
    PreparedStatement pst, pst2;
    public managerViewAll()
    {
        frame = new JFrame("Database Search Result");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable();
        table.setModel(model);
        model.setColumnIdentifiers(columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //from = searchBar.getText();
        String buildingID = "";
        String streetAddress = "";
        String city = "";
        String state = "";
        String country = "";
        int gameID;
        String gameName = "";
        String gameSystem = "";
        String releaseDate = "";
        String shipDate = "";
        String receievedDate = "";
        String toBuildingDI = "";
        String fromBuildingID = "";
        String shipmentID = "";
        String shippingInfoID = "";
        String quantity = "";
        String videogameID = "";
        String shipmentWeight = "";
        String gameStoreID = "";
        String bID = "";
        String gameStoreName = "";
        String shopID = "";
        String gID = "";
        String Quantity = "";
        String aisle = "";
        String price = "";
        String wearhouseID = "";
        String buldingID = "";
        String companyName = "";
        String sorageID = "";
        String sectionID = "";
        String stackID = "";

        //String quantity = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameschema","root", "root");

            pst = con.prepareStatement("select * from game, iis, building");
            //pst2 = con.prepareStatement("select GID.QTT From game G, storage GID Where g.gameID = GID.gameID");

            ResultSet rs = pst.executeQuery();
            //ResultSet rs2 = pst2.executeQuery();

            while(rs.next())
            {
                gameID = rs.getInt("gameID");
                gameName = rs.getString("gameName");
                gameSystem = rs.getString("gameSystem");
                releaseDate = rs.getString("releaseDate");
                //shopID = rs.getString("shopID");
                quantity = rs.getString("quantity");
                streetAddress = rs.getString("streetAddress");
                model.addRow(new Object[]{gameID, gameName, gameSystem, releaseDate, streetAddress, quantity});
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

