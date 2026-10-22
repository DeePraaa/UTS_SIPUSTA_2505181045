package model;

public class Majalah extends KoleksiPerpustakaan implements Terarsip {
    private final int edisi;
    private final String bulanTerbit;
    private final boolean edisiTerbaru;
    private final String nomorRak;

    public Majalah(String kodeKoleksi, String judul, int tahunTerbit,
                   int edisi, String bulanTerbit, boolean edisiTerbaru, String nomorRak) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.edisi = edisi;
        this.bulanTerbit = bulanTerbit;
        this.edisiTerbaru = edisiTerbaru;
        this.nomorRak = nomorRak;
    }

    @Override
    public boolean layakDipinjam() {
        return !edisiTerbaru;
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " - Majalah, edisi " + edisi + " (" + bulanTerbit + "), "
                + ringkasanArsip() + (edisiTerbaru ? ", edisi terbaru" : "");
    }

    @Override
    public String alasanTidakLayak() {
        return "majalah ini adalah edisi terbaru dan hanya boleh dibaca di tempat";
    }

    @Override
    public String nomorRak() {
        return nomorRak;
    }

    @Override
    public String lokasiLantai() {
        return Terarsip.lantaiDariRak(nomorRak);
    }

    public int getEdisi() {
        return edisi;
    }

    public String getBulanTerbit() {
        return bulanTerbit;
    }

    public boolean isEdisiTerbaru() {
        return edisiTerbaru;
    }
}