package org.csuf.cpsc411.Dao.claim

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

// Object definition, provides default arguments for user id and false isSolved.
data class Claim(var title:String?, var date:String?, var isSolved: Boolean? = false, var id : UUID = UUID.randomUUID())

// var id : String? = UUID.randomUUID().toString()

fun main() {
    /*
    // JSON serialization
    val cObj = Claim("House test", "11-21-99")
    val jsonStr = Gson().toJson(cObj)
    println("The converted JSON string : ${jsonStr}")

   // Serialization of List<Claim>
    var cList : MutableList<Claim> = mutableListOf()
    cList.add(cObj)
    cList.add(Claim("Condo test", "9-29-01"))
    val listJsonString = Gson().toJson(cList)
    println("${listJsonString}")

    // List<Person> object deserialization
    val personListType : Type = object: TypeToken<Claim>(){}.type

    // JSON Deserialization
    val listJsonStr = Gson()
    val cObj1 : Claim
    cObj1 = Gson().fromJson(jsonStr, Claim::class.java)
    println("${cObj1.toString()}")

*/

}