package com.agsilva.os.service;

import com.agsilva.os.domain.Cliente;
import com.agsilva.os.domain.Os;
import com.agsilva.os.domain.Tecnico;
import com.agsilva.os.domain.enums.Prioridade;
import com.agsilva.os.domain.enums.Status;
import com.agsilva.os.dtos.OsDTO;
import com.agsilva.os.repositories.OsRepository;

import com.agsilva.os.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {
    @Autowired
    private OsRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Os findById(Integer id){
        Optional<Os> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Os.class.getName()));
    }

    public List<Os> findAll() {
        return repository.findAll();
    }

    public Os create(@Valid OsDTO obj){
        return fromDTO(obj);
    }

    public Os update(@Valid OsDTO obj){
        findById(obj.getId());
        return fromDTO(obj);
    }

    private Os fromDTO(OsDTO obj){
        Os newObj = new Os();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cli = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        if (newObj.getStatus().getCod().equals(2)){
            newObj.setDataFechamento(LocalDateTime.now());
        }
        return repository.save(newObj);
    }
}
