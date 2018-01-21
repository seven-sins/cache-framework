package com.lonecpp.core.config.response;

import java.util.List;

/**
 * 请求的响应
 * 
 * @author seven sins
 * @date 2018年1月1日 下午7:39:06
 */
public class Response<T> {

	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";

	private Integer code;

	private T data;

	private List<T> list;

	private String message;
	
	public Response() {

	}

	public Response(T t) {
		this.data = t;
	}
	
	public Response(List<T> t) {
		this.list = t;
	}

	public Response(Integer code, T data) {
		this.code = code;
		this.data = data;
	}

	public Response(Integer code, List<T> list) {
		this.code = code;
		this.list = list;
	}

	public Response(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
