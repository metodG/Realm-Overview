# Realm

Realm je napredna knjižnica za lokalno shranjevanje podatkov, ki je zasnovana za preprosto uporabo, hiter dostop in brezhibno delovanje na mobilnih platformah, kot sta Android in iOS. Spodaj so vključene ključne informacije o Realm-u, razlogi za njegovo uporabo, prednosti in slabosti, ter informacije o vzdrževanju projekta.

---

## Zakaj izbrati Realm?
Realm je priljubljen zaradi naslednjih razlogov:
- **Hitrost**: Deluje hitreje kot tradicionalne rešitve, kot sta SQLite in Room.
- **Preprosta integracija**: Nudi enostavne API-je, ki zmanjšujejo kompleksnost dela s podatki.
- **Platformska podpora**: Podpira različne platforme, vključno z Android, iOS in .NET.
- **Real-Time Sinhronizacija**: Vključuje možnost sinhronizacije podatkov med napravami (premium funkcionalnost).

---

## Prednosti
- **Preprost objektni model**: Delo neposredno z objekti brez potrebe po pretvarjanju v druge strukture.
- **Visoka zmogljivost**: Hitri vpisi in poizvedbe v primerjavi s tradicionalnimi bazami podatkov.
- **Podpora za več platform**: Uporabno na različnih ekosistemih.
- **Enostavno vzdrževanje**: Manj kode za implementacijo in bolj čitljiva koda.

## Slabosti
- **Zaprt del kode**: Funkcije, kot je Realm Sync, niso del odprtokodne različice.
- **Odvisnost od knjižnice**: Prehod na drugo rešitev je lahko zahteven.
- **Večja velikost aplikacije**: Dodajanje Realm-a lahko poveča velikost aplikacije.

---

## Licenca
Realm je na voljo pod licenco **Apache License 2.0**, kar pomeni, da je odprtokoden in brezplačen za uporabo. Premium funkcije, kot je sinhronizacija podatkov v realnem času, so na voljo kot plačljive storitve.

---

## Statistika in vzdrževanje
- **Število uporabnikov**: Realm ima več milijonov uporabnikov po vsem svetu.
- **Število razvijalcev**: K projektu prispeva več kot 100 razvijalcev.
- **Zadnji popravek**: Projekt je redno vzdrževan, z zadnjimi spremembami objavljenimi pred nekaj tedni (preverite na [uradnem GitHub repozitoriju](https://github.com/realm/realm-java)).
- **Skupnost**: Aktivna skupnost s številnimi prispevki in podporo.

---

## Namestitev
Za dodajanje Realm-a v vaš Android projekt:

```gradle
dependencies {
    implementation 'io.realm:realm-gradle-plugin:<latest_version>'
}
```

Za iOS projekt:

```swift
pod 'RealmSwift', '~> <latest_version>'
```

---

## Dokumentacija in viri
- [Uradna dokumentacija Realm](https://www.mongodb.com/docs/realm/)
- [GitHub repozitorij](https://github.com/realm/realm-java)

---

Realm je odlična izbira za projekte, ki zahtevajo hitro, preprosto in zmogljivo lokalno shranjevanje podatkov, vendar zahteva previdno načrtovanje, če ga želite uporabljati za naprednejše funkcionalnosti.
