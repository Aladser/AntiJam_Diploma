package org.views;

import org.jfree.chart.ChartPanel;

import java.awt.Color;
import java.util.BitSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.models.codecs.HammingCodec;
import org.models.TransmissionMedia;

@SuppressWarnings("serial")
public class ChartDialog extends javax.swing.JDialog{
    private final BitSet srcMessage;
	
    public ChartDialog(MainFrame owner, BitSet srcMessage){
	super(owner, "График", true);
	this.setBounds(190, 190, 900, 540);
	this.srcMessage = srcMessage;	
	final CategoryDataset dataset = createDataset();
	final JFreeChart chart = createChart(dataset);
	final ChartPanel chartPanel = new ChartPanel( chart );
	    
	chartPanel.setPreferredSize( new java.awt.Dimension(900, 500 ) );
	setContentPane( chartPanel );
    }
	
    private CategoryDataset createDataset( ){
        System.out.println("Вычисления начало");
	final DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	double Perr = 0.0001;   // исходная вероятность ошибки
	TransmissionMedia transmedia = new TransmissionMedia();
	int numErrors;          // число ошибок после декодера
		 
	while(Perr < 0.01){
            transmedia.message = HammingCodec.encode(srcMessage);
            transmedia.imposeNoise();
            transmedia.message = HammingCodec.decode(transmedia.message);
            numErrors = TransmissionMedia.equals(srcMessage, transmedia.message);
            dataset.addValue( numErrors, "" , "" + Perr);
            transmedia.setNoiseLevel(Perr * 2);
            Perr = transmedia.getNoiseLevel();
	}
   
        System.out.println("Вычисления конец");
	return dataset;
    }
	
    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createLineChart(
                                                              "Число ошибок (вероятность ошибок)",       // chart title
                                                              "Вероятность ошибки",                    // domain axis label
                                                              "Ошибок не исправлено\"",                   // range axis label
                                                              dataset,                   // data
                                                              PlotOrientation.VERTICAL,  // orientation
                                                              false,                      // include legend
                                                              true,                      // tooltips
                                                              false                      // urls
                                                              );
        chart.setBackgroundPaint(Color.white); 
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.CYAN);
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);
    
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
    
        final LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesLinesVisible(1, false);     
        renderer.setSeriesShapesVisible(1, false); 
        plot.setRenderer(renderer); 
        return chart;
    }
}

