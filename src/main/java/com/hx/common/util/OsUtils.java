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
 * ����ϵͳ��صĹ���
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


    // ------------ �����ݸ��Ƶ����а� ------- 2016.04.07 -------------

    /**
     * windows���а� ���ڴ潻������
     *
     * @param str ��Ҫ���ƶ��ַ���
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
     * windows���а� ���ڴ潻������
     *
     * @param img ������ͼƬ
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:24 PM
     * @since 1.0
     */
    public static void copyImgToClipBoard(RenderedImage img) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //�õ�ϵͳ������
        ImageTransferable selection = new ImageTransferable(img);  //ͼ��ͨ��
        clipboard.setContents(selection, null);
    }

    /**
     * windows���а� ���ڴ潻������
     *
     * @param files �������ļ��б�
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
     * ��ȡ���а���ַ���
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

        if (clipboard.isDataFlavorAvailable(flavor)) { //�Ƿ���ϼ��������������
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
     * ��ȡ���а��ͼƬ
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
     * ��ȡ���а���ļ��б�
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
