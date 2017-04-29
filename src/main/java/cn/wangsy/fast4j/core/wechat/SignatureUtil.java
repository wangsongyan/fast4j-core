/**
 * 
 */
package cn.wangsy.fast4j.core.wechat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author wangsy
 * @date 2017年4月29日下午2:26:43
 */
public class SignatureUtil {

	private static final String token = "asdasdsdasdsadas";
	
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		
		String[] arguments = new String[]{token,timestamp,nonce};
		Arrays.sort(arguments);
		
		StringBuilder builder = new StringBuilder();
		for(String argument:arguments){
			builder.append(argument);
		}
		
		String tmpStr = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(builder.toString().getBytes());
	        tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	 /**
     * 将字节转换为十六进制字符串
     * 
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    /**
     * 将字节数组转换为十六进制字符串
     * 
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
	
}
