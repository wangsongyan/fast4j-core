package cn.wangsy.fast4j.core.spring;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

//<bean id="jobFactory" class="cn.wangsy.fast4j.core.spring.SpringBeanJobFactory"></bean>
//<bean name="quartzScheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
//	<property name="jobFactory" ref="jobFactory"/>
//	<property name=../>
//</bean>
/**
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年9月6日 下午12:54:28
 */
public class SpringBeanJobFactory extends AdaptableJobFactory {

	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;

	protected Object createJobInstance(TriggerFiredBundle bundle)
			throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
}
