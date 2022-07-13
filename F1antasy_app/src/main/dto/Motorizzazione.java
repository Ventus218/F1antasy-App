package main.dto;

import java.util.Objects;

public class Motorizzazione {
    private String Nome;

    public Motorizzazione(String nome) {
        Nome = nome;
    }

    public String getNome() {
        return Nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorizzazione that = (Motorizzazione) o;
        return Objects.equals(Nome, that.Nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nome);
    }
}
