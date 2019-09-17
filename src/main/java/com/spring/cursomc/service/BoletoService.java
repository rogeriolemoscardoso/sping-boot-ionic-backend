package com.spring.cursomc.service;

import com.spring.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto (PagamentoComBoleto pagto, Date instanteDopedido) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDopedido);
        cal.add(Calendar.DAY_OF_MONTH,7);
        pagto.setDataVencimento(cal.getTime());
    }
}
