package com.agsilva.os.service;

import com.agsilva.os.domain.Pessoa;
import com.agsilva.os.domain.Tecnico;
import com.agsilva.os.dtos.TecnicoDTO;
import com.agsilva.os.repositories.PessoaRepository;
import com.agsilva.os.repositories.TecnicoRepository;
import com.agsilva.os.service.exceptions.DataIntegratyVioletionException;
import com.agsilva.os.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TecnicoRepository repository;
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        if (findByCPF(objDTO) != null){
            throw new DataIntegratyVioletionException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);
        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyVioletionException("CPF já cadastrado na base de dados!");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(oldObj.getTelefone());
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Tecnico obj = findById(id);
        if (obj.getList().size() > 0) {
             throw new DataIntegratyVioletionException("Técnico possui Ordens de Serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if(obj != null){
            return obj;
        }
        return null;
    }
}
