package org.models.codecs;

import java.util.BitSet;
import org.models.BinDecTranslation;
import org.models.PolynomDivision;

/**
 * Кодек БЧХ кода
 */
public abstract class BCHCodec extends Codec{
    // длина кодового слова
    private static final int n = 7;
    // длина инфокодового слова
    private static final int k = 4;
    // порождающий многочлен
    public static final int Gx = 0b1011;
    // порождающий многочлен (bin)
    private static final BitSet GxBin = BinDecTranslation.decToBin(Gx, k);
    // кодирующая матрица
    private static final boolean[][] Mx = getCodingMatrix();
    
    /** Кодирование
     * @param Ax - инфокодовое слово
     * @return Sx - кодовое слово
     */
    public static BitSet encode(BitSet Ax){
        BitSet Sx = new BitSet();
        int length = ((Ax.length()-1) * n) / k;
        Sx.set(length);
        int sxi = 0;
        // умножение матриц Ax * Mx
        // i - проход по  A(x)
        // mxj - проход по столбцам M(x)
        // mxi - проход по столбцу M(x)
        boolean[] comp = new boolean[k];
        for(int axi=0; axi<Ax.length()-1; axi+=k){            
            for(int mxj=0; mxj<n; mxj++, sxi++){
                for(int mxi=0; mxi<k; mxi++) comp[mxi] = Ax.get(axi+mxi) & Mx[mxi][mxj];     
                if(comp[0]^comp[1]^comp[2]^comp[3]) Sx.set(sxi);
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
