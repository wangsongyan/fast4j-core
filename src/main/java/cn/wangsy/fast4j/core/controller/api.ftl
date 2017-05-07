<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Spring Thyme Seed Starter Manager</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" media="all" href="../../css/stsm.css" th:href="@{/css/stsm.css}"/>
    <title>请求方法列表</title>
</head>

<body>
<div style="margin: 0;padding: 0;text-align: center"><h1>请求方法列表</h1></div>
<div>
    <ul>
        <#list MethodList as method>
        <li>
            <h3><a href="${contextPath!}${(method.requestUrl)!}" target="_blank">${(method.requestUrl)!}</a></h3>
            <p>${(method.controllerName)!}</p>
            <p>请求方法：${(method.methodName)!}</p>
            <p>请求方式：${(method.requestType)!}</p>
            <div>
                <p>方法参数列表：</p>
                <ul>
                	<#if method.methodParmaTypes??>
                	<#list method.methodParmaTypes as param>
                    <li>
                        <p>${param!}</p>
                    </li>
                    </#list>
                    </#if>
                </ul>
            </div>
        </li>
        </#list>
    </ul>
</div>


</body>
</html>