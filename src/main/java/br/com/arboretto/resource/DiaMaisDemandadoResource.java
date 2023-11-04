package br.com.arboretto.resource;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.arboretto.service.DiaMaisDemandadoService;

@RestController
@RequestMapping("/api-arboretto-dev/v1/dia-demandado")
@CrossOrigin(origins = "*")
public class DiaMaisDemandadoResource {
	
	
	@Autowired
	private DiaMaisDemandadoService diaMaisDemandadoService;
	
	@RequestMapping(value = "/listar-dias-demandados", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> listarDiasDemandados(@RequestHeader(required = false) String token,
			@RequestParam(name = "mes") int mes, @RequestParam(name = "ano") int ano) {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(diaMaisDemandadoService.obterDiasMaisDemandados(mes, ano));
	}

}
