package com.trevisan.poc.crud.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trevisan.poc.crud.dto.ErroDTO;
import com.trevisan.poc.crud.dto.ResponseDTO;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ResponseEntityExceptionHandler {

	private static final String CR_99 = "CR-99";
	private final MessageSource messageSource;

	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<ResponseDTO<ErroDTO>> handlerException(BusinessException ex) {
		String mensagem = messageSource.getMessage(ex.getCodigoErro(), ex.getParametros(),
				LocaleContextHolder.getLocale());
		ErroDTO erro = ErroDTO.builder().codigo(ex.getCodigoErro()).mensagem(mensagem).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(erro));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO<Object>> handleException(MethodArgumentNotValidException exception) {

		List<ErroDTO> erros = new ArrayList<>();
		BindingResult bindingResult = exception.getBindingResult();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String campo = fieldError.getField();
			String mensagem = fieldError.getDefaultMessage();

			erros.add(ErroDTO.builder().campo(campo).mensagem(mensagem).build());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(erros));
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ResponseDTO<Object>> handleException(Exception exception) {
		String mensagem = messageSource.getMessage(CR_99, null, LocaleContextHolder.getLocale());
		ErroDTO erro = ErroDTO.builder().codigo(CR_99).mensagem(mensagem).build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(erro));
	}
}
