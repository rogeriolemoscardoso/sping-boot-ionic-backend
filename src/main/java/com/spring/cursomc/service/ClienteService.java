package com.spring.cursomc.service;


import com.spring.cursomc.domain.Cliente;
import com.spring.cursomc.dto.ClienteDTO;
import com.spring.cursomc.repositories.ClienteRepository;
import com.spring.cursomc.service.exception.DataIntegrityException;
import com.spring.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Cliente obj = clienteRepository.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public Cliente update(Cliente obj) {
        find(obj.getId());
        return clienteRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Cliente que possui produto.");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderbY, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderbY);
        return clienteRepository.findAll(pageRequest);

    }

    public Cliente fromDTO(ClienteDTO ObjDto) {
        throw new UnsupportedOperationException();
    }
}
