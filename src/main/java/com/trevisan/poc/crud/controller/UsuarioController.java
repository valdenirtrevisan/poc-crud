package com.trevisan.poc.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevisan.poc.crud.dto.ResponseDTO;
import com.trevisan.poc.crud.dto.UsuarioDTO;
import com.trevisan.poc.crud.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<ResponseDTO<List<UsuarioDTO>>> buscarTodos() {
		List<UsuarioDTO> usuariosDTO = service.buscarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(usuariosDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO<UsuarioDTO>> buscarPorId(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = service.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(usuarioDTO));
	}

	@PostMapping
	public ResponseEntity<ResponseDTO<UsuarioDTO>> salvar(@Valid @RequestBody UsuarioDTO dto) {
		UsuarioDTO usuarioDTO = service.salvar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(usuarioDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO<Void>> editar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
		service.editar(id, dto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.excluir(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
