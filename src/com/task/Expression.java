package com.task;


public class Expression {

    private String expression;
   private String operator;
   private Operand[] operands;
    private String operandsType;
   private String resultInString;

    public Expression(String exp) throws Exception {
        this.expression = exp.trim();
        if (expression.isEmpty()) throw new Exception("Пустая строка");

        this.operator = Operator.define(expression); //Поиск оператора выражения

        String[] operandsInString = expression.split(String.format("\\s*(\\%s)\\s*", operator)); // Создание String-массива операндов выражения
        int operandsCount = operandsInString.length;
        //System.out.println(Arrays.toString(operandsInString));
        checkForOperandsCount(operandsCount); // Проверка на корректное количество операндов в выражении (Необходимое количество: 2)

        /**
         * Создание объектов типа Operand, в зависимости от числа операндов в выражении (Ввиду предыдущей проверки их будет всегда 2)
         */
        this.operands = new Operand[operandsCount];
        for (int i = 0; i < operandsCount; i++) {
            operands[i] = new Operand(operandsInString[i]);
        }

        /**
         * Проверка на соотвествие типов операндов
         */
        this.operandsType = operands[0].getType();
        for (int i = 1; i < operands.length; i++) {
            if (!operands[i].getType().equals(operandsType)) throw new Exception("Используются разные системы счисления");
        }
        //System.out.println(Arrays.toString(operands));
    }

    private void checkForOperandsCount(int count) throws Exception {
        switch (count) {
            case 1:
                throw new Exception("Строка не ялвяется математической операцией (отсутсвует второй операнд)");
            case 2:
                break;
            default:
                throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }
    }

    public void calculate() throws Exception {
/**
 * Рассчет результата выражения, в зависимости от значения оператора
 */
        int result=operands[0].getValue();
        for (int i = 1; i < operands.length; i++) {
            switch(Operator.toChar(this.operator)){
                case Operator.add: result += operands[i].getValue(); break;
                case Operator.subtract: result -= operands[i].getValue(); break;
                case Operator.multiply: result *= operands[i].getValue(); break;
                case Operator.divide: if(operands[i].getValue()==0) throw new ArithmeticException("Делитель равен 0");
                result /= operands[i].getValue(); break;}
        }

        if(operandsType.equals(Operand.Numerals.ROMAN.toString()) && result < 0) //Проверка знака результата выражения в римсой системе счисления
            throw new Exception("Результат выражения < 0 (в римской системе счисления нет отрицательных чисел)");

        printResult(result);
        }

        private void printResult(int result){
            if(operandsType.equals(Operand.Numerals.ROMAN.toString()))
                resultInString=convertResultToRoman(result);
            else
                resultInString=Integer.toString(result);

            System.out.println(resultInString.trim());
        }


        private String convertResultToRoman(int result) {

            StringBuilder resultInRoman = new StringBuilder();
            int [] dividers = {100,50,10};
            String[] romanSymbols={"C","L","X"}; int romanSymbolsIndex;

            if(result>=90 && result<100) { resultInRoman.append("XC"); result-=90;}
            else if (result>=40 && result<50) {resultInRoman.append("XL"); result-=40;}

        while (result>9) {
            romanSymbolsIndex=0;

            for (int divider : dividers) {
                if (result / divider >= 1) {
                    resultInRoman.append(romanSymbols[romanSymbolsIndex]);
                    result -= divider;
                    break;
                }
                romanSymbolsIndex++;
            }
        }
            if(result==9) { resultInRoman.append("IX"); result-=9; }
            else if(result>4) { resultInRoman.append("V");result-=5; }
            else if(result==4) { resultInRoman.append("IV"); result-=4;}

            while(result>0){
                resultInRoman.append("I");
                result--;
            }
           return resultInRoman.toString();
        }
}