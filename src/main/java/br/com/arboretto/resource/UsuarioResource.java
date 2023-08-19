package br.com.arboretto.resource;

import java.util.Map;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.CacheControl;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import br.com.arboretto.model.Usuario;

import br.com.arboretto.service.UsuarioService;

@RestController
@RequestMapping("/api-arboretto-dev/v1/usuario")
@CrossOrigin(origins = "", allowedHeaders = "")
public class UsuarioResource {
	
	@Autowired
    private UsuarioService usuarioService;
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ResponseEntity<Usuario> salvar(@RequestHeader(required = false) Map<String, String> headers,
            @RequestBody Usuario usuario) {



        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
                .body(usuarioService.salvar(usuario));
    }

}
