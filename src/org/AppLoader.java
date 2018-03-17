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
        int n = 7;
        Codec codec = new BCHCodec(gx, n, 4);
        BitSet orignumber = BinOperations.decToBin(7, 4);
        BitSet origcode = codec.encode(orignumber);
        System.out.println(ArrayShow.exec(origcode) + " = " + ArrayShow.exec(orignumber) + "\n-------");    
        BitSet[] codes = new BitSet[n];
        for(int i=0; i<codes.length; i++){
            codes[i] = new BitSet(); codes[i].set(7);
            for(int k=0; k<origcode.length(); k++) if(origcode.get(k)) codes[i].set(k);
            if(codes[i].get(i)) codes[i].clear(i);
            else codes[i].set(i);
        }
        int[] icodes = new int[n];
        for(int i=0; i<icodes.length; i++) icodes[i] = BinOperations.binToDec(codes[i]);
        PolynomDivision.Result[] res = new PolynomDivision.Result[n];
        int[] w = new int[n];
        for(int i=0; i<res.length; i++){ 
            res[i] = PolynomDivision.exec(icodes[i], n, gx);
            res[i].quotient = NumberCoup.exec(res[i].quotient, 2, 4);
            w[i] = BinOperations.decToBin(res[i].reminder).cardinality() - 1;
            if(w[i]>1){ 
                icodes[i] = BinOperations.binToDec( codec.fixError(icodes[i], w[i]) );
                res[i] = PolynomDivision.exec(icodes[i], n, gx);
                res[i].quotient = NumberCoup.exec(res[i].quotient, 2, 4);
            }
        }
        
        BitSet r;
        for(int i=0; i<codes.length; i++){
            System.out.print( ArrayShow.exec(codes[i]) + "(раз."+ BinOperations.equalBitSets(origcode, codes[i]) +")");
            System.out.print( " = ");
            r = BinOperations.decToBin(res[i].quotient, 4);
            System.out.println( ArrayShow.exec(r) );
        }
        

        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
}
