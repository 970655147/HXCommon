/**
 * file name : GeometryUtil.java
 * created at : 2:12:59 PM Nov 26, 2015
 * created by 970655147
 */

package com.hx.common.math;

import com.hx.common.util.AssertUtils;
import com.hx.common.util.InnerTools;

import java.awt.geom.Point2D;

/**
 * ������ؼ���
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:15 PM
 */
public final class GeometryUtils {

    // disable constructor
    private GeometryUtils() {
        AssertUtils.assert0("can't instantiate !");
    }

    /**
     * Ĭ�ϵ�ԭ������[ûָ����׼��, Ĭ����Զ��Ϊ��׼��]
     */
    public static final Point2D defaultZero = new Point2D.Double(0, 0);
    /**
     * ������double��Ϊ��ͬ�Ĳ�ֵ����ֵ[���������ж�]
     */
    public static final double minOff = 0.0001d;

    // check
    public static void check(Point2D p) {
        InnerTools.log("dis to 'zero' : " + distanceOfPoints(defaultZero, p));
    }

    /**
     * ����p1, p2֮��ľ���
     *
     * @param x1 x1, y1ȷ����һ����
     * @param y1 x1, y1ȷ����һ����
     * @param x2 x2, y2ȷ���ڶ�����
     * @param y2 x2, y2ȷ���ڶ�����
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static double distanceOfPoints(double x1, double y1, double x2, double y2) {
        double disX = x2 - x1;
        double disY = y2 - y1;
        return Math.sqrt(disX * disX + disY * disY);
    }

    /**
     * ����p1, p2֮��ľ���
     *
     * @param p1 ��һ����
     * @param p2 �ڶ�����
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static double distanceOfPoints(Point2D p1, Point2D p2) {
        return distanceOfPoints(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * ����p1, p2���е�
     *
     * @param x1 x1, y1ȷ����һ����
     * @param y1 x1, y1ȷ����һ����
     * @param x2 x2, y2ȷ���ڶ�����
     * @param y2 x2, y2ȷ���ڶ�����
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D middlePoint(double x1, double y1, double x2, double y2) {
        double x = (x1 + x2) / 2;
        double y = (y1 + y2) / 2;
        return new Point2D.Double(x, y);
    }

    /**
     * ����p1, p2���е�
     *
     * @param p1 ��һ����
     * @param p2 �ڶ�����
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D middlePoint(Point2D p1, Point2D p2) {
        return middlePoint(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * ����p1 -> p2�����Ͼ���p1ΪdisToStartPoint�ĵ�
     *
     * @param x1              x1, y1ȷ����һ����
     * @param y1              x1, y1ȷ����һ����
     * @param x2              x2, y2ȷ���ڶ�����
     * @param y2              x2, y2ȷ���ڶ�����
     * @param disToStartPoint ��Ҫ�ӳ��ĳ���
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D extentPoint(double x1, double y1, double x2, double y2, double disToStartPoint) {
        double dis = distanceOfPoints(x1, y1, x2, y2);
        double sin = (y2 - y1) / dis;
        double cos = (x2 - x1) / dis;
        double deltaX = disToStartPoint * cos;
        double deltaY = disToStartPoint * sin;

        return new Point2D.Double(x1 + deltaX, y1 + deltaY);
    }

    /**
     * ����p1 -> p2�����Ͼ���p1ΪdisToStartPoint�ĵ�
     *
     * @param p1              ��һ����
     * @param p2              �ڶ�����
     * @param disToStartPoint ��Ҫ�ӳ��ĳ���
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D extentPoint(Point2D p1, Point2D p2, double disToStartPoint) {
        return extentPoint(p1.getX(), p1.getY(), p2.getX(), p2.getY(), disToStartPoint);
    }

    /**
     * ��p1, p2��p1Ϊ��׼��ʱ�뷴ת90��
     * e'->|
     * -----    =>		    |
     * /|\ /|\			    |
     * |	 |			    	|
     * s   e			   s->  |
     *
     * @param x1 x1, y1ȷ����һ����
     * @param y1 x1, y1ȷ����һ����
     * @param x2 x2, y2ȷ���ڶ�����
     * @param y2 x2, y2ȷ���ڶ�����
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:17 PM
     * @since 1.0
     */
    public static Point2D verticalPoint(double x1, double y1, double x2, double y2) {
        return rotate(x1, y1, x2, y2, 90);
    }

