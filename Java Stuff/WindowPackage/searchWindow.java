
/*
Author: Tyler Wagner
Date Created: ?
Date Modified: 11/8/22
Modified by: Tyler Wagner
 */

package WindowPackage;

import java.awt.BorderLayout;

/*
    Use: Creating the secondary window that will be used to search through the DB

    Date Created: 10/18/22
    Author: Tyler Wagner

    TODO:
     Add a menu bar to log out of an account
     Fix the text area so it shows ALL previous searches and not just the last one ( THIS IS DONE WITH AN ARRAY LIST DIP SHIT )

 */

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class searchWindow {
    //Declaring all Variables
    JFrame frame2;
    JButton search;
    JTextArea previousSearches;
    JLabel PreviousSearches;
    JLabel Search;
    JTextField searchBar;
    JPanel panel2;

    JMenuBar menuBar = new JMenuBar();

    WindowLogic wl = new WindowLogic();

    public void searchWindow()
    {
        // setting all Variables
        frame2 = new JFrame();
        panel2 = new JPanel();
        search = new JButton("Search");
        previousSearches = new JTextArea("");
        PreviousSearches = new JLabel("Previous Searches");
        searchBar = new JTextField(20);
        Search = new JLabel("Name of Game");
        
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search.addActionListener(this::actionPerformed);
        
        panel2.add(PreviousSearches);
        panel2.add(previousSearches);
        panel2.add(Search);
        panel2.add(searchBar);
        panel2.add(search);

        frame2.getContentPane().add(BorderLayout.NORTH, PreviousSearches);
        frame2.getContentPane().add(BorderLayout.CENTER, previousSearches);
        frame2.getContentPane().add(BorderLayout.SOUTH, panel2);
        
        frame2.setSize(500,500);
        frame2.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        String searchedItem = searchBar.getText();
        previousSearches.setText(searchedItem);
        String temp = searchedItem;




        wl.WindowLogic();

        /*
        implement the use of the Database here. IE if statements searching the DB for the correct game or something.
        You could put this logic in a different class to keep this one less cluttered.
         */
    }

}
