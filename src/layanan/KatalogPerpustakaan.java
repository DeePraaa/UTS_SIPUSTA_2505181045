package layanan;

import eksepsi.KoleksiSedangDipinjamException;
import eksepsi.KoleksiTidakLayakException;
import eksepsi.PustakaException;
import model.KoleksiPerpustakaan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KatalogPerpustakaan {
    private final List<KoleksiPerpustakaan> daftarKoleksi = new ArrayList<>();

    public void tambah(KoleksiPerpustakaan koleksi) {
        daftarKoleksi.add(koleksi);
    }

    public List<KoleksiPerpustakaan> getDaftarKoleksi() {
        return daftarKoleksi;
    }

    public void cetakStatus() {
        for (KoleksiPerpustakaan koleksi : daftarKoleksi) {
            String status = koleksi.layakDipinjam() ? "LAYAK PINJAM" : "TIDAK LAYAK";
            System.out.println(koleksi.deskripsi() + " => " + status);
        }
    }

    public void urut(Comparator<? super KoleksiPerpustakaan> pembanding) {
        daftarKoleksi.sort(pembanding);
    }

    public void pinjam(String kodeKoleksi) throws PustakaException {
        KoleksiPerpustakaan koleksi = cari(kodeKoleksi);
        if (!koleksi.layakDipinjam()) {
            throw new KoleksiTidakLayakException(koleksi.getJudul(), koleksi.alasanTidakLayak());
        }
        if (koleksi.isSedangDipinjam()) {
            throw new KoleksiSedangDipinjamException(koleksi.getJudul());
        }
        koleksi.setSedangDipinjam(true);
    }

    private KoleksiPerpustakaan cari(String kodeKoleksi) throws PustakaException {
        for (KoleksiPerpustakaan koleksi : daftarKoleksi) {
            if (koleksi.getKodeKoleksi().equalsIgnoreCase(kodeKoleksi)) {
                return koleksi;
            }
        }
        throw new PustakaException("Koleksi dengan kode " + kodeKoleksi
                + " tidak ditemukan di katalog. Periksa kembali kode yang Anda masukkan.");
    }
}