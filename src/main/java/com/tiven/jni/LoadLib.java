package com.tiven.jni;

import java.util.Properties;

/** 
 * @author tiven 
 * 类说明 
 * 根据不同平台获取组件
 */
public class LoadLib {
	
	public static String getLibPath(){
		Properties properties=System.getProperties();
		StringBuffer sys=new StringBuffer("/libs/");
		String lib="";
		if (properties.get("os.name").toString().toLowerCase().contains("win")) {
			sys.append("win");
			lib="/NLPIR.dll";
		}else {
			sys.append("Linux");
			lib="/libNLPIR.so";
		}
		if (properties.get("os.arch").toString().toLowerCase().contains("86")) {
			sys.append("32");
		}else {
			sys.append("64");
		}
		sys.append(lib);
		return sys.toString();
	}
}
