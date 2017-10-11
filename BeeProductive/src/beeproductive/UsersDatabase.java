package beeproductive;

import java.util.ArrayList;

/**
 *
 * @author Lukasz Bol
 * Used Singleton pattern to create only one instance of the UsersDatabase class. 
 * This is because we want to have only one instance of the users' database.
 * The precedence will make sure that only one instance of the UserDatabase is accessed,
 * which decreases any confusion or errors that may arise later on while using the application.
 */
public class UsersDatabase 
{
    private static UsersDatabase theDatabaseInstance;
    private ArrayList<UserProfile> users;
    
    private UsersDatabase()
    {
        users = new ArrayList<>();
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
        users.add(newUser);
    }         
    
    public UserProfile findUser(String name)
    {
        for (UserProfile temp : users)
        {
            if(temp.getName().equals(name))
            {
                return temp;
            }
        }
        return null;
    }
    
    public void removeUser(UserProfile existingUser)
    {
        users.remove(existingUser);
    }
    
    public String toString()
    {
        String s = "";
        for (UserProfile temp : users)
        {
            s = s + temp.toString() + "\n";
        }
        return s;
    }
}