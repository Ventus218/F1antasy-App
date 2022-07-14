package main.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Campionato {

    private Integer anno;

    public Campionato(Integer anno) {
        this.anno = anno;
    }

    public Integer getAnno() {
        return anno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campionato that = (Campionato) o;
        return Objects.equals(anno, that.anno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anno);
    }

    public static List<Campionato> getSample() {
        List<Campionato> list = new ArrayList();

        list.add(new Campionato(2021));
        list.add(new Campionato(2022));

        return list;
    }
}

