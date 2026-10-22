package layanan;

import model.KoleksiPerpustakaan;

import java.util.Comparator;

public class UrutLayakLaluJudul implements Comparator<KoleksiPerpustakaan> {
    @Override
    public int compare(KoleksiPerpustakaan a, KoleksiPerpustakaan b) {
        int hasil = Boolean.compare(b.layakDipinjam(), a.layakDipinjam());
        if (hasil != 0) {
            return hasil;
        }
        return a.getJudul().compareToIgnoreCase(b.getJudul());
    }
}