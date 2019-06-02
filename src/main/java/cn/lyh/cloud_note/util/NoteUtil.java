
package cn.lyh.cloud_note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
/*
 * 利用UUID算法生成主键
 */

public class NoteUtil {
	public static String createId() {
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString();
		return id.replace("-", "");
	}
/*
	public static String md5(String src) {
		try {
			MessageDigest md=MessageDigest.getInstance("");
			//MD5加密处理
			byte[] output=md.digest(src.getBytes());
			//Base64处理
			String ret=Base64.encodeBase64String();
			return ret;
		} catch (Exception e) {
			throw new NoteException("密码加密失败",e);
		}
	}*/
	public static void main(String[] ars) {
		System.out.println(createId());
	}
}
