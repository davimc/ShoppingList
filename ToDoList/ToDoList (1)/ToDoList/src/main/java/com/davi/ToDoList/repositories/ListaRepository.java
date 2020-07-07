package com.davi.ToDoList.repositories;

import com.davi.ToDoList.domains.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {


}
