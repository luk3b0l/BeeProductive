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
    private JFrame myFrame = new JFrame("Bee Productive");
    
    private JLabel titleLabel = new JLabel(":: Bee Productive ::", JLabel.CENTER);
    private JLabel setInterval = new JLabel("Set INTERVAL (minutes)");
    private JLabel setBreak = new JLabel("Set BREAK (minutes)");
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JTextField counter = new JTextField("25:00");
    
    public UserGUI()
    {
        setFrame();
    }
    
    private void setFrame()
    {
        setMenuBar(myFrame);
        
        Container contentPane = myFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        // N O R T H
        JPanel northPanel = new JPanel();
        contentPane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(titleLabel);
        
        // C E N T E R
        JPanel centerPanel = new JPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(4,1));
        centerPanel.add(counter);
        counter.setEditable(false);
        centerPanel.add(startButton);
        centerPanel.add(resetButton);
        
        // S O U T H
        JPanel southPanel = new JPanel();
        contentPane.add(southPanel, BorderLayout.SOUTH);
        centerPanel.setLayout(new GridLayout(4,1));        
        // add 2 sliders + 2 JTextFields + 2 JLabels
        
        // E A S T
        JPanel eastPanel = new JPanel();
        contentPane.add(eastPanel, BorderLayout.EAST);
        contentPane.setLayout(new GridLayout(2,1));
       
        // W E S T
        JPanel westPanel = new JPanel();
        contentPane.add(westPanel, BorderLayout.WEST);
        contentPane.setLayout(new GridLayout(2,1));        
        
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