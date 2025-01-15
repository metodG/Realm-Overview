<picture>
    <source srcset="logo.png" media="(prefers-color-scheme: dark)" alt="realm by MongoDB">
    <img src="logo.png" alt="realm by MongoDB">
</picture>

# Realm

Realm je knjižnica, ki je namenjena lokalnemu shranjevanju. Namen knjižnice je ustvariti lokalno podatkovno bazo, ki se jo potem uporablja kot vsako ostalo podatkovno bazo. Realm je odprtokodna objektna podatkovna baza, ki je v lasti Mongo Db. Leta 2024 septembra je Mongo DB napovedal opusčanja Realm podatkovne baze in zastaranje ter odstranitev njenih plačlivih storitev z koncem septembra leta 2025.

## ![Icon](https://img.icons8.com/ios/20/000000/why-quest.png) Zakaj izbrati Realm?
Realm je priljubljen zaradi naslednjih razlogov:
- **Hitrost**: Deluje hitreje kot tradicionalne rešitve, kot sta SQLite in Room.
- **Lokalna uporaba** : Deluje brez interneta, v primerjavi z nekaterimi oblačnimi rešitvami.
- **Preprostost uporabe** : Je objektno orientirana baza, ne potrejemo ORM

## ![Icon](https://img.icons8.com/ios/20/000000/positive-dynamic.png) Prednosti
- **Preprost objektni model**: Delo neposredno z objekti brez potrebe po pretvarjanju v druge strukture.
- **Visoka zmogljivost**: Hitri vpisi in poizvedbe v primerjavi s tradicionalnimi bazami podatkov.
- **Podpora za več platform**: Uporabno na različnih ekosistemih.
- **Enostavno vzdrževanje**: Manj kode za implementacijo in bolj čitljiva koda.

## ![Icon](https://img.icons8.com/ios/20/000000/negative-dynamic.png) Slabosti
- **Zaprt del kode**: Funkcije, kot je Realm Sync, niso del odprtokodne različice.
- **Zastarnje plačlivih funkcij**: Mongo DB podjetje se je odločilo, da neha aktivno razvijati Realm.
- **Minimalni maintence** : Zaradi Atlas Sync zastaranja se je vzdrževanje z strani Mongo DB ustavilo
- **Nepodprost različič Kotlina** : Ne podpira vseh verzij Kotlina

