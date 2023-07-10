package org.spgerg.rpa.fractions.model.serializable;

import java.util.ArrayList;
import java.util.List;

public class PlayerSerializable {

    public final String name;

    public String fraction;

    public String post;

    public Integer arrestTime;

    public Integer wanted;

    public List<DeloSerializble> delos;

    public PlayerSerializable(String name, String fraction, String post) {
        this.name = name;
        this.fraction = fraction;
        this.post = post;

        wanted = 0;
        arrestTime = 0;
        delos = new ArrayList<>();
    }

    public PlayerSerializable(String name, String fraction, String post, int wanted, int arrestTime, List<DeloSerializble> delos) {
        this.name = name;
        this.fraction = fraction;
        this.post = post;
        this.wanted = wanted;
        this.arrestTime = arrestTime;
        this.delos = delos;
    }
}
