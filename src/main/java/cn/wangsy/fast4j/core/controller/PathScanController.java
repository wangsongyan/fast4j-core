package cn.wangsy.fast4j.core.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import cn.wangsy.fast4j.core.entity.RequestToMethodItem;
import cn.wangsy.fast4j.util.SetUtils;

/** 
 * 说明：获取系统所有的url
 * @author wangsy
 * @date 创建时间：2016年8月5日 下午4:00:04
 */
@Controller
@RequestMapping("/test")
public class PathScanController {

	@Resource
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@RequestMapping("api")
	public String api(Model model){
		
		//请求url和处理方法的映射
        List<RequestToMethodItem> requestToMethodItemList = new ArrayList<RequestToMethodItem>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()){
            RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
            HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();

            RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
            RequestMethod method = SetUtils.first(methodCondition.getMethods());
            String requestType = "";
            if(null != method){
            	requestType = method.name();
            }

            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            String requestUrl = SetUtils.first(patternsCondition.getPatterns());
            System.out.println(requestUrl);
            
            String controllerName = mappingInfoValue.getBeanType().toString();
            String requestMethodName = mappingInfoValue.getMethod().getName();
            Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
            RequestToMethodItem item = new RequestToMethodItem(requestUrl, requestType, controllerName, requestMethodName, methodParamTypes);

            requestToMethodItemList.add(item);
        }
        Collections.sort(requestToMethodItemList);
        model.addAttribute("MethodList", requestToMethodItemList);
		return "/api";
	}
	
	@RequestMapping("/requestmappingdetail.do")
	public void requestmappingdetail(HttpServletResponse response) throws Exception{
		List<HashMap<String, String>> urlList = new ArrayList<HashMap<String, String>>();
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			RequestMappingInfo info = m.getKey();
			HandlerMethod method = m.getValue();
			PatternsRequestCondition p = info.getPatternsCondition();
			for (String url : p.getPatterns()) {
				hashMap.put("url", url);
			}
			hashMap.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
			hashMap.put("method", method.getMethod().getName()); // 方法名
			RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
			String type = methodsCondition.toString();
			if (type != null && type.startsWith("[") && type.endsWith("]")) {
				type = type.substring(1, type.length() - 1);
				hashMap.put("type", type); // 方法名
			}
			urlList.add(hashMap);
		}
		StringBuffer buffer=new StringBuffer();
		for (HashMap<String, String> hashMap : urlList) {
			buffer.append("//----"+hashMap.get("url")+"\n");
			buffer.append(hashMap.get("className")+"."+hashMap.get("method")+"()"+"\n\n");
		}
		response.getWriter().write(buffer.toString());
	}
	
}
