package org.views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.models.ImageBits;
import org.models.TransmissionMedia;

/**
 * Главное окно
 * @author Aladser
 */
public class MainFrame extends javax.swing.JFrame {
    private java.awt.image.BufferedImage image;  // Исходное изображение
    private ImageBits imageBits;                 // Битовый массив изображения
    private ImageBits recImageBits;              // Полученный массив изображения    
    private final TransmissionMedia transmedia;  // Канал передачи данных
    private int numErrors;                       // Число ошибок после передачи
    
    public MainFrame(){
        initComponents();
        transmedia = new TransmissionMedia();
        kerrLabel2.setText( Double.toString(transmedia.getNoiseLevel()) );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileOpenButton = new javax.swing.JButton();
        externalInfoPanel = new javax.swing.JPanel();
        clearTextPanel = new javax.swing.JButton();
        InfoScrollPanel = new javax.swing.JScrollPane();
        infoPanel = new javax.swing.JTextArea();
        extrernalImagePanel = new javax.swing.JPanel();
        imagePanel = new javax.swing.JLabel();
        graphicButton = new javax.swing.JButton();
        noisePanel = new javax.swing.JPanel();
        coderButton = new javax.swing.JButton();
        addNoiseButton = new javax.swing.JButton();
        decoderButton = new javax.swing.JButton();
        codecPanel = new javax.swing.JPanel();
        minusNoiseButton = new javax.swing.JButton();
        plusNoiseButton = new javax.swing.JButton();
        kerrLabel = new javax.swing.JLabel();
        kerrLabel2 = new javax.swing.JLabel();
        selectCodecComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Кодеки");
        setResizable(false);

        fileOpenButton.setText("Открыть файл");
        fileOpenButton.setFocusPainted(false);
        fileOpenButton.setPreferredSize(new java.awt.Dimension(107, 25));
        fileOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileOpenButtonActionPerformed(evt);
            }
        });

        externalInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Информация", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12))); // NOI18N
        externalInfoPanel.setToolTipText("");
        externalInfoPanel.setName(""); // NOI18N

        clearTextPanel.setText("Очистить");
        clearTextPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTextPanelActionPerformed(evt);
            }
        });

        infoPanel.setColumns(20);
        infoPanel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        infoPanel.setRows(5);
        infoPanel.setBorder(null);
        InfoScrollPanel.setViewportView(infoPanel);

        javax.swing.GroupLayout externalInfoPanelLayout = new javax.swing.GroupLayout(externalInfoPanel);
        externalInfoPanel.setLayout(externalInfoPanelLayout);
        externalInfoPanelLayout.setHorizontalGroup(
            externalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, externalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InfoScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(externalInfoPanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(clearTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        externalInfoPanelLayout.setVerticalGroup(
            externalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, externalInfoPanelLayout.createSequentialGroup()
                .addComponent(InfoScrollPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearTextPanel)
                .addContainerGap())
        );

        extrernalImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Изображение", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12))); // NOI18N
        extrernalImagePanel.setPreferredSize(new java.awt.Dimension(650, 500));

        imagePanel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        imagePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagePanel.setToolTipText("");
        imagePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        imagePanel.setFocusable(false);
        imagePanel.setOpaque(true);

        javax.swing.GroupLayout extrernalImagePanelLayout = new javax.swing.GroupLayout(extrernalImagePanel);
        extrernalImagePanel.setLayout(extrernalImagePanelLayout);
        extrernalImagePanelLayout.setHorizontalGroup(
            extrernalImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extrernalImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                .addContainerGap())
        );
        extrernalImagePanelLayout.setVerticalGroup(
            extrernalImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extrernalImagePanelLayout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        graphicButton.setText("График");
        graphicButton.setEnabled(false);
        graphicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphicButtonActionPerformed(evt);
            }
        });

        noisePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        coderButton.setText("Кодер и Отправка");
        coderButton.setEnabled(false);
        coderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coderButtonActionPerformed(evt);
            }
        });

        addNoiseButton.setText("Наложить шум");
        addNoiseButton.setEnabled(false);
        addNoiseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNoiseButtonActionPerformed(evt);
            }
        });

        decoderButton.setText("Получение и декодер");
        decoderButton.setEnabled(false);
        decoderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decoderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout noisePanelLayout = new javax.swing.GroupLayout(noisePanel);
        noisePanel.setLayout(noisePanelLayout);
        noisePanelLayout.setHorizontalGroup(
            noisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(noisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(decoderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addNoiseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        noisePanelLayout.setVerticalGroup(
            noisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addNoiseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decoderButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        codecPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        minusNoiseButton.setText("Шум \\\\//");
        minusNoiseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusNoiseButtonActionPerformed(evt);
            }
        });

        plusNoiseButton.setText("Шум //\\\\");
            plusNoiseButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    plusNoiseButtonActionPerformed(evt);
                }
            });

            kerrLabel.setText("K ош");
            kerrLabel.setPreferredSize(new java.awt.Dimension(34, 20));

            kerrLabel2.setBackground(new java.awt.Color(255, 255, 255));
            kerrLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            kerrLabel2.setPreferredSize(new java.awt.Dimension(34, 20));

            javax.swing.GroupLayout codecPanelLayout = new javax.swing.GroupLayout(codecPanel);
            codecPanel.setLayout(codecPanelLayout);
            codecPanelLayout.setHorizontalGroup(
                codecPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(codecPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(codecPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(minusNoiseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plusNoiseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(codecPanelLayout.createSequentialGroup()
                            .addComponent(kerrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(kerrLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            codecPanelLayout.setVerticalGroup(
                codecPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(codecPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(codecPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kerrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kerrLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(plusNoiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(minusNoiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            selectCodecComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Хэмминга", "Рида-Соломона" }));

            jLabel1.setBackground(new java.awt.Color(255, 255, 255));
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("Кодек");
            jLabel1.setMaximumSize(new java.awt.Dimension(32, 20));
            jLabel1.setPreferredSize(new java.awt.Dimension(32, 20));

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(noisePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(codecPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fileOpenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(graphicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectCodecComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(externalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(extrernalImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(externalInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(fileOpenButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(graphicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(selectCodecComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(noisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(codecPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 265, Short.MAX_VALUE))
                        .addComponent(extrernalImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    // Открыть файл
    private void fileOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileOpenButtonActionPerformed
        // создание FileChooser;
        javax.swing.filechooser.FileNameExtensionFilter filefilter; 
        filefilter = new javax.swing.filechooser.FileNameExtensionFilter("Изображения (.jpg,.png,.bmp)", "jpg", "png", "bmp");
        javax.swing.JFileChooser filechooser = new javax.swing.JFileChooser("C:\\");
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.addChoosableFileFilter(filefilter);
        int ret = filechooser.showDialog(null, "Открыть файл");
        
        // если выбран файл
        if (ret == javax.swing.JFileChooser.APPROVE_OPTION) {
            try {  
                image = ImageIO.read(filechooser.getSelectedFile());
                imageBits = new ImageBits(image);
                ArrayShow.show(imageBits.bits, 48, 8, "Исходный битовый массив");
            } catch (IOException ex) {
                Logger.getLogger("Не удалось прочитать файл");
            }
            try {
                // Проверка, что bitImages верный
                imagePanel.setIcon( org.models.ImageResizing.execute( imageBits.toImage(), imagePanel.getWidth(), imagePanel.getHeight() ) );
            } catch (IOException ex) {
                System.out.println("Ошибка создания рисунка");
            }
            infoPanel.append("   Количество цветов = " + 3 + "\n");
            coderButton.setEnabled(true);
            graphicButton.setEnabled(true);
        }
    }//GEN-LAST:event_fileOpenButtonActionPerformed

    // Нажать на кнопку "Очистить"
    private void clearTextPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearTextPanelActionPerformed
        infoPanel.setText("");
    }//GEN-LAST:event_clearTextPanelActionPerformed

    // Нажать на кнопку "График"
    private void graphicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphicButtonActionPerformed
        ChartDialog chart = new ChartDialog(this, imageBits.bits);
        chart.setVisible(true);
    }//GEN-LAST:event_graphicButtonActionPerformed

    /** Нажать на кнопку "Кодер" */
    private void coderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coderButtonActionPerformed
        switch(selectCodecComboBox.getSelectedIndex()){
            case 0:
                transmedia.message = org.models.codecs.HammingCodec.encode( imageBits.bits );
                break;
            case 1:
                System.out.println("111");
                System.exit(42);
                break;
        }
        infoPanel.append( ArrayShow.show(imageBits.bits, 42, 7, "\n  Закодированный битовый массив"));
        selectCodecComboBox.setEnabled(false);
        addNoiseButton.setEnabled(true);
    }//GEN-LAST:event_coderButtonActionPerformed
    
    /** Нажать на кнопку "Декодер" */
    private void decoderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decoderButtonActionPerformed
	transmedia.message = org.models.codecs.HammingCodec.decode( transmedia.message );
        infoPanel.append( ArrayShow.show(transmedia.message, 42, 7, "\n  Полученный битовый массив"));
        recImageBits = new ImageBits( transmedia.message, imageBits.width, imageBits.height);
        numErrors = TransmissionMedia.equals(imageBits.bits, transmedia.message);
        infoPanel.append( "\n Число неисправленных ошибок после передачи:\n" );
        infoPanel.append( numErrors + " (");
        double errRate = (double)numErrors/transmedia.message.size() * 100000;  //% ошибок от всех битов
        int iSubErrRate = (int)Math.round(errRate);                             // округление 1
        errRate = (double)iSubErrRate;                                          // округление 2
        errRate /= 1000;                                                        // округление 3
        infoPanel.append(String.valueOf(errRate) + "%)\n");
        
        try {
            imagePanel.setIcon(org.models.ImageResizing.execute( recImageBits.toImage(), imagePanel.getWidth(), imagePanel.getHeight()) );
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selectCodecComboBox.setEnabled(true);
        addNoiseButton.setEnabled(false);
	decoderButton.setEnabled(false);
        plusNoiseButton.setEnabled(true);
        minusNoiseButton.setEnabled(true);
    }//GEN-LAST:event_decoderButtonActionPerformed

    /** Нажать на кнопку "Наложить шум" */
    private void addNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoiseButtonActionPerformed
        transmedia.imposeNoise();
        infoPanel.append( ArrayShow.show(transmedia.message, 42, 7, "\n  Битовый массив с шумом"));
        decoderButton.setEnabled(true);
        plusNoiseButton.setEnabled(false);
        minusNoiseButton.setEnabled(false);
    }//GEN-LAST:event_addNoiseButtonActionPerformed

    /** Повысить уровень шума*/
    private void plusNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusNoiseButtonActionPerformed
        if(transmedia.getNoiseLevel() > 0.001)
            transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 2);
	else
            transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 10);
        kerrLabel2.setText( Double.toString(transmedia.getNoiseLevel()) );        
    }//GEN-LAST:event_plusNoiseButtonActionPerformed

    /** Понизить уровень шума*/
    private void minusNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusNoiseButtonActionPerformed
	if(transmedia.getNoiseLevel() > 0.001)
            transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 0.5);
	else
            transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 0.1);
        kerrLabel2.setText( Double.toString(transmedia.getNoiseLevel()) );
    }//GEN-LAST:event_minusNoiseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane InfoScrollPanel;
    private javax.swing.JButton addNoiseButton;
    private javax.swing.JButton clearTextPanel;
    private javax.swing.JPanel codecPanel;
    private javax.swing.JButton coderButton;
    private javax.swing.JButton decoderButton;
    private javax.swing.JPanel externalInfoPanel;
    private javax.swing.JPanel extrernalImagePanel;
    private javax.swing.JButton fileOpenButton;
    private javax.swing.JButton graphicButton;
    private javax.swing.JLabel imagePanel;
    private javax.swing.JTextArea infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel kerrLabel;
    private javax.swing.JLabel kerrLabel2;
    private javax.swing.JButton minusNoiseButton;
    private javax.swing.JPanel noisePanel;
    private javax.swing.JButton plusNoiseButton;
    private javax.swing.JComboBox selectCodecComboBox;
    // End of variables declaration//GEN-END:variables
}
