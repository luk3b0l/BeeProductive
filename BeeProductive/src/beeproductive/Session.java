/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeproductive;

/**
 *
 * @author Lukasz Bol
 * @version 1.1
 */
public class Session 
{
    private int interval;
    private int breakTime;   
    private int repetitions;
    
    public Session(int newInterval, int newBreakTime, int newRepetition)
    {
        this.interval = newInterval;
        this.breakTime = newBreakTime;
        this.repetitions = newRepetition;
    }

    public int getInterval() 
    {
        return interval;
    }

    public int getBreakTime() 
    {
        return breakTime;
    }

    public int getRepetitions() 
    {
        return repetitions;
    }

    public void setInterval(int interval) 
    {
        this.interval = interval;
    }

    public void setBreakTime(int breakTime) 
    {
        this.breakTime = breakTime;
    }

    public void setRepetitions(int repetitions) 
    {
        this.repetitions = repetitions;
    }
}