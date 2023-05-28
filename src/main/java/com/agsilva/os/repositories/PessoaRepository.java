package com.agsilva.os.repositories;

import com.agsilva.os.domain.Pessoa;
import com.agsilva.os.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Tecnico, Integer>{
    @Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
    Pessoa findByCPF(@Param("cpf") String cpf);
}
