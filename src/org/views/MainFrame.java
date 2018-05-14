package org.views;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.jfree.data.category.DefaultCategoryDataset;
import org.models.ImageBits;
import org.models.ImageResizing;
import org.models.TransmissionMedia;
import org.models.codecs.BCHCodec;
import org.models.codecs.Codec;
import org.models.codecs.ConvolCodec;
import org.models.codecs.RSCodec;

// Главное окно
public class MainFrame extends javax.swing.JFrame {
    private java.awt.image.BufferedImage image;  // Исходное изображение
    private ImageBits imageBits;                 // Исходный битовый массив изображения
    private ImageBits recImageBits;              // Полученный битовый массив изображения    
    private final TransmissionMedia transmedia;  // Канал передачи данных
    private int numErrors;                       // Число неисправленных ошибок
    public Codec codec;                          // Кодек
    
    public MainFrame(){
        initComponents();
        setCodec( codecChoiceComboBox.getSelectedIndex() );
        transmedia = new TransmissionMedia();
        kerrLabel2.setText( formateDoubleNumber(transmedia.getNoiseLevel()) );
    }

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
        codecChoiceComboBox = new javax.swing.JComboBox();
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
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
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

        coderButton.setText("Кодер");
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

        decoderButton.setText("Декодер");
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

            codecChoiceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "БЧХ", "РС", "Сверточный" }));
            codecChoiceComboBox.setSelectedIndex(2);
            codecChoiceComboBox.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    selectCodecButton(evt);
                }
            });

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
                        .addComponent(codecChoiceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(externalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(extrernalImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
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
                            .addComponent(codecChoiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // выбор файла для загрузки в приложение
        javax.swing.filechooser.FileNameExtensionFilter filefilter;
        filefilter = new javax.swing.filechooser.FileNameExtensionFilter("Изображения (.jpg,.png,.bmp)", "jpg", "png", "bmp");
        java.io.File[] roots = java.io.File.listRoots();
        String diskName= "C:\\";
        for(java.io.File file: roots) if(file.getAbsolutePath().equals("D:\\")){ diskName = "D:\\"; break; }
        javax.swing.JFileChooser filechooser = new javax.swing.JFileChooser(diskName);
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.addChoosableFileFilter(filefilter);
        int fileChoiceResp = filechooser.showDialog(null, "Открыть файл");
        if (fileChoiceResp == javax.swing.JFileChooser.APPROVE_OPTION) {   
            try { 
                image = ImageIO.read(filechooser.getSelectedFile());
                image = ImageResizing.exec(image, imagePanel.getWidth(), imagePanel.getHeight());
                
                imageBits = new ImageBits(image);
                imagePanel.setIcon( new ImageIcon(image) );                
            } catch (IOException ex) {Logger.getLogger("Не удалось прочитать файл");}
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
        infoPanel.append("  Зависимость числа неисправленных ошибок от уров-\n");
        infoPanel.append("ня шума\n");
        double backupKerr = transmedia.getNoiseLevel(); // сохранение текущего Perr
        final int GRAPH_PTS = 7;                   // число точек графика ошибок
        double[] pErrArray = new double[GRAPH_PTS]; // массив Kош
        int[] numErrArray = new int[GRAPH_PTS];     // массив числа неисправленных ошибок
        double Perr = 0.00001;                      // исходный Kош
        double unCorrErrPercent;
        for(int i=0; i<GRAPH_PTS; i++){
            pErrArray[i] = Perr;
            if(Perr >= 0.001) Perr *= 2;
            else Perr *= 10;  
        }
        for(int i=0; i<GRAPH_PTS; i++){
            transmedia.setNoiseLevel(pErrArray[i]);
            transmedia.message = codec.encode(imageBits.bits);
            transmedia.imposeNoise();
            transmedia.message = codec.decode(transmedia.message);
            numErrArray[i] = TransmissionMedia.equals(imageBits.bits, transmedia.message);
            infoPanel.append("  Kош=" + transmedia.getNoiseLevel() + "; ");
            infoPanel.append( "Неисправлено ошибок: " + numErrArray[i] + " (");
            unCorrErrPercent = (double)numErrArray[i]/(double)imageBits.bits.length();
            // округление до сотых
            unCorrErrPercent *= 10000;
            unCorrErrPercent = (int)unCorrErrPercent;
            unCorrErrPercent = unCorrErrPercent/100;
            infoPanel.append( unCorrErrPercent + "%)\n");
            
            if(Perr >= 0.001) Perr *= 2;
            else Perr *= 10;            
        }
        transmedia.setNoiseLevel(backupKerr);
        ChartDialog chart = new ChartDialog(this, imageBits.bits, codec, pErrArray, numErrArray);
        chart.setLocation(this.getX()+520, this.getY()+70);
        chart.setVisible(true);
    }//GEN-LAST:event_graphicButtonActionPerformed

    //Нажать на кнопку "Кодер"
    private void coderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coderButtonActionPerformed
        Date time = new Date();
        transmedia.message = codec.encode(imageBits.bits);        
        infoPanel.append("  Кодирование завершено (" + (new Date().getTime()-time.getTime()) + " msec)\n");
        codecChoiceComboBox.setEnabled(false);
        addNoiseButton.setEnabled(true);
    }//GEN-LAST:event_coderButtonActionPerformed
    
    // Нажать на кнопку "Декодер"
    private void decoderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decoderButtonActionPerformed
	Date time = new Date();
        transmedia.message = codec.decode( transmedia.message );
        infoPanel.append("\n  Декодирование завершено (" + (new Date().getTime()-time.getTime()) + " msec)\n");
        recImageBits = new ImageBits( transmedia.message, imageBits.width, imageBits.height);
        numErrors = TransmissionMedia.equals(imageBits.bits, transmedia.message);
        infoPanel.append( "  Число неисправленных ошибок после передачи:\n" );
        infoPanel.append( numErrors + " (");
        double errRate = (double)numErrors/transmedia.message.size() * 100000;  //% ошибок от всех битов
        int iSubErrRate = (int)Math.round(errRate);                             // округление 1
        errRate = (double)iSubErrRate;                                          // округление 2
        errRate /= 1000;                                                        // округление 3
        infoPanel.append(String.valueOf(errRate) + "%)\n\n");
        try {    
            imagePanel.setIcon( new ImageIcon(recImageBits.toImage()) );
        } catch (IOException ex) {
            System.err.println("Не удалось создать изображение из массива бит");
        }
        codecChoiceComboBox.setEnabled(true);
        addNoiseButton.setEnabled(false);
	decoderButton.setEnabled(false);
        plusNoiseButton.setEnabled(true);
        minusNoiseButton.setEnabled(true);
        coderButton.setEnabled(true);
        addNoiseButton.setEnabled(false);
    }//GEN-LAST:event_decoderButtonActionPerformed

    // Нажать на кнопку "Наложить шум"
    private void addNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoiseButtonActionPerformed
        if(transmedia.getNoiseLevel() < 0.001){
            double zeros = transmedia.getNoiseLevel();
            int numZeros=0;
            while(zeros != 1){
                zeros*=10;
                numZeros++;
            }
            infoPanel.append("  Наложен шум Kош = 10^-"+ numZeros);
        }
        else
            infoPanel.append("  Наложен шум Kош = "+ transmedia.getNoiseLevel());
        transmedia.imposeNoise();
        
        decoderButton.setEnabled(true);
        plusNoiseButton.setEnabled(false);
        minusNoiseButton.setEnabled(false);
        coderButton.setEnabled(false);
        addNoiseButton.setEnabled(false);
    }//GEN-LAST:event_addNoiseButtonActionPerformed

    // Повысить уровень шума
    private void plusNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusNoiseButtonActionPerformed
        if(transmedia.getNoiseLevel() > 0.001)
            transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 2);
	else
            transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 10);
        kerrLabel2.setText( formateDoubleNumber(transmedia.getNoiseLevel()) );
        if(transmedia.getNoiseLevel() >= 0.1) plusNoiseButton.setEnabled(false);
        if(transmedia.getNoiseLevel() > 0.000001) minusNoiseButton.setEnabled(true);
    }//GEN-LAST:event_plusNoiseButtonActionPerformed

    // Понизить уровень шума
    private void minusNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusNoiseButtonActionPerformed
        double noise = transmedia.getNoiseLevel();
        int exp = 0;
        while(noise < 1){ 
            noise *= 10; 
            exp++;
        }
        noise = Math.pow(10, -++exp);
        transmedia.setNoiseLevel(noise);
        kerrLabel2.setText( formateDoubleNumber(transmedia.getNoiseLevel()) );
        if(transmedia.getNoiseLevel() <= 0.000001) minusNoiseButton.setEnabled(false);
        if(transmedia.getNoiseLevel() < 0.1) plusNoiseButton.setEnabled(true);
    }//GEN-LAST:event_minusNoiseButtonActionPerformed

    // Нажать на selectCodecComboBox
    private void selectCodecButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCodecButton
        infoPanel.setText("");
        setCodec(codecChoiceComboBox.getSelectedIndex());       
    }//GEN-LAST:event_selectCodecButton
    
    // Установить кодек
    private void setCodec(int par){
        switch(par){
            case 0:
                codec = new BCHCodec(0b1011, 7, 4);
                infoPanel.append( "  Код БЧХ(7,4) \n  Порождающий полином g(x) = 1011\n");
                infoPanel.append( "  Кодирование: умножение информационного слова \nна кодирующую матрицу\n");
                infoPanel.append( "  Декодирование: деление кодового слова\nна порождающий многочлен\n");
                infoPanel.append( "  Исправление ошибок основано на теореме Меггита\n\n");
                break;
            case 1:
                codec = new RSCodec(4,2,3);
                infoPanel.append( "  Код Рида-Соломона(12,6) \n  Поле Галуа Gf(8)\n");
                infoPanel.append( "  Кодирование: умножение информационного \nполинома и порождающего полинома в поле Галуа\n");
                infoPanel.append( "  Декодирование: деление кодового полинома \nна порождающий полинома в поле Галуа\n");
                infoPanel.append( "  Исправление ошибок: синдромное декодирование\n\n");
                break;
            case 2:
                codec = new ConvolCodec(12, 9, 3);
                infoPanel.append( "  Сверточный код (12,9)\n");
                infoPanel.append( "  Кодирование: схема кодера основана \nна порождающем полиноме\n");
                infoPanel.append( "  Декодирование: схема, обратная кодеру\n");
                infoPanel.append( "  Исправление ошибок: синдромное декодирование\n\n");
        }         
    }
    
    // Представление дробных чисел в форме 10^(-exp)
    private String formateDoubleNumber(double number){
        double zeros = number;
        int numZeros = 0;
        while(zeros < 1){
                zeros*=10;
                numZeros++;
            }
        String res;
        if(numZeros < 3) res = Double.toString(number);
        else res = "10^-"+numZeros;
        return res;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane InfoScrollPanel;
    private javax.swing.JButton addNoiseButton;
    private javax.swing.JButton clearTextPanel;
    private javax.swing.JComboBox codecChoiceComboBox;
    private javax.swing.JPanel codecPanel;
    private javax.swing.JButton coderButton;
    private javax.swing.JButton decoderButton;
    private javax.swing.JPanel externalInfoPanel;
    private javax.swing.JPanel extrernalImagePanel;
    private javax.swing.JButton fileOpenButton;
    private javax.swing.JButton graphicButton;
    private javax.swing.JLabel imagePanel;
    public javax.swing.JTextArea infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel kerrLabel;
    private javax.swing.JLabel kerrLabel2;
    private javax.swing.JButton minusNoiseButton;
    private javax.swing.JPanel noisePanel;
    private javax.swing.JButton plusNoiseButton;
    // End of variables declaration//GEN-END:variables
}
