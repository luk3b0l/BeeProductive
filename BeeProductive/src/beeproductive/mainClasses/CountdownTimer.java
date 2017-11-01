/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeproductive.mainClasses;

import java.text.NumberFormat;
import javax.swing.Timer;
/**
 *
 * @author Lukasz Bol
 */
public class CountdownTimer 
{
    private int intervalTime;
    private int breakTime;
    private Timer timer;
    private long lastTimeUpdate;    // when count was last updated
    
    private long remainder; // to see how many milliseconds remained in the countdown
    private NumberFormat format; // for formatting minutes and seconds 00:00
    // add audio sound
    
    public CountdownTimer(int newInterval, int newBreak)
    {
        this.intervalTime = newInterval;
        this.breakTime = newBreak;
        startCountdown(intervalTime, breakTime);        
    }    
    
    public void startCountdown(int newIntervalTime, int newBreakTime)
    {
        format = NumberFormat.getNumberInstance();
        format.setMinimumIntegerDigits(2);
        
        if(newIntervalTime > 0)
        {
            remainder = newIntervalTime * 60000;
        }
        else
        {
            remainder = 600000; // 10 minutes (by default)
        }
    }
    
    public void start()
    {
        resume();
    }
    
    public void stop()
    {
        pause();
    }
    // To START or RESUME the countdown
    public void resume()
    {
        lastTimeUpdate = System.currentTimeMillis();    // gives a current time in ms
        timer.start();
    }
    
    // To PAUSE the countdown
    public void pause()
    {
        // Subtract elapsed time from the remaining time and STOP TIMING!
        long timeNow = System.currentTimeMillis();
        remainder = remainder - (timeNow - lastTimeUpdate);
        timer.stop();
    }
    
    public void updateDisplay()
    {
        long timeNow = System.currentTimeMillis();
        long elapsed = timeNow - lastTimeUpdate;
        remainder = remainder - elapsed;
        lastTimeUpdate = timeNow;
        
        //Converting remaining miliseconds to mm:ss format and display it
        if(remainder < 0)
        {
            remainder = 0;
        }
        int minutes = (int)(remainder/60000);
        int seconds = (int)((remainder)/1000);
        
        if(remainder == 0)
        {
            timer.stop();
            System.out.println("INTERVAL COMPLETED");
//            if(sound != null)
//            {
//                sound.play();
//            }
        }
    }  
}