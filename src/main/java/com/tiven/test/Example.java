package com.tiven.test;

import com.tiven.jni.NlpirTest.CLibrary;

/** 
 * @author Pecan 
 * 类说明 
 */
public class Example {

	public static void main(String[] args) throws Exception{
		String argu = "";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "GBK";
		int charset_type = 1;
		// int charset_type = 0;
		// 调用printf打印信息
		int init_flag = CLibrary.Instance.NLPIR_Init(argu.getBytes(system_charset), charset_type, "0".getBytes(system_charset));

		if (0 == init_flag) {
			System.err.println("初始化失败！");
			return;
		}
		String sInput = "据悉，质检总局已将最新有关情况再次通报美方，要求美方加强对输华玉米的产地来源、运输及仓储等环节的管控措施，有效避免输华玉米被未经我国农业部安全评估并批准的转基因品系污染。";

		String nativeBytes = null;
		nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 3);
		System.out.println("分词结果为： " + nativeBytes);
		CLibrary.Instance.NLPIR_Exit();
	}
}
