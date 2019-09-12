package com.spring.cursomc.resources;

import com.spring.cursomc.domain.Produto;
import com.spring.cursomc.dto.ProdutoDTO;
import com.spring.cursomc.resources.utils.URL;
import com.spring.cursomc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto obj = produtoService.find(id);
        return  ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam( value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBY", defaultValue = "nome") String orderbY,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Produto> list = produtoService.search(URL.decodeParam(nome), URL.decoodeIntList(categorias), page, linesPerPage, orderbY, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
