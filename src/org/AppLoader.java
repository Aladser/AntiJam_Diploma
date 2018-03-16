package org;

import java.util.BitSet;
import org.models.BinOperations;
import org.models.NumberCoup;
import org.models.codecs.PolynomDivision;
import org.models.codecs.BCHCodec;
import org.models.codecs.Codec;
import org.views.ArrayShow;
import org.views.MainFrame;

/**
 * Точка входа
 */
public class AppLoader {
    public static void main(String args[]){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // ТЕСТ 
        int gx = 0b1011;
        int num = 7;
        Codec codec = new BCHCodec(gx, 7, 4);
        
        BitSet number = BinOperations.decToBin(num, 4);
        BitSet binNumber = codec.encode(number);
        System.out.println(ArrayShow.exec(binNumber) + " = " + ArrayShow.exec(number) + "\n-------");
              
        BitSet[] codes = new BitSet[7];
        for(int i=0; i<codes.length; i++){
            codes[i] = new BitSet();
            codes[i].set(7);
            for(int k=0; k<binNumber.length(); k++) if( binNumber.get(k) ) codes[i].set(k);
            if(codes[i].get(i)) codes[i].clear(i);
            else codes[i].set(i);
        }      
        
        BitSet code1 = codes[0];
        int icode1 = BinOperations.binToDec(code1);
        System.out.print(ArrayShow.exec(code1) + " = ");
        PolynomDivision.Result res = PolynomDivision.execute(icode1, 7, 0b1011);
        BitSet quot = BinOperations.decToBin(res.quotient, 4);
        System.out.println( ArrayShow.exec(quot) + " ост." + Integer.toBinaryString(res.reminder) + "\n-------");
        
        codec.fixError(icode1, 2);
        
        BinOperations.shifLefttBits(icode1, 7);
        
        
        
        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
            //new MainFrame().setVisible(true);
        });
    }
    
}
