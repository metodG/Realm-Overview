package com.example.testrealm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
// Definicija modela

class User() : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
}