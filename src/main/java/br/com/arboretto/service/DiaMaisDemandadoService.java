package br.com.arboretto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arboretto.model.DiaMaisDemandado;
import br.com.arboretto.repository.jdbc.DiaMaisDemandadoRepositoryJdbc;

@Service
public class DiaMaisDemandadoService {
	
	@Autowired
	private DiaMaisDemandadoRepositoryJdbc diaMaisDemandadoRepositoryJdbc;
	
	public List<DiaMaisDemandado> obterDiasMaisDemandados(int mes, int ano) {
        return diaMaisDemandadoRepositoryJdbc.obterDiasMaisDemandados(mes, ano);
    }

}
