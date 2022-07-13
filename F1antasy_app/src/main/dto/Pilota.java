package main.dto;

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
}
