package org.models.codecs;

import java.util.BitSet;
import org.models.PolynomDivision;

/**
 * Кодек БЧХ кода
 */
public abstract class BCHCodec extends Codec{
    // Порождающий многочлен g(x)
    public static final int Gx = 0b1011; 
    
    // Кодирование
    // Ax - информация
    // Sx - кодовое слово
    public static BitSet encode(BitSet Ax){
        int length = ((Ax.length()) * 7) / 4; // размер = n / n - k
        BitSet Sx = new BitSet();
        // S[length] - флаг конца массива
        Sx.set( length );

        // Mx - Образующая матрица M(x). 
        // Первое число - строки, второе число - столбцы
        boolean[][] Mx = new boolean[4][7]; 
        Mx[0][0] = false; //0
        Mx[0][1] = false; //0
        Mx[0][2] = false; //0
        Mx[0][3] = true;  //1
        Mx[0][4] = false; //0
        Mx[0][5] = true;  //1
        Mx[0][6] = true;  //1
        
        Mx[1][0] = false; //0
        Mx[1][1] = false; //0
        Mx[1][2] = true;  //1
        Mx[1][3] = false; //0
        Mx[1][4] = true;  //1
        Mx[1][5] = true;  //1
        Mx[1][6] = false; //0

        Mx[2][0] = false; //0
        Mx[2][1] = true;  //1
        Mx[2][2] = false; //0
        Mx[2][3] = true;  //1
        Mx[2][4] = true;  //1
        Mx[2][5] = false; //0
        Mx[2][6] = false; //0
        
        Mx[3][0] = true;  //1
        Mx[3][1] = false; //0
        Mx[3][2] = true;  //1
        Mx[3][3] = true;  //1
        Mx[3][4] = false; //0
        Mx[3][5] = false; //0
        Mx[3][6] = false; //0
               
        boolean r = false;
        for(int i=0, j=0; i<Ax.length(); i+=4){
            for(int k=0; k<7; k++, j++){
                if( Ax.get(i) & Mx[0][k] ) Sx.set(j);
                else Sx.clear(j);             
                
                if( Ax.get(i+1) & Mx[1][k] ) r = true;
                if( Sx.get(j)^r ) Sx.set(j);
                else Sx.clear(j);
                r = false;
                
                if( Ax.get(i+2) & Mx[2][k] ) r = true;
                if( Sx.get(j)^r ) Sx.set(j);
                else Sx.clear(j);
                r = false;
                
                if( Ax.get(i+3) & Mx[3][k] ) r = true;
                if( Sx.get(j)^r ) Sx.set(j);
                else Sx.clear(j);
                r = false;
            }
        }        
        return Sx;
    }
    
    /**
     * 
     * @param encodeMessage - закодированный битовый массив
     * @return информационный блок
     */
    public static BitSet decode(BitSet encodeMessage){
        // {1, 0, 1, 0, 0, 1, 1};
        BitSet code = new BitSet();
        code.set(7);
        
        code.set(0);
        code.clear(1);
        code.clear(2);
        code.set(3);
        code.set(4);
        code.set(5);
        code.clear(6);
        
        BitSet infocode = PolynomDivision.execute( code );     
        return infocode;
    }
    
}
