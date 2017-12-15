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
    private boolean timerNewInterval = false;
    private boolean timerNewBreak = false;
    private Timer timer;
    private Timer breakTimer;
    
    private UserProfile user;
    private JFrame mainGUIFrame = new JFrame("Bee Productive");
    private JPanel tempPanel;

    private JLabel labelSetInterval = new JLabel("Interval:");
    private JLabel labelSetBreak = new JLabel("Break:");
    private JLabel labelSetRepetitiions = new JLabel("Repetitions: ");
    private JButton buttonNewSession = new JButton("New Session");
    private JButton buttonStart = new JButton("Start");
    private JButton buttonReset = new JButton("Reset");
    private JTextField outputCountdownTimer = new JTextField("", JTextField.CENTER);
    private JTextField inputIntervalTime = new JTextField("");
    private JTextField inputBreakTime = new JTextField("");
    private JTextField inputRepetitions = new JTextField("");
    private JSlider sliderInterval = new JSlider(1, 90, 1);
    private JSlider sliderBreak = new JSlider(1, 30, 1);
    private JSlider sliderRepetitions = new JSlider(1, 10, 1);
    
    public UserGUI()
    {
        setGUIFrame();
    }
    
    private void setGUIFrame()
    {        
        Container contentPane = mainGUIFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        Dimension preferredWindowSize = new Dimension(350, 400);
        mainGUIFrame.setPreferredSize(preferredWindowSize);
        setMenuBar(mainGUIFrame);
       
        // ***** N O R T H
        JPanel northPanel = new JPanel();
        contentPane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(buttonNewSession);
        
        // ***** C E N T E R
        JPanel centerPanel = new JPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcCenter = new GridBagConstraints();
        gcCenter.weightx = 0.5; gcCenter.weighty = 10;
        
        gcCenter.anchor = GridBagConstraints.SOUTH;
        gcCenter.gridx = 0; gcCenter.gridy = 0;
        centerPanel.add(outputCountdownTimer, gcCenter);
        outputCountdownTimer.setEditable(false);
        outputCountdownTimer.setPreferredSize(new Dimension(160,100));
        outputCountdownTimer.setFont(new Font("Serif", Font.BOLD, 65));
        
        gcCenter.anchor = GridBagConstraints.CENTER;
        gcCenter.gridx = 0; gcCenter.gridy = 1;
        centerPanel.add(buttonStart, gcCenter);
        
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
        southPanel.add(labelSetInterval, gcSouth);
        
        gcSouth.gridx = 0; gcSouth.gridy = 1;
        southPanel.add(labelSetBreak, gcSouth);
        
        gcSouth.gridx = 0; gcSouth.gridy = 2;
        southPanel.add(labelSetRepetitiions, gcSouth);
        
        // COLUMN 2:
        gcSouth.anchor = GridBagConstraints.LINE_START;
        gcSouth.gridx = 1; gcSouth.gridy = 0;
        southPanel.add(inputIntervalTime, gcSouth);
        inputIntervalTime.setPreferredSize(new Dimension(25,25));
        inputIntervalTime.setFont(new Font("Serif", Font.PLAIN, 20));
        inputIntervalTime.setEnabled(false);
        
        gcSouth.gridx = 1; gcSouth.gridy = 1;
        southPanel.add(inputBreakTime, gcSouth);
        inputBreakTime.setPreferredSize(new Dimension(25,25));
        inputBreakTime.setFont(new Font("Serif", Font.PLAIN, 20));
        inputBreakTime.setEnabled(false);

        
        gcSouth.gridx = 1; gcSouth.gridy = 2;
        southPanel.add(inputRepetitions, gcSouth);
        inputRepetitions.setPreferredSize(new Dimension(25,25));
        inputRepetitions.setFont(new Font("Serif", Font.PLAIN, 20));
        inputRepetitions.setEnabled(false);
        
        // COLUMN 3:
        gcSouth.anchor = GridBagConstraints.LINE_START;
        gcSouth.gridx = 2; gcSouth.gridy = 0;
        southPanel.add(sliderInterval, gcSouth);
        
        gcSouth.gridx = 2; gcSouth.gridy = 1;
        southPanel.add(sliderBreak, gcSouth);
        
        gcSouth.gridx = 2; gcSouth.gridy = 2;
        southPanel.add(sliderRepetitions, gcSouth);


        // ***** E A S T
        JPanel eastPanel = new JPanel();
        contentPane.add(eastPanel, BorderLayout.EAST);
        eastPanel.setLayout(new FlowLayout());
        eastPanel.add(buttonReset);
        
        
        // ***** Adding INDIVIDUALISED LISTENERS (event handlers):
        
        // Menu:
        buttonStart.addActionListener(new buttonStartHandler());
        buttonReset.addActionListener(new buttonResetHandler());
        buttonNewSession.addActionListener(new NewSessionButtonHandler());
        sliderInterval.addChangeListener(new sliderIntervalHandler());
        sliderBreak.addChangeListener(new sliderBreakHandler());
        sliderRepetitions.addChangeListener(new sliderRepetitionsHandler());

        initialState();     // setting initial state of the application
        mainGUIFrame.pack();
        mainGUIFrame.setVisible(true);
        mainGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGUIFrame.setLocationRelativeTo(null);    // setting the program in the centre of the screen
    }
    
    private void initialState()
    {
            labelSetInterval.setVisible(false);
            labelSetBreak.setVisible(false);
            labelSetRepetitiions.setVisible(false);
            buttonNewSession.setVisible(true);
            buttonStart.setVisible(false);
            buttonReset.setVisible(false);
            outputCountdownTimer.setVisible(false);    
            inputIntervalTime.setVisible(false);        
            inputBreakTime.setVisible(false);        
            inputRepetitions.setVisible(false);        
            sliderInterval.setVisible(false);        
            sliderBreak.setVisible(false);  
            sliderRepetitions.setVisible(false);
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
        newSessionItem.addActionListener(new NewSessionButtonHandler());
        
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
    
    private class NewSessionButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String name = JOptionPane.showInputDialog("Give your name: ");
            if(name != null & name.length() >= 3)
            {
                System.out.println("NAME: " + name);
                setUserProfile(name);
                 
                labelSetInterval.setVisible(true);
                labelSetBreak.setVisible(true);
                labelSetRepetitiions.setVisible(true);
                buttonNewSession.setVisible(false);
                buttonStart.setVisible(true);
                buttonReset.setVisible(true);
                outputCountdownTimer.setVisible(true);    
                inputIntervalTime.setVisible(true);        
                inputBreakTime.setVisible(true);        
                inputRepetitions.setVisible(true);        
                sliderInterval.setVisible(true);        
                sliderBreak.setVisible(true);      
                sliderRepetitions.setVisible(true);
                JPanel tempPanel = getPanel();
                tempPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
            }
            else
            {
                JOptionPane.showMessageDialog(mainGUIFrame, "No name added or name less than 3.", "ERROR Info", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class ExitHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int answer = JOptionPane.showConfirmDialog(mainGUIFrame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION); 
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
            JOptionPane.showMessageDialog(mainGUIFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(mainGUIFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // ---------------------------------------------------------------------------------------------------
    
    // ***** BUTTON HANDLERS:
    private class buttonStartHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int intervalTime = sliderInterval.getValue();
            int breakTime = sliderBreak.getValue();
            String repetitions = inputRepetitions.getText();
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
                outputCountdownTimer.setText(finalMinutes + ":" + finalSeconds);           
            }
            else
            {            
                timer.stop();
                Toolkit.getDefaultToolkit().beep();


                
                int breakTime = sliderBreak.getValue();
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
                outputCountdownTimer.setText(finalMinutes + ":" + finalSeconds);        
            }
            else
            {
                breakTimer.stop();
                Toolkit.getDefaultToolkit().beep();

            }                  
        }
    }
    

    
    private class buttonResetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            inputIntervalTime.setText("0");
            inputBreakTime.setText("0");
            inputRepetitions.setText("0");
            outputCountdownTimer.setText("--:--");
        }
    }
    // ---------------------------------------------------------------------------------------------------

    // ***** SLIDERS HANDLERS:
    private class sliderIntervalHandler implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            inputIntervalTime.setText("" + sliderInterval.getValue());
        }
    }
    
    private class sliderBreakHandler implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            inputBreakTime.setText("" + sliderBreak.getValue());
        }
    }
    
    private class sliderRepetitionsHandler implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            inputRepetitions.setText("" + sliderRepetitions.getValue());
        }
    }
    // ---------------------------------------------------------------------------------------------------
    public void sendPanel(JPanel panel)
    {
        this.tempPanel = panel;
    }
    
    public JPanel getPanel()
    {
        return this.tempPanel;
    }
    
    public void setUserProfile(String newName)
    {
        this.user = new UserProfile(newName);
    }
    
    public UserProfile getUserProfile()
    {
        return this.user;
    }    
}