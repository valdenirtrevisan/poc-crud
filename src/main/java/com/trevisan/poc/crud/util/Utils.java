package com.trevisan.poc.crud.util;

import br.com.caelum.stella.validation.CPFValidator;

public class Utils {

	public static boolean validarCPF(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		return cpfValidator.invalidMessagesFor(cpf).isEmpty();
	}
}
