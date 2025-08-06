# Project Recycle View - Data Siswa

Project ini menampilkan daftar data siswa menggunakan komponen Recycle View untuk efisiensi tampilan data dalam jumlah banyak. Setiap item menampilkan informasi seperti Nama, NIS, dan Kelas siswa. Aplikasi ini dirancang untuk menampilkan data secara dinamis dan responsif, serta dapat dikembangkan lebih lanjut untuk menambahkan fitur seperti pencarian, atau detail siswa.

## Tim :
- Cahyo Budi Wibowo
- Faradhina Nazura
- Efanda Reyo Saputra

## Fitur - fitur :
### 1. Menampilkan daftar siswa dengan RecyclerView :
### a. Model (Student.kt)
<img width="1234" height="379" alt="Screenshot 2025-08-06 185348" src="https://github.com/user-attachments/assets/2b811dde-891f-4f4d-9a87-bd81c0f9ddd2" />
Kode program pada gambar tersebut merupakan deklarasi sebuah data class bernama Student dalam bahasa Kotlin yang berada dalam package com.smkth.app3_recycleview.model. Data class ini digunakan untuk merepresentasikan data siswa dengan tiga properti utama, yaitu nama (tipe String), nis (Nomor Induk Siswa, tipe String), dan kelas (tipe String). Karena menggunakan data class, Kotlin secara otomatis akan menyediakan fungsi-fungsi seperti toString(), equals(), hashCode(), dan copy() untuk memudahkan pengelolaan data. Kelas ini kemungkinan besar digunakan sebagai model data untuk menampilkan daftar siswa dalam aplikasi yang menggunakan RecyclerView.

### b. Adapter (StudentAdapter.kt)
<img width="1421" height="423" alt="Screenshot 2025-08-06 191035" src="https://github.com/user-attachments/assets/bb140345-bc5d-429b-a0dc-a324a05bf464" />
Kode tersebut adalah kelas StudentAdapter yang berfungsi sebagai adapter untuk menampilkan data siswa di RecyclerView. Kelas ini menerima context, daftar siswa (studentList), serta fungsi lambda onEdit dan onDelete untuk menangani aksi edit dan hapus. Adapter ini menghubungkan data Student dengan tampilan, dan mengimplementasikan Filterable untuk fitur pencarian atau filter data.


<img width="1448" height="902" alt="Screenshot 2025-08-06 190231" src="https://github.com/user-attachments/assets/2c576615-4bf9-4c96-8e25-af3c4053daee" />
Potongan kode tersebut merupakan bagian dari StudentAdapter yang berfungsi menampilkan data siswa di RecyclerView. filteredList menyimpan daftar siswa yang dapat difilter dari studentList. ViewHolder bertugas menampung referensi tampilan dari setiap item (nama, NIS, kelas, tombol edit dan delete). Fungsi onCreateViewHolder() akan membuat tampilan item dari layout item_student, getItemCount() mengembalikan jumlah data yang ditampilkan, dan onBindViewHolder() digunakan untuk mengisi tampilan dengan data dari objek Student sesuai posisi, seperti nama, NIS, dan kelas.


<img width="1307" height="530" alt="Screenshot 2025-08-06 190648" src="https://github.com/user-attachments/assets/66225346-21d2-475c-99fd-334b2ee1c3ef" />
Potongan kode tersebut menangani aksi ketika item RecyclerView diklik dan tombol edit ditekan. Saat item diklik, aplikasi membuat Intent untuk membuka DetailActivity, lalu mengirim data siswa (nama, nis, kelas) melalui putExtra(), dan memulai aktivitas tersebut dengan startActivity(). Sedangkan pada tombol edit (btnEdit), fungsi onEdit() dipanggil dengan parameter data siswa dan posisinya, yang nantinya akan menangani logika edit di luar adapter.


<img width="1316" height="454" alt="Screenshot 2025-08-06 192136" src="https://github.com/user-attachments/assets/66ff2ba4-6f44-4f26-a92e-a224929864c1" />
Kode tersebut menangani aksi saat tombol hapus (btnDelete) ditekan. Ketika ditekan, akan muncul dialog konfirmasi (AlertDialog) dengan judul "Hapus Siswa" dan pesan berisi nama siswa yang akan dihapus. Jika pengguna menekan tombol "Hapus", maka fungsi onDelete(position) dipanggil untuk menghapus data siswa dari list. Jika memilih "Batal", dialog akan ditutup tanpa aksi. Ini bertujuan untuk mencegah penghapusan data secara tidak sengaja.


