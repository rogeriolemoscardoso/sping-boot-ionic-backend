package com.spring.cursomc.service.validator;

import com.spring.cursomc.domain.Cliente;
import com.spring.cursomc.dto.ClienteDTO;
import com.spring.cursomc.repositories.ClienteRepository;
import com.spring.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ClienteUpdate ann){

    }

    @Override
    public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.valueOf(map.get("id"));

        Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());

        if (aux != null && !aux.getId().equals(uriId)) {
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
