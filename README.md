## Product Management Service

Langkah-langkah installasi service

### Git Command

- git clone: mengunggah project backend ke local
- git pull: mengambil perubahan berdasarkan commmitan terbaru

### Maven Command

- mvn clean install: mengunggah dependency-dependency yang dibutuhkan pada project ini
- mvn spring-boot:run : menjalankan project backend springboot

### How to use

Cara menjalankan Service ini bisa melalui postman atau menggunakan swagger yang disediakan

BASE_URL: http://localhost:8080

Swagger : http://localhost:8080/swagger-ui/index.html#/

note: Service ini menggunakan JWT sebagai Authentication dan Authorization, dimohon untuk melakukan register dan login terlebih dahulu
untuk mendapatkan token agar dapat menggunakan API. Ketika melakukan register otomatis user akan mendapatkan role User. 
Update dan Delete product hanya bisa dilakukan oleh user yang memiliki role Admin

Service ini menyediakan Username dan Password Admin :

Username : admin

Password : admin123


Terima kasih.
