package br.com.arboretto.service;

import org.springframework.stereotype.Service;

import br.com.arboretto.model.SensorChurrasqueira;

@Service
public class SensorChurrasqueiraService {

    private SensorChurrasqueira sensorChurrasqueira = new SensorChurrasqueira();

    public void iniciarSimulacaoStatus() {
     
        new java.util.Timer().schedule(
            new java.util.TimerTask() {
                @Override
                public void run() {
                    String statusAtual = sensorChurrasqueira.getStatus();
                    String novoStatus = "false".equals(statusAtual) ? "true" : "false";
                    sensorChurrasqueira.setStatus(novoStatus);
                }
            },
            60000, 60000 
        );
    }

    public SensorChurrasqueira getSensorChurrasqueira() {
        return sensorChurrasqueira;
    }
}
