package br.com.arboretto.repository.jdbc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import br.com.arboretto.exception.ErroInternoServidorException;
import br.com.arboretto.model.DiaMaisDemandado;

@Repository
public class DiaMaisDemandadoRepositoryJdbc {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<DiaMaisDemandado> obterDiasMaisDemandados(int mes, int ano) {
		try {
			String query = "SELECT DAYOFWEEK(data_marcada) as dia_semana,\r\n"
					+ "       COUNT(*) as totalReservas,\r\n"
					+ "       data_marcada as datasMarcadas\r\n"
					+ "FROM usuario_space\r\n"
					+ "WHERE MONTH(data_marcada) = ? AND YEAR(data_marcada) = ?\r\n"
					+ "GROUP BY dia_semana, datasMarcadas\r\n"
					+ "ORDER BY dia_semana;\r\n";  
  


			List<DiaMaisDemandado> diasMaisDemandados = jdbcTemplate.query(query, new Object[] { mes, ano },
				    (rs, rowNum) -> {
				        int diaDaSemana = rs.getInt("dia_semana");
				        String nomeDiaDaSemana = obterNomeDiaSemana(diaDaSemana);
				        int totalReservas = rs.getInt("totalReservas");
				        String datasMarcadas = rs.getString("datasMarcadas");
				        List<String> datasMarcadasList = Arrays.asList(datasMarcadas.split(","));
				        return new DiaMaisDemandado(nomeDiaDaSemana, totalReservas, datasMarcadasList);
				    });


			return diasMaisDemandados;
		} catch (Exception e) {
			throw new ErroInternoServidorException("Erro ao tentar obter os dias mais demandados.");
		}
	}

	private String obterNomeDiaSemana(int diaDaSemana) {
		switch (diaDaSemana) {
		case 1:
			return "Domingo";
		case 2:
			return "Segunda-feira";
		case 3:
			return "Terça-feira";
		case 4:
			return "Quarta-feira";
		case 5:
			return "Quinta-feira";
		case 6:
			return "Sexta-feira";
		case 7:
			return "Sábado";
		default:
			return "Desconhecido";
		}
	}

}
