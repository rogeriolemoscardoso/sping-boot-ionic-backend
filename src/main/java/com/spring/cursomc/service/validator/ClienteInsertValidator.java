package com.spring.cursomc.service.validator;

import com.spring.cursomc.domain.Cliente;
import com.spring.cursomc.domain.enums.TipoCliente;
import com.spring.cursomc.dto.ClienteNewDTO;
import com.spring.cursomc.repositories.ClienteRepository;
import com.spring.cursomc.resources.exception.FieldMessage;
import com.spring.cursomc.service.validator.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann){

    }

    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDTO != null && objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDTO != null && objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        if (clienteRepository.findByEmail(objDTO.getEmail()) != null) {
            list.add(new FieldMessage("email", "Email já existente"));
       }

        for (FieldMessage e : list) {
            //Adicionando a lista no erro do framework :> essa lista sera mostada no Exception Handle
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getFieldName()).
                    addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
