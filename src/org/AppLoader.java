package org;

import java.util.BitSet;
import org.models.BinOperations;

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
        
        
        org.models.codecs.Codec rsc = new org.models.codecs.RSCodec(4,2,3);
        int[] src = {0,0,1,0,1,0,0,1,1,1,0,0,1,0,1,0};
        BitSet bits = new BitSet();
        bits.set(src.length);
        for(int i=0; i<bits.length()-1; i++) if(src[i]==1) bits.set(i);
        System.out.println(BinOperations.showBitSet(bits, 3)+"\n");
        BitSet bits1 = rsc.encode(bits);
        BitSet bits2 = rsc.encode(bits);
        
        System.out.println(BinOperations.showBitSet(bits1, 3)); 
        for(int pos, i=0; i<bits1.length()-1; i+=20){
            pos = (int) (Math.random()*8);
            if(bits1.get(i)) bits1.clear(i);
            else bits1.set(i);
        }
        int n=0;
        for(int i=0; i<bits1.length(); i++) if(bits1.get(i)!=bits2.get(i))n++;
        System.out.println(n);
        System.out.println(BinOperations.showBitSet(bits1, 3)+"\n");
        
        bits = rsc.decode(bits1);
        System.out.println(BinOperations.showBitSet(bits, 3));
        
        /* Вызов главного окна */
        new org.views.MainFrame().setVisible(true);
    } 
}
