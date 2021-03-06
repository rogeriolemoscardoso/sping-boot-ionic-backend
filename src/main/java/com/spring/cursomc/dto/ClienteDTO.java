package com.spring.cursomc.dto;

import com.spring.cursomc.domain.Cliente;
import com.spring.cursomc.service.validator.ClienteInsert;
import com.spring.cursomc.service.validator.ClienteUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String nome;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail()     ;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
