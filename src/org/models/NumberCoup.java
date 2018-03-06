package org.models;

/**
 * Переворачивает разряды числа
 * @author Aladser
 */
public abstract class NumberCoup {
    
    /**
     * @param origNumber
     * @param base - основание числа
     * @param digitNumber
     */   
    public static int execute(int origNumber, int base, int digitNumber){
        int number = 0; // перевернутое число
        int degree = 0; // степень результата
        int highOrder = (int) Math.pow(base, digitNumber) / base;
        while(origNumber != 0){
            if(origNumber >= highOrder){
                origNumber -= highOrder;
                number += (int) Math.pow(base, degree);      
            }
            highOrder /= base;
            degree++;
        }
        return number;
    }
}
