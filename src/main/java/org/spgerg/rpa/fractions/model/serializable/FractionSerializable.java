package org.spgerg.rpa.fractions.model.serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FractionSerializable {

    public final String name;

    public final String leader;

    public final String default_post;

    public final List<PlayerSerializable> members;

    public final List<String> permissions;

    public final Map<String, PostSerializable> posts;

    public FractionSerializable(String name, String leader, String defaultPost, ArrayList<PlayerSerializable> members, List<String> permissions, Map<String, PostSerializable> posts) {
        this.name = name;
        this.leader = leader;
        this.default_post = defaultPost;
        this.members = members;
        this.permissions = permissions;
        this.posts = posts;
    }
}
