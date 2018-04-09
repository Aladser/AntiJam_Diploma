package org;

import java.util.BitSet;
import org.models.codecs.BinOperations;
import org.models.codecs.GaluaField;
import org.models.codecs.RSCodec;

/**
 * Точка входа
 */
public class AppLoader {
    public static org.views.ProgressFrame pf;
    public static void main(String args[]){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(org.views.MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        GaluaField galua = new GaluaField(0b1011, 8);
        RSCodec rsc = new RSCodec(4, 2, 3);
        
        // Создание исходного битового массива
        int[] src = {0,0,1, 0,1,0, 0,1};
        BitSet binSrc = new BitSet();
        binSrc.set(src.length);
        for(int i=0; i<src.length; i++) if(src[i]==1) binSrc.set(i);
        rsc.correctBitSetLength(binSrc, 3);
        System.out.println( BinOperations.showBitSet(binSrc, 3) );
        int[] arr = rsc.createIntArray(binSrc);
        for(int i=0; i<arr.length; i++) System.out.print(arr[i]+", ");
        System.out.println();
        
        
        /*int[] pol = new int[2];
        int[] code;
        int[] a = new int[2];
        for(int i=0; i<8; i++){
            pol[0] = i;
            a[0] = i;
            for(int j=0; j<8; j++){
                pol[1] = j;
                a[1] = j;
                System.out.print( galua.polynomToString(pol) );
                System.out.print("*");
                System.out.print( galua.polynomToString(galua.GX) );
                System.out.print("=");
                code =  galua.multiplyPolynoms(pol, galua.GX);
                System.out.print( galua.polynomToString(code) );
                System.out.print("   ");
                System.out.print( galua.polynomToString(code) );
                System.out.print("/");
                System.out.print( galua.polynomToString(galua.GX) );
                System.out.print("=");
                code = galua.dividePolynoms(code, galua.GX);
                System.out.println( galua.polynomToString(code) );
            }
        }*/
        
        
        
        
        
        /* Вызов главного окна */
        //pf = new org.views.ProgressFrame();
        //new org.views.MainFrame().setVisible(true);
    }
    
    
    
}
