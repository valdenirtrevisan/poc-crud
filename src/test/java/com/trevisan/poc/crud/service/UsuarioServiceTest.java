package com.trevisan.poc.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import com.trevisan.poc.crud.dto.UsuarioDTO;
import com.trevisan.poc.crud.entity.Usuario;
import com.trevisan.poc.crud.repository.UsuarioRepository;
import com.trevisan.poc.crud.util.BusinessException;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;
	@Mock
	private UsuarioRepository usuarioRepository;

	@BeforeEach
	public void beforeEach() {
		ReflectionTestUtils.setField(usuarioService, "modelMapper", new ModelMapper());
	}

	@Test
	public void salvarSucesso() {
		UsuarioDTO mockUsuarioDTO = criarUsuarioDTO();
		Usuario mockUsuario = criarUsuario();
		Usuario mockUsuarioSalvo = criarUsuario();
		mockUsuarioSalvo.setId(1L);

		when(usuarioRepository.findByCpf(mockUsuarioDTO.getCpf())).thenReturn(Optional.empty());
		when(usuarioRepository.save(mockUsuario)).thenReturn(mockUsuarioSalvo);

		UsuarioDTO resultado = usuarioService.salvar(mockUsuarioDTO);

		assertEquals(mockUsuarioSalvo.getId(), resultado.getId());
	}

	@Test
	public void salvarCpfInvalido() {
		UsuarioDTO mockUsuarioDTO = criarUsuarioDTO();
		mockUsuarioDTO.setCpf("45134512415");

		BusinessException exception = assertThrows(BusinessException.class,
				() -> usuarioService.salvar(mockUsuarioDTO));

		assertEquals("CR-3", exception.getCodigoErro());
	}

	@Test
	public void salvarCpfInvalidoNumerosIguais() {
		UsuarioDTO mockUsuarioDTO = criarUsuarioDTO();
		mockUsuarioDTO.setCpf("11111111111");

		BusinessException exception = assertThrows(BusinessException.class,
				() -> usuarioService.salvar(mockUsuarioDTO));

		assertEquals("CR-3", exception.getCodigoErro());
	}

	@Test
	public void editarSucesso() {
		Long id = 2L;
		UsuarioDTO mockUsuarioDTO = criarUsuarioDTO();
		Usuario mockUsuario = criarUsuario();
		Usuario mockUsuarioSalvo = criarUsuario();
		mockUsuarioSalvo.setId(id);

		when(usuarioRepository.findByCpf(any(String.class))).thenReturn(Optional.empty());
		when(usuarioRepository.findById(id)).thenReturn(Optional.of(mockUsuario));
		when(usuarioRepository.save(mockUsuario)).thenReturn(mockUsuarioSalvo);

		UsuarioDTO resultado = usuarioService.editar(id, criarUsuarioDTO());

		verify(usuarioRepository).findByCpf(mockUsuarioDTO.getCpf());
		assertEquals(id, resultado.getId());
	}

	@Test
	public void editarIdNaoExistente() {
		UsuarioDTO mockUsuarioDTO = criarUsuarioDTO();

		when(usuarioRepository.findByCpf(mockUsuarioDTO.getCpf())).thenReturn(Optional.empty());
		when(usuarioRepository.findById(any(Long.class))).thenReturn(Optional.empty());

		BusinessException exception = assertThrows(BusinessException.class,
				() -> usuarioService.editar(3L, criarUsuarioDTO()));

		assertEquals("CR-1", exception.getCodigoErro());
	}

	private UsuarioDTO criarUsuarioDTO() {
		return UsuarioDTO.builder().nome("João").cpf("30112895069").dtNascimento(LocalDate.now()).build();
	}

	private Usuario criarUsuario() {
		return Usuario.builder().nome("João").cpf("30112895069").dtNascimento(LocalDate.now()).build();
	}
}
