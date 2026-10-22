package model;

public interface Terarsip {

    String nomorRak();

    String lokasiLantai();

    default String ringkasanArsip() {
        return "Rak " + nomorRak() + ", " + lokasiLantai();
    }

    static String lantaiDariRak(String nomorRak) {
        int lantai = Character.toUpperCase(nomorRak.charAt(0)) - 'A' + 1;
        return "Lantai " + lantai;
    }
}
