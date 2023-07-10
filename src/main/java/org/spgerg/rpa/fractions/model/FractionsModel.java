package org.spgerg.rpa.fractions.model;

import com.google.gson.annotations.SerializedName;
import org.spgerg.rpa.fractions.model.serializable.FractionSerializable;

import java.util.HashMap;
import java.util.Map;

public class FractionsModel {

    @SerializedName("fractions")
    private Map<String, FractionSerializable> fractions;

    public void setFractions(Map<String, FractionSerializable> fractions) {
        this.fractions = fractions;
    }

    public Map<String, FractionSerializable> getFractions() {
        if (fractions == null) fractions = new HashMap<>();

        return fractions;
    }
}
