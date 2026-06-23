package br.edu.ifsuldeminas.mch.sd.rmi.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.edu.ifsuldeminas.mch.sd.rmi.remote.Operations;

public class Calculator implements Operations {
    private List<String> lastOperations = new ArrayList<String>();

    public Number sum(Number x, Number y) {
        Number result = x.doubleValue() + y.doubleValue();
        log(x, "+", y, result);
        return result;
    }

    public Number sub(Number x, Number y) {
        Number result = x.doubleValue() - y.doubleValue();
        log(x, "-", y, result);
        return result;
    }

    public Number mul(Number x, Number y) {
        Number result = x.doubleValue() * y.doubleValue();
        log(x, "*", y, result);
        return result;
    }

    public Number div(Number x, Number y) {
        Number result = Double.NaN;
        if (y.doubleValue() != 0) {
            result = x.doubleValue() / y.doubleValue();
        }
        log(x, "/", y, result);
        return result;
    }


    public Number root(Number value, Number index) {
        Number result = Math.pow(value.doubleValue(), 1.0 / index.doubleValue());
        log(value, "raiz de índice " + index, 0, result); 
        return result;
    }

    public Number power(Number base, Number exponent) {
        Number result = Math.pow(base.doubleValue(), exponent.doubleValue());
        log(base, "^", exponent, result);
        return result;
    }

    public Number percentage(Number value, Number total) {
        Number result = (value.doubleValue() / total.doubleValue()) * 100;
        log(value, "% de", total, result);
        return result;
    }

    public Number modulo(Number x, Number y) {
        Number result = x.doubleValue() % y.doubleValue();
        log(x, "% (mod)", y, result);
        return result;
    }

    public long factorial(int n) {
        if (n < 0) {
            log(n, "!", 0, -1); 
            return -1; 
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        log(n, "!", 0, result);
        return result;
    }

    public String decToBin(int dec) {
        String result = Integer.toBinaryString(dec);
        logString(dec + " para Binário", result);
        return result;
    }

    public String decToHex(int dec) {
        String result = Integer.toHexString(dec).toUpperCase();
        logString(dec + " para Hexadecimal", result);
        return result;
    }


    public List<String> lastOperations(int howMany) {
        if (lastOperations.size() < howMany)
            return lastOperations();
        return new ArrayList<String>(lastOperations.subList(
                lastOperations.size() - howMany, lastOperations.size()));
    }

    public List<String> lastOperations() {
        return lastOperations;
    }

    private void log(Number operatorOne, String operation, Number operatorTwo, Number result) {
        String formattedOperation = String.format("%s %s %s = %s",
                operatorOne.toString(), operation, operatorTwo.toString(), result.toString());
        lastOperations.add(formattedOperation);
        System.out.printf("%s at %s\n", formattedOperation, new Date());
    }
    
    private void logString(String operation, String result) {
        String formattedOperation = String.format("%s = %s", operation, result);
        lastOperations.add(formattedOperation);
        System.out.printf("%s at %s\n", formattedOperation, new Date());
    }
}