package com.spring.cursomc.resources;

import com.spring.cursomc.domain.Categoria;
import com.spring.cursomc.service.CategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaServices service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categoria obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}

