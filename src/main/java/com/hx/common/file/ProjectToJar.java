/**
 * file name : ProjectToJar.java
 * created at : 下午7:07:08 2016年8月11日
 * created by 970655147
 */

package com.hx.common.file;

import com.hx.common.util.InnerTools;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

/**
 * 将给定的项目中的东西打包到一个jar中
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:10 PM
 */
public final class ProjectToJar {

    // disable constructor
    private ProjectToJar() {
        InnerTools.assert0("can't instantiate !");
    }

    // 清单文件的常量, 以及一些常量
    /**
     * Manifest-Version
     */
    public static final String MANIFEST_VERSION = "Manifest-Version";
    /**
     * Main-Class
     */
    public static final String MAIN_CLASS = "Main-Class";
    /**
     * Created-By
     */
    public static final String CREATED_BY = "Created-By";
    /**
     * $manifestVersion
     */
    public static String DEFAULT_MANIFEST_VERSION = "1.0";
    /**
     * $mainClass
     */
    public static String DEFAULT_MAIN_CLASS = "";
    /**
     * $createdBy
     */
    public static String DEFAULT_CREATED_BY = "1.7.0_40 (Oracle Corporation)";
    /**
     * 回车
     */
    public static String CRLF = "\r\n";
    /**
     * 清单文件kv的分隔符
     */
    public static String SEP = ": ";

    /**
     * 默认的清单文件配置manifestEntries
     */
    private static Map<String, String> manifestEntrysTemplates = new LinkedHashMap<>();

    static {
        manifestEntrysTemplates.put(MANIFEST_VERSION, DEFAULT_MANIFEST_VERSION);
        manifestEntrysTemplates.put(MAIN_CLASS, DEFAULT_MAIN_CLASS);
        manifestEntrysTemplates.put(CREATED_BY, DEFAULT_CREATED_BY);
    }

    /**
     * 将给定的projectPath的数据拷贝到给定的jarPath中, manifestEntrys为需要写出的清单文件的内容
     *
     * @param projectPath    项目的路径
     * @param jarPath        jar的路径
     * @param manifestEntrys 清单文件的条目
     * @param filter         需要过滤掉的文件的filter
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:00 PM
     * @since 1.0
     */
    public static void updateJarAndSrc(String projectPath, String jarPath, Map<String, String> manifestEntrys,
                                       FileNameFilter filter) throws IOException {
        JarOutputStream jarJos = null;
        try {
            jarJos = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(jarPath)));
            updateJarAndSrc0(new File(projectPath), jarJos, "", 0, filter);
            writeManifest(jarJos, manifestEntrys);
        } finally {
            if (jarJos != null) {
                jarJos.close();
            }
        }
    }

    /**
     * 创建一个默认的清单文件
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author Jerry.X.He
     * @date 5/5/2017 8:08 PM
     * @since 1.0
     */
    public static Map<String, String> newManifestEntrysTemplate() {
        return new LinkedHashMap<String, String>(manifestEntrysTemplates);
    }

    // ----------------- 辅助方法 -----------------------

    /**
     * 将给定的projectPath的数据拷贝到给定的jarPath中
     * 使用JarOutputStream 配合ZipEntry
     *
     * @param projectPath 项目的路径
     * @param jos         给定的jar输出流
     * @param prefix      当前上下文的文件夹的前缀
     * @param depth       当前上下文的深度
     * @param filter      需要过滤文件的filter
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:01 PM
     * @since 1.0
     */
    private static void updateJarAndSrc0(File projectPath, JarOutputStream jos, String prefix, int depth,
                                         FileNameFilter filter) throws IOException {
        if (projectPath.isDirectory()) {
            filter.setPatternIdx(depth);
            File[] childs = projectPath.listFiles(filter);
            for (File child : childs) {
                if (child.isDirectory()) {
                    String childFileName = prefix + "/" + child.getName();
                    if (depth == 0) {
                        childFileName = child.getName();
                    }
                    updateJarAndSrc0(child, jos, childFileName, depth + 1, filter);
                } else {
                    boolean isOk = true;
                    ZipEntry entry = null;
                    try {
                        if (depth == 0) {
                            entry = new ZipEntry(child.getName());
                        } else {
                            entry = new ZipEntry(prefix + "/" + child.getName());
                        }
                        jos.putNextEntry(entry);
                    } catch (Exception e) {
                        isOk = false;
                        InnerTools.err(e.getMessage());
                    }

                    if (isOk) {
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(child));
                        byte[] buff = new byte[2048];
                        int len = -1;

                        while ((len = bis.read(buff)) > 0) {
                            jos.write(buff, 0, len);
                        }
                        jos.closeEntry();
                        InnerTools.log("update the file : '" + child.getAbsolutePath() + "' -> '" + entry.getName() + "' success !");
                    } else {
                        InnerTools.err("update the file : '" + child.getAbsolutePath() + "' failed !");
                    }
                }
            }
        }
    }

    /**
     * 写出清单文件到给定的jar包
     *
     * @param jarJos         给定的jar输出流
     * @param manifestEntrys 清单文件的数据
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:07 PM
     * @since 1.0
     */
    private static void writeManifest(JarOutputStream jarJos, Map<String, String> manifestEntrys) throws IOException {
        jarJos.putNextEntry(new ZipEntry("META-INF/MANIFEST.MF"));
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> it = manifestEntrys.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            if ((!InnerTools.isEmpty(entry.getKey())) && (!InnerTools.isEmpty(entry.getValue()))) {
                sb.append(entry.getKey());
                sb.append(SEP);
                sb.append(entry.getValue());
                sb.append(CRLF);
            }
        }

//		Log.log(sb.toString() );
        jarJos.write(sb.toString().getBytes());
        InnerTools.log("write manifest with mainClass : '" + String.valueOf(manifestEntrys.get("Main-Class")) + "'");
    }

    /**
     * 文件名过滤器
     *
     * @author Jerry.X.He <970655147@qq.com>
     * @version 1.0
     * @date 5/5/2017 8:02 PM
     */
    public static class FileNameFilter implements FilenameFilter {
        /**
         * 需要过滤出来的文件pattern
         */
        private String[] patterns;
        /**
         * 当前正在使用的pattern的索引
         */
        private int patternIdx;

        /**
         * 初始化
         *
         * @param patterns 按照深度需求编写的文件的过滤列表
         * @since 1.0
         */
        public FileNameFilter(String[] patterns) {
            this.patterns = patterns;
        }

        @Override
        public boolean accept(File dir, String name) {
            if (patternIdx >= patterns.length) {
                return true;
            }

            return FileNameMatcher.match(name, patterns[patternIdx]);
        }

        // setter & getter
        public void setPatternIdx(int idx) {
            this.patternIdx = idx;
        }
    }

}
