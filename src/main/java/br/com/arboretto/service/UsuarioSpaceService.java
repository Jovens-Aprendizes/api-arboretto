package br.com.arboretto.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arboretto.exception.ErroInternoServidorException;
import br.com.arboretto.exception.RegraNegocioException;

import br.com.arboretto.model.UsuarioSpace;
import br.com.arboretto.repository.jdbc.UsuarioSpaceRepositoryJdbc;
import br.com.arboretto.validations.Numero;

@Service
public class UsuarioSpaceService {

	@Autowired
	private UsuarioSpaceRepositoryJdbc usuarioSpaceRepositoryJdbc;

	@Transactional
	public UsuarioSpace salvar(UsuarioSpace usuarioSpace) {
		if (StringUtils.isBlank(usuarioSpace.getUsuarioId())) {
			throw new RegraNegocioException("O Usuario deve ser informado");
		}

		if (StringUtils.isBlank(usuarioSpace.getSpaceId())) {
			throw new RegraNegocioException("O espaço desejado deve ser selecionado");
		}

		if (StringUtils.isBlank(usuarioSpace.getDataMarcada())) {
			throw new RegraNegocioException("Selecione uma data para marcar");
		}

		if (usuarioSpace.getObservacao() != null && usuarioSpace.getObservacao().length() > 100) {
		    throw new RegraNegocioException("A Observação excede ao limite permitido.");
		}


		long l = usuarioSpaceRepositoryJdbc.salvar(usuarioSpace);
		return usuarioSpaceRepositoryJdbc.getPorId(String.valueOf(l));
	}

	@Transactional
	public UsuarioSpace atualizar(UsuarioSpace usuarioSpace) {
	    try {
	    	
	    	
	    	
	        if (StringUtils.isBlank(usuarioSpace.getId())) {
	            throw new RegraNegocioException("O Id da Solicitação deve ser informado");
	        }


	        if (usuarioSpace.getObservacao().length() > 100) {
	            throw new RegraNegocioException("A Observação execede ao limite permitido.");
	        }

	      
	         usuarioSpaceRepositoryJdbc.atualizar(usuarioSpace);

	        
	        if (usuarioSpace.getStatus() == null) {
	            usuarioSpace.setAutorizacao("pendente");
	        } else {
	            usuarioSpace.setAutorizacao(usuarioSpace.getStatus() ? "permitido" : "negado");
	        }

	        return usuarioSpaceRepositoryJdbc.getPorId(usuarioSpace.getId());

	    } catch (Exception e) {
	        throw new ErroInternoServidorException("Erro ao tentar atualizar dados do espaço marcado");
	    }
	}

	
	public List<UsuarioSpace> listarUsuarioSpacePorUsuarioId(String usuarioId){
		
		return usuarioSpaceRepositoryJdbc.listarUsuarioSpacePorUsuarioId(usuarioId);
	}
	
public List<UsuarioSpace> ListarSpacePorId(String spaceId){
		
		return usuarioSpaceRepositoryJdbc.listarSpacePorId(spaceId);
	}

	public void delete(String id) {

		if (StringUtils.isBlank(id)) {
			throw new RegraNegocioException("O ID do Espaço deve ser informado.");
		}

		if (!Numero.isLong(id)) {
			throw new RegraNegocioException("O ID do Espaço informado é inválido.");
		}

		usuarioSpaceRepositoryJdbc.delete(id);

	}

	public UsuarioSpace getPorId(String id) {
	    UsuarioSpace usuarioSpace = usuarioSpaceRepositoryJdbc.getPorId(id);

	    if (usuarioSpace == null) {
	        throw new NoSuchElementException("Não existe solicitação para o ID fornecido: " + id);
	    }

	    return usuarioSpace;
	}


	public List<UsuarioSpace> listarUsuarioSpace() {

		return usuarioSpaceRepositoryJdbc.listarUsuarioSpace();
	}

}
