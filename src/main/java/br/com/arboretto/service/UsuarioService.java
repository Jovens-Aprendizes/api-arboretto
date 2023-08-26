package br.com.arboretto.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.arboretto.exception.RegraNegocioException;

import br.com.arboretto.model.Usuario;

import br.com.arboretto.repository.jdbc.UsuarioRepositoryJdbc;

import br.com.arboretto.validations.CPF;
import br.com.arboretto.validations.Numero;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositoryJdbc usuarioRepositoryJdbc; 
	
	public Usuario salvar(Usuario usuario) {
		if (StringUtils.isBlank(usuario.getNome())) {
            throw new RegraNegocioException("O Usuario deve ser informado");
        }
		
		if (StringUtils.isBlank(usuario.getSenha())) {
            throw new RegraNegocioException("A Senha deve ser informada");
        }
		
		if (usuario.getNome().length() > 120) {
            throw new RegraNegocioException("O Nome execede ao limite permitido.");
        }
		
		if (usuario.getSenha().length() > 120) {
            throw new RegraNegocioException("A Senha execede ao limite permitido.");
        }
		
		if (StringUtils.isNotBlank(usuario.getCpf()) && (usuario.getCpf().length() == 11)
                && !CPF.isCPF(usuario.getCpf())) {
            throw new RegraNegocioException("O CPF é inválido.");
        }
		
		if (StringUtils.isBlank(usuario.getEmail())) {
            throw new RegraNegocioException("Email deve ser informado.");
        }

        if (usuario.getNome().length() > 60) {
            throw new RegraNegocioException("Email excede 60 dígitos.");
        }
        
        if (StringUtils.isBlank(usuario.getNumeroApartamento())) {
            throw new RegraNegocioException("o Número do apartamento deve ser informado.");
        }
        
        if (usuario.getNumeroApartamento().length()>3) {
            throw new RegraNegocioException("o Número do apartamento não pode exceder o limite.");
        }
        
        if (StringUtils.isBlank(usuario.getBloco())) {
            throw new RegraNegocioException("o Número do bloco deve ser informado.");
        }
        
        if (usuario.getBloco().length()>2) {
            throw new RegraNegocioException("o Número do bloco deve conter 2 caracteres.");
        }
        String cargo="Usuário";
        usuario.setCargo(cargo);
        
        if (StringUtils.isBlank(usuario.getDataNascimento())) {
            throw new RegraNegocioException("A data de nascimento deve ser informada.");
        }
       long l=usuarioRepositoryJdbc.salvar(usuario);
       return usuarioRepositoryJdbc.getPorId(String.valueOf(l));
	}
	
	
	public Usuario atualizar (Usuario usuario) {
		
		if (StringUtils.isBlank(usuario.getNome())) {
            throw new RegraNegocioException("O Usuario deve ser informado");
        }
		
		if (StringUtils.isBlank(usuario.getSenha())) {
            throw new RegraNegocioException("A Senha deve ser informada");
        }
		
		if (usuario.getNome().length() > 120) {
            throw new RegraNegocioException("O Nome execede ao limite permitido.");
        }
		
		if (usuario.getSenha().length() > 120) {
            throw new RegraNegocioException("A Senha execede ao limite permitido.");
        }
		
		if (StringUtils.isNotBlank(usuario.getCpf()) && (usuario.getCpf().length() == 11)
                && !CPF.isCPF(usuario.getCpf())) {
            throw new RegraNegocioException("O CPF é inválido.");
        }
		
		if (StringUtils.isBlank(usuario.getEmail())) {
            throw new RegraNegocioException("Email deve ser informado.");
        }

        if (usuario.getNome().length() > 60) {
            throw new RegraNegocioException("Email excede 60 dígitos.");
        }
        
        if (StringUtils.isBlank(usuario.getNumeroApartamento())) {
            throw new RegraNegocioException("o Número do apartamento deve ser informado.");
        }
        
        if (usuario.getNumeroApartamento().length()>3) {
            throw new RegraNegocioException("o Número do apartamento não pode exceder o limite.");
        }
        
        if (StringUtils.isBlank(usuario.getBloco())) {
            throw new RegraNegocioException("o Número do bloco deve ser informado.");
        }
        
        if (usuario.getBloco().length()>2) {
            throw new RegraNegocioException("o Número do bloco deve conter 2 caracteres.");
        }
      
        
        if (StringUtils.isBlank(usuario.getDataNascimento())) {
            throw new RegraNegocioException("A data de nascimento deve ser informada.");
        }
        
        usuarioRepositoryJdbc.atualizar(usuario);
        
      return usuario;
	
		
	}
	
	public Usuario getLogin(String cpf, String senha) {

		if (StringUtils.isBlank(cpf) || StringUtils.isBlank(senha)) {
			throw new RegraNegocioException("Cpf e senha devem ser informados.");
		}

		String id = usuarioRepositoryJdbc.getLogin(cpf, senha);

		if (id == null) {
			throw new RegraNegocioException("Cpf ou senha não conferem.");
		}

		return this.getPorId(id);

	}
	
	public void delete(String id) {

		if (StringUtils.isBlank(id)) {
			throw new RegraNegocioException("O ID do Usuario deve ser informado.");
		}

		if (!Numero.isLong(id)) {
			throw new RegraNegocioException("O ID do Usuario informado é inválido.");
		}

		usuarioRepositoryJdbc.delete(id);

	}
	
	public Usuario getPorId(String id) {
		
		return usuarioRepositoryJdbc.getPorId(id);
	}
	
	public  List<Usuario> listarUsuario(){
		
		return usuarioRepositoryJdbc.listarUsuario();
		
	}
	
}
