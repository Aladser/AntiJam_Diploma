package org.models.codecs;

import java.util.BitSet;
import org.models.BinDecTranslation;
import org.models.NumberCoup;
import org.models.PolynomDivision;

/**
 * Кодек БЧХ кода
 */
public class BCHCodec extends Codec{
    private final int n; // длина кодового слова
    private final int k; // длина инфоблока
    private final int Gx; // порождающий многочлен
    private final BitSet GxBin; // порождающий многочлен (bin)
    private final boolean[][] Mx; // кодирующая матрица
    
    /**
     * @param Gx порождающий полином
     * @param n длина кодового слова
     * @param k длина инфоблока
     */
    public BCHCodec(int Gx, int n, int k){
        this.Gx = Gx;
        this.GxBin = BinDecTranslation.decToBin(Gx, k);
        this.n = n;
        this.k = k;
        this.Mx = getCodingMatrix();
    }
    
    /** Кодирование
     * @param Ax инфоблок
     * @return Sx - кодовое слово
     */
    @Override
    public BitSet encode(BitSet Ax){
        BitSet Sx = new BitSet();
        int length = ((Ax.length()-1) * n) / k;
        Sx.set(length);
        // умножение матриц Ax * Mx
        // axi - индекс по  A(x)
        // sxi - индекс по S(x)
        // mxj - проход по строке M(x)
        // mxi - проход по столбцу M(x)
        boolean[] comp = new boolean[k];
        for(int sxi = 0, axi=0; axi<Ax.length()-1; axi+=k){            
            for(int mxj=0; mxj<n; mxj++, sxi++){
                for(int mxi=0; mxi<k; mxi++) comp[mxi] = Ax.get(axi+mxi) & Mx[mxi][mxj];     
                if(comp[0]^comp[1]^comp[2]^comp[3]) Sx.set(sxi);
            }              
        }
        return Sx;
    }
    
    /**
     * Декодирование
     * @param Sx -закодированное сообщение
     * @return Ax - инфоблок 
     */
    @Override
    public BitSet decode(BitSet Sx){
        BitSet Ax = new BitSet();
        int length = ((Sx.length()-1) * k) / n;
        Sx.set(length);
        // Деление полиномов, перевот числа, прибавка нулей слева
        BitSet codeBlock = new BitSet();
        int iiblock; // int-код инфоблока
        BitSet biblock; // bin-код инфоблока 
        for(int axi=0, sxi=0; sxi<Sx.length()-1; sxi+=n){
            codeBlock.clear();
            codeBlock.set(n);
            for(int ci=0, i=sxi; i<sxi+n; i++, ci++) if(Sx.get(i)) codeBlock.set(ci);
            PolynomDivision.Result divResult = PolynomDivision.execute(BinDecTranslation.binToDec(codeBlock), n, Gx);
            iiblock = NumberCoup.execute(divResult.quotient, 2, k);
            biblock = BinDecTranslation.decToBin(iiblock);
            biblock = addZeroToCode(biblock, k);
            for(int i=0; i<biblock.length()-1; i++, axi++) if(biblock.get(i)) Ax.set(axi);
        }
        return Ax;
    }
    
    /**
     * Возвращает порождающий полином
     * @return 
     */
    @Override
    public int getGX(){ return Gx; }
    
    /**
     * Создает кодирующую матрицу
     * @return
     */
    private boolean[][] getCodingMatrix(){
        boolean[][] mx = new boolean[k][n];        
        //  по строкам матрицы идет сдвиг влево gx
        // 00gx
        // 0gx0
        // gx00
        for(int i=0, numZero=n-k; i<k; i++){
            for(int j=numZero, z=0; j<n; j++)
                if(z<GxBin.length()-1) mx[i][j] = GxBin.get(z++);
            numZero--;
        }         
        return mx;
    }
    
    /**
     * Добавляет недостающие нули коду
     * @param code код
     * @param numOrders требуемая разрядность
     * @return 
     */
    private static BitSet addZeroToCode(BitSet code, int numOrders){
        BitSet result = new BitSet();
        result.set(numOrders);
        int j = numOrders - code.length() + 1;
        for(int i=0; i<code.length()-1; i++, j++)
            if(code.get(i)) result.set(j);
        return result;
    }
}
