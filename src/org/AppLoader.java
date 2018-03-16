package org;

import java.util.BitSet;
import org.models.BinDecTranslation;
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
        Codec codec = new BCHCodec(gx, 7, 4);
        BitSet number = BinDecTranslation.decToBin(1, 4);
        BitSet code = codec.encode(number);
        System.out.println(ArrayShow.execute(code, 7) + " = " + ArrayShow.execute(number, 4) + "\n-------");
        
        int j = code.length()- 2;
        PolynomDivision.Result res;       
        BitSet[] codes = new BitSet[7];
        for(int i=0; i<codes.length; i++){
            codes[i] = new BitSet();
            codes[i].set(7);
            for(int k=0; k<code.length(); k++) if( code.get(k) ) codes[i].set(k);
            if(codes[i].get(i)) codes[i].clear(i);
            else codes[i].set(i);
        }
        
        PolynomDivision.Result[] quots = new PolynomDivision.Result[7];
        for(int i=0; i<quots.length; i++){
            quots[i] = PolynomDivision.execute( BinDecTranslation.binToDec(codes[i]), 7, gx );
            quots[i].quotient = NumberCoup.execute(quots[i].quotient, 2, 4);
        }
        
        BitSet a;
        for(int i=0; i<quots.length; i++){
            System.out.print(ArrayShow.execute(codes[i], 7) + " = ");
            a = BinDecTranslation.decToBin(quots[i].quotient, 4);
            System.out.print( ArrayShow.execute(a, 7) );
            a = BinDecTranslation.decToBin(quots[i].reminder, 3);
            System.out.print( " ост." + ArrayShow.execute(a, 7) );
            System.out.print( ", w = " + (a.cardinality()-1) );
            System.out.println( ", orders = " + BinDecTranslation.countBinaryOrders(quots[i].reminder));
        }
        
        
        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
            //new MainFrame().setVisible(true);
        });
    }
    
}
