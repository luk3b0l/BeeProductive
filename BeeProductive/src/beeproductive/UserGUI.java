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

    private JLabel setInterval = new JLabel("Set INTERVAL (minutes)");
    private JLabel setBreak = new JLabel("Set BREAK (minutes)");
    private JLabel setRepetitions = new JLabel("Number of repetitions (INTERVAL + BREAK)");
    private JLabel empty1 = new JLabel();
    private JLabel empty2 = new JLabel();
    private JLabel empty3 = new JLabel();
    private JLabel empty4 = new JLabel();
    private JLabel empty5 = new JLabel();
    private JLabel empty6 = new JLabel();
    private JLabel empty7 = new JLabel();
    private JLabel empty8 = new JLabel();
    private JLabel empty9 = new JLabel("                    ");
    private JLabel empty10 = new JLabel("                    ");
    private JButton newSessionButton = new JButton("New Session");
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JTextField countdownTimer = new JTextField("--:--", JTextField.CENTER);
    private JTextField intervalTimeInput = new JTextField(" ");
    private JTextField breakTimeInput = new JTextField(" ");
    private JTextField repetitionsInput = new JTextField(" ");
    private JSlider intervalSlider = new JSlider();
    private JSlider breakSlider = new JSlider();
    
    
    public UserGUI()
    {
        setFrame();
    }
    
    private void setFrame()
    {
        Container contentPane = myFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        myFrame.setSize(200, 400);
        setMenuBar(myFrame);
        
        
        
        
        // N O R T H
        JPanel northPanel = new JPanel();
        contentPane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(newSessionButton);
        
        // C E N T E R
        JPanel centerPanel = new JPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(5,1));
        centerPanel.add(empty6);
        centerPanel.add(countdownTimer);
        countdownTimer.setEditable(false);
        centerPanel.add(empty7);
        centerPanel.add(startButton);
        centerPanel.add(empty8);
        
        // S O U T H
        JPanel southPanel = new JPanel();
        contentPane.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new GridLayout(3,3));    
        southPanel.add(setInterval);
        southPanel.add(intervalTimeInput);
        southPanel.add(intervalSlider);
        southPanel.add(setBreak);
        southPanel.add(breakTimeInput);
        southPanel.add(breakSlider);
        southPanel.add(setRepetitions);
        southPanel.add(repetitionsInput);
        southPanel.add(empty1);
        
        // W E S T
        JPanel westPanel = new JPanel();
        contentPane.add(westPanel, BorderLayout.WEST);
        westPanel.setLayout(new GridLayout(1,2));
        westPanel.add(empty9);
        westPanel.add(empty10);
       
        // E A S T
        JPanel eastPanel = new JPanel();
        contentPane.add(eastPanel, BorderLayout.EAST);
        eastPanel.setLayout(new GridLayout(5,1));
        eastPanel.add(empty2);
        eastPanel.add(empty3);
        eastPanel.add(resetButton);
        eastPanel.add(empty4);
        eastPanel.add(empty5);
        
        
        // ***** Adding INDIVIDUALISED LISTENERS (event handlers):
        
        // Menu:
        startButton.addActionListener(new startButtonHandler());
        resetButton.addActionListener(new resetButtonHandler());
        newSessionButton.addActionListener(new newSessionHandler());

        initialState();
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    private void initialState()
    {
            setInterval.setVisible(false);
            setBreak.setVisible(false);
            setRepetitions.setVisible(false);
            newSessionButton.setVisible(true);
            startButton.setVisible(false);
            resetButton.setVisible(false);
            countdownTimer.setVisible(false);    
            intervalTimeInput.setVisible(false);        
            breakTimeInput.setVisible(false);        
            repetitionsInput.setVisible(false);        
            intervalSlider.setVisible(false);        
            breakSlider.setVisible(false);  
    }
    
    private void setMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // *** FILE menu:
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        JMenuItem newSessionItem = new JMenu("New session");
        fileMenu.add(newSessionItem);
        newSessionItem.addActionListener(new newSessionHandler());
        
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ExitHandler());                           // TESTING!
        // -----------------------------------------------
        
        // *** PROFILE menu:
        JMenu profileMenu = new JMenu("Profile");
        menubar.add(profileMenu);
        
        JMenuItem newItem = new JMenuItem("New");
        profileMenu.add(newItem);
        // TODO - add Action Listener

        JMenuItem loadItem = new JMenuItem("Load");
        profileMenu.add(loadItem);
        // TODO - add Action Listener

        JMenuItem editItem = new JMenuItem("Edit");
        profileMenu.add(editItem);
        // TODO - add Action Listener  
 
        JMenuItem deleteItem = new JMenuItem("Delete");
        profileMenu.add(deleteItem);
        // TODO - add Action Listener
        // -----------------------------------------------
        
        // *** HISTORY menu:
        JMenu historyMenu = new JMenu("History");
        menubar.add(historyMenu);
        
        JMenuItem showItem = new JMenuItem("Show");
        historyMenu.add(showItem);
        // TODO - add Action Listener
        // -----------------------------------------------
        
        
        // *** HELP menu:
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
    
    // ***** MENU HANDLERS:
    
    // File:
    // - New session
    // - Exit
    
    private class newSessionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Session newSession = new Session();
            setInterval.setVisible(true);
            setBreak.setVisible(true);
            setRepetitions.setVisible(true);
            newSessionButton.setVisible(false);
            startButton.setVisible(true);
            resetButton.setVisible(true);
            countdownTimer.setVisible(true);    
            intervalTimeInput.setVisible(true);        
            breakTimeInput.setVisible(true);        
            repetitionsInput.setVisible(true);        
            intervalSlider.setVisible(true);        
            breakSlider.setVisible(true);       
        }
    }
    
    private class ExitHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int answer = JOptionPane.showConfirmDialog(myFrame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION); 
            if(answer == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
    }  
    
    // Profile:
    // - New
    // - Load
    // - Edit
    // - Delete
    private class newProfileHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // TODO!
        }
        
    }
    
    private class loadProfileHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // TODO!
        }
        
    }    
    private class editProfileHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // TODO!
        }
        
    }
    private class deleteProfileHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // TODO!
        }
        
    }    
    
    // History:
    // - Show
   private class showHistoryHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // TODO!
        }
   }
        
    // Help:
    // - How to use
    // - About
    private class howToUseHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
         // TODO!
            String title = "How to use";
            String message = "\n<html><font size='5'>How to use BeeProductive ?</font></html>\n\n"
                             +"For the BeeProductive application to work, please follow these simple steps: \n"
                             + "1. set interval (in which you will be working without any disturbance)\n"
                             + "2. set break (in which you will have time for anything you want)\n"
                             + "3. set how many repetitions (interval + break) you want to have\n"
                             + "4. Press START button and enjoy working!";
            JOptionPane.showMessageDialog(myFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private class aboutHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // TODO!
            String title = "About";
            String message = "\n<html><font size='5'>BeeProductive</font></html>\n\n"
                            + "This application allows to improve daily productivity and helps to finish your tasks on time."
                            + "\n\nAuthor: Lukasz Bol"
                            + "\nContact: lukaszbol@yahoo.co.uk"
                            + "\nVersion: 1.1  [October 2017]";
            JOptionPane.showMessageDialog(myFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // ---------------------------------------------------------------------------------------------------
    
    // ***** BUTTON HANDLERS:
    private class startButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String intervalTime = intervalTimeInput.getText();
            int intervalTimeNum = Integer.parseInt(intervalTime);        // converted for further countdown
            int breakTime = Integer.parseInt(breakTimeInput.getText());
            String repetitions = repetitionsInput.getText();
            int repetitionsNum = Integer.parseInt(repetitions);
            System.out.println("Interval: " + intervalTime);
            System.out.println("Break: " + breakTime);
            System.out.println("Repetitions: " + repetitions);

            countdownTimer.setText(intervalTime);
        }
    }

    private class resetButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            intervalTimeInput.setText("0");
            breakTimeInput.setText("0");
            repetitionsInput.setText("0");
            countdownTimer.setText("--:--");
        }
    }
    // ---------------------------------------------------------------------------------------------------
    
    public static void main(String [] args)
    {
        new UserGUI();
    }
}