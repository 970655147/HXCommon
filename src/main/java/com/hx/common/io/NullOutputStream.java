/**
 * file name : NullOutputStream.java
 * created at : 9:54:39 PM Apr 15, 2016
 * created by 970655147
 */

package com.hx.common.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 直接将给定的需要写出的数据丢弃
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:14 PM
 */
public class NullOutputStream extends OutputStream {

	@Override
	public void write(int b) throws IOException {

	}

}
