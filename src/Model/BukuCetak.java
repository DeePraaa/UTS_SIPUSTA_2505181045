package model;

public class BukuCetak extends KoleksiPerpustakaan implements Terarsip {
    private final int jumlahHalaman;
    private final String nomorRak;
    private final boolean sedangDiperbaiki;

    public BukuCetak(String kodeKoleksi, String judul, int tahunTerbit,
                     int jumlahHalaman, String nomorRak, boolean sedangDiperbaiki) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.jumlahHalaman = jumlahHalaman;
        this.nomorRak = nomorRak;
        this.sedangDiperbaiki = sedangDiperbaiki;
    }

    @Override
    public boolean layakDipinjam() {
        return !sedangDiperbaiki;
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " - Buku Cetak, " + jumlahHalaman + " halaman, "
                + ringkasanArsip() + (sedangDiperbaiki ? ", sedang diperbaiki" : "");
    }

    @Override
    public String alasanTidakLayak() {
        return "buku ini sedang diperbaiki";
    }

    @Override
    public String nomorRak() {
        return nomorRak;
    }

    @Override
    public String lokasiLantai() {
        return Terarsip.lantaiDariRak(nomorRak);
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public boolean isSedangDiperbaiki() {
        return sedangDiperbaiki;
    }
}