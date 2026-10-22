package model;

public class BerkasDigital extends KoleksiPerpustakaan {
    private final int ukuranMB;
    private final String tautanUnduh;

    public BerkasDigital(String kodeKoleksi, String judul, int tahunTerbit,
                         int ukuranMB, String tautanUnduh) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.ukuranMB = ukuranMB;
        this.tautanUnduh = tautanUnduh;
    }

    @Override
    public boolean layakDipinjam() {
        return true;
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " - Berkas Digital, " + ukuranMB + " MB, unduh di " + tautanUnduh;
    }

    public int getUkuranMB() {
        return ukuranMB;
    }

    public String getTautanUnduh() {
        return tautanUnduh;
    }
}