package model;


public abstract class KoleksiPerpustakaan {
    private final String kodeKoleksi;
    private final String judul;
    private final int tahunTerbit;
    private boolean sedangDipinjam;

    protected KoleksiPerpustakaan(String kodeKoleksi, String judul, int tahunTerbit) {
        this.kodeKoleksi = kodeKoleksi;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.sedangDipinjam = false;
    }

    
    public abstract boolean layakDipinjam();

   
    public String deskripsi() {
        return String.format("[%s] \"%s\" (%d)", kodeKoleksi, judul, tahunTerbit);
    }

    public String alasanTidakLayak() {
        return "belum memenuhi syarat peminjaman";
    }

    public String getKodeKoleksi() {
        return kodeKoleksi;
    }

    public String getJudul() {
        return judul;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public boolean isSedangDipinjam() {
        return sedangDipinjam;
    }

    public void setSedangDipinjam(boolean sedangDipinjam) {
        this.sedangDipinjam = sedangDipinjam;
    }
}
