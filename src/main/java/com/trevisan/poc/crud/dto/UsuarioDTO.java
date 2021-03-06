package com.trevisan.poc.crud.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioDTO {

	private Long id;
	@NotNull
	@Size(max = 100)
	private String nome;
	@NotNull
	@Size(min = 11, max = 11)
	private String cpf;
	@NotNull
	private LocalDate dtNascimento;
}
