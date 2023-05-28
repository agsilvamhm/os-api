package com.agsilva.os.service;

import com.agsilva.os.domain.Cliente;
import com.agsilva.os.domain.Os;
import com.agsilva.os.domain.Tecnico;
import com.agsilva.os.domain.enums.Prioridade;
import com.agsilva.os.domain.enums.Status;
import com.agsilva.os.repositories.ClienteRepository;
import com.agsilva.os.repositories.OsRepository;
import com.agsilva.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OsRepository osRepository;

    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Valdir Cesar", "87946858434", "(88) 98888-8888");
        Tecnico t2 = new Tecnico(null, "Adalberto Gonçalves", "87946858434", "(88) 99975-2200");
        Cliente c1 = new Cliente(null, "Betina Campos", "81731620187", "(88) 98888-7777");

        Os os1 = new Os(null, Prioridade.ALTA, "Trocar fonte do notebook", Status.ANDAMENTO, t1, c1);

        tecnicoRepository.saveAll(Arrays.asList(t1,t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
