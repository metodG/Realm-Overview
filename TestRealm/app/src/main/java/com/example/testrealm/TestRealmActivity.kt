package com.example.testrealm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.*
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.collect

class TestRealmActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Realm
        val config = RealmConfiguration.Builder(schema = setOf(User::class)).build()
        val realm = Realm.open(config)

        // Test observing the entire Realm
        lifecycleScope.launchWhenStarted {
            realm.asFlow().collect { realmChange: RealmChange<Realm> ->
                when (realmChange) {
                    is InitialRealm<*> -> println("Initial Realm")
                    is UpdatedRealm<*> -> println("Realm updated")
                }
            }
        }

        // Test observing a RealmObject
        val person = realm.query<Person>().first().find()
        person?.let {
            lifecycleScope.launchWhenStarted {
                it.asFlow().collect { objectChange ->
                    when (objectChange) {
                        is InitialObject -> println("Initial object: ${objectChange.obj.name}")
                        is UpdatedObject -> println("Updated object: ${objectChange.obj.name}, changed fields: ${objectChange.changedFields.size}")
                        is DeletedObject -> println("Deleted object")
                    }
                }
            }
        }

        // Test observing a RealmList

        // Test observing a RealmQuery
        lifecycleScope.launchWhenStarted {
            realm.query<Person>().asFlow().collect { resultsChange ->
                when (resultsChange) {
                    is InitialResults -> println("Initial results size: ${resultsChange.list.size}")
                    is UpdatedResults -> println("Updated results size: ${resultsChange.list.size}, insertions: ${resultsChange.insertions.size}")
                }
            }
        }

        // Test observing a RealmSingleQuery
        lifecycleScope.launchWhenStarted {
            realm.query<Person>("name = $0", "Carlo").first().asFlow().collect { objectChange ->
                when (objectChange) {
                    is PendingObject -> println("Pending object")
                    is InitialObject -> println("Initial object: ${objectChange.obj.name}")
                    is UpdatedObject -> println("Updated object: ${objectChange.obj.name}, changed fields: ${objectChange.changedFields.size}")
                    is DeletedObject -> println("Deleted object")
                }
            }
        }
    }
}
