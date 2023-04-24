package com.ksptooi.backtool;

import java.util.List;

public class Config {

    private String origin;
    private String destination;

    private List<String> ignore;

    @Override
    public String toString() {
        return "Config{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", ignore=" + ignore +
                '}';
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getIgnore() {
        return ignore;
    }

    public void setIgnore(List<String> ignore) {
        this.ignore = ignore;
    }
}
