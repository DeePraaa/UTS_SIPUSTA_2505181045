package layanan;

import model.KoleksiPerpustakaan;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class EksporKatalog {
    private final Path berkas;

    public EksporKatalog() {
        this("katalog.txt");
    }

    public EksporKatalog(String namaBerkas) {
        this.berkas = Paths.get(namaBerkas);
    }

    public void ekspor(KatalogPerpustakaan katalog) throws IOException {
        List<KoleksiPerpustakaan> daftar = katalog.getDaftarKoleksi();

        int lebarJenis = "JENIS".length();
        int lebarJudul = "JUDUL".length();
        for (KoleksiPerpustakaan k : daftar) {
            lebarJenis = Math.max(lebarJenis, k.getClass().getSimpleName().length());
            lebarJudul = Math.max(lebarJudul, k.getJudul().length());
        }
        String format = "%-8s %-" + lebarJenis + "s %-" + lebarJudul + "s %6s  %-12s %-10s%n";

        try (BufferedWriter penulis = Files.newBufferedWriter(berkas)) {
            penulis.write(String.format(format, "KODE", "JENIS", "JUDUL", "TAHUN", "KELAYAKAN", "STATUS"));
            penulis.write("-".repeat(8 + 1 + lebarJenis + 1 + lebarJudul + 1 + 6 + 2 + 12 + 1 + 10));
            penulis.newLine();
            for (KoleksiPerpustakaan k : daftar) {
                penulis.write(String.format(format,
                        k.getKodeKoleksi(),
                        k.getClass().getSimpleName(),
                        k.getJudul(),
                        k.getTahunTerbit(),
                        k.layakDipinjam() ? "Layak" : "Tidak layak",
                        k.isSedangDipinjam() ? "Dipinjam" : "Tersedia"));
            }
        }
    }
}