## ![Icon](https://img.icons8.com/ios/20/000000/certificate.png) Licenca
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
- [GitHub repozitorij](https://github.com/realm/realm-kotlin)

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

## ![Icon](https://img.icons8.com/ios/20/000000/example.png) Primeri opazovanja podatkov

### Opazovanje sprememb na celotni bazi (Realm asFlow)
Realm omogoča spremljanje globalnih sprememb v podatkovni bazi:
```kotlin
realm.asFlow()
    .collect { realmChange: RealmChange<Realm> ->
        when (realmChange) {
            is InitialRealm -> println("realm je bil inicializiran")
            is UpdatedRealm -> println("Realm je bil posodobljen")
        }
    }
```

### Opazovanje sprememb na posameznem objektu (RealmObject asFlow)
Spremljanje sprememb na specifičnih objektih:
```kotlin
person.asFlow().collect { objChange: ObjectChange<User> ->
    when (objChange) {
        is InitialObject -> println("Objekt Inicializiran: ${objChange.obj.name}")
        is UpdatedObject -> println("Posodobil polja : ${objChange.changedFields}")
        is DeletedObject -> println("Objekt odstranje")
    }
}
```

### Opazovanje sprememb na seznamu (RealmList asFlow)
Opazovanje dodanih, spremenjenih ali izbrisanih elementov v seznamu:
```kotlin
person.addresses.asFlow()
    .collect { listChange: ListChange<List<String>> ->
        when (listChange) {
            is InitialList -> println("Začetno stanje seznama: ${listChange.list.size}")
            is UpdatedList -> println("Posodobljeno stranje seznama: ${listChange.insertions}")
            is DeletedList -> println("Odstranjen seznam: ${listChange.deletions}")
        }
    }
```

### Opazovanje rezultatov poizvedb (RealmQuery asFlow)
Spremljanje sprememb rezultatov poizvedb:
```kotlin
realm.query<USer>("age > $0", 30).asFlow()
    .collect { resultChange ->
        println("Rezultati poizvedbe: ${resultChange.results}")
    }
```

### Opazovanje enega elementa (RealmSingleQuery)
Spremljanje sprememb specifičnega elementa:
```kotlin
realm.query<User>("name == $0", "Janez").first().asFlow()
    .collect { objChange ->
        when (objChange) {
            is InitialObject -> println("Inicializacija stanja: ${objChange.obj?.name}")
            is UpdatedObject -> println("Posodobi polja na objektu: ${objChange.changedFields}")
            is DeletedObject -> println("Objekt izbrisan")
        }
    }
```

Te funkcionalnosti omogočajo učinkovito in dinamično delo s podatki v realnem času, kar je eden izmed ključnih razlogov za uporabo knjižnice Realm.

## ![Icon](https://img.icons8.com/ios/20/000000/more.png) Dodatne funkcionalnosti
- **Sinhronizacija podatkov (Realm Sync)**: Samodejna sinhronizacija podatkov med lokalno bazo in oblakom.
- **Podpora za več niti**: Varno delo z bazo podatkov v več niti.
- **Šifriranje podatkov**: Omogoča varno hranjenje občutljivih podatkov.
- **Podpora za platformo Kotlin Multiplatform (KMM)**: Enotna baza podatkov za Android in iOS.
- **Transakcije**: Zagotavljanje atomskosti in konsistence operacij.
- **Napredne poizvedbe**: Kompleksne poizvedbe s podporo za logične operatorje in primerjave.
- **Podpora za migracije podatkov**: Enostavno posodabljanje strukture modelov.
- **Podpora za reaktivne tokove (Reaktive)**: Integracija z orodji, kot so RxJava, Flow, in LiveData.

## Prikaz na primeru iz vaj

- **Dodajanje objekta v lokalno bazo v primeru, da smo brez interneta**

  ```kotlin
   class RealmBeehouse() : RealmObject{
    @PrimaryKey var beeHouseUUID: String = ""
    var name: String = ""
    var coordinatesX: Double = 0.0
    var coordinatesY: Double = 0.0
    var numOfHives: Int = 0
  }
  ```
  
 ```kotlin
    if (isInternetAvailable()) {
            // Save to Firebase
        } else {
            // Save to Realm
            val config = RealmConfiguration.Builder(schema = setOf(RealmBeehouse::class)).build()
            val realm = Realm.open(config)
            realm.writeBlocking  {
                copyToRealm(RealmBeehouse().apply{
                    beeHouseUUID = beeHouse.beeHouseUUID
                    name = beeHouse.name
                    coordinatesX = beeHouse.coordinatesX
                    coordinatesY = beeHouse.coordinatesY
                    numOfHives = beeHouse.numOfHives
                })
            }
            Toast.makeText(requireContext(), "No internet. BeeHouse saved locally.", Toast.LENGTH_SHORT).show()
            clearFields()
        }
  ```
![image](https://github.com/user-attachments/assets/4b45516d-fbf2-4a83-92b4-eba70e66c4b2)

- **Sinhronizacija ko se internet vrne**

  ```kotlin 
    fun syncLocalDataToFirebase() {
            val config = RealmConfiguration.Builder(schema = setOf(RealmBeehouse::class)).build()
            val realm = Realm.open(config)
            val unsyncedBeeHouses = realm.query<RealmBeehouse>().find()
    
            if (unsyncedBeeHouses.isNotEmpty()) {
                val database = FirebaseDatabase.getInstance()
                val beeHouseRef = database.getReference("beeHouses")
                unsyncedBeeHouses.forEach { beeHouse ->
                    val notRealmBeehouse = BeeHouse(
                        beeHouse.beeHouseUUID,
                        beeHouse.name,
                        beeHouse.coordinatesX,
                        beeHouse.coordinatesY,
                        beeHouse.numOfHives
                    )
                    beeHouseRef.push().setValue(notRealmBeehouse)
                        .addOnSuccessListener {
                            // Brisanje uporabnika
                            Toast.makeText(this, "Beehousese synced with cloud", Toast.LENGTH_SHORT).show()
                            println("Beehousese synced with clod")
                            realm.writeBlocking {
                                val user = query<RealmBeehouse>("beeHouseUUID == $0", beeHouse.beeHouseUUID).first().find()
                                user?.let { delete(it) }
                            }
                            Toast.makeText(this, "Beehousese synced with cloud", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            }
  ```

![image](https://github.com/user-attachments/assets/073e0559-a610-4959-a3ce-8b7b7506f68a)




