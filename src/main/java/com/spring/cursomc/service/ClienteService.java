package com.spring.cursomc.service;


import com.spring.cursomc.domain.Cidade;
import com.spring.cursomc.domain.Cliente;
import com.spring.cursomc.domain.Endereco;
import com.spring.cursomc.domain.enums.TipoCliente;
import com.spring.cursomc.dto.ClienteDTO;
import com.spring.cursomc.dto.ClienteNewDTO;
import com.spring.cursomc.repositories.CidadeRepository;
import com.spring.cursomc.repositories.ClienteRepository;
import com.spring.cursomc.repositories.EnderecoRepository;
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

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Cliente obj = clienteRepository.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.save(obj.getEndereco());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente  newObj = find(obj.getId());
        updateData(newObj,obj);
        return clienteRepository.save(obj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
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
        return new Cliente(ObjDto.getId(),ObjDto.getNome(),ObjDto.getEmail(),null,null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
        Endereco end = new Endereco(null,objDto.getLogadouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cid );
        cli.getEndereco().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }
}
