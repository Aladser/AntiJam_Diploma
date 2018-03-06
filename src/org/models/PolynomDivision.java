package org.models;

import java.util.BitSet;

/**
 * Деление полиномов
 * @author Aladser
 */
public abstract class PolynomDivision {
    public static void execute(BitSet arr) throws SignedNumberException{
        int n = 7;                            // длина кодового слова
        int k = 4;                            // длина инфокодового слова
        int divider = 0b1011;                 // делитель
        int reminder = 0;                     // остаток
        int[] result = new int[4];            // результат
        int resultDEC;
        
        // Деление полиномов: поиск первой 1
        // Количество шагов цикла = n-k - столько раз можно сдвинуться до конца массива
        // (arr.get(i)?1:0)
        int i=0, j=0;
        for(; i < (n-k+1); i++){
            if(arr.get(i)){
                int division = 8*(arr.get(i)?1:0) + 4*(arr.get(i+1)?1:0) + 2*(arr.get(i+2)?1:0) + (arr.get(i+3)?1:0);
                reminder = division ^ divider;
                System.out.print( Integer.toBinaryString( division ) + " / 1011 = ");
                i += (k-1);
                result[j++]=1;
                break;
            }
            result[j++]=0;
        }
        System.out.println(Integer.toBinaryString(reminder) + "\n-----");
        
        // Деление полиномов (4,3): продолжение
        while( i < n-1 ){
            // reminder = XXX
            if(reminder > 3){
                if( i+1 >= n ) break;
                System.out.println("XXX");
                reminder <<= 1;
                reminder += (arr.get(i+1)?1:0);
                i++;
                result[j++]=1;
            }
            // reminder = XX
            else if(reminder > 1){
                if( i+2 >= n ) break;
                System.out.println("XX");
                reminder <<= 2;
                reminder += 2 * (arr.get(i+1)?1:0);
                reminder += (arr.get(i+2)?1:0);
                i+=2;
                result[j++]=0;
                result[j++]=1;
            }
            // reminder = 1
            else if(reminder == 1){
                if( i+3 >= n ) break;
                System.out.println("X");
                reminder <<= 3;
                reminder += 4 * (arr.get(i+1)?1:0);
                reminder += 2 * (arr.get(i+2)?1:0);
                reminder += (arr.get(i+3)?1:0);
                i+=3;
                result[j++]=0;
                result[j++]=0;
                result[j++]=1;
            }
            // reminder = 0
            else{
                if( i+3 >= n ) break;
                System.out.println("0-----");
                j++;
                i+=3;
                result[j++]=0;
            }
            
            System.out.print( Integer.toBinaryString( reminder ) + " / 1011 = ");
            reminder ^= divider;
            System.out.println( Integer.toBinaryString(reminder) + "\n-----");
        }
        
        System.out.print( (arr.get(0)?1:0) );
        System.out.print( (arr.get(1)?1:0) );
        System.out.print( (arr.get(2)?1:0) );
        System.out.print( (arr.get(3)?1:0) );
        System.out.print( (arr.get(4)?1:0) );
        System.out.print( (arr.get(5)?1:0) );
        System.out.print( (arr.get(6)?1:0) );
        System.out.print( " / 1011 = " );
        resultDEC = 8*result[0] + 4*result[1] + 2*result[2] + result[3];
        int resultC = NumberCoup.execute(resultDEC, 2, 4);
        if(resultC > 7)
            System.out.print("");
        else if(resultC > 3)
            System.out.print("0");
        else if(resultC > 1)
            System.out.print("00");
        else
            System.out.print("000");
        System.out.print( Integer.toBinaryString( resultC ) );
        System.out.println( " (ост. " + Integer.toBinaryString(reminder) + ")");
    }        
}
