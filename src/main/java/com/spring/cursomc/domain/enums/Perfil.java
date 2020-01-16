package com.spring.cursomc.domain.enums;

public enum Perfil {

    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(2,"ROLE_CLIENTE");

    Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

   private int cod;
   private String descricao;

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido" + cod);
    }
}
