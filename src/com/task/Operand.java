package com.task;

public class Operand {

    enum Numerals {ARABIC,ROMAN}
    String value;
    Numerals type;

    public Operand (String operandInString) throws Exception
    {
        this.value= operandInString; //Если символы нижнего регистра поддерижваются, то можно вызвать метод у operandInString .toUpperCase

        boolean isArabicNumerals=value.matches("(-)*(\\d*)");
        if(isArabicNumerals) this.type=Numerals.ARABIC;
        else
        {
            boolean isRomanNumerals=value.matches("[I,V,X,L,C,D,M]*");
            if(isRomanNumerals) this.type=Numerals.ROMAN;
        }
        if(type==null)
            throw new NullPointerException(String.format("Операнд %s не является числом, поддерживаемых систем счисления (арабская/римская)",this.value));

        if(this.getValue()>10 || this.getValue()<1) throw new Exception("Числовое значение операнда должно быть в пределах от 1 до 10 включительно");
    }

    public String getType() {
        return type.toString();
    }

    public int getValue() throws Exception
    {
        int resultValue=0;
        int bigNumber=0;
        if(this.type==Numerals.ARABIC) resultValue=Integer.parseInt(this.value);
        else if(this.type==Numerals.ROMAN)
        {
            char[] valueInChar = value.toCharArray();
            for(int i=0;i<value.length();i++){
                switch(valueInChar[i])
                {
                    case 'I':resultValue+=1; break;
                    case 'V': resultValue+=(i!=0 && valueInChar[i-1]=='I')? 3:5; break; // IV дает 3 - так как в предыдущей итерации мы уже учли единицу I
                    case 'X': resultValue+=(i!=0 && valueInChar[i-1]=='I')? 8:10; break; // IX дает 8 - так как в предыдущей итерации мы уже учли единицу I
                    default: return bigNumber; // Введеное число содержит символ, значение которого выше 10 (Вернется 0 - не входит в допустимый диапазон)

                }
            }
        }
        return resultValue;
    }

    @Override
    public String toString() {
        return "Operand%s {" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}' ;
    }
}
