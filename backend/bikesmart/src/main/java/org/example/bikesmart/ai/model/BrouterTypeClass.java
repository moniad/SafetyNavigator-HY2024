package org.example.bikesmart.ai.model;

import lombok.Getter;

@Getter
public enum BrouterTypeClass {

    ALL("all"),
    CAR_VARIO("car-vario"),
    DUMMY("dummy"),
    FASTBIKE("fastbike"),
    FASTBIKE_VERYLOWTRAFFIC("fastbike-verylowtraffic"),
    GRAVEL("gravel"),
    HIKING_MOUNTAIN("hiking-mountain"),
    MOPED("moped"),
    MTB("mtb"),
    RAIL("rail"),
    RIVER("river"),
    SAFE("safe"),
    SHORTEST("shortest"),
    SOFTACCESS("softaccess"),
    TREKKING("trekking"),
    VM_FORUM_LIEGERAD_SCHNELL("vm-forum-liegerad-schnell"),
    VM_FORUM_VELOMOBIL_SCHNELL("vm-forum-velomobil-schnell");

    private final String name;

    BrouterTypeClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
