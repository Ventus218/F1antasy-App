package main.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GranPremio {
    private String stato;
    private String nome;
    private Integer numeroGiri;
    private Integer lunghezzaCircuito;

    public GranPremio(String stato, String nome, Integer numeroGiri, Integer lunghezzaCircuito) {
        this.stato = stato;
        this.nome = nome;
        this.numeroGiri = numeroGiri;
        this.lunghezzaCircuito = lunghezzaCircuito;
    }

    public String getStato() {
        return stato;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumeroGiri() {
        return numeroGiri;
    }

    public Integer getLunghezzaCircuito() {
        return lunghezzaCircuito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GranPremio that = (GranPremio) o;
        return Objects.equals(stato, that.stato) && Objects.equals(nome, that.nome) && Objects.equals(numeroGiri, that.numeroGiri) && Objects.equals(lunghezzaCircuito, that.lunghezzaCircuito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stato, nome, numeroGiri, lunghezzaCircuito);
    }

    public static List<GranPremio> getSample() {
        List<GranPremio> list = new ArrayList();
        list.add(new GranPremio("Azerbaijan", "Baku", 68, 3100));
        list.add(new GranPremio("Italia", "Monza", 70, 3000));
        list.add(new GranPremio("Italia", "Mugello", 80, 2500));
        list.add(new GranPremio("Principato di Monaco", "Monte Carlo", 70, 3000));
        list.add(new GranPremio("Spagna", "Catalogna", 69, 3000));
        return list;
    }
}
