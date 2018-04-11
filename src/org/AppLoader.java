package org;

import java.util.BitSet;
import org.models.codecs.BinOperations;

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
        
        org.models.codecs.Codec rsc = new org.models.codecs.RSCodec(4,2,3);
        int[] src = {0,0,1,0,1,0,0,1,1,1,0,0,1,0,1,0};
        BitSet bits = new BitSet();
        bits.set(src.length);
        for(int i=0; i<bits.length()-1; i++) if(src[i]==1) bits.set(i);
        System.out.println(BinOperations.showBitSet(bits, 3));
        BitSet work = rsc.encode(bits);
        work = rsc.decode(work);
        System.out.println(BinOperations.showBitSet(work, 3));
        
        /* Вызов главного окна */
        //pf = new org.views.ProgressFrame();
        //new org.views.MainFrame().setVisible(true);
    }
    
    
    
}
