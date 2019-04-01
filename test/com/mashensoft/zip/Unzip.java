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
			
//			//父目录如果没有文件夹，就创建文件夹
//			if (entry.isDirectory()) {
//				// 如果是路径
//				File f = new File(basePath + File.separator + fileName);
//				f.mkdirs();
//			} else {
			createFolder(sourcePath,fileName);
				OutputStream os = new BufferedOutputStream(new FileOutputStream(basePath + File.separator + fileName));
				BufferedInputStream br = new BufferedInputStream(zip);
				int len = 0;
				byte[] b = new byte[1028 * 1024];
				while ((len = br.read(b)) != -1) {
					os.write(b, 0, len);
				}
				os.close();
			}
//		}
		zip.closeEntry();
		zip.close();
	}
	public static String  createFolder(String sourcePath,String filePath) {
		String baseFolder = new File(sourcePath).getParent();
		File file = new File(filePath);
		String parentFolder = file.getParent();
		String str = baseFolder+File.separator+parentFolder;
		File desc = new File(str);
		desc.mkdirs();
		return str;
	}
	public static void main(String[] args) {
		File file = new File("d:/a.txt");
		String basePath = file.getParent();
		System.out.println(basePath);
	}
}
