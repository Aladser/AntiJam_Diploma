package org.models;
import java.util.BitSet;
import java.util.Random;

// Transmission media. Responsible for message errors
public class TransmissionMedia {
    public BitSet message;
    private boolean TransmissionFlag;
    private double noiseLevel;
    private int numErrors = 0;
	
    public TransmissionMedia(double noiseLevel){
            this.noiseLevel = noiseLevel;
            TransmissionFlag = false;
    }
    public TransmissionMedia(){
            this.noiseLevel = 0.00001;
            TransmissionFlag = false;
    }
    
    // Добаляет шум к сообшению
    public int imposeNoise(BitSet message){
            numErrors = 0;
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
	
    public double getNoiseLevel(){
            return noiseLevel;
	}

    public void setTransmission(boolean flag){
        TransmissionFlag = flag;
    }
    
    public boolean isTransmission(){
        return TransmissionFlag;
    }

    // Equals two bit arrays
    public int equals(BitSet inm, BitSet outm){
            int result = 0;
            for(int i=0; i<inm.length()-2;i++){ if(inm.get(i) != outm.get(i)) result++; }
            return result;
	}

}
