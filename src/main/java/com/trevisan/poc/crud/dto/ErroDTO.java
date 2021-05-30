package com.trevisan.poc.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(Include.NON_NULL)
public class ErroDTO {

	private String campo;
	private String codigo;
	private String mensagem;
}
