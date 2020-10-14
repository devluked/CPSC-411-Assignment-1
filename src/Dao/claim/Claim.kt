package org.csuf.cpsc411.Dao.claim

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

// Object definition, provides default arguments for user id and false isSolved.
data class Claim(var title:String?, var date:String?, var isSolved: Boolean? = false, var id : UUID = UUID.randomUUID())
