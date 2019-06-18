package com.zip.optimizer;


import com.zip.optimizer.common.Utils;
import com.zip.optimizer.common.ZipcodeTestDataFactory;
import com.zip.optimizer.model.ZipcodeRange;
import org.junit.Rule;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;

public class RangeParserTest {

    private static ZipcodeTestDataFactory dataFactory;
    static final String SINGLE_QUOTE_START = "'";
    static final String SINGLE_QUOTE_END = "' - ";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUp() throws Exception
    {
        dataFactory = new ZipcodeTestDataFactory();
    }

    @Test
    public void TestEmptyInput() throws ZipcodeException {
        // get the test data
        String[] data = dataFactory.getEmptyZipcodeDataSet().split("\\|");
        ZipcodeException.ExceptionCode exCode = ZipcodeException.ExceptionCode.NOBOUNDS;
        // expected exception and message
        thrown.expect(ZipcodeException.class);
        thrown.expectMessage(SINGLE_QUOTE_START + data[1] + SINGLE_QUOTE_END + exCode.getExceptionMessage());
        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(data[0]);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
    }

    @Test
    public void TestInputWithInvalidZip() throws ZipcodeException {
        // get the test data
        String[] data = dataFactory.getInvalidZipcodeDataSet_wrongLength().split("\\|");
        ZipcodeException.ExceptionCode exCode = ZipcodeException.ExceptionCode.ZIPINVLD;
        // expected exception and message
        thrown.expect(ZipcodeException.class);
        thrown.expectMessage(SINGLE_QUOTE_START + data[1] + SINGLE_QUOTE_END + exCode.getExceptionMessage());
        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(data[0]);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
    }

    @Test //(expected = ZipcodeException.class)
    public void TestInputWithWhiteSpaces() throws ZipcodeException {
        // get the test data
        String[] data = dataFactory.getInvalidZipcodeDataSet_spaces().split("\\|");
        ZipcodeException.ExceptionCode exCode = ZipcodeException.ExceptionCode.ZIPINVLD;
        // expected exception and message
        thrown.expect(ZipcodeException.class);
        thrown.expectMessage(SINGLE_QUOTE_START + data[1] + SINGLE_QUOTE_END + exCode.getExceptionMessage());
        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(data[0]);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
    }

    @Test
    public void TestInputWithNonNumeric() throws ZipcodeException {
        // get the test data
        String[] data = dataFactory.getInvalidZipcodeDataSet_nonNumeric().split("\\|");
        ZipcodeException.ExceptionCode exCode = ZipcodeException.ExceptionCode.ZIPINVLD;
        // expected exception and message
        thrown.expect(ZipcodeException.class);
        thrown.expectMessage(SINGLE_QUOTE_START + data[1] + SINGLE_QUOTE_END + exCode.getExceptionMessage());
        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(data[0]);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
    }

    @Test
    public void TestInputWithInvalidRange() throws ZipcodeException {
        // get the test data
        String[] data = dataFactory.getInvalidZipcodeDataSet_invalidRange().split("\\|");
        ZipcodeException.ExceptionCode exCode = ZipcodeException.ExceptionCode.RNGINVLD;
        // expected exception and message
        thrown.expect(ZipcodeException.class);
        thrown.expectMessage(SINGLE_QUOTE_START + data[1] + SINGLE_QUOTE_END + exCode.getExceptionMessage());
        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(data[0]);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
    }

    @Test
    public void TestInputWithValidData() throws ZipcodeException {
        // get the test data
        Map<String, List<ZipcodeRange>> data = dataFactory.getValidZipcodeDataSet();
        String inputZipcodesString = data.keySet().iterator().next();
        // run the test
        ZipcodeParser zipcodeParser = new ZipcodeParser(inputZipcodesString);
        List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();

        // check the expected result
        Utils.checkExpectedResult(data.get(inputZipcodesString), zipCodeRangeList);
    }

}
