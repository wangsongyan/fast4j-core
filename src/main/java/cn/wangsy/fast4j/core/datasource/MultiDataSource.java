package cn.wangsy.fast4j.core.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 说明：多数据库支持
 * @author wangsy
 * @date 创建时间：2016年9月8日 下午1:16:56
 */
public class MultiDataSource extends DataSource{

	private static final Logger logger = LoggerFactory.getLogger(MultiDataSource.class);   
	
	private static final String DRIVER_CLASSNAME_MYSQL = "com.mysql.jdbc.Driver";
	private static final String DRIVER_CLASSNAME_ORACLE = "oracle.jdbc.OracleDriver";
	private static final String DRIVER_CLASSNAME_SQLSERVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private static final String DATABASE = "mysql";
	//private static final String DATABASE_SUPPORT = "mysql,oracle,sqlserver";
	
	private static final String MYSQL_URL_KEY = "mysql.url";
	private static final String MYSQL_DRIVERCLASSNAME_KEY = "mysql.driverClassName";
	private static final String ORACLE_URL_KEY = "oracle.url";
	private static final String ORACLE_DRIVERCLASSNAME_KEY = "oracle.driverClassName";
	private static final String SQLSERVER_URL_KEY = "sqlserver.url";
	private static final String SQLSERVER_DRIVERCLASSNAME_KEY = "sqlserver.url";
	
	private String database = DATABASE;
	private String sqlserverDriver = DRIVER_CLASSNAME_SQLSERVER;
	private String oracleDriver = DRIVER_CLASSNAME_ORACLE;
	private String mysqlDriver = DRIVER_CLASSNAME_MYSQL;
	private String mysqlUrl;
	private String oracleUrl;
	private String sqlServerUrl;
	private String resourcePath;


	public MultiDataSource(){
		
	}
	
	public void loadResouce(){
		if(resourcePath != null && resourcePath.length() > 0){
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath);
			Properties prop = new Properties();
			try {
				prop.load(inputStream);
				mysqlUrl = prop.getProperty(MYSQL_URL_KEY);
				mysqlDriver = prop.getProperty(MYSQL_DRIVERCLASSNAME_KEY, DRIVER_CLASSNAME_MYSQL);
				oracleUrl = prop.getProperty(ORACLE_URL_KEY);
				oracleDriver = prop.getProperty(ORACLE_DRIVERCLASSNAME_KEY, DRIVER_CLASSNAME_ORACLE);
				sqlServerUrl = prop.getProperty(SQLSERVER_URL_KEY);
				sqlserverDriver = prop.getProperty(SQLSERVER_DRIVERCLASSNAME_KEY, DRIVER_CLASSNAME_SQLSERVER);
			} catch (IOException e) {
				logger.info("load resource from {} error!",resourcePath);
			}
		}else{
			logger.info("can't load resource from empty path!");
		}
	}
	
	public void setDatabase(String database){
		loadResouce();
		if("mysql".equals(database)){
			setUrl(mysqlUrl);
			setDriverClassName(mysqlDriver);
		}else if("oracle".equals(database)){
			setUrl(oracleUrl);
			setDriverClassName(oracleDriver);
		}else if("sqlserver".equals(database)){
			setUrl(sqlServerUrl);
			setDriverClassName(sqlserverDriver);
		}else{
			logger.info("unsupport database:{}",database);
		}
	}
	
	// setter and getter
	public String getDatabase() {
		return database;
	}

//	public void setDatabase(String database) {
//		this.database = database;
//	}

	public String getSqlserverDriver() {
		return sqlserverDriver;
	}

	public void setSqlserverDriver(String sqlserverDriver) {
		this.sqlserverDriver = sqlserverDriver;
	}

	public String getOracleDriver() {
		return oracleDriver;
	}

	public void setOracleDriver(String oracleDriver) {
		this.oracleDriver = oracleDriver;
	}

	public String getMysqlDriver() {
		return mysqlDriver;
	}

	public void setMysqlDriver(String mysqlDriver) {
		this.mysqlDriver = mysqlDriver;
	}

	public String getMysqlUrl() {
		return mysqlUrl;
	}

	public void setMysqlUrl(String mysqlUrl) {
		this.mysqlUrl = mysqlUrl;
	}

	public String getOracleUrl() {
		return oracleUrl;
	}

	public void setOracleUrl(String oracleUrl) {
		this.oracleUrl = oracleUrl;
	}

	public String getSqlServerUrl() {
		return sqlServerUrl;
	}

	public void setSqlServerUrl(String sqlServerUrl) {
		this.sqlServerUrl = sqlServerUrl;
	}
	
	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
}
