package com.spring.cursomc.service;

import com.spring.cursomc.domain.Categoria;
import com.spring.cursomc.domain.Produto;
import com.spring.cursomc.repositories.CategoriaRepository;
import com.spring.cursomc.repositories.ProdutoRepository;
import com.spring.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Produto obj = repo.findOne(id);
        if(obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo:" + Produto.class.getName());
        }
        return obj;
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Categoria> categorias = categoriaRepository.findAll(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
    }
}
