package com.leon.xstra.xstra.controllers;

import com.leon.xstra.xstra.entities.Catalog;
import com.leon.xstra.xstra.repositories.CatalogRepository;
import com.leon.xstra.xstra.services.CatalogService;
import com.leon.xstra.xstra.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    CatalogService catalogService;

    @GetMapping("/catalogs")
    public ResponseEntity<List<Catalog>> getAllCatalogs(@RequestParam(required = false) String title) {
        return catalogService.getAllWithTitleOrNotCatalogs(title);
    }

    @GetMapping("/catalogs/{id}")
    public ResponseEntity<Catalog> getCatalogsById(@PathVariable("id") long id) {
        return catalogService.getCatalogById(id);
    }

    @PostMapping("/catalogs")
    public ResponseEntity<Catalog> createCatalog(@RequestBody Catalog catalog) {
        return catalogService.addCatalog(catalog);
    }

    @PutMapping("/catalogs/{id}")
    public ResponseEntity<Catalog> updateCatalog(@PathVariable("id") long id, @RequestBody Catalog catalog) {
        return catalogService.editCatalog(id,catalog);
    }

    @DeleteMapping("/catalogs/{id}")
    public ResponseEntity<HttpStatus> deleteCatalog(@PathVariable("id") long id) {
        return catalogService.deleteById(id);
    }

    @DeleteMapping("/catalogs")
    public ResponseEntity<HttpStatus> deleteAllCatalogs() {
        return catalogService.deleteAll();
    }

    @GetMapping("/catalogs/published")
    public ResponseEntity<List<Catalog>> findByPublished() {
        return catalogService.getByPublished();
    }
}

