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
import com.trevisan.poc.crud.util.Utils;

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
		return toDTO(usuario.orElseThrow(() -> new BusinessException("CR-1", id)));
	}

	public UsuarioDTO salvar(UsuarioDTO dto) {
		validar(null, dto);
		return toDTO(repository.save(toEntity(dto)));
	}

	public UsuarioDTO editar(Long id, UsuarioDTO dto) {
		validar(id, dto);
		if (repository.findById(id).isEmpty()) {
			throw new BusinessException("CR-1", id);
		}

		Usuario entity = toEntity(dto);
		entity.setId(id);

		return toDTO(repository.save(entity));
	}

	public void excluir(Long id) {
		if (repository.findById(id).isEmpty())
			throw new BusinessException("CR-1", id);

		repository.deleteById(id);
	}

	private void validar(Long id, UsuarioDTO dto) {
		if (!Utils.validarCPF(dto.getCpf()))
			throw new BusinessException("CR-3", dto.getCpf());

		Optional<Usuario> usuarioCPF = repository.findByCpf(dto.getCpf());
		if (usuarioCPF.isPresent() && !usuarioCPF.get().getId().equals(id))
			throw new BusinessException("CR-2", dto.getCpf());
	}

	private Usuario toEntity(UsuarioDTO dto) {
		return modelMapper.map(dto, Usuario.class);
	}

	private UsuarioDTO toDTO(Usuario entity) {
		return modelMapper.map(entity, UsuarioDTO.class);
	}

}
