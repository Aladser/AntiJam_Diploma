package org.models;

/**
 * Деление полиномов
 * @author Aladser
 */
public abstract class PolynomDivision {
    public static void execute() throws SignedNumberException{
        int n = 7;                            // длина кодового слова
        int k = 4;                            // длина инфокодового слова
        int[] arr = {1, 1, 1, 1, 1, 1, 1};    // инфоркодовое слово
        int divider = 0b1011;                 // делитель
        int[] result = new int[4];            // результат
        int reminder = 0;                     // остаток
        
        // Деление полиномов: поиск первой 1
        // Количество шагов цикла = n-k - столько раз можно сдвинуться до конца массива
        int i=0;
        for(; i < (n-k); i++){
            if(arr[i] == 1){
                reminder = (8*arr[i] + 4*arr[i+1] + 2*arr[i+2] + arr[i+3]) ^ divider;
                System.out.println( "ц1 делимое " + Integer.toBinaryString( 8*arr[i] + 4*arr[i+1] + 2*arr[i+2] + arr[i+3] ) );
                i += (k-1);
                break;
            }
        }
     
        System.out.println("ц1 остаток " + Integer.toBinaryString(reminder));
        System.out.println("ц1 i="+i);
        
        // Деление полиномов (4,3): продолжение
        while( (i+k-2) < n ){
            // reminder = XXX
            if(reminder > 3){
                System.out.println("XXX");
                reminder <<= 1;
                reminder += arr[i+1];
                i++;
            }
            // reminder = XX
            else if(reminder > 1){
                System.out.println("XX");
                reminder <<= 2;
                reminder += 2 * arr[i+1];
                reminder += arr[i+2];
                i+=2;
            }
            // reminder = 1
            else if(reminder == 1){
                System.out.println("X");
                reminder <<= 3;
                reminder += 4 * arr[i+1];
                reminder += 2 * arr[i+2];
                reminder += arr[i+3];
                i+=3;
            }
            // reminder = 0
            else{
                System.out.println("0");
                i+=3;
            }
            
            System.out.println( "делимое " + Integer.toBinaryString(reminder) );
            reminder ^= divider;
            System.out.println( "остаток " + Integer.toBinaryString(reminder) );
        }
        System.out.print(arr[0]+"|"+arr[1]+"|"+arr[2]+"|"+arr[3]+"|"+arr[4]+"|"+arr[5]+"|"+arr[6]+" mod 1011 =");
        System.out.println(Integer.toBinaryString(reminder));
        
    }
        
}
