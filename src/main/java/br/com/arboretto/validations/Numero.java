package br.com.arboretto.validations;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Numero {

	@SuppressWarnings("unused")
	public static boolean isLong(String arg) {
		try {
			Long l = Long.parseLong(arg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isSomenteNumeros(String str) {
		return str.matches("[0-9]+");
	}

	public static boolean isBigDecimal(String valor) {
		// valor = valor.replaceAll(",", ".");
		try {
			@SuppressWarnings("unused")
			BigDecimal big = new BigDecimal(valor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String formatar(boolean agrupar, BigDecimal valor) {

		if (valor == null) {
			return null;
		}

		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator(',');
		formatSymbols.setGroupingSeparator('.');

		DecimalFormat df = new DecimalFormat("#,##0.00", formatSymbols);
		df.setGroupingUsed(agrupar);

		return df.format(valor);

	}

	public static String formatar(boolean agrupar, String valorStr) {
		BigDecimal valor = new BigDecimal(valorStr);

		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator(',');
		formatSymbols.setGroupingSeparator('.');

		DecimalFormat df = new DecimalFormat("#,##0.00", formatSymbols);
		df.setGroupingUsed(agrupar);

		return df.format(valor);

	}

}
