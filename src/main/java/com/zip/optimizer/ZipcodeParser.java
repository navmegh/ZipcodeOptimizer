package com.zip.optimizer;

import com.zip.optimizer.model.ZipcodeRange;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ZipcodeParser {
    private final int LOWER_BOUND = 10000;
    private String zipcodeRangeString;

    public ZipcodeParser(String zipcodeRangeString) {
        this.zipcodeRangeString = zipcodeRangeString;
    }

    /***
     * Parses the zip code range string to get the lower & upper bound of each range
     * and create a list of corresponding ZipcodeRange object
     * @return List of ZipcodeRange objects
     * @throws ZipcodeException
     */
    public List<ZipcodeRange> parseZipcodeRanges() throws ZipcodeException {
        if(zipcodeRangeString == null || zipcodeRangeString.length() == 0) {
            throw new ZipcodeException("Input missing", ZipcodeException.ExceptionCode.INPTMSNG);
        }
        // get an array of ranges from the input string (stripping out any brackets)
        String[] zipcodeRanges = getZipcodeRanges(zipcodeRangeString);
        // parse string array of ranges into a list of ZipcodeRange objects
        List<ZipcodeRange> zipcodeRangeList = new LinkedList<ZipcodeRange>();
        for (int i = 0; i < zipcodeRanges.length; i++) {
            int[] zipRange = getValidatedZipcodeRanges(zipcodeRanges[i]);
            zipcodeRangeList.add(new ZipcodeRange(zipRange));
        }
        return zipcodeRangeList;
    }

    /***
     * Gets a String array with upper & lower bound strings from the given string representing a zip code range
     * @param rangeString - string representing a single zip code range
     * @return String array with lower and upper bounds of the range
     * @throws ZipcodeException
     */
    private String[] getZipcodeRanges(String rangeString) throws ZipcodeException {
        // check if ranges are properly formed
        long rangeStartCount = rangeString.chars().filter(ch -> ch =='[').count();
        long rangeEndCount = rangeString.chars().filter(ch -> ch ==']').count();
        if(rangeStartCount == 0 || rangeEndCount == 0 || rangeStartCount != rangeEndCount) {
            throw new ZipcodeException(rangeString, ZipcodeException.ExceptionCode.INPTINVLD);
        }
        //making sure that it is only the brackets that seggregate the ranges, not white spaces
        return rangeString.replaceAll("\\] \\[", "\\]|\\[").replaceAll("\\]\\[", "\\]|\\[").split("\\|");
    }

    /***
     * Get a ZipcodeRange object from the array with lower & upper bound of zip code strings
     * @param range
     * @return
     * @throws ZipcodeException
     */
    private int[] getValidatedZipcodeRanges(String range) throws ZipcodeException {
        String [] asRange = range.replaceAll("\\s+|\\[|\\]","").split(",");
        // Check if both upper & lower bounds are 5 digit numbers
        for (String bound: asRange) {
            if (bound.length() != 0 && !bound.matches("\\d{5}")){
                throw new ZipcodeException(bound, ZipcodeException.ExceptionCode.ZIPINVLD);
            }
        }
        // Check if both upper & lower bounds are present
        if(asRange.length != 2) {
            if(asRange.length == 0) {
                throw new ZipcodeException(range, ZipcodeException.ExceptionCode.NOBOUNDS);
            } else if(asRange.length == 1) {
                if(asRange[0].length() == 0) {
                    throw new ZipcodeException(zipcodeRangeString, ZipcodeException.ExceptionCode.INPTINVLD);
                } else if(asRange[0].charAt(0) == ',') {
                    throw new ZipcodeException(zipcodeRangeString, ZipcodeException.ExceptionCode.NOLBOUND);
                } else {
                    throw new ZipcodeException(zipcodeRangeString, ZipcodeException.ExceptionCode.NOUBOUND);
                }
            }
        } else {
            if(asRange[0].length() == 0) {
                throw new ZipcodeException(zipcodeRangeString, ZipcodeException.ExceptionCode.NOLBOUND);
            }
        }
        // get zip codes as integers checking the minimum value
        int lBound = getIntValue(asRange[0]);
        int uBound = getIntValue(asRange[1]);
        if(uBound < lBound) {
            throw new ZipcodeException(lBound + "-" + uBound, ZipcodeException.ExceptionCode.RNGINVLD);
        }
        return new int[]{lBound, uBound};
    }

    /***
     * Get the zip code as integer from the given string representation
     * @param value zip code as a string
     * @return integer value of the zip code
     * @throws ZipcodeException
     */
    private int getIntValue(String value) throws ZipcodeException{
        int retVal = 0;
        try {
            retVal =  Integer.parseInt(value);
            if(retVal < LOWER_BOUND) {
                throw new ZipcodeException(value, ZipcodeException.ExceptionCode.ZIPINVLD);
            }
        } catch (NumberFormatException ne) {
            throw new ZipcodeException(value, ZipcodeException.ExceptionCode.ZIPINVLD);
        }
        return retVal;
    }
}
