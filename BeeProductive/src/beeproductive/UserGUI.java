/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeproductive;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author xxx
 */
public class UserGUI
{
    private JFrame myFrame = new JFrame();
    private JLabel helloLabel = new JLabel("Hello");
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");

    
    public UserGUI()
    {
        setFrame();
    }
    
    private void setFrame()
    {
        setMenuBar(myFrame);
        
        Container contentPane = myFrame.getContentPane();
        contentPane.setLayout(new FlowLayout());       
        
        contentPane.add(helloLabel);
        contentPane.add(startButton);
        contentPane.add(resetButton);
        
        
        // add INDIVIDUALISED LISTENERS (event handlers):
        startButton.addActionListener(new startButtonHandler());
        resetButton.addActionListener(new resetButtonHandler());

        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    private void setMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // *** FILE menu:
        // create and add the File menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ExitHandler());                           // TESTING!
        // -----------------------------------------------
        
        // *** ABOUT menu:
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
        JMenuItem howToItem = new JMenuItem("How to use");
        helpMenu.add(howToItem);
        howToItem.addActionListener(new howToUseHandler());
        
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(new aboutHandler());
        // -----------------------------------------------
    }
    
    private class ExitHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    private class howToUseHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Give info on how to use the software");
        }
    }
    
    private class aboutHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("About section...");
        }
    }

    private class startButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("START button executed...");
        }
    }

    private class resetButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("RESET the settings...");
        }
    }
}