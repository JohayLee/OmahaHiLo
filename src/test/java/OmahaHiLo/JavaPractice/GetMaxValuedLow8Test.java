package OmahaHiLo.JavaPractice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class GetMaxValuedLow8Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GetMaxValuedLow8Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FlushHandCompareTest.class );
    }


    /**
     * Test filtering a hand of cards by low 8 ranking rule.
     */

    public void testGetMaxValuedLowMaxValued()
    {
    }

}
