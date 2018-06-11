package org.models;

/** Деление полиномов */
public abstract class PolynomDivision {
    /** Деление
     * @param division делимое
     * @param n требуемое число разрядов делимого
     * @param divider делитель
     * @return частное и остаток деления
     */
    public static Result exec(int division, int n, int divider){
        int k = BinOperations.countOrders(divider);
        Result result = new Result();
        
        int[] quotient = new int[k]; // частное в виде битового массива
        int n1 = BinOperations.countOrders(division); // число разрядов делимого
        int j = n - n1; // позиция первой единицы частного                   
        for(int i=0; i<n1-k; i++) divider <<= 1; // сдвиг делителя для начала деления       
        while(  n1 > k-1 ){ 
            division ^= divider;
            quotient[j++] = 1;
            j += (n1 - BinOperations.countOrders(division) - 1);
            for(int i=0; i<(n1 - BinOperations.countOrders(division)); i++) divider >>= 1;
            n1 = BinOperations.countOrders(division);
        }
        
        int[] orders = new int[k]; // разряды частного в десятичной форме
        for(int z=0, i=k-1; i>=0; i--, z++) orders[z] = (int) Math.pow(2, i);
        for(int i=0; i<k; i++) result.quotient += orders[i]*quotient[i];
        result.reminder = division;
        
        return result;
    }
    
    /**
     * Возвращает остаток деления по модулю полиномов
     * @param division делимое
     * @param divider делитель
     * @return остаток деления
     */
    public static int mod(int division, int divider){
        int n1 = BinOperations.countOrders(division);
        int n2 = BinOperations.countOrders(divider);
        int n3;  // число разрядов остатка от субделения
        if(n1 < n2) return division;
        // сдвиг делителя длево ля начала деления
        for(int i=0; i<n1-n2; i++) divider <<= 1;
        while(n1 >= n2){
            division ^= divider;
            n3 = BinOperations.countOrders(division);
            if(n3 >= n2) for(int i=0; i<n1-n3; i++) divider >>= 1;
            n1 = n3;
        }
        return division;
    }
  
    /**
     * Класс результата деления
     */
    public static class Result{
        public int quotient = 0; // частное
        public int reminder = 0; // остаток
    }
}
