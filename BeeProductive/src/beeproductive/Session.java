/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeproductive;

/**
 *
 * @author xxx
 */
public class Session 
{
    private int interval;
    private int breakTime;    
    
    public Session(int newInterval, int newBreakTime)
    {
        this.interval = newInterval;
        this.breakTime = newBreakTime;
    }
}


