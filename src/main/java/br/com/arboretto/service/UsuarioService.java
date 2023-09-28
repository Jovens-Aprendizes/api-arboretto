package br.com.arboretto.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.arboretto.exception.ErroInternoServidorException;
import br.com.arboretto.exception.RegraNegocioException;

import br.com.arboretto.model.Usuario;

import br.com.arboretto.repository.jdbc.UsuarioRepositoryJdbc;


import br.com.arboretto.validations.Numero;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositoryJdbc usuarioRepositoryJdbc; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario salvar(Usuario usuario) {
		if (StringUtils.isBlank(usuario.getNome())) {
            throw new RegraNegocioException("O Usuario deve ser informado");
        }
		
		if (StringUtils.isBlank(usuario.getSenha())) {
            throw new RegraNegocioException("A Senha deve ser informada");
        }
		
		if (usuario.getSenha().length() < 8) {
            throw new RegraNegocioException("A Senha requer um minimo de 8 caracteres.");
        }
		
		 String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
	        usuario.setSenha(senhaCriptografada);
		
		
		if (usuario.getNome().length() > 120) {
            throw new RegraNegocioException("O Nome execede ao limite permitido.");
        }
		
		if (usuario.getSenha().length() > 120) {
            throw new RegraNegocioException("A Senha execede ao limite permitido.");
        }
		
		 if (!validarCPF(usuario.getCpf())) {
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
        String cargo = usuario.getCargo();

        // Verificar se o valor de cargo é válido
        if (!cargo.equals("inquilino") && !cargo.equals("administrador") && !cargo.equals("proprietário")) {
            throw new RegraNegocioException("Cargo inválido. Use 'inquilino', 'administrador' ou 'proprietário'.");
        }
        
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
		
		if (usuario.getSenha().length() < 8) {
            throw new RegraNegocioException("O minimo para cadastrar a Senha é de 8 Digitos.");
        }
		
		 if (!validarCPF(usuario.getCpf())) {
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
	
	public void atualizarSenha(String id, String novaSenha) {
		if (novaSenha.length() > 120) {
            throw new RegraNegocioException("A Senha execede ao limite permitido.");
        }
		
		if (novaSenha.length() < 8) {
            throw new RegraNegocioException("O minimo para cadastrar a Senha é de 8 Digitos.");
        }

        // Criptografe a nova senha
        String senhaCriptografada = passwordEncoder.encode(novaSenha);

        // Atualize a senha no banco de dados
        usuarioRepositoryJdbc.atualizarSenha(id, senhaCriptografada);
    }
	
	public Usuario getLogin(String cpf, String senha) {
        if (StringUtils.isBlank(cpf) || StringUtils.isBlank(senha)) {
            throw new RegraNegocioException("Cpf e senha devem ser informados.");
        }

        try {
            String senhaArmazenada = usuarioRepositoryJdbc.getSenhaByCpf(cpf);

            if (senhaArmazenada != null && passwordEncoder.matches(senha, senhaArmazenada)) {
                String id = usuarioRepositoryJdbc.getLogin(cpf, senha);
                return this.getPorId(id);
            } else {
                throw new RegraNegocioException("Cpf ou senha não conferem.");
            }
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new RegraNegocioException("Cpf ou senha não conferem.");
        } catch (Exception exception) {
            throw new ErroInternoServidorException("Erro ao tentar autenticar usuário.");
        }
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
	
	private boolean validarCPF(String cpf) {
	    // Remove caracteres não numéricos do CPF
	    cpf = cpf.replaceAll("[^0-9]", "");

	    // Verifica se o CPF possui 11 dígitos
	    if (cpf.length() != 11) {
	        return false;
	    }

	    // Verifica se todos os dígitos são iguais (caso especial que não é válido)
	    boolean digitosIguais = true;
	    for (int i = 1; i < cpf.length(); i++) {
	        if (cpf.charAt(i) != cpf.charAt(0)) {
	            digitosIguais = false;
	            break;
	        }
	    }
	    if (digitosIguais) {
	        return false;
	    }

	    // Calcula o primeiro dígito verificador
	    int soma = 0;
	    for (int i = 0; i < 9; i++) {
	        soma += (cpf.charAt(i) - '0') * (10 - i);
	    }
	    int primeiroDigito = 11 - (soma % 11);
	    if (primeiroDigito >= 10) {
	        primeiroDigito = 0;
	    }

	    // Calcula o segundo dígito verificador
	    soma = 0;
	    for (int i = 0; i < 10; i++) {
	        soma += (cpf.charAt(i) - '0') * (11 - i);
	    }
	    int segundoDigito = 11 - (soma % 11);
	    if (segundoDigito >= 10) {
	        segundoDigito = 0;
	    }

	    // Verifica se os dígitos calculados batem com os dígitos informados
	    return (cpf.charAt(9) - '0' == primeiroDigito) && (cpf.charAt(10) - '0' == segundoDigito);
	}

	
}
