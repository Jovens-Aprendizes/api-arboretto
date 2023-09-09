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

import br.com.arboretto.model.UsuarioSpace;
import br.com.arboretto.service.UsuarioSpaceService;

@RestController
@RequestMapping("/api-arboretto-dev/v1/usuario-space")
@CrossOrigin(origins = "", allowedHeaders = "")
public class UsuarioSpaceResource {

	@Autowired
	private UsuarioSpaceService usuarioSpaceService;

	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ResponseEntity<UsuarioSpace> salvar(@RequestHeader(required = false) Map<String, String> headers,
			@RequestBody UsuarioSpace usuarioSpace) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(usuarioSpaceService.salvar(usuarioSpace));
	}

	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT)
	public ResponseEntity<UsuarioSpace> atualizar(@RequestHeader(required = false) Map<String, String> headers,
			@RequestBody UsuarioSpace usuarioSpace) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(usuarioSpaceService.atualizar(usuarioSpace));
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> ListarUsuario(@RequestHeader(required = false) Map<String, String> headers) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioSpaceService.listarUsuarioSpace());
	}

	@RequestMapping(value = "/filter-id", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getPorId(@RequestHeader(required = false) String token,
			@RequestParam(name = "id") String id) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuarioSpaceService.getPorId(id));

	}

	

	@RequestMapping(value = "/apagar", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> apagar(@RequestHeader Map<String, String> headers, @RequestParam("id") String id) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);

		usuarioSpaceService.delete(id);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body("");
	}

}
