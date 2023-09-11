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
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
                .body(usuarioService.salvar(usuario));
    }
	
	@ApiOperation("Atualizar um Usuario")
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizar(@RequestHeader(required = false) Map<String, String> headers,
			@RequestBody Usuario usuario) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioService.atualizar(usuario));
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
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getLogin(@RequestHeader Map<String, String> headers) {

		String cpf = headers.get("cpf");
		String senha = headers.get("senha");

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(usuarioService.getLogin(cpf, senha));
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
