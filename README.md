<picture>
    <source srcset="./images/logo-dark.svg" media="(prefers-color-scheme: dark)" alt="realm by MongoDB">
    <img src="./images/logo.svg" alt="realm by MongoDB">
</picture>

# Realm

Realm je knjižnica, ki je namenjena lokalnemu shranjevanju. Namen knjižnice je ustvariti lokalno podatkovno bazo, ki se jo potem uporablja kot vsako ostalo podatkovno bazo. Realm je odprtokodna objektna podatkovna baza, ki je v lasti Mongo Db. Leta 2024 septembra je Mongo DB napovedal opusčanja Realm podatkovne baze in zastaranje ter odstranitev njenih plačlivih storitev z koncem septembra leta 2025.

## ![Icon](https://img.icons8.com/ios/20/000000/why.png) Zakaj izbrati Realm?
Realm je priljubljen zaradi naslednjih razlogov:
- **Hitrost**: Deluje hitreje kot tradicionalne rešitve, kot sta SQLite in Room.
- **Lokalna uporaba** : Deluje brez interneta, v primerjavi z nekaterimi oblačnimi rešitvami.

## ![Icon](https://img.icons8.com/ios/20/000000/positive-dynamic.png) Prednosti
- **Preprost objektni model**: Delo neposredno z objekti brez potrebe po pretvarjanju v druge strukture.
- **Visoka zmogljivost**: Hitri vpisi in poizvedbe v primerjavi s tradicionalnimi bazami podatkov.
- **Podpora za več platform**: Uporabno na različnih ekosistemih.
- **Enostavno vzdrževanje**: Manj kode za implementacijo in bolj čitljiva koda.

## ![Icon](https://img.icons8.com/ios/20/000000/negative-dynamic.png) Slabosti
- **Zaprt del kode**: Funkcije, kot je Realm Sync, niso del odprtokodne različice.
- **Odvisnost od knjižnice**: Prehod na drugo rešitev je lahko zahteven.
- **Večja velikost aplikacije**: Dodajanje Realm-a lahko poveča velikost aplikacije.
- **Zastarnje plačlivih funkcij**: Mongo DB podjetje se je odločilo, da neha aktivno razvijati Realm.

## ![Icon](https://img.icons8.com/ios/20/000000/license.png) Licenca
Realm je na voljo pod licenco **Apache License 2.0**, kar pomeni, da je odprtokoden in brezplačen za uporabo. Premium funkcije, kot je sinhronizacija podatkov v realnem času, so na voljo kot plačljive storitve.

## ![Icon](https://img.icons8.com/ios/20/000000/maintenance.png) Statistika in vzdrževanje

![GitHub last commit](https://img.shields.io/github/last-commit/realm/realm-kotlin)

![GitHub commit activity](https://img.shields.io/github/commit-activity/m/realm/realm-kotlin)

![GitHub contributors](https://img.shields.io/github/contributors/realm/realm-kotlin)

## ![Icon](https://img.icons8.com/ios/20/000000/star.png) Število zvezdic in forkov

| **Stars**      | **Forks**   |
|-----------------|-------------|
| ![Stars](https://img.shields.io/github/stars/realm/realm-kotlin) | ![Forks](https://img.shields.io/github/forks/realm/realm-kotlin) |

## ![Icon](https://img.icons8.com/ios/20/000000/book.png) Dokumentacija in viri
- [Uradna dokumentacija Realm](https://www.mongodb.com/docs/realm/)
- [GitHub repozitorij](https://github.com/realm/realm-java)

## ![Icon](https://img.icons8.com/ios/20/000000/code.png) Primeri osnovnih funkcionalnosti

### Ustvarjanje objekta in shranjevanje podatkov
```kotlin
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

// Definicija modela
class User : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
}

// Inicializacija
val config = RealmConfiguration.Builder(schema = setOf(User::class)).build()
val realm = Realm.open(config)

// Shranjevanje podatkov
realm.writeBlocking {
    copyToRealm(User().apply {
        id = "1"
        name = "Janez Novak"
    })
}
```

### Pridobivanje podatkov
```kotlin
// Pridobivanje vseh uporabnikov
val users = realm.query<User>().find()
users.forEach { user ->
    println(user.name)
}
```

### Posodabljanje podatkov
```kotlin
// Posodabljanje obstoječega uporabnika
realm.writeBlocking {
    val user = query<User>("id == $0", "1").first().find()
    user?.name = "Anton Kranjc"
}
```

### Brisanje podatkov
```kotlin
// Brisanje uporabnika
realm.writeBlocking {
    val user = query<User>("id == $0", "1").first().find()
    user?.let { delete(it) }
}
```
