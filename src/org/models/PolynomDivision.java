package org.models;

/**
 * Деление полиномов
 * @author Aladser
 */
public abstract class PolynomDivision {  
    public static int execute(int division, int divider){
        int n = 7;                   // длина кодового слова
        int k = 4;                   // размер полинома
        int[] quotient = new int[k]; // частное
        
        System.out.print( Integer.toBinaryString(division) +" => ");
        // размер делимого
        int n1 = countBinaryOrders(division);
        // позиция в инфокоде
        int j = n - n1;                   
        // поиск первой единицы
        for(int i=0; i<n1-k; i++) divider <<= 1;
        while(  n1 > 3 ){
            //System.out.println("делим "+Integer.toBinaryString(division));
            //System.out.println("делит "+Integer.toBinaryString(divider));  
            division ^= divider;
            quotient[j++] = 1;
            j += (n1 - countBinaryOrders(division) - 1);
            for(int i=0; i<(n1 - countBinaryOrders(division)); i++) divider >>= 1;
            n1 = countBinaryOrders(division);
            //System.out.println("частн "+Integer.toBinaryString(division));
            //System.out.println("-------");
        }   
        int result = NumberCoup.execute(8*quotient[0] + 4*quotient[1] + 2*quotient[2] + quotient[3], 2, k);
        System.out.println( Integer.toBinaryString(result) +"\n-----");
        return result;
    }
    
    // считает число двоичных разрядов
    public static int countBinaryOrders(int num){
        if(num==0 || num==1) return 1;
        int numOrders = 0;
        while(num != 0){
            num >>= 1;
            numOrders++;
        }
        return numOrders;
    }
}
