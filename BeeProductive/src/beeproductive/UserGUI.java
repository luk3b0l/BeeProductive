/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeproductive;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author xxx
 */
public class UserGUI 
{
    private JFrame myFrame = new JFrame();
    
    public UserGUI()
    {
        Container contentPane = myFrame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        setMenuBar();

        
        JLabel helloLabel = new JLabel("Hello");
        contentPane.add(helloLabel);
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    public void setMenuBar()
    {
        JMenuBar menubar = new JMenuBar();
        myFrame.setJMenuBar(menubar);
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        JMenu aboutMenu = new JMenu("About");
        menubar.add(aboutMenu);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
    }
}
