package org.models.codecs;

import java.util.BitSet;
import org.models.ImageBits;

/**
 * Кодек БЧХ кода
 */
public abstract class BCHCodec extends Codec{
    public static final int Gx = 0b1011; // порождающий многочлен g(x)
    
    // кодирование
    // Ax - информация
    // Sx - кодовое слово
    public static BitSet encode(BitSet Ax){
        int length = ((Ax.length()) * 7) / 4; // размер = n / n - k
	BitSet Sx = new BitSet(length); // битовый массив кодовых слов
        Sx.set(length); // установка конца бита. length = размер массива + 1

        // Mx - Образующая матрица M(x). 
        // Первое число - строки, второе число - столбцы
        int[][] Mx = new int[4][7]; 
        Mx[0][0] = 0;
        Mx[0][1] = 0;
        Mx[0][2] = 0;
        Mx[0][3] = 1;
        Mx[0][4] = 0;
        Mx[0][5] = 1;
        Mx[0][6] = 1;
        
        Mx[1][0] = 0;
        Mx[1][1] = 0;
        Mx[1][2] = 1;
        Mx[1][3] = 0;
        Mx[1][4] = 1;
        Mx[1][5] = 1;
        Mx[1][6] = 0;

        Mx[2][0] = 0;
        Mx[2][1] = 1;
        Mx[2][2] = 0;
        Mx[2][3] = 1;
        Mx[2][4] = 1;
        Mx[2][5] = 0;
        Mx[2][6] = 0;
        
        Mx[3][0] = 1;
        Mx[3][1] = 0;
        Mx[3][2] = 1;
        Mx[3][3] = 1;
        Mx[3][4] = 0;
        Mx[3][5] = 0;
        Mx[3][6] = 0;
        
        // Кодирование
        boolean[] tAx = {false,true,false,true, false,false,false,false, true,true,false,true};
        int[] tSx = new int[7];
                
        for(int i=0; i<12; i+=4){
            for(int k=0; k<7; k++){
                
                tSx[k] = ImageBits.getBit(tAx[i]) & Mx[0][k];
               // System.out.println( tAx[i] + "&" + Mx[0][k] + "=" + tAx[i]*Mx[0][k] );
                tSx[k] ^= ImageBits.getBit(tAx[i+1]) & Mx[1][k];
                //System.out.println( tAx[i+2] + "&" + Mx[1][k] + "=" + tAx[i+1]*Mx[1][k] );
                tSx[k] ^= ImageBits.getBit(tAx[i+2]) & Mx[2][k];
                //System.out.println( tAx[i+2] + "&" + Mx[2][k] + "=" + tAx[i+2]*Mx[2][k] );
                tSx[k] ^= ImageBits.getBit(tAx[i+3]) & Mx[3][k];
                //System.out.println( tAx[i+3] + "&" + Mx[3][k] + "=" + tAx[i+3]*Mx[3][k] );
                //System.out.println( "S(" + k + ") = " + tSx[k] );
                //System.out.print(tAx[i] +"|"+ tAx[i+1] +"|"+ tAx[i+2] +"|"+ tAx[i+3] + " = ");
                if(k==6)
                    System.out.println(
                            ImageBits.getBit(tAx[i]) +"|"+ ImageBits.getBit(tAx[i+1]) +"|"+ ImageBits.getBit(tAx[i+2]) +"|"+ ImageBits.getBit(tAx[i+3]) + " = " +
                            tSx[k-6] + "|"+ tSx[k-5] + "|"+ tSx[k-4] + "|"+ tSx[k-3] + "|"+ tSx[k-2] + "|"+ tSx[k-1] + "|"+ tSx[k] + "\n" );
                
            }
        }

        // Кодирование
    /*            
        for(int i=0; i<12; i+=4){
            for(int k=0; k<7; k++){
                
                tSx[k] = ImageBits.getBit(tAx[i]) & Mx[0][k];
               // System.out.println( tAx[i] + "&" + Mx[0][k] + "=" + tAx[i]*Mx[0][k] );
                tSx[k] ^= ImageBits.getBit(tAx[i+1]) & Mx[1][k];
                //System.out.println( tAx[i+2] + "&" + Mx[1][k] + "=" + tAx[i+1]*Mx[1][k] );
                tSx[k] ^= ImageBits.getBit(tAx[i+2]) & Mx[2][k];
                //System.out.println( tAx[i+2] + "&" + Mx[2][k] + "=" + tAx[i+2]*Mx[2][k] );
                tSx[k] ^= ImageBits.getBit(tAx[i+3]) & Mx[3][k];
                //System.out.println( tAx[i+3] + "&" + Mx[3][k] + "=" + tAx[i+3]*Mx[3][k] );
                //System.out.println( "S(" + k + ") = " + tSx[k] );
                //System.out.print(tAx[i] +"|"+ tAx[i+1] +"|"+ tAx[i+2] +"|"+ tAx[i+3] + " = ");
                if(k==6)
                    System.out.println(
                            ImageBits.getBit(tAx[i]) +"|"+ ImageBits.getBit(tAx[i+1]) +"|"+ ImageBits.getBit(tAx[i+2]) +"|"+ ImageBits.getBit(tAx[i+3]) + " = " +
                            tSx[k-6] + "|"+ tSx[k-5] + "|"+ tSx[k-4] + "|"+ tSx[k-3] + "|"+ tSx[k-2] + "|"+ tSx[k-1] + "|"+ tSx[k] + "\n" );
                
            }
        }       
    */
        
        return Sx;
    }
}
