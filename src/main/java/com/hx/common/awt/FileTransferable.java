/**
 * file name : ImageFravable.java
 * created at : 9:18:42 PM Oct 23, 2015
 * created by 970655147
 */

package com.hx.common.awt;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.util.List;

/**
 * ʵ���ļ�ͨ�ŵĹܵ��ӿ�
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 7:45 PM
 */
public class FileTransferable implements Transferable {

    /**
     * �洢��file����
     */
    private List<File> files;

    /**
     * ��ʼ��
     *
     * @param files �������ļ��б�
     * @since 1.0
     */
    public FileTransferable(List<File> files) {
        this.files = files;
    }

    /**
     * getter
     */
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.javaFileListFlavor};
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.javaFileListFlavor);
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
        if (flavor.equals(DataFlavor.javaFileListFlavor)) {
            return files;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
