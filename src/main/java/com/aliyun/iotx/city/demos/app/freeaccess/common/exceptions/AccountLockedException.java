package com.aliyun.iotx.city.demos.app.freeaccess.common.exceptions;

public class AccountLockedException extends ApplicationException {
	private static final long serialVersionUID = 6885439420865899825L;

	/**
	 * @param code
	 * @param defaultMessage
	 * @param args
	 */
	public AccountLockedException(String code, String defaultMessage,
								  Object[] args) {
		super(code, defaultMessage, args);
	}

	public AccountLockedException() {
		this("account.locked", null, null);
	}
}