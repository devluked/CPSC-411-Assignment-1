package org.csuf.cpsc411

import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import com.google.gson.Gson
import io.ktor.utils.io.readAvailable
import org.csuf.cpsc411.Dao.claim.Claim
import org.csuf.cpsc411.Dao.claim.ClaimDao
import java.util.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    // extension
    // @annotation
    routing {
        this.post("/ClaimService/add") {
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            val str = String(output)

            // Json deserialization
            var cObj = Gson().fromJson<Claim>(str, Claim::class.java)
            val dao = ClaimDao().addClaim(cObj)

            // println("HTTP message is using POST method with /post ${contType} ${str}")
            call.respondText("The POST request was successfully processed. ",
                    status = HttpStatusCode.OK, contentType = ContentType.Text.Plain)
        }

        this.get("ClaimService/getAll") {
            val cList = ClaimDao().getAll()
            println("The number of claims: ${cList.size}")
            // JSON Serialization/Deserialization
            val respJsonStr = Gson().toJson(cList)
            call.respondText(respJsonStr, status = HttpStatusCode.OK, contentType = ContentType.Application.Json)
        }
    }


}