package com.tj.exercise.flash.sale.demo.service.Impl;

/**
 * @Author: tj
 * @Date: 2023/6/25 20:27
 */

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialScale;
import org.jfree.chart.plot.dial.StandardDialRange;

public class StandartDiaRangSelf extends StandardDialRange {

    public StandartDiaRangSelf(double lower, double upper, Paint paint) {
        super(lower, upper, paint);
    }
    private static final long serialVersionUID = 1L;

    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view) {
        Rectangle2D arcRectInner = DialPlot.rectangleByRadius(frame, (getOuterRadius() -getInnerRadius())/2 + getInnerRadius(), (getOuterRadius() -getInnerRadius())/2 + getInnerRadius());


        DialScale scale = plot.getScale(getScaleIndex());
        if (scale == null) {
            throw new RuntimeException("No scale for scaleIndex = " + getScaleIndex());
        }

        double angleMin = scale.valueToAngle(getLowerBound());
        double angleMax = scale.valueToAngle(getUpperBound());


        Arc2D arcOuter = new Arc2D.Double(arcRectInner, angleMax, angleMin - angleMax, 0);
        double s = (getOuterRadius() -getInnerRadius()) * 180;
        g2.setPaint(getPaint());
        g2.setStroke(new BasicStroke((float)s));
        g2.draw(arcOuter);
    }
}

