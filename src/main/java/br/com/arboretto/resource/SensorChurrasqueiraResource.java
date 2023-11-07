package br.com.arboretto.resource;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import br.com.arboretto.model.SensorChurrasqueira;
import br.com.arboretto.service.SensorChurrasqueiraService;

@RestController
@RequestMapping("/api-arboretto-dev/v1/sensor")
@CrossOrigin(origins = "*")
public class SensorChurrasqueiraResource {
	
	
	@Autowired
	private SensorChurrasqueiraService sensorChurrasqueiraService;
	
	@RequestMapping(value = "/churrasqueira", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SensorChurrasqueira> getStatusChurrasqueira() {
	    sensorChurrasqueiraService.iniciarSimulacaoStatus();
	    SensorChurrasqueira sensor = sensorChurrasqueiraService.getSensorChurrasqueira();
	    return new ResponseEntity<>(sensor, HttpStatus.OK);
	}


}
