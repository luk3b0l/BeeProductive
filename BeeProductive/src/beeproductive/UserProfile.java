/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeproductive;

import java.util.ArrayList;

/**
 *
 * @author Lukasz Bol
 * @version 1.1
 */
public class UserProfile 
{
    private String name;
    private ArrayList<Session> session;
    
    public UserProfile(String newName)
    {
        this.name = newName;
        session = new ArrayList<>();
    }

    public String getName() 
    {
        return name;
    }
}
