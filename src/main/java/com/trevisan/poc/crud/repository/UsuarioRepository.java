package com.trevisan.poc.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trevisan.poc.crud.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
