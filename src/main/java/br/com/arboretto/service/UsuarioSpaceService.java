package br.com.arboretto.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		if (usuarioSpace.getObservacao().length() > 100) {
			throw new RegraNegocioException("A Observação execede ao limite permitido.");
		}

		long l = usuarioSpaceRepositoryJdbc.salvar(usuarioSpace);
		return usuarioSpaceRepositoryJdbc.getPorId(String.valueOf(l));
	}

	@Transactional
	public UsuarioSpace atualizar(UsuarioSpace usuarioSpace) {

		if (StringUtils.isBlank(usuarioSpace.getUsuarioId())) {
			throw new RegraNegocioException("O Usuario deve ser informado");
		}

		if (StringUtils.isBlank(usuarioSpace.getSpaceId())) {
			throw new RegraNegocioException("O espaço desejado deve ser selecionado");
		}

		if (usuarioSpace.getObservacao().length() > 100) {
			throw new RegraNegocioException("A Observação execede ao limite permitido.");
		}

		usuarioSpaceRepositoryJdbc.atualizar(usuarioSpace);

		return usuarioSpace;

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

		return usuarioSpaceRepositoryJdbc.getPorId(id);
	}

	public List<UsuarioSpace> listarUsuarioSpace() {

		return usuarioSpaceRepositoryJdbc.listarUsuarioSpace();
	}

}
