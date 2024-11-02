# ComposeCryptoApp
Jetpack Compose ile hazırlanmış bir kripto para takip uygulaması. Bu proje, modern Android geliştirme pratiklerini kullanarak dinamik ve kullanıcı dostu bir arayüz oluşturmayı hedeflemektedir. Proje kapsamında, veri akışını yönetmek ve bağımlılıkları enjekte etmek için çeşitli kütüphaneler entegre edilmiştir.

## Özellikler
- Kripto Para Fiyatları: Kripto para fiyatları ve detayları görüntülenebilir.
- Arama Özelliği: Kripto paralar arasında kolayca arama yapabilir ve filtreleyebilirsiniz.
- Görsel Yükleme: Kripto para logoları hızlı ve optimize bir şekilde yüklenir.
- Ağ Bağlantısı Entegrasyonu: REST API çağrıları için Retrofit kullanılarak veriler yapılandırılmış ve güvenilir bir şekilde çekilir.
- Bağımlılık Enjeksiyonu: Hilt kullanılarak bağımlılıklar yönetilir ve test edilebilir bir kod yapısı sağlanır.
- Asenkron Veri Akışı: Coroutines ile asenkron görevler yönetilir, böylece uygulama akıcı ve hızlı çalışır.
- Yaşam Döngüsü Yönetimi: Lifecycle-aware yapılar ile coroutines daha etkili bir şekilde yönetilir.

 ## Kullanılan Teknolojiler
- Retrofit: REST API çağrıları için kullanılan bir ağ bağlantısı kütüphanesi. Veri çekme işlemleri yapılandırılmış ve güvenilir bir şekilde yapılır.
- Navigation: Ekranlar arası geçişleri ve dinamik argümanları yönetmek için yapılandırılmış bir Navigation yapısı.
- Kotlin Coroutines: Asenkron programlama için kullanılır. Ağ bağlantıları gibi uzun süreli işlemler arka planda gerçekleştirilerek kullanıcı deneyimi iyileştirilir.
- Lifecycle Scopes: ViewModel yaşam döngüsüne bağlı olarak coroutine işlemlerinin daha etkin yönetilmesini sağlar.
- Coil: Görsellerin hızlı ve verimli bir şekilde yüklenmesi için kullanılan bir kütüphane. Jetpack Compose ile uyumludur.- Hilt: Uygulama boyunca kullanılacak nesnelerin bağımlılıklarını yönetmek için kullanılan bir bağımlılık enjeksiyonu kütüphanesi. Bu, kodun daha temiz ve test edilebilir olmasını sağlar.

## Öğrenim Hedeflerim
Bu projede, aşağıdaki konuları öğrenmeyi ve pekiştirmeyi amaçladım:

- Jetpack Compose ile Derinlemesine UI Geliştirme: Tamamen Compose tabanlı bir arayüz inşa etmek, UI durumunu ve yaşam döngüsünü yönetmek.
- Modern Android Mimarisi Uygulamak: ViewModel ve Hilt gibi yapı taşlarını kullanarak daha ayrıştırılmış ve temiz bir kod yapısına sahip olmak.
- Asenkron Veri İşlemlerinde Yetkinlik Kazanmak: Kotlin Coroutines kullanarak bloklamayan ağ bağlantıları gerçekleştirmek, böylece uygulamanın hızını ve performansını artırmak.
- Bağımlılık Yönetiminde Hilt Kullanımı: Hilt ile ölçeklenebilir ve test edilebilir bir kod tabanı oluşturmak.
- Coil ile Görsel Yükleme Deneyimi Kazanmak: Coil kullanarak görsellerin hızlı ve verimli bir şekilde yüklenmesini sağlamak.

<table>
   <tr>
    <td><img src="https://github.com/user-attachments/assets/94614915-9b02-4ec3-b067-acab33d6bdf4" width="290"></td>
    <td>&nbsp;</td>
    <td><img src="https://github.com/user-attachments/assets/0f6d3185-3f5a-41b2-b38f-03a4d9c2ec85" width="290"></td>
     <td>&nbsp;</td>
    <td><img src="https://github.com/user-attachments/assets/04afae09-d45d-42cf-9d64-71682e576153" width="290"></td>
  </tr>
</table>
