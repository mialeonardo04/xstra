package com.leon.xstra.xstra.repositories;

import com.leon.xstra.xstra.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    List<Catalog> findByPublished(boolean published);

    List<Catalog> findByTitleContaining(String title);

}
