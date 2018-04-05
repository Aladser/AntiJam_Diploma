package org;

import java.util.BitSet;
import org.models.codecs.GaluaField;
import org.views.MainFrame;

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        BitSet arr = new BitSet();
        arr.clear(0);
        arr.clear(1);
        arr.set(2);
        arr.clear(3);
        arr.set(4);
        arr.clear(5);
        arr.clear(6);
        arr.set(7);
        arr.set(8);
        arr.clear(9);
        arr.clear(10);
        arr.set(11);
        arr.clear(12);
        arr.set(13);
        arr.clear(14);
        arr.clear(15);
        arr.set(16);
        
        GaluaField galua = new GaluaField(0b1011, 8);
        int[] pol1 = {1,4};
        int[] pol2 = {4,6,1};
        galua.multilpyPolynoms(pol1, pol2);
        
        /* Вызов главного окна */
        //pf = new org.views.ProgressFrame();
        //new MainFrame().setVisible(true);
    }
    
}
