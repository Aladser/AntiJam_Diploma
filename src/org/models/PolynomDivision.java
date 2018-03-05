package org.models;

/**
 * Деление полиномов
 * @author Aladser
 */
public abstract class PolynomDivision {
    public static void execute() throws SignedNumberException{
        int n = 7;                            // длина кодового слова
        int k = 4;                            // длина инфокодового слова
        int[] arr = {1, 0, 0, 1, 1, 1, 0};    // инфоркодовое слово
        int divider = 0b1011;                 // делитель
        int reminder = 0;                     // остаток
        int[] result = new int[4];            // результат
        int resultDEC;
        
        // Деление полиномов: поиск первой 1
        // Количество шагов цикла = n-k - столько раз можно сдвинуться до конца массива
        int i=0, j=0;
        for(; i < (n-k); i++){
            if(arr[i] == 1){
                reminder = (8*arr[i] + 4*arr[i+1] + 2*arr[i+2] + arr[i+3]) ^ divider;
                System.out.print( Integer.toBinaryString( 8*arr[i] + 4*arr[i+1] + 2*arr[i+2] + arr[i+3] ) + " / 1011 = ");
                i += (k-1);
                result[j++]++;
                break;
            }
        }     
        System.out.println(Integer.toBinaryString(reminder) + "\n-----");
        
        // Деление полиномов (4,3): продолжение
        while( (i+k-2) < n ){
            // reminder = XXX
            if(reminder > 3){
                System.out.println("XXX");
                reminder <<= 1;
                reminder += arr[i+1];
                i++;
                result[j++]=1;
            }
            // reminder = XX
            else if(reminder > 1){
                System.out.println("XX");
                reminder <<= 2;
                reminder += 2 * arr[i+1];
                reminder += arr[i+2];
                i+=2;
                result[j++]=0;
                result[j++]=1;
            }
            // reminder = 1
            else if(reminder == 1){
                System.out.println("X");
                reminder <<= 3;
                reminder += 4 * arr[i+1];
                reminder += 2 * arr[i+2];
                reminder += arr[i+3];
                i+=3;
                result[j++]=0;
                result[j++]=0;
                result[j++]=1;
            }
            // reminder = 0
            else{
                System.out.println("0");
                j++;
                i+=3;
                result[j++]=0;
            }
            
            System.out.print( Integer.toBinaryString( reminder ) + " / 1011 = ");
            reminder ^= divider;
            System.out.println( Integer.toBinaryString(reminder) + "\n-----");
        }
        System.out.print("Результат: "+arr[0]+"|"+arr[1]+"|"+arr[2]+"|"+arr[3]+"|"+arr[4]+"|"+arr[5]+"|"+arr[6]+" / 1011 = ");
        resultDEC = 8*result[0] + 4*result[1] + 2*result[2] + result[3];
        if(resultDEC < 2) 
            System.out.print("000");
        else if(resultDEC > 3)
            System.out.print("0");
        else if(resultDEC > 1)
            System.out.print("00");
        System.out.println( Integer.toBinaryString( NumberCoup.execute(resultDEC, 2, 4) ) );
    }
        
}
