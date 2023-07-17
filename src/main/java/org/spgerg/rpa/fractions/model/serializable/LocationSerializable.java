package org.spgerg.rpa.fractions.model.serializable;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializable {

    public final Double x;

    public final Double y;

    public final Double z;

    public final World world;

    public LocationSerializable(Double x, Double y, Double z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public LocationSerializable(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.world = location.getWorld();
    }

    public Location toLocation() {
        return new Location(world, x, y, z);
    }
}
