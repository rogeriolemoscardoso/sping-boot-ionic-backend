package com.spring.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public Categoria() {
    }

    public Categoria(Integer id, String name) {
         this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}