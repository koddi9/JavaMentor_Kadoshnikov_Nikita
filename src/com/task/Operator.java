package com.task;

public class Operator {

static final char add='+';
static final char subtract ='-';
static final char multiply ='*';
static final char divide ='/';

    public static String define(String expression) throws Exception {
        char[] validOperators = {add, subtract, multiply, divide};
        char[] charFromEntry = expression.toCharArray();
        boolean operatorIsFound = false;
        String operator = "";

        for (int i = 1; i < expression.length(); i++) {
            for (char charFromOperators : validOperators) {
                if (charFromEntry[i] == charFromOperators) operator = Character.toString(charFromOperators);
            }
            if (!operator.isBlank()) { operatorIsFound = true; break; }
        }
        if (!operatorIsFound) throw new Exception("Оператор выражения использован некорректно или не является допустимым (+,-,*,/)");
        return operator;
    }

    public static char toChar (String operator) {
     return operator.charAt(0);
    }
}


