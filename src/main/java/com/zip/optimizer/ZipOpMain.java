package com.zip.optimizer;

import com.zip.optimizer.model.ZipcodeRange;

import java.util.List;
import java.util.Scanner;

public class ZipOpMain implements Runnable {

    static boolean quit=false;
    static Scanner input;

    /***
     * Runs the optimizer as a separate thread
     */
    public void run() {
        String inputRanges = null;
        printInputPrompt();
        while (true) {
            inputRanges = input.nextLine();
            if(inputRanges.equalsIgnoreCase("Q")){
                quit = true;
                break;
            }

            try {
                // parse the input, validate and initialize model object with it
                ZipcodeParser zipcodeParser = new ZipcodeParser(inputRanges);
                List<ZipcodeRange> zipCodeRangeList = zipcodeParser.parseZipcodeRanges();
                prettyPrintZipcodeRanges(" INPUT  ", zipCodeRangeList, "");

                // Now optimize the zip code ranges
                ZipcodeOptimizer zipcodeOptimizer = new ZipcodeOptimizer();
                // First sort the zip code ranges by their lower bound
                List<ZipcodeRange> sortedZipCodeRangeList = zipcodeOptimizer.sortZipCodeRanges(zipCodeRangeList);
                prettyPrintZipcodeRanges(" SORTED ", sortedZipCodeRangeList, "");
                // now optimize the zip code ranges by merging the overlapping ranges
                List<ZipcodeRange> optimizedZipCodeRangeList = zipcodeOptimizer.optimizeZipcodes(sortedZipCodeRangeList);
                prettyPrintZipcodeRanges(" OUTPUT ", optimizedZipCodeRangeList, "");

            } catch (ZipcodeException ze) {
                System.out.println("ERRROR: " + ze.getMessage());
                printInputPrompt();
            }
        }
    }

    /***
     * Main entry point for the application
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception{
        input = new Scanner(System.in);
        // creating a new thread to handle the user input
        Thread t1=new Thread(new ZipOpMain());
        t1.start();
        while(true){
            try{
                t1.sleep(10); // Main thread sleeps
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            // Break away once user enters Q/q
            if(quit) {
                System.out.println("\nUser terminated the program.... ");
                break;
            }
        }

    }

    // Prompts user to input the zip code ranges
    private static void printInputPrompt() {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Please enter Zip Code ranges to optimize... ('Q'/'q' to terminate)");
        System.out.println("E.g.: [95747,97877] [67989,98965] ...");
        System.out.println("-------------------------------------------------------------------");
    }

    // Prints zip code range strings generated by various steps during
    private static void prettyPrintZipcodeRanges(String title, List<ZipcodeRange> ranges, String newline) {
        StringBuffer sb = new StringBuffer(title + " --> ");
        for(ZipcodeRange range: ranges) {
            sb.append("[" + range.getRangeFrom() + "," + range.getRangeTo() + "] ");
        }
        sb.append(newline);
        System.out.println(sb.toString());
    }

}
