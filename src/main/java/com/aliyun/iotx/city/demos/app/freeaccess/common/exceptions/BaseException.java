package com.aliyun.iotx.city.demos.app.freeaccess.common.exceptions;

import org.springframework.util.StringUtils;

public class BaseException extends RuntimeException {

	/**
     *
	 */
	private static final long serialVersionUID = -6328696065275715259L;
	
	/**
	 * 消息代号
	 */
	private String code;
	/**
	 * 默认消息
	 */
	private String defaultMessage;
	/**
	 * 参数
	 */
	private Object[] args;
	
	public BaseException(String code, String defaultMessage, Object[] args) {
		this.code = code;
		this.defaultMessage = defaultMessage;
		this.args = args;
	}
	
	public BaseException(Throwable cause) {
		super(cause);
		if (cause instanceof BaseException) {
			BaseException e = (BaseException)cause;
			this.code = e.getCode();
			this.defaultMessage = e.getDefaultMessage();
			this.args = e.getArgs();
		}
	}
	
	@Override
	public String getMessage() {
		/*String message = null;
		if (!StringUtils.isEmpty(code)) {
			message = MessageUtils.getMessage(code, args);
		}
		if (null == message) {
			message = defaultMessage;
		}
		return message;*/
		return StringUtils.hasText(defaultMessage) ? defaultMessage : code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}