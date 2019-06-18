package com.zip.optimizer;


import com.zip.optimizer.common.Utils;
import com.zip.optimizer.common.ZipcodeTestDataFactory;
import com.zip.optimizer.model.ZipcodeRange;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RangeSorterTest {

    private static ZipcodeTestDataFactory dataFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        dataFactory = new ZipcodeTestDataFactory();
    }

    @Test
    // Tests sorting of 5 pre-defined zip code ranges and comparing the results with expected output
    public void TestZipcodeRangeSorter() throws ZipcodeException {
        Utils.prettyPrintZipcodeRanges("MANUALLY GENERATED ZIP CODE RANGES SORT TEST",  "", null, false);
        for (int x = 1; x < 5; x++) {
            // get the test data
            Map<String, List<ZipcodeRange>> data = dataFactory.getZipcodeDataSetToSort(x);
            String inputZipcodesString = data.keySet().iterator().next();
            // run the test
            ZipcodeParser zipcodeParser = new ZipcodeParser(inputZipcodesString);
            List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
            Utils.prettyPrintZipcodeRanges(null,  x + ". INPUT ", zipCodeRangeList, false);
            ZipcodeOptimizer optimizer = new ZipcodeOptimizer();
            List<ZipcodeRange> sortedZipCodeRangeList = optimizer.sortZipCodeRanges(zipCodeRangeList);
            Utils.prettyPrintZipcodeRanges(null, "   OUTPUT", sortedZipCodeRangeList, true);

            // check the expected result
            Utils.checkExpectedResult(data.get(inputZipcodesString), sortedZipCodeRangeList);
        }
    }

    @Test
    // Tests sorting of randomly generated zip code ranges. Relies on visual/manual verification.
    public void TestZipcodeRangeSorter_random() throws ZipcodeException {
        int testCount = 5;
        Utils.prettyPrintZipcodeRanges("RANDOMLY GENERATED ZIP CODE RANGES SORT TEST (Manual Check)",  "", null, false);
        for (int x = 1; x < testCount + 1; x++) {
            int ranges = 0; // specify number of ranges, 0 will generate random number of ranges from 2 - 10
            // get the test data
            String data = dataFactory.getZipcodeDataSetToOptimize_random(ranges);
            // run the test
            ZipcodeParser zipcodeParser = new ZipcodeParser(data);
            List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
            Utils.prettyPrintZipcodeRanges(null,  x + ". INPUT ", zipCodeRangeList, false);
            ZipcodeOptimizer optimizer = new ZipcodeOptimizer();
            List<ZipcodeRange> sortedZipCodeRangeList = optimizer.sortZipCodeRanges(zipCodeRangeList);
            Utils.prettyPrintZipcodeRanges(null, "   OUTPUT", sortedZipCodeRangeList, true);

            // check the expected result
            assertTrue(sortedZipCodeRangeList.size() == zipCodeRangeList.size());
        }
    }

}
