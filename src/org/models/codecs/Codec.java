package org.models.codecs;

import java.util.BitSet;

/**
 * Абстрактный класс кодека
 */
abstract public class Codec {
    public abstract BitSet encode(BitSet message);       // кодирование
    public abstract BitSet decode(BitSet encodeMessage); // декодирование
    public abstract int fixError(int number, int w);     // исправление ошибок
    public abstract int getGX();                         // показать полином
    
    // Преобразует логическое в целое
    protected static int getInteger(boolean value){
        return value ? 1 : 0;
    }
}
