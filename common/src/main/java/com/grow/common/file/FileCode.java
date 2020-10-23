package com.grow.common.file;

public enum FileCode {
	/**
	 * png格式
	 */
	PNG("png"),
	/**
	 * jpg格式
	 */
	JPG("jpg"),
	/**
	 * gif格式
	 */
	GIF("gif"),
	/**
	 * 后缀为：.png
	 */
	FILE_PNG(".png"),
	/**
	 * 后缀为：.jpg
	 */
	FILE_JPG(".jpg"),
	/**
	 * 后缀为：.jpeg
	 */
	FILE_JPEG(".jpeg"),
	/**
	 * 后缀为：.gif
	 */
	FILE_GIF(".gif"),
	/**
	 * 后缀为：.txt
	 */
	FILE_TXT(".txt"),
	/**
	 * 后缀为：.pdf
	 */
	FILE_PDF(".pdf");

	private final String value;

	private FileCode(String value) {
		this.value = value;
	}

	public String toValue() {
		return this.value;
	}
}
