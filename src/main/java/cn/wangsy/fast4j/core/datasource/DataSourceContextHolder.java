package cn.wangsy.fast4j.core.datasource;

/** 
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年8月28日 下午3:48:01
 */
public class DataSourceContextHolder {  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(); // 线程本地环境  
  
    // 设置数据源类型  
    public static void setDataSourceType(String dataSourceType) {  
        contextHolder.set(dataSourceType);  
    }  
  
    // 获取数据源类型  
    public static String getDataSourceType() {  
        return contextHolder.get();  
    }  
  
    // 清除数据源类型  
    public static void clearDataSourceType() {  
        contextHolder.remove();  
    }  
  
}  
