package com.system.service.annotation;
/**
 * 查询方式枚举
 * @author 结发受长生
 *
 */
public enum CriteriaType {
	// = 条件
	EQ,
	
	// < 条件
	LT,
	
	// > 条件
	GT,

	// like 模糊查询条件
	LIKE,
	
	// 忽略大小写的like条件
	I_LIKE
}
