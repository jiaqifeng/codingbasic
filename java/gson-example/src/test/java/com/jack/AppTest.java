package com.jack;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public void testParseJsonRule() {
        assertNotNull(RuleApp.parse("[{type=regex,regex=abc},{type=number,value=2}]"));
    }
    public void testParseJsonRule_TypeNotSupport() {
        assertNull(RuleApp.parse("[{type=regex,regex=abc},{type=number,value=2},{type=auto,value=2}]"));// auto not support
    }
    public void testParseJsonRule_WrongRuleField() {
        assertNull(RuleApp.parse("[{type=regex,regex=abc},{type=number,value=2},{type=number,regex=2}]"));//not legal for value is null
    }
    public void testParseJsonRule_NoRuleField() {
        assertNull(RuleApp.parse("[{type=regex,regex=abc},{type=number,value=2},{type=number}]"));// not legal for regex is null
    }
}
