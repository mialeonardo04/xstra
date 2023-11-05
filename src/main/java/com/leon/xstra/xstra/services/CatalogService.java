package com.leon.xstra.xstra.services;

import com.leon.xstra.xstra.entities.Catalog;
import com.leon.xstra.xstra.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;

    public CatalogService() {
    }

    public ResponseEntity<List<Catalog>> getAllWithTitleOrNotCatalogs(String title){
        try {
            List<Catalog> catalogs = new ArrayList<>();

            if (title == null)
                catalogs.addAll(catalogRepository.findAll());
            else
                catalogs.addAll(catalogRepository.findByTitleContaining(title));

            if (catalogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(catalogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Catalog> getCatalogEntityById(long id){
        return catalogRepository.findById(id);
    }

    public ResponseEntity<Catalog> getCatalogById(long id){
        Optional<Catalog> catalogData = this.getCatalogEntityById(id);

        return catalogData.map(catalog -> new ResponseEntity<>(catalog, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Catalog> addCatalog(Catalog catalog){
        try {
            Catalog _catalog = catalogRepository
                    .save(new Catalog(catalog.getTitle(), catalog.getDescription(), false));
            return new ResponseEntity<>(_catalog, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Catalog> editCatalog(long id, Catalog catalog){
        Optional<Catalog> catalogData = this.getCatalogEntityById(id);

        if (catalogData.isPresent()) {
            Catalog _catalog = catalogData.get();
            _catalog.setTitle(catalog.getTitle());
            _catalog.setDescription(catalog.getDescription());
            _catalog.setPublished(catalog.isPublished());
            return new ResponseEntity<>(catalogRepository.save(_catalog), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAll(){
        try {
            catalogRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteById(long id){
        try {
            catalogRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Catalog>> getByPublished(){
        try {
            List<Catalog> catalogs = catalogRepository.findByPublished(true);

            if (catalogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(catalogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
