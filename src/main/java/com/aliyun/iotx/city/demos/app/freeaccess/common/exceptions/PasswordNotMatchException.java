package com.aliyun.iotx.city.demos.app.freeaccess.common.exceptions;

public class PasswordNotMatchException extends ApplicationException {

	private static final long serialVersionUID = 5689384250694419954L;

    /**
	 * @param code
	 * @param defaultMessage
	 * @param args
	 */
	public PasswordNotMatchException(String code, String defaultMessage,
			Object[] args) {
		super(code, defaultMessage, args);
	}

	public PasswordNotMatchException() {
		this("user.password.not.match", null, null);
	}
}