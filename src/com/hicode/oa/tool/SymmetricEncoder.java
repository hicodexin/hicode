package com.hicode.oa.tool;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * aes加密工具
 * 
 * @author yuanshaosong
 *
 */
public class SymmetricEncoder {
	private static final String encryptEncodeRules = "dyjappgroup";// 定义默认加密规则
	private static final String symbol="dyjappkey";
	
	public static String getSymbol() {
		return symbol;
	}

	/**
	 * @param content
	 * @return string @Description： Aes加密流程： 1.构造密钥生成器 2.根据ecnodeRules规则初始化密钥生成器
	 *         3.产生密钥 4.创建和初始化密码器 * 5.内容加密 6.返回字符串
	 */
	public static String invokeEncryptEncode(String content) {
		return parseByte2HexStr(encrypt(content, encryptEncodeRules));
	}

	/**
	 * @param content
	 * @return String @Description：解密流程： * 1.同加密1-4步 2.将加密后的字符串反纺成byte[]数组
	 *         3.将加密内容解密
	 */
	public static String invokeDecryptEncode(String content) {
		return new String(decrypt(parseHexStr2Byte(content), encryptEncodeRules));
	}
	
	private static SecureRandom getKey(String keystr) throws NoSuchAlgorithmException{
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		 random.setSeed(keystr.getBytes());
		 return random;
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, getKey(password));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, getKey(password));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) {
//		long uid = 342207l;
		
		String uid = "us_1005";
		System.out.println("用户id：" + uid);
//     jdk1.7之后支持
//		String date = DateFormatUtils.format(new Date(), "yyyy+MM+dd");
		String date = new SimpleDateFormat("yyyy+MM+dd").format(new Date());
		String key = UUID.randomUUID().toString() + "@" + uid + "@" + date + "@"+symbol;
		System.out.println(key);
		String encodedKey = invokeEncryptEncode(key);
		System.out.println("加密后的秘钥：" + encodedKey);
		String key1 = invokeDecryptEncode(encodedKey);
		System.out.println("解密后的秘钥：" + key1);
//		System.out.println("用户id为：" + Long.parseLong(key1.split("@")[1])+",日期："+key1.split("@")[2]+",标识:"+key1.split("@")[3]);
		System.out.println("用户id为：" + key1.split("@")[1]+",日期："+key1.split("@")[2]+",标识:"+key1.split("@")[3]);
		
	}
	// public static void main(String[] args) {
	// String[] keys = {
	// "123456", "123abc"
	// };
	// System.out.println("测试加密：");
	// for (String key : keys) {
	// System.out.print("原始密文key:[" + key + "],");
	// String encryptString = invokeEncryptEncode(key);
	// System.out.print("加密encryptString:[" + encryptString + "],");
	// String decryptString = invokeDecryptEncode(encryptString);
	// System.out.println("解密decryptString:[" + decryptString + "]");
	// }
	// }
}
