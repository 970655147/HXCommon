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
 * 几何相关计算
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
     * 默认的原点坐标[没指明基准点, 默认以远点为基准点]
     */
    public static final Point2D defaultZero = new Point2D.Double(0, 0);
    /**
     * 将两个double视为相同的差值的阈值[浮点区间判定]
     */
    public static final double minOff = 0.0001d;

    // check
    public static void check(Point2D p) {
        InnerTools.log("dis to 'zero' : " + distanceOfPoints(defaultZero, p));
    }

    /**
     * 计算p1, p2之间的距离
     *
     * @param x1 x1, y1确定第一个点
     * @param y1 x1, y1确定第一个点
     * @param x2 x2, y2确定第二个点
     * @param y2 x2, y2确定第二个点
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
     * 计算p1, p2之间的距离
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static double distanceOfPoints(Point2D p1, Point2D p2) {
        return distanceOfPoints(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 计算p1, p2的中点
     *
     * @param x1 x1, y1确定第一个点
     * @param y1 x1, y1确定第一个点
     * @param x2 x2, y2确定第二个点
     * @param y2 x2, y2确定第二个点
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
     * 计算p1, p2的中点
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D middlePoint(Point2D p1, Point2D p2) {
        return middlePoint(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 沿着p1 -> p2方向上距离p1为disToStartPoint的点
     *
     * @param x1              x1, y1确定第一个点
     * @param y1              x1, y1确定第一个点
     * @param x2              x2, y2确定第二个点
     * @param y2              x2, y2确定第二个点
     * @param disToStartPoint 需要延长的长度
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
     * 沿着p1 -> p2方向上距离p1为disToStartPoint的点
     *
     * @param p1              第一个点
     * @param p2              第二个点
     * @param disToStartPoint 需要延长的长度
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D extentPoint(Point2D p1, Point2D p2, double disToStartPoint) {
        return extentPoint(p1.getX(), p1.getY(), p2.getX(), p2.getY(), disToStartPoint);
    }

    /**
     * 将p1, p2以p1为基准逆时针反转90度
     * e'->|
     * -----    =>		    |
     * /|\ /|\			    |
     * |	 |			    	|
     * s   e			   s->  |
     *
     * @param x1 x1, y1确定第一个点
     * @param y1 x1, y1确定第一个点
     * @param x2 x2, y2确定第二个点
     * @param y2 x2, y2确定第二个点
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:17 PM
     * @since 1.0
     */
    public static Point2D verticalPoint(double x1, double y1, double x2, double y2) {
        return rotate(x1, y1, x2, y2, 90);
    }

    /**
     * 将p1, p2以p1为基准逆时针反转90度
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return double
     * @author Jerry.X.He
     * @date 5/5/2017 8:15 PM
     * @since 1.0
     */
    public static Point2D verticalPoint(Point2D p1, Point2D p2) {
        return verticalPoint(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * 如果p1, p2的y坐标相同, 则直接旋转degree度
     * 否则, 将两个点的y坐标以p1的y坐标为基准, 旋转degree + '(p1 -> p2)相对于x轴的度数'
     *
     * @param x1     x1, y1确定第一个点
     * @param y1     x1, y1确定第一个点
     * @param x2     x2, y2确定第二个点
     * @param y2     x2, y2确定第二个点
     * @param degree 需要旋转的度数
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
            // 通过tan计算度数		 	(tan) => Math.toDegrees(Math.atan(tan ))
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
     * 如果p1, p2的y坐标相同, 则直接旋转degree度
     * 否则, 将两个点的y坐标以p1的y坐标为基准, 旋转degree + '(p1 -> p2)相对于x轴的度数'
     *
     * @param p1     第一个点
     * @param p2     第二个点
     * @param degree 需要旋转的度数
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:20 PM
     * @since 1.0
     */
    public static Point2D rotate(Point2D p1, Point2D p2, double degree) {
        return rotate(p1.getX(), p1.getY(), p2.getX(), p2.getY(), degree);
    }

    /**
     * 将p1以原点为基准旋转degree度
     *
     * @param x      x, y确定一个点
     * @param y      x, y确定一个点
     * @param degree 需要旋转的度数
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:21 PM
     * @since 1.0
     */
    public static Point2D rotate(double x, double y, double degree) {
        return rotate(defaultZero.getX(), defaultZero.getY(), x, y, degree);
    }

    /**
     * 将p1以原点为基准旋转degree度
     *
     * @param p      一个点
     * @param degree 需要旋转的度数
     * @return java.awt.geom.Point2D
     * @author Jerry.X.He
     * @date 5/5/2017 8:22 PM
     * @since 1.0
     */
    public static Point2D rotate(Point2D p, double degree) {
        return rotate(p.getX(), p.getY(), degree);
    }

    /**
     * 判定两个浮点数是否相同
     *
     * @param d1 操作数1
     * @param d2 操作数2
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:23 PM
     * @since 1.0
     */
    public static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) < minOff;
    }

    /**
     * 将Point2D转换为Point
     *
     * @param p2d 给定的point2d
     * @return java.awt.Point
     * @author Jerry.X.He
     * @date 5/5/2017 8:23 PM
     * @since 1.0
     */
    public static java.awt.Point toPoint(Point2D p2d) {
        return new java.awt.Point((int) p2d.getX(), (int) p2d.getY());
    }

}
