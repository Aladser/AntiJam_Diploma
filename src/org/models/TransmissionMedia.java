package org.models;
import java.util.BitSet;
import java.util.Random;

// Канал передачи
public class TransmissionMedia {
    public BitSet message;
    private double noiseLevel;
    
    public TransmissionMedia(){
        this.noiseLevel = 0.0001;
    }
    
    // Наложить шум на сообщение
    public int imposeNoise(){
        int numErrors = 0;
        Random rnd = new Random(System.currentTimeMillis());
        int errorIndex;
        int convNoiseLevel = (int)(1 / noiseLevel);
        for (int i = 0; i < message.length() - 1; i += convNoiseLevel){
            if ((i + convNoiseLevel) < message.length() - 1) errorIndex = i + rnd.nextInt( convNoiseLevel );
            else errorIndex = i + rnd.nextInt( message.length() - 1 - i );
            message.flip(errorIndex);
            numErrors++;
        }         
        return numErrors;
    }
    
    public int setNoiseLevel(double value){
        if(value < 0 || value >= 1) return -1;
        noiseLevel = value;
        return 0;
    }
	
    // Получить уровень шума
    public double getNoiseLevel(){
        return noiseLevel;
    }
    
    // Сравнение двух битовых массивов
    public static int equals(BitSet inm, BitSet outm){
        int result = 0;
        for(int i=0; i<inm.length()-2;i++){ if(inm.get(i) != outm.get(i)) result++; }
        return result;
    }

}
