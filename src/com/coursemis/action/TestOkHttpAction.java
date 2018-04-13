package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.net.SocketFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class TestOkHttpAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	public void okhttp() throws IOException, NoSuchAlgorithmException {
		System.out.println("测试。。。。。");
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String header = request.getHeader(headerName);
			System.out.println("测试响应头。。。。" + headerName + "......." + header);
		}
		
		String key = request.getHeader("sec-websocket-key");
		String magicString = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		byte[] bytes = (key+magicString).getBytes(Charset.forName("UTF-8")) ;
		byte[] digest = MessageDigest.getInstance("SHA-1").digest(bytes) ;
		String encode = encode(digest, MAP);
		response.setStatus(101);
		response.setHeader("Connection", "Upgrade");
		response.setHeader("Upgrade", "websocket");
		response.setHeader("Sec-WebSocket-Accept", encode);
		PrintWriter writer = response.getWriter();
		writer.write("123");
		String protocol = request.getProtocol();
		System.out.println("测试协议。。。" + protocol);
		SocketFactory socket = SocketFactory.getDefault() ;
		Socket createSocket = socket.createSocket() ;
	}
	
	
	
	private static final byte[] MAP = new byte[] {
	      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
	      'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
	      'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
	      '5', '6', '7', '8', '9', '+', '/'
	  };
	
	private static String encode(byte[] in, byte[] map) {
	    int length = (in.length + 2) / 3 * 4;
	    byte[] out = new byte[length];
	    int index = 0, end = in.length - in.length % 3;
	    for (int i = 0; i < end; i += 3) {
	      out[index++] = map[(in[i] & 0xff) >> 2];
	      out[index++] = map[((in[i] & 0x03) << 4) | ((in[i + 1] & 0xff) >> 4)];
	      out[index++] = map[((in[i + 1] & 0x0f) << 2) | ((in[i + 2] & 0xff) >> 6)];
	      out[index++] = map[(in[i + 2] & 0x3f)];
	    }
	    switch (in.length % 3) {
	      case 1:
	        out[index++] = map[(in[end] & 0xff) >> 2];
	        out[index++] = map[(in[end] & 0x03) << 4];
	        out[index++] = '=';
	        out[index++] = '=';
	        break;
	      case 2:
	        out[index++] = map[(in[end] & 0xff) >> 2];
	        out[index++] = map[((in[end] & 0x03) << 4) | ((in[end + 1] & 0xff) >> 4)];
	        out[index++] = map[((in[end + 1] & 0x0f) << 2)];
	        out[index++] = '=';
	        break;
	    }
	    try {
	      return new String(out, "US-ASCII");
	    } catch (UnsupportedEncodingException e) {
	      throw new AssertionError(e);
	    }
	  }
	
	
	
	
	
	
	
	
	
	

	/**
	 * @param decript
	 *            要加密的字符串
	 * @return 加密的字符串 SHA1加密
	 */
	public final static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param decript
	 *            要加密的字符串
	 * @return 加密的字符串 MD5加密
	 */
	public final static String MD5(String decript) {
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			byte[] strTemp = decript.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte tmp[] = mdTemp.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char strs[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				strs[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				strs[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			return new String(strs).toUpperCase(); // 换后的结果转换为字符串
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	private String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bt;
	}

}
