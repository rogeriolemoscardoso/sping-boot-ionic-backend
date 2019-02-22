package com.spring.cursomc.repositories;

import com.spring.cursomc.domain.Categoria;
import com.spring.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
