package com.trevisan.poc.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.trevisan.poc.crud.dto.UsuarioDTO;
import com.trevisan.poc.crud.entity.Usuario;
import com.trevisan.poc.crud.repository.UsuarioRepository;
import com.trevisan.poc.crud.util.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final ModelMapper modelMapper;

	public List<UsuarioDTO> buscarTodos() {
		List<Usuario> usuarios = repository.findAll();
		return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public UsuarioDTO buscarPorId(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		return toDTO(usuario.orElseThrow(() -> new BusinessException()));
	}

	public UsuarioDTO salvar(UsuarioDTO dto) {
		return toDTO(repository.save(toEntity(dto)));
	}

	public UsuarioDTO editar(Long id, UsuarioDTO dto) {
		if (repository.findById(id).isEmpty()) {
			throw new BusinessException();
		}

		Usuario entity = toEntity(dto);
		entity.setId(id);

		return toDTO(repository.save(entity));
	}

	public void excluir(Long id) {
		repository.deleteById(id);
	}

	private Usuario toEntity(UsuarioDTO dto) {
		return modelMapper.map(dto, Usuario.class);
	}

	private UsuarioDTO toDTO(Usuario entity) {
		return modelMapper.map(entity, UsuarioDTO.class);
	}

}
