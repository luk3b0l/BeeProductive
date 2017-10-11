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
    private ArrayList<Session> sessions;
    
    public UserProfile(String newName)
    {
        this.name = newName;
        sessions = new ArrayList<>();
    }

    public String getName() 
    {
        return name;
    }
    
    public void addSession(int interval, int breakTime, int repetitions)
    {
        Session temp = new Session(interval, breakTime, repetitions);
        sessions.add(temp);
    }
    
    private String getAllSessions()
    {
        String s = "";
        for (Session temp :  sessions)
        {
            s = s + temp.toString() + "\n";
        }
        return s;
    }
    
    public String toString()
    {
        String s = "NAME: " + name + 
                "ALL SESSIONS: " + getAllSessions();
        return s;
    }
}