package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Aneesh Soni
 * as76745
 * Section: 15460
 * Zarif Choudhury
 * zc3733
 * Section: 15460
 * Spring 2018
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */

        /**
         * Here is the start of the actual program
         * The user inputs a series of commands and the program continues running until the user explicitly types in "quit"
         */
        String initial;
        while(true){
        initial = kb.nextLine();
            try {
                int value = 1;
                int stepCount = 1;
                int seedCount = 1;
                List<Critter> listOfCritters = null;

                if (initial.contains("quit")) {
                    System.out.flush();
                    return;
                }

                else if (initial.contains("show")) {
                    Critter.displayWorld();
                }

                else if(initial.contains("step")) {
                    String stepNumber = initial.replaceAll("[^0-9]", "");
                    if(stepNumber.isEmpty()){
                        stepCount = 1;
                    }
                    else {
                        stepCount = Integer.parseInt(stepNumber);
                    }

                    for (int i = 0; i < stepCount; i++) {
                        Critter.worldTimeStep();
                    }
                }

                else if(initial.contains("seed")){
                    String seedNumber = initial.replaceAll("[^0-9]", "");
                    if(seedNumber.isEmpty()){
                        seedCount = 1;
                    }
                    else {
                        seedCount = Integer.parseInt(seedNumber);
                    }
                    Critter.setSeed(seedCount);

                }


                else if(initial.contains("stats")){
                    if(initial.contains("Algae")){
                        listOfCritters = Critter.getInstances("Algae");
                        Critter.runStats(listOfCritters);
                    }

                    if(initial.contains("Craig")){
                        listOfCritters = Critter.getInstances("Craig");
                        Critter.runStats(listOfCritters);
                    }

                    if(initial.contains("Critter1")){
                        listOfCritters = Critter.getInstances("Critter1");
                        Critter.runStats(listOfCritters);
                    }

                    if(initial.contains("Critter2")){
                        listOfCritters = Critter.getInstances("Critter2");
                        Critter.runStats(listOfCritters);
                    }

                    if(initial.contains("Critter3")){
                        listOfCritters = Critter.getInstances("Critter3");
                        Critter.runStats(listOfCritters);
                    }

                    if(initial.contains("Critter4")){
                        listOfCritters = Critter.getInstances("Critter4");
                        Critter.runStats(listOfCritters);
                    }


                }


                else if (initial.contains("make")) {
                    if (initial.contains("Algae")) {
                        String numberOnly = initial.replaceAll("[^0-9]", "");
                        if(numberOnly.isEmpty()){
                            value = 1;
                        }
                        else {
                            value = Integer.parseInt(numberOnly);
                        }
                        if(((numberOnly.length()) + 11) == initial.length()) {
                            for(int i = 0; i < value; i++){
                                Critter.makeCritter("assignment4.Algae");
                            }
                        }
                        else {
                            System.out.println("error processing: " + initial);
                        }
                    }
                    else if (initial.contains("Craig")) {
                        String numberOnly = initial.replaceAll("[^0-9]", "");
                        if(numberOnly.isEmpty()){
                            value = 1;
                        }
                        else {
                            value = Integer.parseInt(numberOnly);
                        }
                        if(((numberOnly.length()) + 11) == initial.length()) {
                            for(int i = 0; i < value; i++){
                                Critter.makeCritter("assignment4.Craig");
                            }
                        }
                        else{
                            System.out.println("error processing: " + initial);
                        }
                    }
                    else if (initial.contains("Critter1")) {
                        String numberOnly = initial.replaceAll("[^0-9]", "");
                        if(numberOnly.isEmpty()){
                            value = 1;
                        }
                        else {
                            numberOnly = numberOnly.substring(1);
                            value = Integer.parseInt(numberOnly);
                        }
                        if(((numberOnly.length()) + 14) == initial.length()) {
                            for (int i = 0; i < value; i++) {
                                Critter.makeCritter("assignment4.Critter1");
                            }
                        }
                        else {
                            System.out.println("error processing: " + initial);
                        }
                    }
                    else if (initial.contains("Critter2")) {
                        String numberOnly = initial.replaceAll("[^0-9]", "");
                        if(numberOnly.isEmpty()){
                            value = 1;
                        }
                        else {
                            numberOnly = numberOnly.substring(1);
                            value = Integer.parseInt(numberOnly);
                        }
                        if(((numberOnly.length()) + 14) == initial.length()) {
                            for (int i = 0; i < value; i++) {
                                Critter.makeCritter("assignment4.Critter2");
                            }
                        }
                        else {
                            System.out.println("error processing: " + initial);
                        }
                    }
                    else if (initial.contains("Critter3")) {
                        String numberOnly = initial.replaceAll("[^0-9]", "");
                        if(numberOnly.isEmpty()){
                            value = 1;
                        }
                        else {
                            numberOnly = numberOnly.substring(1);
                            value = Integer.parseInt(numberOnly);
                        }
                        if(((numberOnly.length()) + 14) == initial.length()) {
                            for (int i = 0; i < value; i++) {
                                Critter.makeCritter("assignment4.Critter3");
                            }
                        }
                        else {
                            System.out.println("error processing: " + initial);
                        }
                    }
                    else if (initial.contains("Critter4")) {
                        String numberOnly = initial.replaceAll("[^0-9]", "");
                        if(numberOnly.isEmpty()){
                            value = 1;
                        }
                        else {
                            numberOnly = numberOnly.substring(1);
                            value = Integer.parseInt(numberOnly);
                        }
                        if(((numberOnly.length()) + 14) == initial.length()) {
                            for (int i = 0; i < value; i++) {
                                Critter.makeCritter("assignment4.Critter4");
                            }
                        }
                        else {
                            System.out.println("error processing: " + initial);
                        }
                    }
                }
                else {
                    System.out.println("invalid command: " + initial);
                }
            }
            catch (InvalidCritterException e){
            }
        }

        /* Write your code above */
    }
}
