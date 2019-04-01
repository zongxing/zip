package com.mashensoft.zip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ZipTest {
	/**
	 * ����ѹ��һ���ļ�
	 * @throws Exception
	 */
	@Test
	void testZipFile() throws Exception {
		Zip.zipFile("e:/data.txt", "e:/data3.zip");
	}
	/**
	 * ����ѹ��һ���ļ���
	 * @throws Exception
	 */
	@Test
	void testZipFileFolder() throws Exception {
		Zip.zipFileFolderOneLevel("e:/testfolder", "e:/testfolder.zip");
	}
	/**
	 * ����ѹ��һ���ļ���,�����ļ���
	 * @throws Exception
	 */
	@Test
	void testZipFileFolder2() throws Exception {
		Zip.zipFileFolderOneLevel("e:/��Ŀ", "e:/��Ŀ.zip");
	}
	/**
	 * ���Եݹ�
	 * @throws Exception
	 */
	@Test
	void testRecursion() throws Exception {
		Zip.recursion("e:/��Ŀ");
	}
	@Test
	void testRecursion2() throws Exception {
		Zip.recursion("e:/��Ŀ","e:/��Ŀ.zip");
	}
	@Test
	void testRecursion3() throws Exception {
		Zip.recursion("D:\\soft\\apache-tomcat-8.0.47\\bin","D:\\soft\\apache-tomcat-8.0.47\\bin.zip");
	}
	@Test
	void testUnzipFolder() throws Exception {
		Unzip.unzipFolder("E:/bin.zip");
	}
	@Test
	void testUnzipFolder2() throws Exception {
		Unzip.unzipFolder("E:/bin/��Ŀ.zip");
	}
	@Test
	void testCreateFolder() throws Exception {
		System.out.println(Unzip.createFolder("E:/bin.zip","bin/a.txt"));
	}
	@Test
	void testCreateFolder2() throws Exception {
		System.out.println(Unzip.createFolder("E:/bin.zip","bin/a/b/a.txt"));
	}

}
