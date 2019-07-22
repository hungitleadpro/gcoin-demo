package com.feature.gcoin.common.exception;

/**
 * @author TienNV
 * @CreatedDate Oct 4, 2017 2:56:36 PM
 */
public interface ExceptionCode {

	public static final String BAD_REQUEST = "BAD_REQUEST";
	public static final String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";
	public static final String METHOD_ARGUMENT_TYPE_MISMATCH = "METHOD_ARGUMENT_TYPE_MISMATCH";
	public static final String ERROR_RUNTIME = "ERROR_RUNTIME";

	public interface Authentication {
		public static final String AUTHENTICATION_USER_PASSWORD_INVALID = "AUTHENTICATION_USER_PASSWORD_INVALID";
		public static final String AUTHENTICATION_USER_INACTIVE = "AUTHENTICATION_USER_INACTIVE";
		public static final String AUTHENTICATION_CATPCHAR_IS_REQUIRED = "AUTHENTICATION_CATPCHAR_IS_REQUIRED";
		public static final String AUTHENTICATION_CATPCHAR_IS_INVALID = "AUTHENTICATION_CATPCHAR_IS_INVALID";
		public static final String REQUEST_INVALID = "REQUEST_INVALID";
		public static final String REQUEST_SESSION_IS_INVALID = "REQUEST_SESSION_IS_INVALID";
	}

	public interface User {
		public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	}

}
