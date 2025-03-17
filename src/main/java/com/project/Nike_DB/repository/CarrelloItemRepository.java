package com.project.Nike_DB.repository;

import com.project.Nike_DB.model.CarrelloItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloItemRepository extends JpaRepository<CarrelloItem, Long> {

    List<CarrelloItem> findByCarrelloId(Long carrelloId);

}