    /**
     * ��p1, p2��p1Ϊ��׼��ʱ�뷴ת90��
     *
     * @param p1 ��һ����
     * @param p2 �ڶ�����
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D verticalPoint(Point2D p1, Point2D p2) {
        return verticalPoint(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * ���p1, p2��y������ͬ, ��ֱ����תdegree��
     * ����, ���������y������p1��y����Ϊ��׼, ��תdegree + '(p1 -> p2)�����x��Ķ���'
     *
     * @param x1     x1, y1ȷ����һ����
     * @param y1     x1, y1ȷ����һ����
     * @param x2     x2, y2ȷ���ڶ�����
     * @param y2     x2, y2ȷ���ڶ�����
     * @param degree ��Ҫ��ת�Ķ���
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:20 PM
     * @since 1.0
     */
    public static Point2D rotate(double x1, double y1, double x2, double y2, double degree) {
//    	Log.log(y1, y2);
//    	Log.log("degree : " + degree);
        double dis = distanceOfPoints(x1, y1, x2, y2);
        if (!equals(y1, y2)) {
            // ͨ��tan�������		 	(tan) => Math.toDegrees(Math.atan(tan ))
            return rotate(x1, y1, x1 + dis, y1, degree + Math.toDegrees(Math.atan((y2 - y1) / (x2 - x1))));
        }
        double radians = Math.toRadians(degree);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);

        double offX = dis * cos;
        double offY = dis * sin;

        return new Point2D.Double(x1 + offX, y1 + offY);
    }

    /**
     * ���p1, p2��y������ͬ, ��ֱ����תdegree��
     * ����, ���������y������p1��y����Ϊ��׼, ��תdegree + '(p1 -> p2)�����x��Ķ���'
     *
     * @param p1     ��һ����
     * @param p2     �ڶ�����
     * @param degree ��Ҫ��ת�Ķ���
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:20 PM
     * @since 1.0
     */
    public static Point2D rotate(Point2D p1, Point2D p2, double degree) {
        return rotate(p1.getX(), p1.getY(), p2.getX(), p2.getY(), degree);
    }

    /**
     * ��p1��ԭ��Ϊ��׼��תdegree��
     *
     * @param x      x, yȷ��һ����
     * @param y      x, yȷ��һ����
     * @param degree ��Ҫ��ת�Ķ���
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:21 PM
     * @since 1.0
     */
    public static Point2D rotate(double x, double y, double degree) {
        return rotate(defaultZero.getX(), defaultZero.getY(), x, y, degree);
    }

    /**
     * ��p1��ԭ��Ϊ��׼��תdegree��
     *
     * @param p      һ����
     * @param degree ��Ҫ��ת�Ķ���
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:22 PM
     * @since 1.0
     */
    public static Point2D rotate(Point2D p, double degree) {
        return rotate(p.getX(), p.getY(), degree);
    }

    /**
     * �ж������������Ƿ���ͬ
     *
     * @param d1 ������1
     * @param d2 ������2
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:23 PM
     * @since 1.0
     */
    public static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) < minOff;
    }

    /**
     * ��Point2Dת��ΪPoint
     *
     * @param p2d ������point2d
     * @return java.awt.Point
     * @author Jerry.X.He
     * @date 5/5/2017 8:23 PM
     * @since 1.0
     */
    public static java.awt.Point toPoint(Point2D p2d) {
        return new java.awt.Point((int) p2d.getX(), (int) p2d.getY());
    }

}
