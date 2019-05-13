package com.aliyun.iotx.city.demos.app.freeaccess.common.exceptions;

public class ApplicationException extends BaseException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3265165653728067841L;

	/**
	 * @param code
	 * @param defaultMessage
	 * @param args
	 */
	public ApplicationException(String code, String defaultMessage,
			Object[] args) {
		super(code, defaultMessage, args);
	}
	
	public ApplicationException(Throwable cause) {
		super(cause);
	}
}