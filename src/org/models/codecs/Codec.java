package org.models.codecs;

import java.util.BitSet;

/**
 * Абстрактный класс кодека
 * @author Aladser
 */
abstract public class Codec {
    // кодирование
    public static BitSet encode(BitSet message){
        return new BitSet();
    };
    // декодирование
    public static BitSet decode(BitSet encodeMessage){
        return new BitSet();
    };
    // Преобразует логическое в целое
    protected static int getInteger(boolean value){
        return value ? 1 : 0;
    }
}
