package br.com.arboretto.validations;

import java.util.InputMismatchException;

import org.apache.commons.lang3.StringUtils;



public class CPF {

	public static boolean isCPF(String CPF) {

		if (CPF == null || StringUtils.isEmpty(CPF)) {
			return (false);
		}

		CPF = removeCaracteresEspeciais(CPF);

		if (!Numero.isSomenteNumeros(CPF)) {
			return false;
		}

		if (isNumerosIguais(CPF) || (CPF.length() != 11)) {
			return (false);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;

			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String removeCaracteresEspeciais(String doc) {
		if (doc.contains(".")) {
			doc = doc.replace(".", "");
		}
		if (doc.contains("-")) {
			doc = doc.replace("-", "");
		}
		if (doc.contains("/")) {
			doc = doc.replace("/", "");
		}
		return doc;
	}

	private static boolean isNumerosIguais(String arg) {
		char compare = ' ';

		for (int i = 0; i < arg.length(); i++) {
			char c = arg.charAt(i);
			if (i == 0) {
				compare = c;
			} else {
				if (c != compare) {
					return false;
				}
			}
		}

		return true;
	}
	
	
}
