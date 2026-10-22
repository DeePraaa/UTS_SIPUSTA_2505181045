import eksepsi.PustakaException;
import layanan.EksporKatalog;
import layanan.KatalogPerpustakaan;
import layanan.UrutLayakLaluJudul;
import model.BerkasDigital;
import model.BukuCetak;
import model.Majalah;
import model.Terarsip;

import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        KatalogPerpustakaan katalog = new KatalogPerpustakaan();
        BukuCetak bk1 = new BukuCetak("BK-001", "Java How to Program", 2022, 620, "A-12", false);
        BukuCetak bk2 = new BukuCetak("BK-002", "Basis Data Relasional", 2021, 410, "B-04", true);
        Majalah mj1 = new Majalah("MJ-001", "Info Teknologi Edisi 88", 2026, 88, "Agustus", true, "C-01");
        Majalah mj2 = new Majalah("MJ-002", "Info Teknologi Edisi 87", 2026, 87, "Juli", false, "C-02");

        katalog.tambah(bk1);
        katalog.tambah(bk2);
        katalog.tambah(mj1);
        katalog.tambah(mj2);
        katalog.tambah(new BerkasDigital("BD-001", "Panduan Pemrograman Java", 2024, 15, "unduh.polmed.ac.id/bd001"));
        katalog.tambah(new BerkasDigital("BD-002", "Jurnal Rekayasa Perangkat", 2025, 8, "unduh.polmed.ac.id/bd002"));

        System.out.println("=== 1. Status seluruh koleksi (polimorfik) ===");
        katalog.cetakStatus();

        System.out.println("\n=== 2. Ringkasan arsip (interface Terarsip) ===");
        Terarsip[] terarsip = {bk1, bk2, mj1, mj2};
        for (Terarsip t : terarsip) {
            System.out.println(t.ringkasanArsip());
        }

        System.out.println("\n=== 3. Urut: layak dulu, lalu judul ===");
        katalog.urut(new UrutLayakLaluJudul());
        katalog.cetakStatus();

        System.out.println("\n=== 4. Urut: tahun terbit (Comparator dari luar) ===");
        katalog.urut(Comparator.comparingInt(k -> k.getTahunTerbit()));
        katalog.cetakStatus();

        System.out.println("\n=== 5. Uji peminjaman ===");
        String[] kodeUji = {"BK-001", "BK-001", "BK-002", "MJ-001", "MJ-002", "BD-001", "XX-999"};
        for (String kode : kodeUji) {
            try {
                katalog.pinjam(kode);
                System.out.println(kode + ": berhasil dipinjam.");
            } catch (PustakaException e) {
                System.out.println(kode + ": " + e.getMessage());
            }
        }

        System.out.println("\n=== 6. Ekspor katalog ===");
        try {
            new EksporKatalog("katalog.txt").ekspor(katalog);
            System.out.println("Katalog berhasil ditulis ke katalog.txt");
        } catch (IOException e) {
            System.out.println("Gagal menulis katalog.txt: " + e.getMessage());
        }
    }
}