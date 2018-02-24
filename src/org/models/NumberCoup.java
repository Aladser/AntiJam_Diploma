package org.models;

/**
 * Переворачивает биты числа
 * @author Aladser
 */
public abstract class NumberCoup {
    
    /**
     * @param origNumber
     * @param base - основание числа
     * @param digitNumber
     * @throws org.models.SignedNumberException
     */   
    public static int execute(int origNumber, int base, int digitNumber) throws SignedNumberException{
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
