package org.models.codecs;

import org.models.BinDecTranslation;

/**
 * Деление полиномов
 */
public abstract class PolynomDivision {
    /** Деление
     * @param division делимое
     * @param n требуемое число разрядов делимого
     * @param divider делитель
     * @return частное и остаток деления
     */
    public static Result execute(int division, int n, int divider){
        int k = BinDecTranslation.countBinaryOrders(divider);
        Result result = new Result();
        
        int[] quotient = new int[k]; // частное в виде битового массива
        int n1 = BinDecTranslation.countBinaryOrders(division); // число разрядов делимого
        int j = n - n1; // позиция первой единицы частного                   
        for(int i=0; i<n1-k; i++) divider <<= 1; // сдвиг делителя для начала деления       
        while(  n1 > k-1 ){ 
            division ^= divider;
            quotient[j++] = 1;
            j += (n1 - BinDecTranslation.countBinaryOrders(division) - 1);
            for(int i=0; i<(n1 - BinDecTranslation.countBinaryOrders(division)); i++) divider >>= 1;
            n1 = BinDecTranslation.countBinaryOrders(division);
        }
        
        int[] orders = new int[k]; // разряды частного в десятичной форме
        for(int z=0, i=k-1; i>=0; i--, z++) orders[z] = (int) Math.pow(2, i);
        for(int i=0; i<k; i++) result.quotient += orders[i]*quotient[i];
        result.reminder = division;
        
        return result;
    }
    
    /**
     * Класс результата деления
     */
    public static class Result{
        public int quotient = 0; // частное
        public int reminder = 0; // остаток
    }
}
