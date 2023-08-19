package br.com.arboretto.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import br.com.arboretto.exceptions.ErroInternoServidorException;
import br.com.arboretto.model.Usuario;
import br.com.arboretto.repository.UsuarioRepository;

public class UsuarioRepositoryJdbc implements UsuarioRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
    public long salvar(Usuario usuario) {

        try {

            StringBuilder query = new StringBuilder();
            query.append("insert into ");

            query.append("USUARIO ");

            query.append("(nome, ");
            query.append("senha, ");
            query.append("cpf, ");
            query.append("email ");
            query.append("numero_apartamento, ");
            query.append("bloco, ");
            query.append("cargo, ");
            query.append("data_marcada ) ");

            query.append("values ");
            query.append("(?, ");
            query.append("?, ");
            query.append("?, ");
            query.append("?, ");
            query.append("?, ");
            query.append("?, ");
            query.append("?, ");
            query.append("? ) ");

            KeyHolder holder = new GeneratedKeyHolder();

            jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(query.toString(),
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, usuario.getNome());
                    ps.setString(2, usuario.getSenha());
                    ps.setString(3, usuario.getCpf());
                    ps.setString(4, usuario.getEmail());
                    ps.setString(5, usuario.getNumeroApartamento());
                    ps.setString(6, usuario.getBloco());
                    ps.setString(7, usuario.getCargo());
                    ps.setString(8, usuario.getDataMarcada());

                    return ps;
                }

            }, holder);

            usuario.setId(String.valueOf(holder.getKey().longValue()));

            return holder.getKey().longValue();

        } catch (Exception e) {
            throw new ErroInternoServidorException("Erro ao tentar salvar usuário.");
        }

    }
		
	

}
