package com.spring.cursomc.service;

import com.spring.cursomc.domain.Pedido;
import com.spring.cursomc.repositories.PedidoRepository;
import com.spring.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) {
        Pedido obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
            + ", Tipo: " + Pedido.class.getName());
        }
        return obj;
    }
}
