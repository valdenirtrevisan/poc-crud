package com.trevisan.poc.crud.dto;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseDTO<T> {

	private T dados;
	private List<ErroDTO> erros;

	public ResponseDTO(T dados) {
		this.dados = dados;
	}

	public ResponseDTO(List<ErroDTO> erros) {
		this.erros = erros;
	}

	public ResponseDTO(ErroDTO erro) {
		erros = Arrays.asList(erro);
	}
}
