package com.example.mappe1;

public class Sporsmaal {
    String sporsmaal;
    String riktigSvar;
    boolean svartRiktig;

    public Sporsmaal (String sporsmaal, String riktigSvar, boolean svartRiktig){
        this.riktigSvar = riktigSvar;
        this.sporsmaal = sporsmaal;
        this.svartRiktig = svartRiktig;
    }

    public String getSporsmaal() {
        return sporsmaal;
    }

    public void setSporsmaal(String sporsmaal) {
        this.sporsmaal = sporsmaal;
    }

    public String getRiktigSvar() {
        return riktigSvar;
    }

    public void setRiktigSvar(String riktigSvar) {
        this.riktigSvar = riktigSvar;
    }

    public boolean isSvartRiktig() {
        return svartRiktig;
    }

    public void setSvartRiktig(boolean svartRiktig) {
        this.svartRiktig = svartRiktig;
    }
}
