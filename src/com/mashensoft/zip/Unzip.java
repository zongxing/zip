package com.mashensoft.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.condition.OS;

public class Unzip {
	/**
	 * 解压文件
	 * @param sourcePath  压缩包文件
	 * @param descPath	解压到到的目录
	 * @throws Exception
	 */
	public static void unzipFolder(String sourcePath, String descPath) throws Exception {
		File file = new File(sourcePath);
		String basePath = file.getParent();
		ZipInputStream zip = new ZipInputStream(new FileInputStream(sourcePath));
		ZipEntry entry = null;
		while ((entry = zip.getNextEntry()) != null) {
			String fileName = entry.getName();
			OutputStream os = new BufferedOutputStream(new FileOutputStream(basePath + File.separator + fileName));
			BufferedInputStream br = new BufferedInputStream(zip);
			int len = 0;
			byte[] b = new byte[1028 * 1024];
			while ((len = br.read(b)) != -1) {
				os.write(b, 0, len);
			}
			os.close();
		}
		zip.closeEntry();
		zip.close();
	}

	public static void unzipFolder(String sourcePath) throws Exception {
		ZipInputStream zip = new ZipInputStream(new FileInputStream(sourcePath));
		ZipEntry entry = null;
		File file = new File(sourcePath);
		String basePath = file.getParent();
		while ((entry = zip.getNextEntry()) != null) {
			String fileName = entry.getName();
			System.out.println(fileName);
			createFolder(sourcePath, fileName);
			OutputStream os = new BufferedOutputStream(new FileOutputStream(basePath + File.separator + fileName));
			BufferedInputStream br = new BufferedInputStream(zip);
			int len = 0;
			byte[] b = new byte[1028 * 1024];
			while ((len = br.read(b)) != -1) {
				os.write(b, 0, len);
			}
			os.close();
		}
		zip.closeEntry();
		zip.close();
	}

	/**
	 * 创建文件夹，解压时，如果压缩包中的文件在第二层或者以后，则先在本地文件系统中创建文件夹，再解压
	 * 
	 * @param sourcePath 压缩包在本地磁盘中的路径
	 * @param filePath   压缩包里的文件路径
	 * @return
	 */
	public static String createFolder(String sourcePath, String filePath) {
		String baseFolder = new File(sourcePath).getParent();
		File file = new File(filePath);
		String parentFolder = file.getParent();
		String str = baseFolder + File.separator + parentFolder;
		File desc = new File(str);
		desc.mkdirs();
		return str;
	}
}
