package com.example.testrealm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.InitialRealm
import io.realm.kotlin.notifications.RealmChange
import io.realm.kotlin.notifications.UpdatedRealm


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val config = RealmConfiguration.Builder(schema = setOf(User::class)).build()
        val realm = Realm.open(config)

        //         Shranjevanje podatkov
        realm.writeBlocking {
            copyToRealm(User().apply {
                id = "1"
                name = "Janez Novak"
            })
        }
        // Pridobivanje vseh uporabnikov
        println("aaaaaaa")
        val users = realm.query<User>().find()
        users.forEach { user ->
            println(user.name)
        }
        // Posodabljanje obstoječega uporabnika
        realm.writeBlocking {
            val user = query<User>("id == $0", "1").first().find()
            user?.name = "Anton Kranjc"
        }
        // Brisanje uporabnika
        realm.writeBlocking {
            val user = query<User>("id == $0", "1").first().find()
            user?.let { delete(it) }
        }
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}