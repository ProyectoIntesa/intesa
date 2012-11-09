package edu.client;

public class Validaciones {

	public Validaciones() {

	}

	public boolean textBoxVacio(String texto) {

		if (texto.length() == 0)
			return true;
		else
			return false;
	}

	public boolean textBoxSoloLetras(String texto) {

		boolean result = true;

		for (int i = 0; i < texto.length(); i++) {

			if (Character.isDigit(texto.charAt(i))) {
				result = false;
				break;
			}
		}
		return result;

	}

	public boolean textBoxSoloNumeros(String texto) {

		boolean result = true;
		if (texto.length() > 0) {
			for (int i = 0; i < texto.length(); i++) {

				if (Character.isLetter(texto.charAt(i))) {
					result = false;
					break;
				}
			}
		} else
			result = false;
		return result;

	}

}
