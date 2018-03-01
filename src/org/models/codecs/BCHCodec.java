package org.models.codecs;

import java.util.BitSet;
//import org.models.ImageBits;

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
        for(int i=0, j=0; i<20; i+=4){
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
                
                // Таблица A(x)=S(x)
                /*if((j+1)%7 == 0 && j!=0){
                    System.out.print( 
                            ImageBits.getBit(Ax.get(i)) 
                            +"|"
                            + ImageBits.getBit(Ax.get(i+1))
                            +"|"
                            + ImageBits.getBit(Ax.get(i+2))
                            +"|"
                            + ImageBits.getBit(Ax.get(i+3))
                            +" = "
                            + ImageBits.getBit(Sx.get(j-6))
                            + ImageBits.getBit(Sx.get(j-5))
                            + ImageBits.getBit(Sx.get(j-4))
                            + ImageBits.getBit(Sx.get(j-3))
                            + ImageBits.getBit(Sx.get(j-2))
                            + ImageBits.getBit(Sx.get(j-1))
                            + ImageBits.getBit(Sx.get(j))
                            + "\n"
                    );
                }*/
            }
        }
        
        return Sx;
    }
}
