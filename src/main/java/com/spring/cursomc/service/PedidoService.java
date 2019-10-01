package com.spring.cursomc.service;

import com.spring.cursomc.domain.ItemPedido;
import com.spring.cursomc.domain.Pagamento;
import com.spring.cursomc.domain.PagamentoComBoleto;
import com.spring.cursomc.domain.Pedido;
import com.spring.cursomc.domain.enums.EstadoPagamento;
import com.spring.cursomc.repositories.*;
import com.spring.cursomc.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Pedido buscar(Integer id) {
        Pedido obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
            + ", Tipo: " + Pedido.class.getName());
        }
        return obj;
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstance(new Date());
        obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

            if (obj.getPagamento() instanceof PagamentoComBoleto) {
                PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
                boletoService.preencherPagamentoComBoleto(pagto, obj.getInstance());
            }

        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
            ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.save(obj.getItens());
        System.out.println(obj);
        return obj;
    }
}
