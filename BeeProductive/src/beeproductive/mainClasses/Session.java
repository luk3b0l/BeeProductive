package beeproductive.mainClasses;

/**
 * This class provides a user session, in which they can set interval count, break and more.
 * @author Lukasz Bol
 * @version 1.1
 */
public class Session 
{
    private int countInterval;
    private int countBreakTime;   
    private int countRepetitions;
    private int countSessionNumber = 0;
    
    public Session(int newInterval, int newBreakTime, int newRepetition)
    {
        this.countSessionNumber += 1;
        this.countInterval = newInterval;
        this.countBreakTime = newBreakTime;
        this.countRepetitions = newRepetition;
    }

    public int getInterval() 
    {
        return countInterval;
    }

    public int getBreakTime() 
    {
        return countBreakTime;
    }

    public int getRepetitions() 
    {
        return countRepetitions;
    }

    public void setInterval(int newCountInterval) 
    {
        this.countInterval = newCountInterval;
    }

    public void setBreakTime(int newBreakTime) 
    {
        this.countBreakTime = newBreakTime;
    }

    public void setRepetitions(int countRepetitions) 
    {
        this.countRepetitions = countRepetitions;
    }
    
    public String toString()
    {
        String sessionInfo = "No: " + this.countSessionNumber + 
                   "Interval: " + this.countInterval + 
                   "Break: " + this.countBreakTime +
                   "Repetitions: " + this.countRepetitions;
        return sessionInfo;
    }           
}