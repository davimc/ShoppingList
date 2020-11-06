package com.davi.ToDoList.repositories;

import com.davi.ToDoList.domains.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {
    public Optional<Lista> findByName(String name);
    @Query("SELECT l FROM Lista l WHERE l.name LIKE :name% ORDER BY l.name ASC")
    public List<Lista> listListas(@Param("name") String name);

}
