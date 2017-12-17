package beeproductive.mainClasses;

import java.util.ArrayList;

/**
 * This class is for creating a user profile, together with their listOfSessions' data.
 * @author Lukasz Bol
 * @version 1.1
 */
public class UserProfile 
{
    private String name;
    private ArrayList<Session> listOfSessions;
    
    public UserProfile(String newName)
    {
        this.name = newName;
        listOfSessions = new ArrayList<>();
    }

    public String getName() 
    {
        return name;
    }
    
    public void addSession(int newIinterval, int newBreakTime, int newRepetitions)
    {
        Session temp = new Session(newIinterval, newBreakTime, newRepetitions);
        listOfSessions.add(temp);
    }
    
    private String getAllSessions()
    {
        String allUserSessionsInfo = "";
        for (Session tempSession :  listOfSessions)
        {
            allUserSessionsInfo = allUserSessionsInfo + tempSession.toString() + "\n";
        }
        return allUserSessionsInfo;
    }
    
    public String toString()
    {
        String userProfileInfo = "NAME: " + this.name + 
                "ALL SESSIONS: " + getAllSessions();
        return userProfileInfo;
    }
}