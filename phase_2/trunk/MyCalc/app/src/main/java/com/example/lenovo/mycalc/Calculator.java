package com.example.lenovo.mycalc;

/**
 * Created by Lenovo on 2017/11/30.
 */

public class Calculator {
    private double firstNum;
    private StringBuffer currentNum = new StringBuffer();
    private char currentSign ='+';

    public StringBuffer getCurrentNum() {
        return currentNum;
    }

    public void append(String num) {
        if (num == "."
                && currentNum.indexOf(".") != -1) {
            return;
        }
        currentNum.append(num);
    }

    public Double dealWithOperatin(char oper) {
        Double result = 0.0;
        do {

            if ('=' == oper) {
                result = getRusult();
                break;
            }

            if ('B' == oper || 'b' == oper) {
                currentNum = new StringBuffer();
                break;
            }

            currentSign = oper;
            firstNum = stringToDouble(currentNum.toString());
            currentNum = new StringBuffer();
        } while (false);

        return result;
    }

    private Double getRusult() {
        Double result = calcu();
        firstNum = 0.0;
        currentNum = new StringBuffer();
        return result;
    }


    private Double calcu() {
        Double result = 0.0;
        switch (currentSign) {
            case '+' :
                result = firstNum + stringToDouble(currentNum.toString());
                break;
            case '-' :
                result = firstNum - stringToDouble(currentNum.toString());
                break;
            case '/' :
                result = firstNum / stringToDouble(currentNum.toString());
                break;
            case '*' :
                result = firstNum * stringToDouble(currentNum.toString());
                break;
            default:
                break;
        }
        return result;

    }

    static private double stringToDouble(String num) {
        double result = 0.0;

        do {
            if (0 == num.length()) {
                break;
            }
            result = Double.parseDouble(num);
        } while (false);

        return result;
    }

}
