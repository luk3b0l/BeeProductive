package beeproductive.mainClasses;

import java.util.ArrayList;

/**
 *
 * @author Lukasz Bol
 * Used Singleton pattern to create only one instance of the UsersDatabase class. 
 * This is because we want to have only one instance of the userProfiles' database.
 * The precedence will make sure that only one instance of the UserDatabase is accessed,
 * which decreases any confusion or errors that may arise later on while using the application.
 */
public class UsersDatabase 
{
    private static UsersDatabase theDatabaseInstance;
    private ArrayList<UserProfile> userProfiles;
    
    private UsersDatabase()
    {
        userProfiles = new ArrayList<>();
    }
    
    public UsersDatabase getInstance()
    {
        if(theDatabaseInstance == null)
        {
            theDatabaseInstance = new UsersDatabase();
        }
        return theDatabaseInstance;
    }
    
    public void addUser(UserProfile newUser)
    {
        userProfiles.add(newUser);
    }         
    
    public UserProfile findUser(String name)
    {
        for (UserProfile tempUserProfile : userProfiles)
        {
            if(tempUserProfile.getName().equals(name))
            {
                return tempUserProfile;
            }
        }
        return null;
    }
    
    public void removeUser(UserProfile existingUser)
    {
        userProfiles.remove(existingUser);
    }
    
    public String toString()
    {
        String allUsersInfo = "";
        for (UserProfile tempUserProfile : userProfiles)
        {
            allUsersInfo = allUsersInfo + tempUserProfile.toString() + "\n";
        }
        return allUsersInfo;
    }
}