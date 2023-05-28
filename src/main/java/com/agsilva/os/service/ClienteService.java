package com.agsilva.os.service;

import com.agsilva.os.domain.Cliente;
import com.agsilva.os.domain.Pessoa;
import com.agsilva.os.dtos.ClienteDTO;
import com.agsilva.os.repositories.ClienteRepository;
import com.agsilva.os.repositories.PessoaRepository;
import com.agsilva.os.service.exceptions.DataIntegratyVioletionException;
import com.agsilva.os.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ClienteRepository repository;
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        if (findByCPF(objDTO) != null){
            throw new DataIntegratyVioletionException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
       Cliente oldObj = findById(id);
        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyVioletionException("CPF já cadastrado na base de dados!");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(oldObj.getTelefone());
        return repository.save(oldObj);
    }

    public void delete(Integer id){
       Cliente obj = findById(id);
        if (obj.getList().size() > 0) {
             throw new DataIntegratyVioletionException("Cliente possui Ordens de Serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private Pessoa findByCPF(ClienteDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if(obj != null){
            return obj;
        }
        return null;
    }
}
