package com.mashensoft.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	/**
	 * ѹ������ǰ�ļ���
	 * @param sourceFile
	 * @throws Exception
	 */
	public static void zipFile(String sourceFile) throws Exception {
		zipFile(sourceFile,sourceFile);
	}
	/**
	 * ѹ��һ���ļ�
	 * 
	 * @param sourceFile   Դ�ļ�
	 * @param descFilePath Ŀ���ļ���zip��׺��β
	 * @throws Exception
	 */
	public static void zipFile(String sourceFile, String descFilePath) throws Exception {
		File file = new File(sourceFile);
		String fileName = file.getName();
		System.out.println(fileName);
		if(fileName.startsWith(".")) {
			descFilePath = sourceFile+".zip";
			System.out.println(descFilePath);
		}else if(!fileName.startsWith(".")&&fileName.contains(".")) {
			descFilePath = sourceFile.substring(0,descFilePath.lastIndexOf("."))+".zip";
			System.out.println(descFilePath);
		}
		
		
		
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(descFilePath));
		ZipEntry zipEntry = new ZipEntry(fileName);
		zip.putNextEntry(zipEntry);
		if (file.isFile()) {
			FileInputStream is = new FileInputStream(file);
			byte[] b = new byte[1024 * 1024];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				zip.write(b, 0, len);
			}
			is.close();
		}
		zip.flush();
		zip.closeEntry();
		zip.close();
		
	}
	/**
	 * ѹ��ֻ��һ���ļ��е������ļ�
	 * @param sourceFile
	 * @param descFilePath
	 * @throws Exception
	 */
	public static void zipFileFolderOneLevel(String sourceFile, String descFilePath) throws Exception {
		File file = new File(sourceFile);
		if(!file.isDirectory()) {
			throw new Exception("�����ļ���");
		}
		FileInputStream is = null;
		String folderName = file.getName();
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(descFilePath));
		
		File[] files = file.listFiles();
		for(File f:files) {
			if(f.isDirectory()) {
				continue;
			}
			ZipEntry zipEntry = new ZipEntry(folderName+File.separator+f.getName());
			zip.putNextEntry(zipEntry);
				is = new FileInputStream(f);
				byte[] b = new byte[1024 * 1024];
				int len = 0;
				while ((len = is.read(b)) != -1) {
					zip.write(b, 0, len);
				}
				is.close();
		}
		
		zip.flush();
		zip.closeEntry();
		zip.close();
		
	}
	/**
	 * ѹ��һ���ļ�
	 * @throws FoundException 
	 */
	public static void compressFile(OutputStream zip,File file) throws Exception {
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		byte[] b = new byte[1024 * 1024*1024];
		int len = 0;
		while ((len = is.read(b)) != -1) {
			zip.write(b, 0, len);
		}
		is.close();
	}
	
	/**
	 * �ݹ��ļ�
	 * @param filePath
	 * @throws IOException 
	 */
	public static void recursion(String sourceFile, String descFilePath) throws Exception {
		
		
		File file = new File(sourceFile);
		if(!file.isDirectory()) {
			throw new Exception("�����ļ���");
		}
		FileInputStream is = null;
		String folderName = file.getName();
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(descFilePath+".zip"));
		BufferedOutputStream os = new BufferedOutputStream(zip);
		Files.walkFileTree(Paths.get(sourceFile),new FileVisitor<Object>() {

			@Override
			public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
				Path p = (Path)file;
				//String filePath = p.toString().replace(p.getRoot().toString(), "");
				String filePath = getFileName(sourceFile,p.toString());
				ZipEntry zipEntry = new ZipEntry(filePath);
				zip.putNextEntry(zipEntry);
				try {
					System.out.println(p);
					compressFile(os,p.toFile());
				} catch (Exception e) {
					e.printStackTrace();
				}
				zip.flush();
//				System.out.println(p.toString());
//				System.out.println(p.getFileName().toString());
//				System.out.println(p.getRoot().toString());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
				System.out.println("ʧ��");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}

		});
		zip.flush();
		zip.closeEntry();
		zip.close();
	}
	/**
	 * �ݹ��ļ�
	 * @param filePath
	 * @throws IOException 
	 */
	public static void recursion(String filePath) throws Exception {
		recursion(filePath, filePath);
//		
//		
//		
//		
//		Files.walkFileTree(Paths.get(filePath),new FileVisitor<Object>() {
//			
//			@Override
//			public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
//				// TODO Auto-generated method stub
//				return FileVisitResult.CONTINUE;
//			}
//			
//			@Override
//			public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
//				//����File f = (File)file;
//				Path p = (Path)file;
//				System.out.println(p.toString());
//				System.out.println(p.getFileName().toString());
//				System.out.println(p.getRoot().toString());
//				return FileVisitResult.CONTINUE;
//			}
//			
//			@Override
//			public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
//				System.out.println("ʧ��");
//				return FileVisitResult.CONTINUE;
//			}
//			
//			@Override
//			public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
//				// TODO Auto-generated method stub
//				return FileVisitResult.CONTINUE;
//			}
//			
//		});
		
	}
	public static String getFileName(String currentFileName,String fullPath) {
		File file = new File(currentFileName);
		return file.getName().replace(File.separator, "")+fullPath.replace(currentFileName, "");
	}
	public static void main(String[] args) {
		System.out.println(getFileName("d:/soft","d:/soft/m/b/a.txt"));
	}
}
