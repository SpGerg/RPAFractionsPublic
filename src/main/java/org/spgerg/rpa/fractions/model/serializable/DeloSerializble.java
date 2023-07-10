package org.spgerg.rpa.fractions.model.serializable;

public class DeloSerializble {

    public final String uuid;

    public final String reason;

    public final Integer id;

    public DeloSerializble(String uuid, String reason, Integer id) {
        this.uuid = uuid;
        this.reason = reason;
        this.id = id;
    }
}
