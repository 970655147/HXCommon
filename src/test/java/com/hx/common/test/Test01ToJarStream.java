package com.hx.common.test;

import com.hx.common.util.InnerTools;
import org.junit.Test;

import java.io.*;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

/**
 * Test01ToJarStream
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:06 PM
 */
public class Test01ToJarStream {

    // 测试将给定的projectPath中的数据写出到给定的jarPath对应的文件[使用Jar 'Input / Output' Stream]
    @Test
    public void testForStream() throws Exception {
        String projPath = "";
        String jarPath = null;

        testForStream(projPath, jarPath);
    }

    private void testForStream(String projectPath, String jarPath) throws IOException {
//		JarFile jar = new JarFile(new File(jarPath) );
        JarInputStream jis = new JarInputStream(new BufferedInputStream(new FileInputStream(jarPath)));
        JarOutputStream jos = new JarOutputStream(new BufferedOutputStream(new FileOutputStream("D:/1.jar")));

        byte[] buff = new byte[1024];
        int len = -1;

        // ZipExceptione : casuse expect 1126, but got 1130 !
        // copy by JarInputStream, failed !
        ZipEntry entry = null;
        while ((entry = jis.getNextEntry()) != null) {
            InnerTools.log(entry.getName() + " -> " + entry.getSize() + " -> " + entry.getCompressedSize());
//			jos.putNextEntry(entry);
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(Tools.getNextTmpPath(Tools.TXT)) );
            while ((len = jis.read(buff)) > 0) {
//				jos.write(buff, 0, len);
//				bos.write(buff, 0, len);
            }
//			jos.closeEntry();
//			bos.close();
        }
        jis.close();


        // copy by JarFile, failed !
//		Enumeration<JarEntry> it = jar.entries();
//		while(it.hasMoreElements() ) {
//			JarEntry entry = it.nextElement();
//			Log.log(entry.getName() );
//			jos.putNextEntry(entry);
//			BufferedInputStream bis = new BufferedInputStream(jar.getInputStream(entry) );
//			while((len = bis.read(buff)) > 0) {
//				jos.write(buff, 0, len);
//			}
//			jos.closeEntry();
//		}


//		jos.putNextEntry(new ZipEntry("a.txt"));
//		jos.write("HelloWorld.java".getBytes() );

        File binFolder = new File(projectPath);

//		jar.close();
        jos.close();
        jis.close();

//		new ZipEntry(name)
    }




}
