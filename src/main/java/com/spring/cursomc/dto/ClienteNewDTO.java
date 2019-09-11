package com.spring.cursomc.dto;

import com.spring.cursomc.service.validator.ClienteInsert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min=5, max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "preenchimento obrigatorio")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String cpfOuCnpj;
    private Integer tipo;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String logadouro;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String numero;
    private String complemento;
    private String bairro;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String cep;

    @NotEmpty(message = "preenchimento obrigatorio")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;


    public ClienteNewDTO() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public String getLogadouro() {
        return logadouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
