package org.controllers;

import java.awt.image.BufferedImage;
import java.util.BitSet;

//import models.Codec;
//import models.ImageBytes;
//import models.ImageTransform;
//import models.TransmissionMedia;
//import views.ImageFileChooser;
//import views.frames.ChartDialog;
//import views.frames.ImageDialog;

@SuppressWarnings("serial")
public class MainFrameController {//extends views.frames.MainFrame{
	
	/** ��������� ������� ��������� */
	private BitSet outMessage;
	/** �������� ������� ��������� */
	private BitSet inMessage;
	/** �������������� ������� ��������� */
	private BitSet encodeMessage;
	/** ����� �������� */
	private TransmissionMedia transmedia;
	
	private ImageBytes imageBytes;   // ������ �����������
	ImageDialog imageDialog;
	
	MainFrameController(){
		super();
		transmedia = new TransmissionMedia(0.0001); // ����� ��������
		initHandlers();
		initMenuHandlers();
		imageDialog = new ImageDialog(this);
	}
	
	public static void main(String[] args) {
		 MainFrameController GUI = new MainFrameController();
	     GUI.setVisible(true);
	}

	@Override
	protected void initHandlers() {
		/** ������� ��������� infoList */
	    btnClear.addActionListener((java.awt.event.ActionEvent e)->{
		    infoList.setText("");
		});
	    /** ���������� ���� � �������� */
	    btnChart.addActionListener((java.awt.event.ActionEvent e)->{
	    	ChartDialog dialog = new ChartDialog(this, outMessage);
	    	dialog.pack();
	    	dialog.setVisible(true);
		});
	    /** ���������� ���� � ������������ */
	    btnShowImage.addActionListener((java.awt.event.ActionEvent e)->{
	    	imageDialog.setVisible(true);	    	
		});
	}

	@Override
	protected void initMenuHandlers(){
	    /** �������� ����������� */
		openFileItem.addActionListener((java.awt.event.ActionEvent e)->{
		    ImageFileChooser fileDlg = new ImageFileChooser();
		    if(fileDlg.showDialog(null, "������� ����") == 0){
		    	try {
		    		// �������� ���������
		    		BufferedImage image = javax.imageio.ImageIO.read(fileDlg.getSelectedFile());
		    		imageDialog.imagePanel.updateImage(image);
		    		
		    		image = ImageTransform.scaleImage(image, 400, 250);
		    		java.io.File backgroundFile = new java.io.File( "./info/image.jpg" );
		    		javax.imageio.ImageIO.write(image, "jpg", backgroundFile);
		    	    image = javax.imageio.ImageIO.read(backgroundFile);
		    	    
					imageBytes = new ImageBytes(image);			
					outMessage = imageBytes.toBitArray();

					infoList.append(imageBytes.toString());
					coderItem.setEnabled(true);
				} catch (java.io.IOException e1) {
					e1.printStackTrace();
				}
		    } 
		 });
		
		/** ����� */
		coderItem.addActionListener((java.awt.event.ActionEvent e)->{
			encodeMessage = Codec.encode(outMessage);		
			
			imposeNoiseItem.setEnabled(true);
			decoderItem.setEnabled(true);
		});
		
		/** ������� */
		decoderItem.addActionListener((java.awt.event.ActionEvent e)->{
			inMessage = Codec.decode(encodeMessage);
			imageBytes = new ImageBytes(inMessage, 
					                    imageDialog.imagePanel.getWidth(), 
					                    imageDialog.imagePanel.getHeight()
					                    );
			try {
				imageDialog.imagePanel.updateImage(imageBytes.toImage());
			} catch (java.io.IOException e1) {
				e1.printStackTrace();
			}
			decoderItem.setEnabled(false);
			btnChart.setVisible(true);
		});
		
		/** �������� ��� */
		imposeNoiseItem.addActionListener((java.awt.event.ActionEvent e)->{
			transmedia.imposeNoise(encodeMessage);
			decoderItem.setEnabled(true);
		});
		
		/** �������� ��� */
		upNoiseItem.addActionListener((java.awt.event.ActionEvent e)->{
			if(transmedia.getNoiseLevel() > 0.001)
			    transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 2);
			else
				transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 10);
			infoList.append("K�� = " + transmedia.getNoiseLevel() + "\n");
		});
		
		/** �������� ��� */
		downNoiseItem.addActionListener((java.awt.event.ActionEvent e)->{
			if(transmedia.getNoiseLevel() > 0.001)
			    transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 0.5);
			else
				transmedia.setNoiseLevel(transmedia.getNoiseLevel() * 0.1);
			infoList.append("K�� = " + transmedia.getNoiseLevel() + "\n");
		});
		
	}
}