/**
 * file name : ImageFravable.java
 * created at : 9:18:42 PM Oct 23, 2015
 * created by 970655147
 */

package com.hx.common.awt;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.RenderedImage;

/**
 * ʵ��ͼ��ͨ�ŵĹܵ��ӿ�
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 7:47 PM
 */
public class ImageTransferable implements Transferable {

    /**
     * �洢��img����
     */
    private RenderedImage img;

    /**
     * ��ʼ�� [�Բ� ����ĳ�ʼ������, ���һ�����һЪ, img = img [�ͼ�����] ]
     *
     * @param img ������ͼƬ
     * @since 1.0
     */
    public ImageTransferable(RenderedImage img) {
        this.img = img;
    }

    /**
     * getter
     */
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.imageFlavor);
    }

    /**
     * ���ش洢������
     *
     * @param flavor flavor
     * @return java.lang.Object
     * @author Jerry.X.He
     * @date 5/5/2017 7:46 PM
     * @since 1.0
     */
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(DataFlavor.imageFlavor)) {
            return img;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
