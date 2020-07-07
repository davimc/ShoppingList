package com.davi.ToDoList.repositories;

import com.davi.ToDoList.domains.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    
}
