package main.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pilota {

    private Integer codice;
    private String nome;
    private String cognome;

    public Pilota(Integer codice, String nome, String cognome) {
        this.codice = codice;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Integer getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilota pilota = (Pilota) o;
        return Objects.equals(codice, pilota.codice) && Objects.equals(nome, pilota.nome) && Objects.equals(cognome, pilota.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codice, nome, cognome);
    }

    public static List<Pilota> getSample() {
        return PilotaConPrezzo.getSample().stream().map(PilotaConPrezzo::getPilota).collect(Collectors.toList());
    }
}
