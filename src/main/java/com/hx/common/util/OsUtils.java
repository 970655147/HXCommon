/**
 * file name : OsUtils.java
 * created at : 23:11:59 2016-12-30
 * created by 970655147
 */

package com.hx.common.util;

import com.hx.common.awt.FileTransferable;
import com.hx.common.awt.ImageTransferable;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 操作系统相关的工具
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:24 PM
 */
public final class OsUtils {

    // disable constructor
    private OsUtils() {
        InnerTools.assert0("can't instantiate !");
    }


    // ------------ 将数据复制到剪切板 ------- 2016.04.07 -------------

    /**
     * windows剪切板 和内存交互数据
     *
     * @param str 需要复制额字符串
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:24 PM
     * @since 1.0
     */
    public static void copyStringToClipBoard(String str) {
//		Clipboard clipboard = System.getToolkit().getSystemClipboard();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(str);
        clipboard.setContents(ss, null);
    }

    /**
     * windows剪切板 和内存交互数据
     *
     * @param img 给定的图片
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:24 PM
     * @since 1.0
     */
    public static void copyImgToClipBoard(RenderedImage img) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
        ImageTransferable selection = new ImageTransferable(img);  //图像通道
        clipboard.setContents(selection, null);
    }

    /**
     * windows剪切板 和内存交互数据
     *
     * @param files 给定的文件列表
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:24 PM
     * @since 1.0
     */
    public static void copyFilesToClipBoard(List<File> files) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        FileTransferable selection = new FileTransferable(files);
        clipboard.setContents(selection, null);
    }

    /**
     * 获取剪切板的字符串
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:24 PM
     * @since 1.0
     */
    public static String getStringFromClipBoard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        DataFlavor flavor = DataFlavor.stringFlavor;
        String res = InnerTools.EMPTY_STR;

        if (clipboard.isDataFlavorAvailable(flavor)) { //是否符合剪贴板的数据类型
            try {
                res = clipboard.getData(flavor).toString();
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    /**
     * 获取剪切板的图片
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:24 PM
     * @since 1.0
     */
    public static RenderedImage getImgFromClipBoard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        DataFlavor flavor = DataFlavor.imageFlavor;
        RenderedImage img = null;

        if (clipboard.isDataFlavorAvailable(flavor)) {
            try {
                img = (RenderedImage) clipboard.getData(flavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return img;
    }

    /**
     * 获取剪切板的文件列表
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:24 PM
     * @since 1.0
     */
    public static List<File> getFilesFromClipBoard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        DataFlavor flavor = DataFlavor.javaFileListFlavor;
        List<File> files = null;

        if (clipboard.isDataFlavorAvailable(flavor)) {
            try {
                files = (List<File>) clipboard.getData(flavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return files;
    }

}
