package com.example.realm_overview


import io.realm.kotlin.types.RealmObject


class User : RealmObject {
    var id: String = ""
    var name: String = ""
}