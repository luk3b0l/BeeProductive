package beeproductive.gui;

import beeproductive.mainClasses.UserProfile;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;

/**
 *
 * @author Lukasz Bol
 * @version 1.1
 */

public class UserGUI
{   
    private boolean newIntervalTimer = false;
    private boolean newBreakTimer = false;
    private Timer timer;
    private Timer breakTimer;
    
    private UserProfile user;
    private JFrame myFrame = new JFrame("Bee Productive");
    private JPanel tempPanel;

    private JLabel setInterval = new JLabel("Interval:");
    private JLabel setBreak = new JLabel("Break:");
    private JLabel setRepetitions = new JLabel("Repetitions: ");
    private JButton newSessionButton = new JButton("New Session");
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JTextField countdownTimer = new JTextField("", JTextField.CENTER);
    private JTextField intervalTimeInput = new JTextField("");
    private JTextField breakTimeInput = new JTextField("");
    private JTextField repetitionsInput = new JTextField("");
    private JSlider intervalSlider = new JSlider(1, 90, 1);
    private JSlider breakSlider = new JSlider(1, 30, 1);
    private JSlider repetitionSlider = new JSlider(1, 10, 1);
    
    public UserGUI()
    {
        setFrame();
    }
    
    private void setFrame()
    {        
        Container contentPane = myFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        Dimension preferredSize = new Dimension(350, 400);
        myFrame.setPreferredSize(preferredSize);
        setMenuBar(myFrame);
       
        // ***** N O R T H
        JPanel northPanel = new JPanel();
        contentPane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(newSessionButton);
        
        // ***** C E N T E R
        JPanel centerPanel = new JPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcCenter = new GridBagConstraints();
        gcCenter.weightx = 0.5; gcCenter.weighty = 10;
        
        gcCenter.anchor = GridBagConstraints.SOUTH;
        gcCenter.gridx = 0; gcCenter.gridy = 0;
        centerPanel.add(countdownTimer, gcCenter);
        countdownTimer.setEditable(false);
        countdownTimer.setPreferredSize(new Dimension(160,100));
        countdownTimer.setFont(new Font("Serif", Font.BOLD, 65));
        
        gcCenter.anchor = GridBagConstraints.CENTER;
        gcCenter.gridx = 0; gcCenter.gridy = 1;
        centerPanel.add(startButton, gcCenter);
        
        gcCenter.weighty = 7; 
        gcCenter.gridx = 0; gcCenter.gridy = 2;
        centerPanel.add(new JLabel(""), gcCenter);
        
        // ***** S O U T H
        JPanel southPanel = new JPanel();
        contentPane.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new GridBagLayout());
        sendPanel(southPanel);
        GridBagConstraints gcSouth = new GridBagConstraints();
        gcSouth.weightx = 0.5; gcSouth.weighty = 0.5;        
        
        // COLUMN 1:
        gcSouth.anchor = GridBagConstraints.LINE_START;
        gcSouth.gridx = 0; gcSouth.gridy = 0;
        southPanel.add(setInterval, gcSouth);
        
        gcSouth.gridx = 0; gcSouth.gridy = 1;
        southPanel.add(setBreak, gcSouth);
        
        gcSouth.gridx = 0; gcSouth.gridy = 2;
        southPanel.add(setRepetitions, gcSouth);
        
        // COLUMN 2:
        gcSouth.anchor = GridBagConstraints.LINE_START;
        gcSouth.gridx = 1; gcSouth.gridy = 0;
        southPanel.add(intervalTimeInput, gcSouth);
        intervalTimeInput.setPreferredSize(new Dimension(25,25));
        intervalTimeInput.setFont(new Font("Serif", Font.PLAIN, 20));
        intervalTimeInput.setEnabled(false);
        
        gcSouth.gridx = 1; gcSouth.gridy = 1;
        southPanel.add(breakTimeInput, gcSouth);
        breakTimeInput.setPreferredSize(new Dimension(25,25));
        breakTimeInput.setFont(new Font("Serif", Font.PLAIN, 20));
        breakTimeInput.setEnabled(false);

        
        gcSouth.gridx = 1; gcSouth.gridy = 2;
        southPanel.add(repetitionsInput, gcSouth);
        repetitionsInput.setPreferredSize(new Dimension(25,25));
        repetitionsInput.setFont(new Font("Serif", Font.PLAIN, 20));
        repetitionsInput.setEnabled(false);
        
        // COLUMN 3:
        gcSouth.anchor = GridBagConstraints.LINE_START;
        gcSouth.gridx = 2; gcSouth.gridy = 0;
        southPanel.add(intervalSlider, gcSouth);
        
        gcSouth.gridx = 2; gcSouth.gridy = 1;
        southPanel.add(breakSlider, gcSouth);
        
