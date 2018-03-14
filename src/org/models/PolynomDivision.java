package org.models;

/**
 * Деление полиномов
 */
public abstract class PolynomDivision {
    /** Деление
     * @param division - делимое
     * @param n - число разрядов делимого
     * @param divider - делитель
     * @param k - число разрядов
     * @return частное
     */
    public static int execute(int division, int n, int divider, int k){
        int result = 0;
        int[] quotient = new int[k]; // частное
        int n1 = BinDecTranslation.countBinaryOrders(division); // число разрядов делимого
        int j = n - n1; // позиция первой единицы частного                   
        for(int i=0; i<n1-k; i++) divider <<= 1; // сдвиг делителя для начала деления делимого        
        while(  n1 > 3 ){ 
            division ^= divider;
            quotient[j++] = 1;
            j += (n1 - BinDecTranslation.countBinaryOrders(division) - 1);
            for(int i=0; i<(n1 - BinDecTranslation.countBinaryOrders(division)); i++) divider >>= 1;
            n1 = BinDecTranslation.countBinaryOrders(division);
        }
        int[] orders = new int[k];
        for(int i=k-1, z=0; i>=0; i--, z++) orders[z] = (int) Math.pow(2, i);
        for(int i=0; i<k; i++) result += orders[i]*quotient[i];
        return result;
    }
    
    // Класс результата деления
    public class DivisionResult{
        int quotient;
        int reminder;
    }
}
