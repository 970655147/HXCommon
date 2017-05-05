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
 * 实现图像通信的管道接口
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 7:47 PM
 */
public class ImageTransferable implements Transferable {

    /**
     * 存储的img对象
     */
    private RenderedImage img;

    /**
     * 初始化 [卧槽 这里的初始化问题, 把我还看了一歇, img = img [低级错误] ]
     *
     * @param img 给定的图片
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
     * 返回存储的数据
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