        gcSouth.gridx = 2; gcSouth.gridy = 2;
        southPanel.add(repetitionSlider, gcSouth);


        // ***** E A S T
        JPanel eastPanel = new JPanel();
        contentPane.add(eastPanel, BorderLayout.EAST);
        eastPanel.setLayout(new FlowLayout());
        eastPanel.add(resetButton);
        
        
        // ***** Adding INDIVIDUALISED LISTENERS (event handlers):
        
        // Menu:
        startButton.addActionListener(new startButtonHandler());
        resetButton.addActionListener(new resetButtonHandler());
        newSessionButton.addActionListener(new newSessionHandler());
        intervalSlider.addChangeListener(new intervalSliderHandler());
        breakSlider.addChangeListener(new breakSliderHandler());
        repetitionSlider.addChangeListener(new repetitionSliderHandler());

        initialState();     // setting initial state of the application
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);    // setting the program in the centre of the screen
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
            repetitionSlider.setVisible(false);
    }
    
    private void setMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // *** FILE menu:
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        JMenuItem newSessionItem = new JMenuItem("New session");
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
            String name = JOptionPane.showInputDialog("Give your name: ");
            System.out.println("NAME: " + name);
            setUserProfile(name);
            
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
            repetitionSlider.setVisible(true);
            JPanel tempPanel = getPanel();
            tempPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
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
            int intervalTime = intervalSlider.getValue();
            int breakTime = breakSlider.getValue();
            String repetitions = repetitionsInput.getText();
            int repetitionsNum = Integer.parseInt(repetitions);     
            System.out.println("TEST 1");

            
            // =================================================================


                

                    TimeClass intervalTimeCounter = new TimeClass(intervalTime);
                    timer = new Timer(500, intervalTimeCounter);
                    timer.start();  





         }
    }
    
    public class TimeClass implements ActionListener
    {
        int newInterval;
        long millis;

        public TimeClass(int intervalTime)
        {
            this.newInterval = (intervalTime * 60) + 1;
            System.out.println("TimeClass constructor");
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            this.newInterval -= 1;
            int intervalToSeconds = this.newInterval;
            int calculateMinutes = intervalToSeconds / 60;
            int calculateSeconds = intervalToSeconds % 60;
            String finalMinutes = Integer.toString(calculateMinutes);
            String finalSeconds = Integer.toString(calculateSeconds);

            if(calculateSeconds < 10)
            {
                finalSeconds = "0" + finalSeconds;
            }            
            if(intervalToSeconds >= 0)
            {   
                System.out.println("Check: interval");
                countdownTimer.setText(finalMinutes + ":" + finalSeconds);           
            }
            else
            {            
                timer.stop();
                Toolkit.getDefaultToolkit().beep();


                
                int breakTime = breakSlider.getValue();
                BreakTimeClass breakTimeCounter = new BreakTimeClass(breakTime);
                breakTimer = new Timer(500, breakTimeCounter);
                breakTimer.start();
            }
        }
    }
    
    public class BreakTimeClass implements ActionListener
    {
        int newBreak;
        long millis;

        public BreakTimeClass(int breakTime)
        {
            this.newBreak = (breakTime * 60) + 1;
            System.out.println("BreakTimeClass constructor");
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            this.newBreak -= 1;
            int breakToSeconds = this.newBreak;
            int calculateMinutes = breakToSeconds / 60;
            int calculateSeconds = breakToSeconds % 60;
            String finalMinutes = Integer.toString(calculateMinutes);
            String finalSeconds = Integer.toString(calculateSeconds);

            if(calculateSeconds < 10)
            {
                finalSeconds = "0" + finalSeconds;
            }

            if(breakToSeconds >= 0)
            {
                System.out.println("Check: break");
                countdownTimer.setText(finalMinutes + ":" + finalSeconds);        
            }
            else
            {
                breakTimer.stop();
                Toolkit.getDefaultToolkit().beep();

            }                  
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

    // ***** SLIDERS HANDLERS:
    private class intervalSliderHandler implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            intervalTimeInput.setText("" + intervalSlider.getValue());
        }
    }
    
    private class breakSliderHandler implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            breakTimeInput.setText("" + breakSlider.getValue());
        }
    }
    
    private class repetitionSliderHandler implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            repetitionsInput.setText("" + repetitionSlider.getValue());
        }
    }
    // ---------------------------------------------------------------------------------------------------
 
    // ***** Additional methods:
    
    public void sendPanel(JPanel panel)
    {
        this.tempPanel = panel;
    }
    
    public JPanel getPanel()
    {
        return this.tempPanel;
    }
    
    public void setUserProfile(String name)
    {
        this.user = new UserProfile(name);
    }
    
    public UserProfile getUserProfile()
    {
        return this.user;
    }    
    // ---------------------------------------------------------------------------------------------------

    public static void main(String [] args)
    {
        new UserGUI();
    }
}