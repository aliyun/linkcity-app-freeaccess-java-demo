package com.aliyun.iotx.city.demos.app.freeaccess.common.exceptions;

public class AccountNotExistException extends ApplicationException {

	private static final long serialVersionUID = -5441794349513569089L;

	/**
	 * @param code
	 * @param defaultMessage
	 * @param args
	 */
	public AccountNotExistException(String code, String defaultMessage,
									Object[] args) {
		super(code, defaultMessage, args);
	}

	public AccountNotExistException() {
		this("account.not.exists", null, null);
	}
}