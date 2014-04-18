package com.tiven.jni;

import java.io.UnsupportedEncodingException;


import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirTest {

	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
		CLibrary Instance = (CLibrary) Native.loadLibrary(NativeUtils.loadLibraryFromJarPath(LoadLib.getLibPath()), CLibrary.class);

		// printf函数声明
		public int NLPIR_Init(byte[] sDataPath, int encoding,
				byte[] sLicenceCode);

		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);

		public void NLPIR_Exit();
	}

	public static String transString(String aidString, String ori_encoding,
			String new_encoding) {
		try {
			return new String(aidString.getBytes(ori_encoding), new_encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String argu = "";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "GBK";
		int charset_type = 1;
		// int charset_type = 0;
		// 调用printf打印信息
		int init_flag = CLibrary.Instance.NLPIR_Init(argu
				.getBytes(system_charset), charset_type, "0"
				.getBytes(system_charset));

		if (0 == init_flag) {
			System.err.println("初始化失败！");
			return;
		}

		String sInput = "据悉，质检总局已将最新有关情况再次通报美方，要求美方加强对输华玉米的产地来源、运输及仓储等环节的管控措施，有效避免输华玉米被未经我国农业部安全评估并批准的转基因品系污染。";

		String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 3);
			System.out.println("分词结果为： " + nativeBytes);
			// String nativeStr = new String(nativeBytes, 0,
			// nativeBytes.length,"utf-8");
			// System.out.println("分词结果为： "
			// + transString(nativeBytes, system_charset, "UTF-8"));
			//
			// System.out.println("分词结果为： "
			// + transString(nativeBytes, "gb2312", "utf-8"));

			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);

			System.out.print("关键词提取结果是：" + nativeByte);

			// int nativeElementSize = 4 * 6 +8;//size of result_t in native
			// code
			// int nElement = nativeByte.length / nativeElementSize;
			// ByteArrayInputStream(nativeByte));
			//
			// nativeByte = new byte[nativeByte.length];
			// nCountKey = testNLPIR30.NLPIR_KeyWord(nativeByte, nElement);
			//
			// Result[] resultArr = new Result[nCountKey];
			// DataInputStream dis = new DataInputStream(new
			// ByteArrayInputStream(nativeByte));
			// for (int i = 0; i < nCountKey; i++)
			// {
			// resultArr[i] = new Result();
			// resultArr[i].start = Integer.reverseBytes(dis.readInt());
			// resultArr[i].length = Integer.reverseBytes(dis.readInt());
			// dis.skipBytes(8);
			// resultArr[i].posId = Integer.reverseBytes(dis.readInt());
			// resultArr[i].wordId = Integer.reverseBytes(dis.readInt());
			// resultArr[i].word_type = Integer.reverseBytes(dis.readInt());
			// resultArr[i].weight = Integer.reverseBytes(dis.readInt());
			// }
			// dis.close();
			//
			// for (int i = 0; i < resultArr.length; i++)
			// {
			// System.out.println("start=" + resultArr[i].start + ",length=" +
			// resultArr[i].length + "pos=" + resultArr[i].posId + "word=" +
			// resultArr[i].wordId + "  weight=" + resultArr[i].weight);
			// }

			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
}
