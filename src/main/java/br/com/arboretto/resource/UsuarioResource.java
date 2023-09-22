package br.com.arboretto.resource;

import java.util.Map;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.CacheControl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;



import br.com.arboretto.model.Usuario;

import br.com.arboretto.service.UsuarioService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-arboretto-dev/v1/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation("Salvar um Usuario")
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestHeader(required = false) Map<String, String> headers,
			@RequestBody Usuario usuario) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioService.salvar(usuario));
	}

	@ApiOperation("Atualizar um Usuario")
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizar(@RequestHeader(required = false) Map<String, String> headers,
			@RequestBody Usuario usuario) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioService.atualizar(usuario));
	}

	@ApiOperation("Atualizar a Senha")
	@RequestMapping(value = "/atualizar-senha", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarSenha(@RequestHeader(required = false) Map<String, String> headers,
			@RequestParam(name = "id") String id, @RequestParam(name = "novaSenha") String novaSenha) {

		usuarioService.atualizarSenha(id, novaSenha);
		

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body("Atualizado Com Sucesso");
	}

	@ApiOperation("Listar Usuarios")
	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> ListarUsuario(@RequestHeader(required = false) Map<String, String> headers) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioService.listarUsuario());
	}

	@ApiOperation("Get Usuario por id")
	@RequestMapping(value = "/filter-id", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getPorId(@RequestHeader(required = false) String token,
			@RequestParam(name = "id") String id) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioService.getPorId(id));

	}

	@ApiOperation("Login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Usuario> login(@RequestHeader(required = false) Map<String, String> headers,
            @RequestBody Map<String, String> requestBody) {

        // Obter o token Base64 do corpo da requisição
        String base64Token = requestBody.get("token");

        // Decodificar o token Base64
        byte[] decodedBytes = Base64.getDecoder().decode(base64Token);
        String decodedString = new String(decodedBytes);

        // Converter a String JSON decodificada para um Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> loginRequest;
        try {
            loginRequest = objectMapper.readValue(decodedString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Obter CPF e senha do Map
        String cpf = loginRequest.get("cpf");
        String senha = loginRequest.get("senha");

        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);

        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioService.getLogin(cpf, senha));
    }

	@ApiOperation("Deletar um usuario")
	@RequestMapping(value = "/apagar", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> apagar(@RequestHeader Map<String, String> headers, @RequestParam("id") String id) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);

		usuarioService.delete(id);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body("");
	}

}
