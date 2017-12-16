package beeproductive.mainClasses;

/**
 * This class provides a user session, in which they can set interval count, break and more.
 * @author Lukasz Bol
 * @version 1.1
 */
public class Session 
{
    private int countInterval;
    private int breakTime;   
    private int countRepetitions;
    private int countSessionNumber = 0;
    
    public Session(int newInterval, int newBreakTime, int newRepetition)
    {
        this.countSessionNumber += 1;
        this.countInterval = newInterval;
        this.breakTime = newBreakTime;
        this.countRepetitions = newRepetition;
    }

    public int getInterval() 
    {
        return countInterval;
    }

    public int getBreakTime() 
    {
        return breakTime;
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
        this.breakTime = newBreakTime;
    }

    public void setRepetitions(int countRepetitions) 
    {
        this.countRepetitions = countRepetitions;
    }
    
    public String toString()
    {
        String s = "No: " + countSessionNumber + 
                   "Interval: " + countInterval + 
                   "Break: " + breakTime +
                   "Repetitions: " + countRepetitions;
        return s;
    }           
}