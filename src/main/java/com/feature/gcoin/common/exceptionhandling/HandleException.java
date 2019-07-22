package com.feature.gcoin.common.exceptionhandling;

import com.feature.gcoin.common.exception.BusinessException;
import com.feature.gcoin.common.exception.ExceptionCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author TienNV
 * @CreatedDate Oct 4, 2017 2:53:22 PM
 */
@ControllerAdvice
@RestController
public class HandleException {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ErrorInfo handleException(Exception e) {
		e.printStackTrace();
		return new ErrorInfo(ExceptionCode.ERROR_RUNTIME, e.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ErrorInfo handleMySQLIntegrityConstraintViolationException(ConstraintViolationException e) {
		e.printStackTrace();
		return new ErrorInfo(ExceptionCode.ERROR_RUNTIME, "Could not execute statement. Please check reference data");
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ErrorInfo handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		e.printStackTrace();
		return new ErrorInfo(ExceptionCode.ERROR_RUNTIME, e.getMessage());
	}

//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value = ConstraintViolationException.class)
//	public ErrorInfo handleConstraintViolationException(ConstraintViolationException e) {
//		e.printStackTrace();
//		ErrorInfo errorInfo = new ErrorInfo();
//		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//		StringBuilder msgBuider = new StringBuilder();
//		for (ConstraintViolation<?> violation : violations) {
//			String message = violation.getMessage();
//			msgBuider.append(message).append(";");
//		}
//		errorInfo.setCode(ExceptionCode.CONSTRAINT_VIOLATION);
//		errorInfo.setMessage(msgBuider.toString());
//		return errorInfo;
//	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ErrorInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		e.printStackTrace();
		ErrorInfo errorInfo = new ErrorInfo();
		StringBuilder msgBuider = new StringBuilder();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		fieldErrors.forEach(item -> {
			String message = item.getDefaultMessage();
			if (item.getArguments().length > 1) {
				Object[] params = new Object[item.getArguments().length - 1];
				for (int i = 1; i < item.getArguments().length; i++) {
					params[i - 1] = item.getArguments()[i];
				}
				message = MessageFormat.format(message, params);
			}
			msgBuider.append(message).append(";");
		});
		errorInfo.setCode(ExceptionCode.BAD_REQUEST);
		errorInfo.setMessage(msgBuider.toString());
		return errorInfo;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = BusinessException.class)
	public ErrorInfo handleValidBusinessException(BusinessException e) {
		e.printStackTrace();
		return new ErrorInfo(e.getCode(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = TransactionSystemException.class)
	public ErrorInfo handleTransactionSystemException(TransactionSystemException e) {
		e.printStackTrace();
		return new ErrorInfo(ExceptionCode.ERROR_RUNTIME, e.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = RollbackException.class)
	public ErrorInfo handleRollbackException(RollbackException e) {
		e.printStackTrace();
		return new ErrorInfo(ExceptionCode.ERROR_RUNTIME, e.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ErrorInfo handleValidTypeException(MethodArgumentTypeMismatchException e) {
		e.printStackTrace();
		return new ErrorInfo(ExceptionCode.METHOD_ARGUMENT_TYPE_MISMATCH, e.getMessage());
	}
}
