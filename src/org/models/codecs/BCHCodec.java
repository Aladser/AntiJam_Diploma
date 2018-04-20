package org.models.codecs;

import org.models.BinOperations;
import java.util.BitSet;
import org.models.PolynomArith;

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
        this.GxBin = BinOperations.decToBin(Gx, k);
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
        Ax.set( ((Sx.length()-1) * k) / n );
        // Деление полиномов, перевот числа, прибавка нулей слева
        int iCodeBlock;                 // int-версия кодового слова
        int w;                          // вес остатка
        BitSet infoBlock;               // инфоблок 
        PolynomArith.Result divRes;  // результат деления
        for(int axi=0, exp, sxi=0; sxi<Sx.length()-1; sxi+=n){
            exp =(int) Math.pow(2, n-1); // степень разряда числа
            iCodeBlock = 0;
            for(int i=sxi; i<sxi+n; i++){
                if(Sx.get(i))iCodeBlock+=exp;
                exp/=2;
            }
            divRes = PolynomArith.divide(iCodeBlock, n, Gx);
            w = BinOperations.decToBin(divRes.reminder).cardinality() - 1;
            if(w>1){ 
                iCodeBlock = fixError(iCodeBlock, w);
                divRes = PolynomArith.divide(iCodeBlock, n, Gx);
            }
            iCodeBlock = BinOperations.coupNumber(divRes.quotient ,k);
            infoBlock = BinOperations.decToBin(iCodeBlock);
            infoBlock = BinOperations.addZeroToCode(infoBlock, k);
            for(int i=0; i<infoBlock.length()-1; i++, axi++) if(infoBlock.get(i)) Ax.set(axi);
        }
        return Ax;
    }
    
    /**
     * Исправляет ошибку кодового слова
     * @param number число
     * @param w вес остатка
     * @return
     */
    @Override
    public int fixError(int number, int w){
        PolynomArith.Result quot = new PolynomArith.Result();
        int shift = 0;
        while(w>1){
            shift++;
            number = BinOperations.shifLefttBits(number, n);
            quot = PolynomArith.divide(number, n, 0b1011);
            w = BinOperations.decToBin(quot.reminder).cardinality() - 1; // вес остатка
        }
        number ^= quot.reminder;
        for(int i=0; i<shift; i++) number = BinOperations.shifRightBits(number, n);
        return number;
    }    
    
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
    
    
}
