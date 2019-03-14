package com.spring.cursomc.service;


import com.spring.cursomc.domain.Categoria;
import com.spring.cursomc.repositories.CategoriaRepository;
import com.spring.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Categoria obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id " + id + " , Tipo: " + Categoria.class.getName());
        }
        return obj;
    }
}
