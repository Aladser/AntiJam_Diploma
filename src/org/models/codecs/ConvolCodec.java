package org.models.codecs;

import java.util.BitSet;

/** Кодек сверточного кода */
public class ConvolCodec extends Codec{
    /** Длина кодового слова */
    public final int N;
    /** Длина инфоблока */
    public final int K;
    /** Число бит в блоке A(x) */
    public final int NUM_BITS;
    private final int num_blocks;   // число блоков
    public final int[] syndroms;   // синдромы
    
    /**
     * @param n длина кодового слова
     * @param k длина инфослова
     * @param numbits число бит инфоблока
     */
    public ConvolCodec(int n, int k, int numbits){
        K = k;
        N = n;
        NUM_BITS = numbits;
        num_blocks = k / NUM_BITS;
        syndroms = createSyndrTable();
    }
    
    @Override
    public BitSet encode(BitSet Ax) {
        BitSet Cx = new BitSet();
        int ai=0, ci=0;
        // коррекция размера для деления без остатка
        int rem = NUM_BITS - (Ax.length()-1)%NUM_BITS; 
        if(rem != NUM_BITS) Ax.set(Ax.length()+rem-1);
        Cx.set((Ax.length()-1)*N/K);
        // кодирование
        int checkBit = 0;
        for(; ai<Ax.length()-1; ai++){
            // XOR инфобит
            if(Ax.get(ai)){
                Cx.set(ci);
                checkBit ^= 1;
            }
            ci++;
            // XOR предыдуших бит
            if((ai+1)%NUM_BITS==0){
                if((ai+1) >= 6){
                    if(Ax.get(ai-5)) checkBit^=1;
                    if(Ax.get(ai-4)) checkBit^=1;
                }
                if((ai+1)>6){
                    if(Ax.get(ai-6)) checkBit^=1;
                    if(Ax.get(ai-8)) checkBit^=1;
                }
                if(checkBit == 1) Cx.set(ci);
                ci++;
                checkBit = 0;
            }
        }
        return Cx;
    }

    @Override
    public BitSet decode(BitSet Cx) {
        BitSet Ax = new BitSet();
        Ax.set((Cx.length()-1)*K/N);
        int S=0, si=0;    // синдром
        int checkBit;
        for(int ai=0, ci=0; ci<Cx.length()-1; ai+=3, ci+=4){
            if(Cx.get(ci)) Ax.set(ai);
            if(Cx.get(ci+1)) Ax.set(ai+1);
            if(Cx.get(ci+2)) Ax.set(ai+2);
            checkBit = (Cx.get(ci)?1:0) ^ (Cx.get(ci+1)?1:0) ^ (Cx.get(ci+2)?1:0); // XOR инфобит
            if(ci >= 4) checkBit ^= (Cx.get(ci-4)?1:0) ^ (Cx.get(ci-3)?1:0);       // XOR бит предыдущих кадров
            if(ci > 4) checkBit ^= (Cx.get(ci-8)?1:0) ^ (Cx.get(ci-6)?1:0);        // XOR бит предыдущих кадров
            S += checkBit ^ (Cx.get(ci+3)?1:0);                                    // проверка на ошибку
            // если собран синдром
            if(si == 2){
                if(S==0) si=0;
                // Если синдром имеет 1 в старшем разряде
                else if(S >= 4){
                    int ind = -1;
                    for(int i=0; i<syndroms.length; i++) if(syndroms[i]==S) ind=i;
                    if(Ax.get(ai+ind-6)) Ax.clear(ai+ind-6);
                    else Ax.set(ai+ind-6);
                    S=0;
                    si=0;
                }
                // если синдром неполный, продолжаем его набор
                else S<<=1;
            }
            // продолжение набора синдрома
            else{
                S<<=1;
                si++;
            }
        }
        return Ax;
    }


    
    /**
     * Создает массив синдромов
     * @return 
     */
    private int[] createSyndrTable(){
        int[] res = new int[NUM_BITS * N/K];
        // E(x)
        BitSet Ax = new BitSet(); Ax.set(K);
        BitSet Ex = encode(Ax);
        // формирование синдромов для E(x)
        int S, si=0, checkBit=0; // синдром, i по S, бит четности
        // проход по E(x) с переносом вправо ошибочного бита при итерации
        for(int i=0; i<NUM_BITS * N/K; i++){
            Ex.set(i); // создает ошибочный бит     
            // декодирование
            S = 0; 
            for(int ai=0, ci=0; ci<Ex.length()-1; ci++, ai++){
                if(Ex.get(ci)) checkBit^=1; // XOR инфобит
                // XOR предыдущих бит
                if((ai+1)%NUM_BITS==0){
                    ci++;
                    if(ai+1 >= NUM_BITS*2){
                        if(Ex.get(ci-7)) checkBit^=1;
                        if(Ex.get(ci-6)) checkBit^=1;
                    }
                    if(ai+1 > NUM_BITS*2){
                        if(Ex.get(ci-11)) checkBit^=1;
                        if(Ex.get(ci-9)) checkBit^=1;
                    }
                    checkBit ^= Ex.get(ci)?1:0;
                    S += checkBit;
                    si++;
                    if(si == num_blocks){
                        res[i] = S;
                        si=0;
                    }
                    S<<=1;
                    checkBit=0;
                }
            }            
            Ex.clear(i); // исправляет ошибочный бит
        }
        return res;
    }
    
}
