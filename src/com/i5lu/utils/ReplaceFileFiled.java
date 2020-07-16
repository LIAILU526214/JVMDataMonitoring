package com.i5lu.utils;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReplaceFileFiled {
	/**
	 * 替换文本文件中的 非法字符串
	 * 
	 * @param path
	 * @param srcStr     原有的内容
	 * @param replaceStr 要替换的内容
	 * @throws IOException
	 */
	public static boolean replacTextContent(String path, String srcStr, String replaceStr) throws IOException {
		// 读
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		FileReader in = new FileReader(file);
		BufferedReader bufIn = new BufferedReader(in);
		// 内存流, 作为临时流
		CharArrayWriter tempStream = new CharArrayWriter();
		// 替换
		String line = null;
		while ((line = bufIn.readLine()) != null) {
			// 替换每行中, 符合条件的字符串
			line = line.replaceAll(srcStr, replaceStr);
			// 将该行写入内存
			tempStream.write(line);
			// 添加换行符
			tempStream.append(System.getProperty("line.separator"));
		}
		// 关闭 输入流
		bufIn.close();
		// 将内存中的流 写入 文件
		FileWriter out = new FileWriter(file);
		tempStream.writeTo(out);
		out.close();
		return true;

	}
}
