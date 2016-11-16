package cn.wangsy.fast4j.core.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
	
}
