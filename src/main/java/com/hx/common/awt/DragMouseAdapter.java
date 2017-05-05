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
 * ��ק�����MouseListener
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 7:44 PM
 */
public class DragMouseAdapter extends MouseAdapter {

    /**
     * ���������
     */
    protected Component component;
    /**
     * ��קǰ��x
     */
    private int lastX = -1;
    /**
     * ��קǰ��y
     */
    private int lastY = -1;

    /**
     * ��ʼ��
     *
     * @param component �����ĸ�Ԫ��
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
