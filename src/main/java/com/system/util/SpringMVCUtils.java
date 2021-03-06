package com.system.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * SpringMVC容器工具类
 * 持有其ApplicationContext对象, 可以获取容器中的bean
 * @author 结发受长生
 *
 */
@Component
public class SpringMVCUtils implements ApplicationContextAware {
	private static ApplicationContext context;
	public static final String springMVCName = "org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring_mvc";
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringMVCUtils.context = arg0;
	}
	/**
	 * 根据ID获取单个的bean
	 * @param beanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T getSpringMVCBean(String beanId){
		return (T) context.getBean(beanId);
	}
	/**
	 * 根据类型获取单个的bean
	 * @param clz
	 * @return bean实例
	 */
	public static <T extends Object> T getSpringMVCBean(Class<T> clz){
		return context.getBean(clz);
	}
	/**
	 * 根据类型获取多个bean
	 * @param clz
	 * @return
	 */
	public static <T> Map<String, T> getBeansOfType(Class<T> clz) {
		return context.getBeansOfType(clz);
	}

}
