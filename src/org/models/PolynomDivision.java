package org.models;

/**
 * Деление полиномов
 * @author Aladser
 */
public abstract class PolynomDivision {
    public static void execute() throws SignedNumberException{
        int[] array = {1,0,0,0,1,0,1}; //делимое
        int division=0;
        int divider=0b1011;
        int[] res = new int[4];
        int reminder=0;
        
        int k=0, m=0;
        for(; k<4; k++){ //k<n-3
            if(array[k]==1){
                res[m++]=1;
                division = 8*array[k] + 4*array[k+1] + 2*array[k+2] + array[k+3];
                System.out.println( "Делимое " + Integer.toBinaryString(division) );
                
                reminder = division^divider;
                System.out.println( "Остаток " + Integer.toBinaryString(reminder) + "\n");
                break;
            }
            else
                res[m++]=0;
        }
        
        for(; k<4; k++){
            if(reminder >= 4){
                res[m++]=1;
                k++;
                division = (reminder<<1) + array[k+3];
            }
            else if(reminder >=2){
                res[m++]=0;
                res[m++]=1;
                k+=2;
                division = (reminder<<2) + (2*array[k+2]) + array[k+3];
            }
            else if(reminder == 1){
                res[m++]=0;
                res[m++]=0;
                res[m++]=1;
                k+=3;
                division = (reminder<<3) + (4*array[k+1]) + 2*array[k+2] + array[k+3];
            }
            else{
                res[m++]=0;
                division=0;
            }
            System.out.println( "Делимое " + Integer.toBinaryString(division) );
            if(division !=0) reminder = division^divider;
            System.out.println( "Остаток " + Integer.toBinaryString(reminder) + "\n");
        }
        int result = 8*res[0] + 4*res[1] + 2*res[2] + res[3];
        result = NumberCoup.execute(result, 2, 4);
        System.out.println( Integer.toBinaryString(result) + "(" + result + ")");        
    }
        
}
