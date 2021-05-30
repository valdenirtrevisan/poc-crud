package com.trevisan.poc.crud.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dtNascimento;
}
