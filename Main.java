import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Array menu restoran
    static Menu[] daftarMenu = {
        new Menu("Nasi Padang", 25000, "Makanan"),
        new Menu("Ayam Geprek", 20000, "Makanan"),
        new Menu("Mie Goreng", 18000, "Makanan"),
        new Menu("Soto Ayam", 22000, "Makanan"),
        new Menu("Es Teh", 5000, "Minuman"),
        new Menu("Es Jeruk", 8000, "Minuman"),
        new Menu("Kopi Susu", 15000, "Minuman"),
        new Menu("Air Mineral", 4000, "Minuman")
    };

    // Data pesanan, maksimal 4 menu
    static String[] namaPesanan = new String[4];
    static int[] jumlahPesanan = new int[4];
    static int jumlahJenisPesanan = 0;

    public static void main(String[] args) {
        tampilkanMenu();
        terimaPesanan();
        cetakStruk();
    }

    // Menampilkan menu berdasarkan kategori
    public static void tampilkanMenu() {
        System.out.println("========================================");
        System.out.println("          DAFTAR MENU RESTORAN          ");
        System.out.println("========================================");

        System.out.println("MAKANAN:");
        System.out.println("1. Nasi Padang        : Rp 25.000");
        System.out.println("2. Ayam Geprek        : Rp 20.000");
        System.out.println("3. Mie Goreng         : Rp 18.000");
        System.out.println("4. Soto Ayam          : Rp 22.000");

        System.out.println("\nMINUMAN:");
        System.out.println("5. Es Teh             : Rp 5.000");
        System.out.println("6. Es Jeruk           : Rp 8.000");
        System.out.println("7. Kopi Susu          : Rp 15.000");
        System.out.println("8. Air Mineral        : Rp 4.000");

        System.out.println("========================================");
    }

    // Menerima maksimal 4 pesanan tanpa perulangan
    public static void terimaPesanan() {
        System.out.println("Masukkan pesanan Anda.");
        System.out.println("Format: Nama Menu = Jumlah");
        System.out.println("Contoh : Nasi Padang = 2");
        System.out.println("Maksimal 4 jenis menu.\n");

        String pesanan1, pesanan2, pesanan3, pesanan4;

        System.out.print("Pesanan 1 : ");
        pesanan1 = input.nextLine();
        prosesPesanan(pesanan1);

        System.out.print("Pesanan 2 : ");
        pesanan2 = input.nextLine();
        prosesPesanan(pesanan2);

        System.out.print("Pesanan 3 : ");
        pesanan3 = input.nextLine();
        prosesPesanan(pesanan3);

        System.out.print("Pesanan 4 : ");
        pesanan4 = input.nextLine();
        prosesPesanan(pesanan4);
    }

    // Memproses satu baris pesanan
    public static void prosesPesanan(String teksPesanan) {
        if (teksPesanan.trim().isEmpty()) {
            return;
        }

        if (jumlahJenisPesanan >= 4) {
            System.out.println("Pesanan melebihi batas 4 jenis menu.");
            return;
        }

        if (!teksPesanan.contains("=")) {
            System.out.println("Format salah. Gunakan format: Nama Menu = Jumlah");
            return;
        }

        String[] bagian = teksPesanan.split("=");

        String nama = bagian[0].trim();
        String jumlahTeks = bagian[1].trim();
        int jumlah = Integer.parseInt(jumlahTeks);

        Menu menuDitemukan = cariMenu(nama);

        if (menuDitemukan == null) {
            System.out.println("Menu \"" + nama + "\" tidak ditemukan.");
        } else if (jumlah <= 0) {
            System.out.println("Jumlah pesanan harus lebih dari 0.");
        } else {
            namaPesanan[jumlahJenisPesanan] = menuDitemukan.getNama();
            jumlahPesanan[jumlahJenisPesanan] = jumlah;
            jumlahJenisPesanan = jumlahJenisPesanan + 1;
        }
    }

    // Mencari menu berdasarkan nama
    public static Menu cariMenu(String nama) {
        Menu hasil = null;

        if (daftarMenu[0].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[0];
        }
        if (daftarMenu[1].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[1];
        }
        if (daftarMenu[2].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[2];
        }
        if (daftarMenu[3].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[3];
        }
        if (daftarMenu[4].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[4];
        }
        if (daftarMenu[5].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[5];
        }
        if (daftarMenu[6].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[6];
        }
        if (daftarMenu[7].getNama().equalsIgnoreCase(nama)) {
            hasil = daftarMenu[7];
        }

        return hasil;
    }

    // Menghitung total sebelum pajak dan pelayanan
    public static int hitungTotalMenu() {
        int total = 0;

        if (jumlahJenisPesanan >= 1) {
            total = total + cariMenu(namaPesanan[0]).getHarga() * jumlahPesanan[0];
        }
        if (jumlahJenisPesanan >= 2) {
            total = total + cariMenu(namaPesanan[1]).getHarga() * jumlahPesanan[1];
        }
        if (jumlahJenisPesanan >= 3) {
            total = total + cariMenu(namaPesanan[2]).getHarga() * jumlahPesanan[2];
        }
        if (jumlahJenisPesanan >= 4) {
            total = total + cariMenu(namaPesanan[3]).getHarga() * jumlahPesanan[3];
        }

        return total;
    }

    // Menghitung potongan harga minuman (B1G1)
    public static int hitungPotonganMinuman(int totalMenu) {
        int potongan = 0;

        if (totalMenu > 50000) {
            if (jumlahJenisPesanan >= 1 && cariMenu(namaPesanan[0]).getKategori().equals("Minuman")) {
                potongan = potongan + cariMenu(namaPesanan[0]).getHarga();
            }
            if (jumlahJenisPesanan >= 2 && cariMenu(namaPesanan[1]).getKategori().equals("Minuman")) {
                potongan = potongan + cariMenu(namaPesanan[1]).getHarga();
            }
            if (jumlahJenisPesanan >= 3 && cariMenu(namaPesanan[2]).getKategori().equals("Minuman")) {
                potongan = potongan + cariMenu(namaPesanan[2]).getHarga();
            }
            if (jumlahJenisPesanan >= 4 && cariMenu(namaPesanan[3]).getKategori().equals("Minuman")) {
                potongan = potongan + cariMenu(namaPesanan[3]).getHarga();
            }
        }

        return potongan;
    }

    // Mencetak struk pesanan
    public static void cetakStruk() {
        int totalMenu = hitungTotalMenu();
        int biayaPelayanan = 20000;
        int pajak = (int) (totalMenu * 0.10);
        int totalSebelumDiskon = totalMenu + pajak + biayaPelayanan;

        int diskon = 0;
        int potonganMinuman = hitungPotonganMinuman(totalSebelumDiskon);
        int totalAkhir = totalSebelumDiskon - diskon - potonganMinuman;

        if (totalSebelumDiskon > 100000) {
            diskon = (int) (totalSebelumDiskon * 0.10);
            totalAkhir = totalSebelumDiskon - diskon - potonganMinuman;
        }

        System.out.println("\n========================================");
        System.out.println("              STRUK PESANAN             ");
        System.out.println("========================================");

        if (jumlahJenisPesanan >= 1) {
            Menu m1 = cariMenu(namaPesanan[0]);
            System.out.printf("%-15s x%-3d Rp%,10d%n",
                    m1.getNama(), jumlahPesanan[0], m1.getHarga() * jumlahPesanan[0]);
        }
        if (jumlahJenisPesanan >= 2) {
            Menu m2 = cariMenu(namaPesanan[1]);
            System.out.printf("%-15s x%-3d Rp%,10d%n",
                    m2.getNama(), jumlahPesanan[1], m2.getHarga() * jumlahPesanan[1]);
        }
        if (jumlahJenisPesanan >= 3) {
            Menu m3 = cariMenu(namaPesanan[2]);
            System.out.printf("%-15s x%-3d Rp%,10d%n",
                    m3.getNama(), jumlahPesanan[2], m3.getHarga() * jumlahPesanan[2]);
        }
        if (jumlahJenisPesanan >= 4) {
            Menu m4 = cariMenu(namaPesanan[3]);
            System.out.printf("%-15s x%-3d Rp%,10d%n",
                    m4.getNama(), jumlahPesanan[3], m4.getHarga() * jumlahPesanan[3]);
        }

        System.out.println("----------------------------------------");
        System.out.printf("Total Menu          : Rp%,10d%n", totalMenu);
        System.out.printf("Pajak 10%%           : Rp%,10d%n", pajak);
        System.out.printf("Biaya Pelayanan     : Rp%,10d%n", biayaPelayanan);
        System.out.printf("Total Sebelum Diskon: Rp%,10d%n", totalSebelumDiskon);

        if (diskon > 0) {
            System.out.printf("Diskon 10%%          : -Rp%,9d%n", diskon);
        } else {
            System.out.println("Diskon 10%          : Tidak mendapat diskon");
        }

        if (potonganMinuman > 0) {
            System.out.printf("Promo B1G1 Minuman  : -Rp%,9d%n", potonganMinuman);
        } else {
            System.out.println("Promo B1G1 Minuman  : Tidak mendapat promo");
        }

        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BAYAR         : Rp%,10d%n", totalAkhir);
        System.out.println("========================================");
        System.out.println("Terima kasih telah berbelanja di restoran kami!");

    }
}