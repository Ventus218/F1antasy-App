package main.dto;

import main.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GranPremioProgrammato {

    private Campionato campionato;
    private LocalDate dataGranPremio;
    private String statoGranPremio;
    private String nomeGranPremio;
    private Boolean concluso;

    public GranPremioProgrammato(Campionato campionato, LocalDate dataGranPremio, String statoGranPremio, String nomeGranPremio, Boolean concluso) {
        this.campionato = campionato;
        this.dataGranPremio = dataGranPremio;
        this.statoGranPremio = statoGranPremio;
        this.nomeGranPremio = nomeGranPremio;
        this.concluso = concluso;
    }

    public Campionato getCampionato() {
        return campionato;
    }

    public LocalDate getDataGranPremio() {
        return dataGranPremio;
    }

    public String getStatoGranPremio() { return statoGranPremio; }

    public String getNomeGranPremio() { return nomeGranPremio; }

    public Boolean getConcluso() {
        return concluso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GranPremioProgrammato that = (GranPremioProgrammato) o;
        return Objects.equals(campionato, that.campionato) && Objects.equals(dataGranPremio, that.dataGranPremio) && Objects.equals(statoGranPremio, that.statoGranPremio) && Objects.equals(nomeGranPremio, that.nomeGranPremio) && Objects.equals(concluso, that.concluso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campionato, dataGranPremio, statoGranPremio, nomeGranPremio, concluso);
    }

    public static List<GranPremioProgrammato> getSample() {
        List<GranPremioProgrammato> list = new ArrayList();
        GranPremio gp1 = GranPremio.getSample().get(0);
        GranPremio gp2 = GranPremio.getSample().get(1);
        GranPremio gp3 = GranPremio.getSample().get(2);
        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), LocalDate.of(2021, 8, 21), gp1.getStato(), gp1.getNome(),true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), LocalDate.of(2021, 8, 28), gp2.getStato(), gp2.getNome(), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), LocalDate.of(2021, 9, 5), gp3.getStato(), gp3.getNome(), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), LocalDate.of(2022, 8, 21), gp1.getStato(), gp1.getNome(), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), LocalDate.of(2022, 8, 28), gp2.getStato(), gp2.getNome(), false));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), LocalDate.of(2022, 9, 5), gp3.getStato(), gp3.getNome(), false));

        return list;
    }
}
