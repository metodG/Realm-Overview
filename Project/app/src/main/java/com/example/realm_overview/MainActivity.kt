package com.example.realm_overview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

//        //initialize realm
//        val config = RealmConfiguration.Builder(schema = setOf(User::class)).build()
//        val realm = Realm.open(config)


//        //write to realm
//        realm.writeBlocking {
//            copyToRealm(User().apply {
//                id = "1"
//                name = "Janez Novak"
//            })
//        }
//
//        // Pridobivanje vseh uporabnikov
//        val users = realm.query<User>().find()
//        users.forEach { user ->
//            println(user.name)
//        }
//
//        // Posodabljanje obstoječega uporabnika
//        realm.writeBlocking {
//            val user = query<User>("id == $0", "1").first().find()
//            user?.name = "Anton Kranjc"
//        }
//
//        // Brisanje uporabnika
//        realm.writeBlocking {
//            val user = query<User>("id == $0", "1").first().find()
//            user?.let { delete(it) }
//        }
        setContent {

        }
    }
}
