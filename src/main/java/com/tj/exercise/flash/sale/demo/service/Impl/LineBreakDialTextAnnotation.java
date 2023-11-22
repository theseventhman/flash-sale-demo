//package com.tj.exercise.flash.sale.demo.service.Impl;
//import org.jfree.chart.plot.dial.DialLayer;
//import org.jfree.chart.plot.dial.DialPlot;
//import org.jfree.chart.plot.dial.DialTextAnnotation;
//import org.jfree.text.TextUtilities;
//
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.geom.Rectangle2D;
//
//public class LineBreakDialTextAnnotation extends DialTextAnnotation implements DialLayer {
//    private int maxLines;
//
//    public LineBreakDialTextAnnotation(String text, int maxLines) {
//        super(text);
//        this.maxLines = maxLines;
//    }
//
//    @Override
//    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view) {
//        double datasetValue = plot.getValue();
//        double angle = plot.getValueToAngleFactor() * datasetValue;
//        double radius = getRadius();
//        double x = frame.getCenterX() + Math.cos(Math.toRadians(angle - 90)) * view.getWidth() * radius;
//        double y = frame.getCenterY() - Math.sin(Math.toRadians(angle - 90)) * view.getHeight() * radius;
//
//        String[] lines = getText().split("\n");
//        int lineCount = Math.min(lines.length, maxLines);
//
//        Font font = getFont();
//        g2.setFont(font);
//        g2.setPaint(getPaint());
//
//        Rectangle2D textBounds = TextUtilities.getTextBounds(lines[0], g2, g2.getFontMetrics());
//        double lineHeight = textBounds.getHeight();
//
//        double totalHeight = lineHeight * lineCount;
//        double startY = y - (totalHeight / 2) + (lineHeight / 2);
//
//        for (int i = 0; i < lineCount; i++) {
//            double lineY = startY + (i * lineHeight);
//            TextUtilities.drawAlignedString(lines[i], g2, (float) x, (float) lineY, TextUtilities.CENTER);
//        }
//    }
//
//    // 实现 DialLayer 接口的其他方法，根据需要进行具体实现
//    // ...
//}
