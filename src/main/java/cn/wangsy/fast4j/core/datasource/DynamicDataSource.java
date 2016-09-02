package cn.wangsy.fast4j.core.datasource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年8月28日 下午3:50:45
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final Logger logger = Logger.getLogger(DynamicDataSource.class.toString());

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceContextHolder.getDataSourceType();//获得当前数据源标识符
        logger.info("当前数据源 :" + key);
        return key;
    }
    
}

