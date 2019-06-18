package com.zip.optimizer.model;

public class ZipcodeRange implements Comparable<ZipcodeRange> {
    private int rangeFrom;
    private int rangeTo;

    public ZipcodeRange(int rangeFrom, int rangeTo) {
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    public ZipcodeRange(int[] range) {
        this.rangeFrom = range[0];
        this.rangeTo = range[1];
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
    }

    @Override
    public String toString() {
        return "ZipcodeRange{" + "From=" + rangeFrom + ", To=" + rangeTo + '}';
    }

    @Override
    public int compareTo(ZipcodeRange zipcodeRange) {
        return this.rangeFrom - zipcodeRange.rangeFrom;
    }
}
