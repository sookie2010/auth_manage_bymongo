package com.system.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.alibaba.fastjson.annotation.JSONType;
import com.system.service.annotation.CriteriaField;
import com.system.service.annotation.CriteriaType;
/**
 * 静态消息类
 * @author 结发受长生
 *
 */
@JSONType(ignores={"id","name"})
@Entity("s_msg")
public class Msg implements Serializable {
	private static final long serialVersionUID = -8201227599046330619L;
	@Id
	private ObjectId id;
	private String name;
	
	@CriteriaField(CriteriaType.EQ)
	private String type;
	private String str;
	private int setTime;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getSetTime() {
		return setTime;
	}
	public void setSetTime(int setTime) {
		this.setTime = setTime;
	}
	
}
