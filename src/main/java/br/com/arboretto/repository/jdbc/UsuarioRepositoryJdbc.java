package br.com.arboretto.repository.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.arboretto.exception.ErroInternoServidorException;
import br.com.arboretto.model.Usuario;
import br.com.arboretto.repository.UsuarioRepository;

@Repository
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
			query.append("email, ");
			query.append("numero_apartamento, ");
			query.append("bloco, ");
			query.append("cargo, ");
			query.append("data_marcada, ");
			query.append("data_nascimento ) ");

			query.append("values ");
			query.append("(?, ");
			query.append("?, ");
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
					ps.setString(9, usuario.getDataNascimento());

					return ps;
				}

			}, holder);

			usuario.setId(String.valueOf(holder.getKey().longValue()));

			return holder.getKey().longValue();

		} catch (Exception e) {
			throw new ErroInternoServidorException("Erro ao tentar salvar usuário.");
		}

	}

	@Override
	public Usuario getPorId(String id) {
		try {
			StringBuilder query = new StringBuilder();
			query.append("select ");

			query.append("usu.id, ");
			query.append("usu.nome, ");
			query.append("usu.senha, ");
			query.append("usu.cpf, ");
			query.append("usu.email, ");
			query.append("usu.numero_apartamento, ");
			query.append("usu.bloco, ");
			query.append("usu.cargo, ");
			query.append("usu.data_marcada, ");
			query.append("usu.data_nascimento  ");

			query.append("from ");
			query.append("USUARIO usu ");

			query.append("where ");

			query.append("usu.id = ?");

			return jdbcTemplate.queryForObject(query.toString(), new BeanPropertyRowMapper<Usuario>(Usuario.class), id);

		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			return null;
		} catch (Exception exception) {
			throw new ErroInternoServidorException("Erro ao tentar pesquisar Usuario por id.");
		}
	}

	@Override
	public int atualizar(Usuario usuario) {

		try {

			StringBuilder query = new StringBuilder();

			query.append("update ");
			query.append("USUARIO ");

			query.append("set ");

			query.append("nome = ?, ");
			query.append("senha = ?, ");
			query.append("email = ?, ");
			query.append("numero_apartamento = ?, ");
			query.append("bloco = ?, ");
			query.append("data_marcada = ?, ");
			query.append("data_nascimento = ? ");

			query.append("where ");
			query.append("id = ? ");
			
			
			return jdbcTemplate.update(query.toString(), usuario.getNome(), usuario.getSenha(), usuario.getEmail(),
					usuario.getNumeroApartamento(), usuario.getBloco(), usuario.getDataMarcada(),
					usuario.getDataNascimento(), usuario.getId());

			

		} catch (Exception e) {
			throw new ErroInternoServidorException("Erro ao tentar atualizar dados do usuário");
		}

	}
	
	@Override
	public String getLogin(String cpf, String senha) {

		try {

			StringBuilder query = new StringBuilder();
			query.append("select ");

			query.append("usu.id ");

			query.append("from ");
			query.append("USUARIO usu ");

			query.append("where ");
			query.append("usu.cpf = ? and usu.senha = ?");

			return jdbcTemplate.queryForObject(query.toString(), new Object[] { cpf, senha },
					String.class);

		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			return null;
		} catch (Exception exception) {
			throw new ErroInternoServidorException("Erro ao tentar autenticar usuário.");
		}
	}


	@Override
	public List<Usuario> listarUsuario() {
		try {
			StringBuilder query = new StringBuilder();
			query.append("select ");

			query.append("usu.id, ");
			query.append("usu.nome, ");
			query.append("usu.cpf, ");
			query.append("usu.email, ");
			query.append("usu.numero_apartamento, ");
			query.append("usu.bloco, ");
			query.append("usu.cargo, ");
			query.append("usu.data_marcada, ");
			query.append("usu.data_nascimento  ");

			query.append("from ");
			query.append("USUARIO usu ");


			return jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<Usuario>(Usuario.class));

		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			return null;
		} catch (Exception exception) {
			throw new ErroInternoServidorException("Erro ao tentar pesquisar Usuario por id.");
		}
	}

	@Override
	public void delete(String id) {
		jdbcTemplate.update("delete from USUARIO where id = ?", id);
	}
}
