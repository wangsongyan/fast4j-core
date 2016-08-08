package cn.wangsy.fast4j.core.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
 * @author wangsy
 * @date 创建时间：2016年7月24日 下午10:04:52
 * @version 1.0
 */
public enum OperationType {
    /**
     * 新增，添加
     */
    ADD("新增"),
 
    /**
     * 修改，更新
     */
    UPDATE("修改"),
 
    /**
     * 删除
     */
    DELETE("删除"),
 
    /**
     * 下载
     */
    DOWNLOAD("下载"),
 
    /**
     * 查询
     */
    QUERY("查询"),
 
    /**
     * 登入
     */
    LOGIN("登入"),
 
    /**
     * 登出
     */
    LOGOUT("登出");
 
    private String name;
 
    private OperationType() {
    }
 
    public String getName() {
        return name;
    }
 
    private OperationType(String name) { 
        this.name = name;
    }
 
    /**
     * 获取所有的枚举集合
     * @return
     */
    public static List<OperationType> getOperationTypes() {
        return new ArrayList<OperationType>(Arrays.asList(OperationType
                .values()));
    }
     
 
    public static void main(String[] args) {
        System.out.println(Arrays.toString(OperationType.values()));
    }
}
