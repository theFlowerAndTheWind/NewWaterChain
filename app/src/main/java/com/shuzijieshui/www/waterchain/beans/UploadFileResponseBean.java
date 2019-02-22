package com.shuzijieshui.www.waterchain.beans;

public class UploadFileResponseBean {
	String url;	//图片地址	字符串(string)		图片相对地址 20181214/e35aebda9dcc21472a2c79db61eb0234.PNG
	String filename;	//文件名称	字符串(string)		IMG_1728.PNG
//	String full_url; //图片全路径url

	public String getUrl() {
		return url == null ? "" : url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename == null ? "" : filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

//	public String getFull_url() {
//		return full_url == null ? "" : full_url;
//	}
//
//	public void setFull_url(String full_url) {
//		this.full_url = full_url;
//	}
}