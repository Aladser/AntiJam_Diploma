package org;

import java.util.BitSet;
import org.models.BinOperations;
import org.models.codecs.ConvolCodec;


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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(org.views.MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /*
        ConvolCodec codec = new ConvolCodec(12, 9, 3);
        int[] arr = {1,1,1, 0,0,0, 1,1,1, 0,0,0, 1,1,1, 0,0,0, 1,1,1, 0,0,0};
        BitSet Ax = new BitSet();
        Ax.set(arr.length);
        for(int i=0; i<Ax.length()-1; i++) if(arr[i]==1) Ax.set(i);
        BitSet Cx = codec.encode(Ax);
        System.out.println("---- ---- x---");
        Cx.clear(8);
        codec.decode(Cx);
        Cx.set(8);
        System.out.println("---- ---- -x--");
        Cx.clear(9);
        codec.decode(Cx);
        Cx.set(9);
        System.out.println("---- ---- --x-");
        Cx.clear(10);
        codec.decode(Cx);
        Cx.set(10);
        System.out.println("---- ---- ---x");
        Cx.clear(11);
        codec.decode(Cx);
        Cx.set(11);
        */
        /* Вызов главного окна */
        new org.views.MainFrame().setVisible(true);
    }
    
}
