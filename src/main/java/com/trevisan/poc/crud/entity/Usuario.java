package com.trevisan.poc.crud.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.trevisan.poc.crud.base.EntidadeBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Usuario extends EntidadeBase{

	@Column(length = 100, nullable = false)
	private String nome;
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false)
	private LocalDate dtNascimento;

}
