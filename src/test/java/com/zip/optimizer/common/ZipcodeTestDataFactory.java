package com.zip.optimizer.common;

import com.zip.optimizer.model.ZipcodeRange;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ZipcodeTestDataFactory {

    // Static zip codes for test data generation
    HashMap<Integer, String> masterDataSet = new HashMap<Integer, String>();

    public ZipcodeTestDataFactory() {
        masterDataSet.put(1, "10000");
        masterDataSet.put(2, "11000");
        masterDataSet.put(3, "11100");
        masterDataSet.put(4, "11110");
        masterDataSet.put(5, "11111");
        masterDataSet.put(6, "20000");
        masterDataSet.put(7, "22000");
        masterDataSet.put(8, "22200");
        masterDataSet.put(9, "22220");
        masterDataSet.put(10, "22222");
        masterDataSet.put(11, "30000");
        masterDataSet.put(12, "33000");
        masterDataSet.put(13, "33300");
        masterDataSet.put(14, "33330");
        masterDataSet.put(15, "33333");
        masterDataSet.put(16, "1000");
        masterDataSet.put(17, "-1000");
        masterDataSet.put(18, " 111 ");
        masterDataSet.put(19, "444");
        masterDataSet.put(20, "11r40");
    }


    /*********************************************************************************************************
     * TEST DATA FOR INPUT VALIDATION
     ********************************************************************************************************/
    // Data for testing blank input
    public String getEmptyZipcodeDataSet() {
        return "[,] []" + "|" + "[,]";
    }

    // Data for testing input with incorrect digit zip codes
    public String getInvalidZipcodeDataSet_wrongLength() {
        return Utils.buildTestDataString(masterDataSet.get(16).trim() + "", masterDataSet.get(16), masterDataSet.get(8));
    }

    // Data for testing input with white spaces in zip codes
    public String getInvalidZipcodeDataSet_spaces() {
        return Utils.buildTestDataString(masterDataSet.get(18).trim(), masterDataSet.get(18), masterDataSet.get(8));
    }

    // Data for testing non-numeric zip codes
    public String getInvalidZipcodeDataSet_nonNumeric() {
        return Utils.buildTestDataString(masterDataSet.get(20).trim() + "", masterDataSet.get(20), masterDataSet.get(8));
    }

    // Data with zip code range where lower bound is greater than upper bound
    public String getInvalidZipcodeDataSet_invalidRange() {
        return Utils.buildTestDataString(masterDataSet.get(7).trim() + "-" + masterDataSet.get(6), masterDataSet.get(7), masterDataSet.get(6));
    }

    // Valid zip code range data
    public Map<String, List<ZipcodeRange>> getValidZipcodeDataSet() {
        List<ZipcodeRange> expectedResult = Utils.getExpectedResult(masterDataSet.get(1), masterDataSet.get(2), masterDataSet.get(3), masterDataSet.get(3));
        String inputData = Utils.buildTestDataString(null, masterDataSet.get(1), masterDataSet.get(2), masterDataSet.get(3), masterDataSet.get(3));
        Map<String, List<ZipcodeRange>> data = new HashMap<String, List<ZipcodeRange>>();
        data.put(inputData, expectedResult);
        return data;
    }

    /*********************************************************************************************************
     * TEST DATA FOR ZIP CODE RANGE SORTER
     ********************************************************************************************************/
    // TEst data with valid zip code ranges of varying lengths
    public Map<String, List<ZipcodeRange>> getZipcodeDataSetToSort(int count) {
        List<ZipcodeRange> expectedResult = Utils.getExpectedResult(masterDataSet.get(4), masterDataSet.get(5), masterDataSet.get(9), masterDataSet.get(10),masterDataSet.get(14), masterDataSet.get(15));
        String inputData = null;
        if(count == 1){
            inputData = Utils.buildTestDataString(null, masterDataSet.get(14), masterDataSet.get(15), masterDataSet.get(9),masterDataSet.get(10),masterDataSet.get(4), masterDataSet.get(5));
        } else if(count == 2){
            inputData = Utils.buildTestDataString(null, masterDataSet.get(9),masterDataSet.get(10),masterDataSet.get(14), masterDataSet.get(15), masterDataSet.get(4), masterDataSet.get(5));
        } else if(count == 3){
            inputData = Utils.buildTestDataString(null, masterDataSet.get(4),masterDataSet.get(5),masterDataSet.get(14), masterDataSet.get(15), masterDataSet.get(9), masterDataSet.get(10));
        } else if(count == 4){
            inputData = Utils.buildTestDataString(null, masterDataSet.get(4),masterDataSet.get(5),masterDataSet.get(14), masterDataSet.get(15), masterDataSet.get(9), masterDataSet.get(10),
                    masterDataSet.get(2),masterDataSet.get(3),masterDataSet.get(10), masterDataSet.get(11));
            expectedResult = Utils.getExpectedResult(masterDataSet.get(2),masterDataSet.get(3), masterDataSet.get(4), masterDataSet.get(5), masterDataSet.get(9), masterDataSet.get(10),
                    masterDataSet.get(10), masterDataSet.get(11),masterDataSet.get(14), masterDataSet.get(15));
        }
        Map<String, List<ZipcodeRange>> data = new HashMap<String, List<ZipcodeRange>>();
        data.put(inputData, expectedResult);
        return data;
    }

    /*********************************************************************************************************
     * TEST DATA FOR ZIP CODE RANGE OPTIMIZER
     ********************************************************************************************************/
    // Data with contiguous zip code ranges
    public Map<String, List<ZipcodeRange>> getZipcodeDataSetToOptimize_contiguous() {
        List<ZipcodeRange> expectedResult = Utils.getExpectedResult(masterDataSet.get(1), masterDataSet.get(3), masterDataSet.get(4),masterDataSet.get(6),masterDataSet.get(7), masterDataSet.get(10));
        String inputData = Utils.buildTestDataString(null, masterDataSet.get(1), masterDataSet.get(3), masterDataSet.get(4),masterDataSet.get(6),masterDataSet.get(7), masterDataSet.get(10));
        Map<String, List<ZipcodeRange>> data = new HashMap<String, List<ZipcodeRange>>();
        data.put(inputData, expectedResult);
        return data;
    }

    // Data with overlapping zip code ranges
    public Map<String, List<ZipcodeRange>> getZipcodeDataSetToOptimize_overlapping() {
        List<ZipcodeRange> expectedResult = Utils.getExpectedResult(masterDataSet.get(1), masterDataSet.get(10));
        String inputData = Utils.buildTestDataString(null, masterDataSet.get(1), masterDataSet.get(3), masterDataSet.get(3),masterDataSet.get(6),masterDataSet.get(6), masterDataSet.get(10));
        Map<String, List<ZipcodeRange>> data = new HashMap<String, List<ZipcodeRange>>();
        data.put(inputData, expectedResult);
        return data;
    }

    // Data with zip code ranges that completely include other ranges
    public Map<String, List<ZipcodeRange>> getZipcodeDataSetToOptimize_inclusive() {
        List<ZipcodeRange> expectedResult = Utils.getExpectedResult(masterDataSet.get(1), masterDataSet.get(8));
        String inputData = Utils.buildTestDataString(null, masterDataSet.get(1), masterDataSet.get(6), masterDataSet.get(2),masterDataSet.get(5),masterDataSet.get(4), masterDataSet.get(8));
        Map<String, List<ZipcodeRange>> data = new HashMap<String, List<ZipcodeRange>>();
        data.put(inputData, expectedResult);
        return data;
    }

    // Data with rangomly generated zip code ranges of varying lengths
    public String getZipcodeDataSetToOptimize_random(int count) {
        return Utils.getRandomZipcodes(count);
    }
}
