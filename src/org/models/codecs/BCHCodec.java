package org.models.codecs;

import java.util.BitSet;

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
        int[] tAx = {1,1,1,0, 0,1,0,0};
        int[] tSx = new int[7];
        int R;
                
        for(int i=0; i<8; i+=4){
            for(int k=0; k<7; k++){
                R = ((Ax.get(i) == true) ? 1 : 0) * Mx[0][k]; 
                R ^= ((Ax.get(i+1) == true) ? 1 : 0) * Mx[1][k];  
                R ^= ((Ax.get(i+2) == true) ? 1 : 0) * Mx[2][k];
                R ^= ((Ax.get(i+3) == true) ? 1 : 0) * Mx[3][k];
                tSx[k] = R;
                System.out.print(R);
            }
            System.out.print(" ");
        }
        
    
        
        return Sx;
    }
}
