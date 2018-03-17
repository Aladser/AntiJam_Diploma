package org.models;

/**
 * Переворачивает разряды числа в base-системе счисления
 */
public abstract class NumberCoup {
    
    /** Переворот разрядов числа
     * @param number число
     * @param base основание
     * @param numOrders разрядность
     */   
    public static int exec(int number, int base, int numOrders){
        int result = 0, degree = 0;
        int highOrder = (int) Math.pow(base, numOrders) / base;
        while(number != 0){
            if(number >= highOrder){
                number -= highOrder;
                result += (int) Math.pow(base, degree);      
            }
            highOrder /= base;
            degree++;
        }
        return result;
    }
}
