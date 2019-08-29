package com.spring.cursomc.service;


import java.util.List;

import com.spring.cursomc.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.cursomc.domain.Categoria;
import com.spring.cursomc.repositories.CategoriaRepository;
import com.spring.cursomc.service.exception.DataIntegrityException;
import com.spring.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Categoria obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id " + id + " , Tipo: " + Categoria.class.getName());
        }
        return obj;
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produto.");
        }
    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }
    
    public Page <Categoria> findPage(Integer page, Integer linesPerPage, String orderbY, String direction) {
    	PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderbY);
    	return repo.findAll(pageRequest);
    	
    }

    public Categoria fromDTO(CategoriaDTO ObjDto) {
        return new Categoria(ObjDto.getId(), ObjDto.getNome());
    }
}
