package com.trevisan.poc.crud.integration;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.trevisan.poc.crud.dto.UsuarioDTO;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioIntegrationTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void beforeEach() {
		RestAssured.port = port;
	}

	@Test
	public void cadastraUsuarioSucesso() {
		UsuarioDTO mockUsuarioDTO = criarUsuarioDTO();

		RequestSpecification requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).setBody(mockUsuarioDTO).build();

		RestAssured.given().spec(requestSpecification).log().all().when().post("api/v1/usuarios").then()
				.statusCode(Matchers.equalTo(HttpStatus.CREATED.value())).body("dados.id", Matchers.greaterThan(0))
				.body("dados.cpf", Matchers.equalTo("30112895069"));
	}

	private UsuarioDTO criarUsuarioDTO() {
		return UsuarioDTO.builder().nome("João").cpf("30112895069").dtNascimento(LocalDate.now()).build();
	}

}
