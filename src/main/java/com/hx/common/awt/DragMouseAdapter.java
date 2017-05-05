/**
 * file name : DragMouseListener.java
 * created at : 9:29:40 PM Nov 24, 2015
 * created by 970655147
 */

package com.hx.common.awt;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 拖拽组件的MouseListener
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 7:44 PM
 */
public class DragMouseAdapter extends MouseAdapter {

    /**
     * 依赖的组件
     */
    protected Component component;
    /**
     * 拖拽前的x
     */
    private int lastX = -1;
    /**
     * 拖拽前的y
     */
    private int lastY = -1;

    /**
     * 初始化
     *
     * @param component 依赖的父元素
     * @since 1.0
     */
    public DragMouseAdapter(Component component) {
        super();
        this.component = component;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastX != -1) {
            java.awt.Point point = component.getLocation();
            point.x += e.getX() - lastX;
            point.y += e.getY() - lastY;
            component.setLocation(point);
        }
    }

}
