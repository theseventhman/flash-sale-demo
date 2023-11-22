//package com.tj.exercise.flash.sale.demo.service.Impl;
//
///**
// * @Author: tj
// * @Date: 2023/6/25 20:26
// */
//
//import java.awt.*;
//import java.text.AttributedString;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.dial.*;
//import org.jfree.data.general.DefaultValueDataset;
//import org.jfree.ui.GradientPaintTransformType;
//import org.jfree.ui.RectangleEdge;
//
//
//import org.jfree.ui.StandardGradientPaintTransformer;
//
///**
// * 仪表盘制作
// *
// * @author Administrator
// *
// */
//public class DialDemo1 extends JFrame implements ChangeListener {
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//    /** The first dataset. */
//    DefaultValueDataset dataset1;
//    DialTextAnnotation  annotation1;
//
//    @Override
//    public void stateChanged(ChangeEvent e) {
//       // annotation1.setLabel(dataset1.getValue()+"%");
//    }
//
//    public DialDemo1(String title) {
//      //  super(title);
//        this.dataset1 = new DefaultValueDataset(99.0);
//        DialPlot plot = new DialPlot();
//
//        plot.setView(0.0, 0.0, 1.0, 1.0);
//        plot.setDataset(0, this.dataset1);
//
//        StandardDialFrame dialFrame = new StandardDialFrame();
//        dialFrame.setBackgroundPaint(new Color(54, 54, 54));
//        dialFrame.setForegroundPaint(new Color(54, 54, 54));
//        plot.setDialFrame(dialFrame);
//
//		GradientPaint gp = new GradientPaint(new Point(), new Color(255, 0, 0), new Point(),
//				new Color(170, 170, 220));
//		DialBackground db = new DialBackground(gp);
//		db.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
//		plot.setBackground(db);
////		DialTextAnnotation annotation1 = new DialTextAnnotation("速度");
////		annotation1.setFont(new Font("Dialog", Font.BOLD, 14));
////		annotation1.setRadius(0.7);
////		plot.addLayer(annotation1);
//
//
////		DialValueIndicator dvi2 = new DialValueIndicator(1);
////		dvi2.setFont(new Font("Dialog", Font.PLAIN, 10));
////		dvi2.setOutlinePaint(Color.red);
////		dvi2.setRadius(0.60);
////		dvi2.setAngle(-77.0);
////		plot.addLayer(dvi2);
//
//
//
//        StandartDiaRangSelf standarddialrange =new StandartDiaRangSelf(0D,30D, Color.red);
//        standarddialrange.setInnerRadius(0.83000000000000002D);
//        standarddialrange.setOuterRadius(0.89000000000000004D);
//        plot.addLayer(standarddialrange);
//
//        StandartDiaRangSelf standarddialrange1 =new StandartDiaRangSelf(30D,70D, Color.orange);
//        standarddialrange1.setInnerRadius(0.83000000000000002D);
//
//        standarddialrange1.setOuterRadius(0.89000000000000004D);
//
//        plot.addLayer(standarddialrange1);
//        StandardDialRange standarddialrange2 =new StandartDiaRangSelf(70D,100D, Color.blue);
//
//        standarddialrange2.setInnerRadius(0.83000000000000002D);
//        standarddialrange2.setOuterRadius(0.89000000000000004D);
//
//        plot.addLayer(standarddialrange2);
//
//        StandardDialScale scale = new StandardDialScale();
//        scale.setLowerBound(0); // 最底表盘刻度
//        scale.setUpperBound(100); // 最高表盘刻度
//        scale.setStartAngle(-120); // 弧度为120,刚好与人的正常视觉对齐
//        scale.setExtent(-300); // 弧度为300,刚好与人的正常视觉对齐
//        scale.setTickRadius(0.88);
//        scale.setTickLabelOffset(0.15);
//        scale.setTickLabelFont(new Font("Dialog", Font.PLAIN, 14));
//        scale.setTickLabelPaint(Color.WHITE);
//        scale.setMajorTickPaint(Color.white);
//        scale.setMinorTickPaint(Color.white);
//        scale.setMajorTickLength(0);
//        scale.setMinorTickLength(0);
//        scale.setFirstTickLabelVisible(false);
//        plot.addScale(0, scale);
//
//        DialPointer.Pointer  needle = new DialPointer.Pointer();
//        needle.setRadius(0.8);
//        needle.setFillPaint(Color.blue);
//        needle.setOutlinePaint(Color.blue);
//        plot.addLayer(needle);
//
//
////		DialCap cap = new DialCap();
////		cap.setRadius(0.10);
////		plot.setCap(cap);
////		DialPointer needle2 = new DialPointer.Pin(1);
////		needle2.setRadius(0.55);
////		plot.addLayer(needle2);
//
//        annotation1 = new DialTextAnnotation("DD");
//        annotation1.setPaint(new Color(123,97,64));
//        // 创建文本注释并设置文本内容和位置
//
//      DialTextAnnotation  DialTextAnnotation2 = new DialTextAnnotation("CC");
//
//
//        plot.addLayer(annotation1);
//
////        DialTextAnnotation textAnnotation = new DialTextAnnotation("This is a long text that needs to be wrapped");
////        textAnnotation.setFont(new Font("Arial", Font.PLAIN, 12));
////        textAnnotation.setX(0.5);
////        textAnnotation.setY(0.5);
////        textAnnotation.setWidthRadius(0.8);
//
//        JFreeChart chart1 = new JFreeChart(plot);
//        chart1.setBackgroundPaint(new Color(54, 54, 54));
//         chart1.setTitle("仪表盘");
////        chart1.getTitle().setMargin(5,0,0,0);
////        chart1.getTitle().setPosition(RectangleEdge.BOTTOM);
//        chart1.getTitle().setVisible(false);
//        ChartPanel cp1 = new ChartPanel(chart1);
//        cp1.setPreferredSize(new Dimension(400, 400));
//        JPanel content = new JPanel(new BorderLayout());
//        content.add(cp1);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setContentPane(content);
//    }
//
//    public static void main(String[] args) {
//        DialDemo1 app = new DialDemo1("仪表盘");
//        app.pack();
//        app.setVisible(true);
////	    new	Thread(new Runnable() {
////
////			@Override
////			public void run() {
////				// TODO Auto-generated method stub
////				try {
////					Thread.sleep(5000);
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////				app.annotation1.setLabel("12");
////				app.dataset1.setValue(34);
////				System.out.println("**********");
////			}
////		}).start();
//    }
//
//}
//
//
