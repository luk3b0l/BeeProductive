package beeproductive.mainClasses;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lukasz Bol
 */
public class SessionTest 
{
    private Session testSession = null;
    
    @BeforeClass
    public static void setUpClass(){}
    
    @AfterClass
    public static void tearDownClass(){}
    
    @Before
    public void setUp(){}
    
    @After
    public void tearDown(){}     
    
    @Test
    public void testSetupSession()
    {
        testSession = new Session(25, 5, 3);
        Assert.assertNotNull(testSession);
    }
    
}
