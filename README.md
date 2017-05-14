# TMDb-Droid
## Download
 https://github.com/mfaishalakbar/TMDb-Droid/releases/latest/
## Identitas
* Nama Lengkap : Muhammad Faishal Akbar
* Kelas : XI RPL 2 / 16
* NIS : 4767/1486.070
* Angkatan : 24
* SMK Telkom Malang
## Alamat API Yang Digunakan
TMDb API  (Get Popular, Now Airing, Upcoming Movies, Popular and On Airing TV Shows, Search for Movies and TV Shows) 
https://www.themoviedb.org/documentation/api

## Jenis Navigasi
Swipe Views untuk Saved, Drawer + Fragment untuk Main Activity

## Library untuk Local Database
Untuk project ini, saya menggunakan SugarORM 1.5

## Deskripsi
Aplikasi ini bertujuan untuk memudahkan para penggemar Film maupun TV Show luar untuk melihat rating dari acara kesayangannya secara cepat dan praktis, selain itu. dengan menggunakan Library Image Loading, diharapkan aplikasi ini powerful tanpa mengalami lag sedikitpun. Aplikasi ini bekerja dengan mengirimkan Request GET kepada server TMDb untuk mendapatkan File JSON yang berisi informasi - informasi yang dibutuhkan untuk menampilkan Film yang sedang Hangat, Film yang sekarang diputar, maupun Upocoming Film. tidak hanya terbatas Film, hal ini berlaku juga di TV Show. 

Selain itu dengan adanya fitur search di aplikasi ini memungkinkan pengguna mencari Film maupun TV Show yang tidak ada di dalam list Trending, Ongoing, maupun Upcoming. selain itu user dapat menyimpan Film aupun TV Show kesayangannya kedalam Saved Box. dimana Saved box akan menyimpan Metadata dari Film / TV Show kedalam Local Database ponsel , kelebihan aplikasi ini adalah user tidak perlu mendaftar untuk menyimpan secara offline di perangkat pintarnya, User hanya cukup menekan tombol Archive, dan informasi yang ada dilayar akan tersedia walaupun koneksi internet tidak memungkinkan untuk mengakses TMDb secara online. User juga bisa menghapus Favoritenya dengan menahan Item didalam Saves. Selain itu dengan adanya panduan aplikasi yang berada didalam aplikasi itu sendiri diharapkan user awam dapat mengerti cara pengunaan dari aplikasi tersebut

## Kelebihan
* Adanya fitur search yang memudahkan pengguna mencari Film / TV Show yang tidak ada didalam List
* Adanya Fitur Save offline memudahkan user untuk menandai Film apa yang sekiranya dapat ditonton saat berada dekat dengan Bioskop
* In-App Guide memudahkan User awam untuk menggunakan Aplikasi

## Library Lain?
* EasyPrefs untuk menyimpan Preferensi User (digunakan untuk menyimpan Trigger First Time)
* ShowCase Library untuk Petunjuk didalam Aplikasi

## Screenshot

![Screenshot](https://github.com/mfaishalakbar/TMDb-Droid/blob/master/Screenshots/1.png "Screenshot")
![Screenshot](https://github.com/mfaishalakbar/TMDb-Droid/blob/master/Screenshots/2.png "Screenshot")
![Screenshot](https://github.com/mfaishalakbar/TMDb-Droid/blob/master/Screenshots/3.png "Screenshot")
![Screenshot](https://github.com/mfaishalakbar/TMDb-Droid/blob/master/Screenshots/4.png "Screenshot")
![Screenshot](https://github.com/mfaishalakbar/TMDb-Droid/blob/master/Screenshots/5.png "Screenshot")
![Screenshot](https://github.com/mfaishalakbar/TMDb-Droid/blob/master/Screenshots/6.png "Screenshot")
![Screenshot](https://github.com/mfaishalakbar/TMDb-Droid/blob/master/Screenshots/7.png "Screenshot")
