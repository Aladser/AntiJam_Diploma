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
import org.models.codecs.Codec;

@SuppressWarnings("serial")
public class ChartDialog extends javax.swing.JDialog{
	
    public ChartDialog(MainFrame owner, BitSet srcMessage, Codec codec, double[] PerrArr, int[] numErrArray){
	super(owner, "График", true);
	setBounds(190, 190, 900, 540);	       
	final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0; i<PerrArr.length; i++) dataset.addValue( numErrArray[i], "" , "" + PerrArr[i]);           
	final JFreeChart chart = createChart(dataset);
	final ChartPanel chartPanel = new ChartPanel( chart );   
	chartPanel.setPreferredSize( new java.awt.Dimension(900, 500 ) );
	setContentPane( chartPanel );
    }

    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createLineChart(
                                                              "Число неисправленных ошибок (вероятность ошибок)",       // chart title
                                                              "Вероятность ошибки",                    // domain axis label
                                                              "Ошибок не исправлено",                   // range axis label
                                                              dataset,                   // data
                                                              PlotOrientation.VERTICAL,  // orientation
                                                              false,                      // include legend
                                                              true,                      // tooltips
                                                              false                      // urls
                                                              );
        chart.setBackgroundPaint(Color.white); 
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.BLUE);
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

