package com.agsilva.os.repositories;

import com.agsilva.os.domain.Os;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends JpaRepository<Os, Integer> {
}
