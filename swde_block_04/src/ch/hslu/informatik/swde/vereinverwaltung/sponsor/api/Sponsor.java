package ch.hslu.informatik.swde.vereinverwaltung.sponsor.api;

public interface Sponsor {
    void sendMemberData(String name, String vorname, String adresse) throws Exception;
}
