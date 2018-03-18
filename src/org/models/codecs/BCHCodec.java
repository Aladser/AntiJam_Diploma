package org.models.codecs;

import java.util.BitSet;
import org.models.BinOperations;
import org.models.NumberCoup;

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
        System.out.println( BinOperations.showBitSet(Sx) );
        BitSet Ax = new BitSet();
        int length = ((Sx.length()-1) * k) / n;
        //Sx.set(length);
        // Деление полиномов, перевот числа, прибавка нулей слева
        BitSet codeBlock = new BitSet();
        int iiblock; // int-код инфоблока
        BitSet biblock; // bin-код инфоблока 
        int w; // вес остатка
        PolynomDivision.Result divRes;  // результат деления
        for(int axi=0, sxi=0; sxi<Sx.length()-1; sxi+=n){
            codeBlock.clear(); codeBlock.set(n);
            for(int ci=0, i=sxi; i<sxi+n; i++, ci++) if(Sx.get(i)) codeBlock.set(ci);
            int iCodeBlock = BinOperations.binToDec(codeBlock);
            divRes = PolynomDivision.exec(iCodeBlock, n, Gx);
            w = BinOperations.decToBin(divRes.reminder).cardinality() - 1;
            if(w>1){ 
                iCodeBlock = BinOperations.binToDec( fixError(iCodeBlock, w) );
                divRes = PolynomDivision.exec(iCodeBlock, n, Gx);
            }
            iiblock = NumberCoup.exec(divRes.quotient, 2, k);
            biblock = BinOperations.decToBin(iiblock);
            biblock = BinOperations.addZeroToCode(biblock, k);
            for(int i=0; i<biblock.length()-1; i++, axi++) if(biblock.get(i)) Ax.set(axi);
        }
        return Ax;
    }
    
    @Override
    public BitSet fixError(int number, int w){
        PolynomDivision.Result quot = new PolynomDivision.Result();
        int shift = 0;
        while(w>1){
            shift++;
            number = BinOperations.shifLefttBits(number, n);
            quot = PolynomDivision.exec(number, n, 0b1011);
            w = BinOperations.decToBin(quot.reminder).cardinality() - 1; // вес остатка
        }
        number ^= quot.reminder;
        for(int i=0; i<shift; i++) number = BinOperations.shifRightBits(number, n);
        return BinOperations.decToBin(number);
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
    
    
}
