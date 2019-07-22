//package com.feature.gcoin.common.util;
//
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ResourceBundle;
//
///**
// * @author TienNV
// * @CreatedDate Oct 10, 2017 9:12:12 AM
// */
//@Component
//public class PropertiesUtils {
//
//	private String fileDirectory;
//
//	@PostConstruct
//	public void init() {
//		ResourceBundle bundle = ResourceBundle.getBundle("config/config");
//		this.fileDirectory = bundle.getString("static.file.location") == null ? "" : bundle.getString("static.file.location");
//	}
//
//	/**
//	 * @return the fileDirectory
//	 */
//	public String getFileDirectory() {
//		return fileDirectory;
//	}
//
//	/**
//	 * @param fileDirectory
//	 *            the fileDirectory to set
//	 */
//	public void setFileDirectory(String fileDirectory) {
//		this.fileDirectory = fileDirectory;
//	}
//
//}
