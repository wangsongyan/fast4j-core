package cn.wangsy.fast4j.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class BrowserAgentUtil {

	private BrowserAgentUtil(){
		
	}
	
	public static boolean IsMoblie(HttpServletRequest request) {

		boolean isMoblie = false;
		String[] mobileAgents = { "iphone", "android", "phone", "mobile",
				"wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",
				"windows ce", "symbian", "series", "webos", "sony",
				"blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
				"pieplus", "meizu", "midp", "cldc", "motorola", "foma",
				"docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
				"huawei", "novarra", "coolpad", "webos", "techfaith",
				"palmsource", "alcatel", "amoi", "ktouch", "nexian",
				"ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
				"smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
				"pantech", "gionee", "portalmmm", "jig browser", "hiptop",
				"benq", "haier", "^lct", "320x320", "240x320", "176x220",
				"w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
				"bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
				"doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
				"keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
				"midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
				"newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",
				"port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",
				"send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",
				"sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-",
				"upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",
				"wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };

		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase()
						.indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	}

	public static boolean isIos(HttpServletRequest request) {

		boolean isIos = false;
		String[] mobileAgents = { "iphone", "ipad" };

		if (request.getHeader("User-Agent") != null) {
			String agent = request.getHeader("User-Agent").toLowerCase();
			for (String mobileAgent : mobileAgents) {
				if (agent.indexOf(mobileAgent) >= 0) {
					isIos = true;
					break;
				}
			}
		}
		return isIos;
	}

	public static boolean isAndroid(HttpServletRequest request) {

		boolean isIos = false;
		String[] mobileAgents = { "android" };

		if (request.getHeader("User-Agent") != null) {
			String agent = request.getHeader("User-Agent").toLowerCase();
			for (String mobileAgent : mobileAgents) {
				if (agent.indexOf(mobileAgent) >= 0) {
					isIos = true;
					break;
				}
			}
		}
		return isIos;
	}
	
	/***
	 * 根据UserAgent和指定标识判断是否是Android客户端
	 * @param request 
	 * @param clientTag 客户端标识字符串
	 * @return
	 */
	public static boolean isAndroidAppClient(HttpServletRequest request,String clientTag){
		boolean isAndroidAppClient = false;
		
		String agent = request.getHeader("User-Agent");
		if(StringUtils.isNotBlank(agent) && StringUtils.isNotBlank(clientTag)){
			if(agent.toLowerCase().indexOf(clientTag.toLowerCase()) >= 0){
				isAndroidAppClient = true;
			}
		}
		
		return isAndroidAppClient;
	}
	
	/***
	 * 判断是否是微信客户端
	 * @param request
	 * @return
	 */
	public static boolean isWechat(HttpServletRequest request){
		return isAndroidAppClient(request,"MicroMessenger");
	}
	
}
