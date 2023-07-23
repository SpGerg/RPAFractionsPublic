package org.spgerg.rpa.fractions.model.serializable;
import java.util.List;

public class PostSerializable {

    public final String name;

    public final String fraction;

    public final boolean isArmyBilet;

    public final List<String> permissions;

    public PostSerializable(String name, String fraction, List<String> permissions) {
        this.name = name;
        this.fraction = fraction;
        this.permissions = permissions;

        isArmyBilet = false;
    }
}
