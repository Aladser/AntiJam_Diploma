package org.models;

import java.util.BitSet;

/**
 * Деление полиномов
 * @author Aladser
 */
public abstract class PolynomDivision {
    /*public static BitSet execute(BitSet arr){
        int n = 7;                            // длина кодового слова
        int k = 4;                            // длина инфокодового слова
        int divider = 0b1011;                 // делитель
        int[] quot = new int[k];              // частное
        int reminder = 0;                     // остаток
        // инфокодовое слово после деления                        
        BitSet infocode = new BitSet();
        infocode.set(4);
        
        // Деление полиномов: поиск первой единицы делимого
        // Количество шагов цикла = n-k - столько раз можно сдвинуться до конца массива
        // (arr.get(i)?1:0)
        int i=0, j=0;
        for(; i < (n-k+1); i++){
            if(arr.get(i)){
                int division = 8*(arr.get(i)?1:0) + 4*(arr.get(i+1)?1:0) + 2*(arr.get(i+2)?1:0) + (arr.get(i+3)?1:0);
                reminder = division ^ divider;
                System.out.print( Integer.toBinaryString( division ) + " / 1011 = ");
                i += (k-1);
                quot[j++]=1;
                break;
            }
            quot[j++]=0;
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
                quot[j++]=1;
            }
            // reminder = XX
            else if(reminder > 1){
                if( i+2 >= n ) break;
                System.out.println("XX");
                reminder <<= 2;
                reminder += 2 * (arr.get(i+1)?1:0);
                reminder += (arr.get(i+2)?1:0);
                i+=2;
                quot[j++]=0;
                quot[j++]=1;
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
                quot[j++]=0;
                quot[j++]=0;
                quot[j++]=1;
            }
            // reminder = 0
            else{
                if( i+3 >= n ) break;
                System.out.println("0-----");
                j++;
                i+=3;
                quot[j++]=0;
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
        
        int result = NumberCoup.execute( 8*quot[0] + 4*quot[1] + 2*quot[2] + quot[3], 2, 4 );
        for(int order = 8, z=0; result != 0; z++, order /= 2){
            if(result >= order){
                infocode.set(z);
                result -= order;
            }            
        }
        
        for(int z=0; z<infocode.length()-1; z++) System.out.print(infocode.get(z)?1:0);
        System.out.println( " (ост. " + Integer.toBinaryString(reminder) + ")" );  
        return infocode;
    }  
    */
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
    
    public static int execute(int division, int divider){
        int n = 7;                  // длина кодового слова
        int k = 4;                  // размер полинома
        int[] quotBin = new int[k];    // частное
        int quotDec = 0;            // DEC частное 
        
        int a = 0b1010011;
        int b = a;
        int n1 = countBinaryOrders(a); // размер делимого
        int j = n - n1;                // позиция в инфокоде
        for(int i=0; i<n1-k; i++) divider <<= 1;
        int z=3;
        while(  n1 > 3 ){
            System.out.println("делим "+Integer.toBinaryString(a));
            System.out.println("делит "+Integer.toBinaryString(divider));
            
            a ^= divider;
            quotBin[j++] = 1;
            j += (n1 - countBinaryOrders(a) - 1);
            for(int i=0; i<(n1 - countBinaryOrders(a)); i++) divider >>= 1;
            n1 = countBinaryOrders(a);

            System.out.println("частн "+Integer.toBinaryString(a));
            System.out.println("-------");
            z--;
        }
        for(int i=0; i<k; i++)System.out.print(quotBin[i]);
        quotDec = 8*quotBin[0] + 4*quotBin[1] + 2*quotBin[2] + quotBin[3];
        System.out.print("\n"+Integer.toBinaryString(b)+" = ");
        System.out.print(Integer.toBinaryString(NumberCoup.execute(quotDec, 2, k))+"\n" );
        
        int result = 0;
        return result;
    }
}
