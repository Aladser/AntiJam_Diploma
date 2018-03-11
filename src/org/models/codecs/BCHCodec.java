package org.models.codecs;

import java.util.BitSet;
import org.models.BinDecTranslation;
import org.models.PolynomDivision;

/**
 * Кодек БЧХ кода
 * Gx, GxBin - порождающий полином
 * n - длина кодового слова
 * k - длина инфокодового слова
 * Mx - кодирующая матрица
 */
public abstract class BCHCodec extends Codec{
    // Порождающий многочлен g(x)
    public static final int Gx = 0b1011;
    private static final BitSet GxBin = BinDecTranslation.decToBin(Gx);  
    private static final int n = 7;
    private static final int k = 4;
    private static final boolean[][] Mx = getCodingMatrix();
    
    /** Кодирование
     * @param Ax - инфокодовое слово
     * @return Sx - кодовое слово
     */
    public static BitSet encode(BitSet Ax){
        int length = ((Ax.length()) * 7) / 4; // размер = Ax.length * (n / k)
        BitSet Sx = new BitSet();
        Sx.set(length);
        
        boolean r = false;
        // i - индекс по Ax
        // j - индек по Sx
        // k - индекс умножения Ax на столбец Mx
        // умножение матриц Ax * Mx
        for(int j=0, i=0; i<Ax.length(); i+=4){
            for(int k=0; k<7; k++, j++){
                if( Ax.get(i) & Mx[0][k] ) Sx.set(j);             
                
                if( Ax.get(i+1) & Mx[1][k] ) r = true;
                if( Sx.get(j)^r ) Sx.set(j);
                r = false;
                
                if( Ax.get(i+2) & Mx[2][k] ) r = true;
                if( Sx.get(j)^r ) Sx.set(j);
                r = false;
                
                if( Ax.get(i+3) & Mx[3][k] ) r = true;
                if( Sx.get(j)^r ) Sx.set(j);
                r = false;
            }
        }       
        return Sx;
    }
    
    public static BitSet decode(BitSet msg){
        PolynomDivision.execute(BinDecTranslation.binToDec(msg), Gx);   
        return new BitSet();
    }
    
    /**
     * Создает кодирующую матрицу
     * @return
     */
    private static boolean[][] getCodingMatrix(){
        boolean[][] Mx = new boolean[k][n];        
        //  по строкам матрицы идет сдвиг влево gx
        // 00gx
        // 0gx0
        // gx00
        for(int i=0, numZero=n-k; i<k; i++){
            for(int j=numZero, z=0; j<n; j++)
                if(z<GxBin.length()-1) Mx[i][j] = GxBin.get(z++);
            numZero--;
        }         
        return Mx;
    }
}
