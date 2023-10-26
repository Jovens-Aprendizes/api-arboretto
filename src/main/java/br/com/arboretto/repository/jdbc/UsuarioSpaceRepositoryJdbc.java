package br.com.arboretto.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
import br.com.arboretto.model.UsuarioSpace;
import br.com.arboretto.repository.UsuarioSpaceRepository;

@Repository
public class UsuarioSpaceRepositoryJdbc implements UsuarioSpaceRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public long salvar(UsuarioSpace usuarioSpace) {
	    try {
	        String query = "INSERT INTO usuario_space (usuario_id, space_id, data_marcada, observacao,status) VALUES (?, ?, ?, ?,?)";

	        KeyHolder holder = new GeneratedKeyHolder();

	        jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

	                ps.setString(1, usuarioSpace.getUsuarioId());
	                ps.setString(2, usuarioSpace.getSpaceId());
	                ps.setString(3, usuarioSpace.getDataMarcada());
	                ps.setString(4, usuarioSpace.getObservacao());
	                ps.setObject(5, usuarioSpace.getStatus(), Types.BOOLEAN);


	                return ps;
	            }
	        }, holder);

	        usuarioSpace.setId(String.valueOf(holder.getKey().longValue()));

	        return holder.getKey().longValue();
	    } catch (Exception e) {
	        throw new ErroInternoServidorException("Erro ao tentar salvar Espaço.");
	    }
	}


	@Override
	public UsuarioSpace getPorId(String id) {
	    try {
	        StringBuilder query = new StringBuilder();
	        query.append("select ");

	        query.append("usp.id, ");
	        query.append("usp.usuario_id, ");
	        query.append("usp.space_id, ");
	        query.append("usp.data_marcada, ");
	        query.append("usp.observacao,  ");
	        query.append("usp.status  ");

	        query.append("from ");
	        query.append("usuario_space usp ");

	        query.append("where ");

	        query.append("usp.id = ?");

	        UsuarioSpace usuarioSpace = jdbcTemplate.queryForObject(query.toString(), new BeanPropertyRowMapper<UsuarioSpace>(UsuarioSpace.class), id);

	        if (usuarioSpace.getStatus() == null) {
	            usuarioSpace.setAutorizacao("pendente");
	        } else {
	            usuarioSpace.setAutorizacao(usuarioSpace.getStatus() ? "permitido" : "negado");
	        }

	        return usuarioSpace;

	    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
	        return null;
	    } catch (Exception exception) {
	        throw new ErroInternoServidorException("Erro ao tentar pesquisar Espaço marcado por id.");
	    }
	}

	


	@Override
	public int atualizar(UsuarioSpace usuarioSpace) {
	    try {
	        StringBuilder query = new StringBuilder();

	        query.append("update ");
	        query.append("usuario_space ");

	        query.append("set ");

	        query.append("usuario_id = ?, ");
	        query.append("space_id = ?, ");
	        query.append("data_marcada = ?, ");
	        query.append("observacao = ?, ");
	        query.append("status = ? ");

	        query.append("where ");
	        query.append("id = ? ");
	        
	        return jdbcTemplate.update(query.toString(), usuarioSpace.getUsuarioId(), usuarioSpace.getSpaceId(), usuarioSpace.getDataMarcada(),
	                usuarioSpace.getObservacao(), usuarioSpace.getStatus(), usuarioSpace.getId());

	    } catch (Exception e) {
	        throw new ErroInternoServidorException("Erro ao tentar atualizar dados do espaço marcado");
	    }
	}


	@Override
	public List<UsuarioSpace> listarUsuarioSpace() {
	    try {
	        StringBuilder query = new StringBuilder();
	        query.append("SELECT ");

	        query.append("usp.id, ");
	        query.append("usp.usuario_id AS usuarioId, "); // Alias para manter o nome 'usuarioId'
	        query.append("usp.space_id AS spaceId, "); // Alias para manter o nome 'spaceId'
	        query.append("usp.data_marcada AS dataMarcada, "); // Alias para manter o nome 'dataMarcada'
	        query.append("usp.observacao AS observacao, "); // Alias para manter o nome 'observacao'
	        query.append("usp.status AS status, "); // Alias para manter o nome 'status'
	        query.append("u.nome AS nomeUsuario "); // Renomeando para 'nomeUsuario'

	        query.append("FROM ");
	        query.append("usuario_space usp ");
	        query.append("JOIN usuario u ON usp.usuario_id = u.id"); 

	        List<UsuarioSpace> usuarioSpaces = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(UsuarioSpace.class));

	        for (UsuarioSpace usuarioSpace : usuarioSpaces) {
	            if (usuarioSpace.getStatus() == null) {
	                usuarioSpace.setAutorizacao("pendente");
	            } else {
	                usuarioSpace.setAutorizacao(usuarioSpace.getStatus() ? "permitido" : "negado");
	            }
	        }

	        return usuarioSpaces;

	    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
	        return null;
	    } catch (Exception exception) {
	        throw new ErroInternoServidorException("Erro ao tentar pesquisar espaços marcados.");
	    }
	}



	
	@Override
	public List<UsuarioSpace> listarUsuarioSpacePorUsuarioId(String usuarioId) {
	    try {
	        StringBuilder query = new StringBuilder();
	        query.append("SELECT ");

	        query.append("usp.id, ");
	        query.append("usp.usuario_id, ");
	        query.append("usp.space_id, ");
	        query.append("usp.data_marcada, ");
	        query.append("usp.observacao, ");
	        query.append("usp.status, ");
	        query.append("u.nome AS nomeUsuario "); // Adicionando o campo de nome do usuário

	        query.append("FROM ");
	        query.append("usuario_space usp ");
	        query.append("JOIN usuario u ON usp.usuario_id = u.id"); // Junta com a tabela de usuários

	        query.append("WHERE usp.usuario_id = ?");

	        List<UsuarioSpace> usuarioSpaces = jdbcTemplate.query(query.toString(), new Object[]{usuarioId}, new BeanPropertyRowMapper<>(UsuarioSpace.class));

	        for (UsuarioSpace usuarioSpace : usuarioSpaces) {
	            if (usuarioSpace.getStatus() == null) {
	                usuarioSpace.setAutorizacao("pendente");
	            } else {
	                usuarioSpace.setAutorizacao(usuarioSpace.getStatus() ? "permitido" : "negado");
	            }
	        }

	        return usuarioSpaces;

	    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
	        return null;
	    } catch (Exception exception) {
	        throw new ErroInternoServidorException("Erro ao tentar pesquisar espaços marcados por Usuario id.");
	    }
	}



	@Override
	public void delete(String id) {
		jdbcTemplate.update("delete from usuario_space where id = ?", id);

	}

}
