package com.zip.optimizer;

import com.zip.optimizer.common.Utils;
import com.zip.optimizer.common.ZipcodeTestDataFactory;
import com.zip.optimizer.model.ZipcodeRange;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RangeOptimizerTest {

    private static ZipcodeTestDataFactory dataFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        dataFactory = new ZipcodeTestDataFactory();
    }

    @Test
    // Test case to test Zip Code Optimizer operation with contiguous ranges
    public void getZipcodeDataSetToOptimize_contiguous() throws ZipcodeException {
        // get the test data
        Map<String, List<ZipcodeRange>> data = dataFactory.getZipcodeDataSetToOptimize_contiguous();
        String inputZipcodesString = data.keySet().iterator().next();

        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(inputZipcodesString);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
        Utils.prettyPrintZipcodeRanges("OPTIMIZING CONTIGUOUS RANGES", "  INPUT ", zipCodeRangeList, false);
        ZipcodeOptimizer optimizer = new ZipcodeOptimizer();
        List<ZipcodeRange> sortedZipCodeRangeList = optimizer.sortZipCodeRanges(zipCodeRangeList);
        Utils.prettyPrintZipcodeRanges(null, "  SORTED", sortedZipCodeRangeList, false);
        List<ZipcodeRange> optimizedZipCodeRangeList = optimizer.optimizeZipcodes(sortedZipCodeRangeList);
        Utils.prettyPrintZipcodeRanges(null, "  OUTPUT", optimizedZipCodeRangeList, false);

        // check the expected result
        Utils.checkExpectedResult(data.get(inputZipcodesString), optimizedZipCodeRangeList);
    }

    @Test
    // Test case to test Zip Code Optimizer operation with overlapping ranges
    public void getZipcodeDataSetToOptimize_overlapping() throws ZipcodeException {
        // get the test data
        Map<String, List<ZipcodeRange>> data = dataFactory.getZipcodeDataSetToOptimize_overlapping();
        String inputZipcodesString = data.keySet().iterator().next();

        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(inputZipcodesString);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
        Utils.prettyPrintZipcodeRanges("OPTIMIZING OVERLAPPING RANGES","  INPUT ", zipCodeRangeList, false);
        ZipcodeOptimizer optimizer = new ZipcodeOptimizer();
        List<ZipcodeRange> sortedZipCodeRangeList = optimizer.sortZipCodeRanges(zipCodeRangeList);
        Utils.prettyPrintZipcodeRanges(null, "  SORTED", sortedZipCodeRangeList, false);
        List<ZipcodeRange> optimizedZipCodeRangeList = optimizer.optimizeZipcodes(sortedZipCodeRangeList);
        Utils.prettyPrintZipcodeRanges(null,"  OUTPUT", optimizedZipCodeRangeList, false);

        // check the expected result
        Utils.checkExpectedResult(data.get(inputZipcodesString), optimizedZipCodeRangeList);
    }


    @Test
    // Test case to test Zip Code Optimizer operation with some ranges inclusive of other
    public void TestZipcodeOptimizer_inclusive() throws ZipcodeException {
        // get the test data
        Map<String, List<ZipcodeRange>> data = dataFactory.getZipcodeDataSetToOptimize_inclusive();
        String inputZipcodesString = data.keySet().iterator().next();

        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(inputZipcodesString);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
        Utils.prettyPrintZipcodeRanges("OPTIMIZING INCLUSIVE RANGES","  INPUT ", zipCodeRangeList, false);
        ZipcodeOptimizer optimizer = new ZipcodeOptimizer();
        List<ZipcodeRange> sortedZipCodeRangeList = optimizer.sortZipCodeRanges(zipCodeRangeList);
        Utils.prettyPrintZipcodeRanges(null,"  SORTED", sortedZipCodeRangeList, false);
        List<ZipcodeRange> optimizedZipCodeRangeList = optimizer.optimizeZipcodes(sortedZipCodeRangeList);
        Utils.prettyPrintZipcodeRanges(null,"  OUTPUT", optimizedZipCodeRangeList, false);

        // check the expected result
        Utils.checkExpectedResult(data.get(inputZipcodesString), optimizedZipCodeRangeList);
    }

    @Test
    /***
     * Test cases to test Zip Code Optimizer operation with randomly generated ranges.
     * Length of the ranges is determined by the value passed to  getZipcodeDataSetToOptimize_random function
     * If 0 is specified, it creates input with rangem number of ranges between 2 - 10.
     */
    public void TestZipcodeOptimizer_random() throws ZipcodeException {
        int testCount = 5;
        Utils.prettyPrintZipcodeRanges("OPTIMIZING RANDOMLY GENERATED ZIP CODE RANGES WITH VARIABLE LENGTH",  "", null, false);
        for(int i = 1; i < testCount+1; i++){
            int ranges = 0; // specify number of ranges, 0 will generate random number of ranges from 2 - 10
            // get the test data
            String data = dataFactory.getZipcodeDataSetToOptimize_random(ranges);
            // run the test
            ZipcodeParser zipcodeParser = new ZipcodeParser(data);
            List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
            Utils.prettyPrintZipcodeRanges(null,i + ". INPUT ", zipCodeRangeList, false);
            ZipcodeOptimizer optimizer = new ZipcodeOptimizer();
            List<ZipcodeRange> sortedZipCodeRangeList = optimizer.sortZipCodeRanges(zipCodeRangeList);
            Utils.prettyPrintZipcodeRanges(null, "   SORTED", sortedZipCodeRangeList, false);
            List<ZipcodeRange> optimizedZipCodeRangeList = optimizer.optimizeZipcodes(sortedZipCodeRangeList);
            Utils.prettyPrintZipcodeRanges(null, "   OUTPUT", optimizedZipCodeRangeList, true);

            // check the expected result
            assertTrue(optimizedZipCodeRangeList.size() > 0);
        }
    }
}
