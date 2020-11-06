package com.davi.ToDoList.repositories;

import com.davi.ToDoList.domains.Item;
import com.davi.ToDoList.domains.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public Optional<Item> findByName(String name);
    @Query("SELECT i FROM Item i WHERE i.name LIKE :name%")
    public List<Item> listItems(@Param("name") String name);
}
