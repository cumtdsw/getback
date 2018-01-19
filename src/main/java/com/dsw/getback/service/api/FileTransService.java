package com.dsw.getback.service.api;

public interface FileTransService {
	/**
	 * 
	 * @param sourceIp 源地址
	 * @param sourceFile 源文件地址（目录或文件）
	 * @param targetIp 目的地址ip
	 * @param targetFile 目的文件地址（目录或文件）
	 */
	public void triggerFileTrans(String sourceIp, String sourceFile, String targetIp, String targetFile);

}
