package com.zip.optimizer;

import com.zip.optimizer.model.ZipcodeRange;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ZipcodeOptimizer {
    public List<ZipcodeRange> sortZipCodeRanges(List<ZipcodeRange> zipcodeRangeList) {
        Collections.sort(zipcodeRangeList);
        return zipcodeRangeList;
    }

    public List<ZipcodeRange> optimizeZipcodes(List<ZipcodeRange> sortedZipCodeList) {
        List<ZipcodeRange> optimizedZipcodeList = new LinkedList<ZipcodeRange>();
        ZipcodeRange zipcode = null;
        for (ZipcodeRange range : sortedZipCodeList) {
            if (zipcode == null)
                zipcode = range;
            else {
                if (zipcode.getRangeTo() >= range.getRangeFrom()) {
                    zipcode.setRangeTo(Math.max(zipcode.getRangeTo(), range.getRangeTo()));
                } else {
                    optimizedZipcodeList.add(zipcode);
                    zipcode = range;
                }
            }
        }
        optimizedZipcodeList.add(zipcode);
        return optimizedZipcodeList;
    }
}
