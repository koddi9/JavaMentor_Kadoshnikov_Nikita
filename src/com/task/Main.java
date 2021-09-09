package com.task;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entryScanner = new Scanner(System.in);
        String entryLine = entryScanner.nextLine();

        try {
            Expression e = new Expression(entryLine);
            e.calculate();
        }
        catch (Exception ex) { System.out.println("Ex: " + ex.getMessage()); }

        //  System.out.println(String.join(" ",e.operands[0].value,e.operands[1].value));
    }
}