<img width="1315" height="768" alt="Screenshot 2025-08-06 192508" src="https://github.com/user-attachments/assets/94694c4c-0674-433d-801b-26b7b94171f3" />
Kode tersebut merupakan implementasi getFilter() pada StudentAdapter yang memungkinkan pengguna mencari data siswa berdasarkan nama, NIS, atau kelas. Fungsi performFiltering() mengubah input query menjadi lowercase lalu mencocokkannya dengan data di studentList. Jika query kosong, seluruh data ditampilkan. Jika tidak, data disaring menggunakan filter() untuk mencocokkan keyword dengan nama, nis, atau kelas, dan hasilnya disimpan dalam filteredList. Fungsi publishResults() kemudian mengisi ulang filteredList dengan hasil pencarian (results.values) dan memanggil notifyDataSetChanged() untuk memperbarui tampilan RecyclerView agar menampilkan data yang sesuai pencarian.


<img width="1310" height="336" alt="Screenshot 2025-08-06 192956" src="https://github.com/user-attachments/assets/87ec369a-469a-4d5b-ad2b-1faab9732d72" />
Potongan kode tersebut berisi dua fungsi dalam StudentAdapter. Fungsi updateList() digunakan untuk memperbarui data siswa dengan list baru (newList), yang kemudian disalin ke studentList dan filteredList, lalu memanggil notifyDataSetChanged() agar RecyclerView menampilkan data terbaru. Sementara itu, fungsi getOriginalList() mengembalikan data asli (studentList) dalam bentuk List<Student>, yang bisa digunakan saat dibutuhkan untuk keperluan lain seperti reset filter atau pengolahan data.


### 2. Navigasi ke Detail (DetailActivity.kt)
<img width="1337" height="994" alt="Screenshot 2025-08-06 193506" src="https://github.com/user-attachments/assets/cf289542-1c46-467f-a899-5757b6a5ab3d" />
Kode pada `DetailActivity.kt` bertugas menampilkan detail data siswa seperti nama, NIS, dan kelas. Aktivitas ini menggunakan tiga `TextView` (`tvNama`, `tvNis`, dan `tvKelas`) yang diinisialisasi di dalam metode `onCreate()`. Pertama, layout `activity_detail` ditetapkan sebagai tampilan, lalu dilakukan penyesuaian padding dengan `WindowInsetsCompat` agar sesuai dengan sistem UI seperti status bar. Ketiga `TextView` dihubungkan ke komponen layout menggunakan `findViewById`, kemudian data nama, NIS, dan kelas yang dikirim dari activity sebelumnya diambil menggunakan `intent.getStringExtra()` dengan key `"student_nama"`, `"student_nis"`, dan `"student_kelas"`. Data yang diperoleh ditampilkan ke masing-masing `TextView` dengan mengatur properti `text`. Dengan demikian, `DetailActivity` menampilkan informasi siswa secara lengkap berdasarkan data yang diteruskan dari halaman sebelumnya.


