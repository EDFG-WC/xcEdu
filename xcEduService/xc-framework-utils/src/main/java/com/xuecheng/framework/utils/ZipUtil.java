package com.xuecheng.framework.utils;


import net.lingala.zip4j.core.ZipFile;

public class ZipUtil {

	/**
	 * 解压zip文件
	 */
	public static void unzip(String zipFilePath, String targetPath) throws Exception {
		ZipFile zipFile = new ZipFile(zipFilePath);
		zipFile.extractAll(targetPath);
	}

	/**
	 * 解压zip文件（带密码）
	 */
	public static void unzip(String zipFilePath, String password, String targetPath)
		throws Exception {
		ZipFile zipFile = new ZipFile(zipFilePath);
		if (zipFile.isEncrypted()) {
			zipFile.setPassword(password);
		}
		zipFile.extractAll(targetPath);
	}

	public static void main(String[] args) throws Exception {
		ZipUtil.unzip("F:\\develop\\upload\\upload.zip", "F:\\develop\\upload\\zip\\");
	}
}
