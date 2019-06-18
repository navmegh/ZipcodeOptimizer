package com.zip.optimizer;

public class ZipcodeException extends Exception {

    public enum ExceptionCode {
        INPTMSNG("Please enter a string representation of zip code ranges."),
        INPTINVLD("Invalid input. Ranges must be specified as [95747,97877] [67989,98965] ..."),
        RNGINVLD("Invalid range specified. Upper bound can not be less than the lower bound."),
        NOBOUNDS("Invalid Range specified! Both Upper and Lower Bounds are missing."),
        NOLBOUND("Invalid Range specified! Lower Bound is missing."),
        NOUBOUND("Invalid Range specified! Upper Bound is missing."),
        //ZIPLNEGTH("is not valid. Zip Codes should have 5 digits."),
        ZIPINVLD("is not a valid Zip Code.");

        private final String message;

        ExceptionCode(String code) {
            this.message = code;
        }

        public String getExceptionMessage() {
            return this.message;
        }
    }

    public ZipcodeException(String invalidData, ExceptionCode code) {
        super("'" + invalidData + "' - " + code.getExceptionMessage());
    }

}