## Cara Kerja RecyclerView
Pada project ini, RecyclerView digunakan untuk menampilkan daftar siswa secara dinamis dan efisien. Proses dimulai dari MainActivity, di mana data siswa (nama, NIS, dan kelas) dimasukkan ke dalam sebuah MutableList<Student>. Data ini kemudian dikirim ke StudentAdapter, yaitu adapter yang bertugas mengelola dan menampilkan data ke dalam tampilan RecyclerView.
Di dalam StudentAdapter, metode onCreateViewHolder() dipanggil untuk membuat tampilan item berdasarkan layout XML (item_student.xml) menggunakan LayoutInflater. Kemudian onBindViewHolder() bertugas untuk mengisi data ke tampilan item berdasarkan posisi, seperti menampilkan nama, NIS, dan kelas siswa di dalam TextView. Untuk menghindari pembuatan tampilan baru terus-menerus, RecyclerView menggunakan pola ViewHolder, yaitu sebuah inner class (StudentViewHolder) yang menyimpan referensi view di dalam item layout sehingga tidak perlu mencari ulang menggunakan findViewById setiap kali data di-bind.
Setiap item juga memiliki dua tombol: Edit dan Delete, yang ketika diklik akan memicu lambda function onEdit dan onDelete. Tombol Edit mengirim data siswa ke form edit, sedangkan tombol Delete akan menampilkan dialog konfirmasi, dan jika disetujui, data akan dihapus dari list. Jika pengguna mengklik seluruh item (bukan tombol), maka Intent akan dikirim untuk membuka DetailActivity, di mana data siswa ditampilkan menggunakan TextView.
Selain itu, adapter juga mengimplementasikan Filterable, yang memungkinkan pengguna untuk mencari data berdasarkan nama, NIS, atau kelas. Fitur ini bekerja dengan menyaring studentList sesuai kata kunci pencarian, lalu memperbarui filteredList, dan memanggil notifyDataSetChanged() agar RecyclerView menampilkan data hasil pencarian.
RecyclerView hanya membuat dan menampilkan item yang terlihat di layar. Ketika pengguna menggulir (scroll), item yang keluar dari layar akan didaur ulang (recycled) dan digunakan ulang untuk menampilkan item baru, sehingga performa aplikasi tetap ringan dan efisien, meskipun jumlah data banyak.


## Struktur File dan Alur Data dari Model, Adapter, hingga Activity

1. com.smkth.app3_recycleview
2. │
3. ├── model/
4. │   └── Student.kt                → Model data siswa (nama, NIS, kelas)
5. │
6. ├── adapter/
7. │   └── StudentAdapter.kt         → Adapter RecyclerView untuk mengatur dan menampilkan data siswa
8. │
9. ├── activity/
10. │   ├── MainActivity.kt           → Activity utama, menampilkan daftar siswa di RecyclerView
11. │   └── DetailActivity.kt         → Menampilkan detail data siswa yang dipilih
12. │
13. ├── res/
14. │   └── layout/
15. │       ├── activity_main.xml     → Layout untuk MainActivity (memuat RecyclerView)
16. │       ├── activity_detail.xml   → Layout untuk DetailActivity
17. │       └── item_student.xml      → Layout tampilan per item siswa di RecyclerView


## 🔁 Alur Data: Model → Adapter → Activity:
- Model (Student.kt)
  Merupakan data class yang merepresentasikan entitas siswa, berisi properti nama, nis, dan kelas. Kelas ini berfungsi sebagai struktur data dasar yang akan digunakan oleh adapter dan activity.
- Activity (MainActivity.kt)
  Activity utama memuat RecyclerView dan list siswa. Data dimasukkan ke dalam list MutableList<Student> dan dikirim ke StudentAdapter. Di sinilah adapter disiapkan dan di-set ke RecyclerView agar data bisa ditampilkan ke layar.
- Adapter (StudentAdapter.kt)
  Adapter berfungsi sebagai jembatan antara data (model) dan tampilan (RecyclerView). Fungsi onCreateViewHolder() membuat item berdasarkan layout item_student.xml, dan onBindViewHolder() mengisi data siswa ke tampilan item. Adapter juga menangani event klik item (untuk membuka DetailActivity) serta klik tombol Edit dan Delete. Selain itu, adapter memiliki fitur filter pencarian dan fungsi updateList() untuk memperbarui data.
- Activity (DetailActivity.kt)
  Ketika item siswa diklik, data siswa dikirim melalui Intent ke DetailActivity. Di sana, data diterima dengan getStringExtra() dan ditampilkan ke layar menggunakan TextView.


## Hasil



  ## Kesimpulan
  RecyclerView adalah komponen Android yang digunakan untuk menampilkan data dalam jumlah banyak secara efisien dan fleksibel. Dengan bantuan Adapter dan ViewHolder, RecyclerView mampu mengelola tampilan item secara optimal dengan cara mendaur ulang view yang tidak terlihat, sehingga hemat memori dan performanya tinggi. RecyclerView juga mendukung berbagai fitur seperti layout vertikal, horizontal, grid, filter pencarian, serta event klik item. Dalam implementasinya, data dikelola melalui model (data class), ditampilkan melalui adapter, dan dihubungkan dengan aktivitas untuk interaksi pengguna. Secara keseluruhan, RecyclerView merupakan solusi modern dan powerful untuk membuat daftar data yang interaktif dan responsif dalam aplikasi Android.



