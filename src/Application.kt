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
    // routing constructor takes only one parameter which is a lambda function
    routing{
        this.get("/ClaimService/add"){
            println("HTTP message is GET method with /get")
            val id : String? = call.request.queryParameters["id"] //UUID.randomUUID()
            val title : String? = call.request.queryParameters["title"]
            val date : String?= call.request.queryParameters["date"]
            val isSolved : String? = call.request.queryParameters["isSolved"]

            val response = String.format("ID is ${id}, title is ${title}, date is ${date}, isSolved is ${isSolved}", id, title, date, isSolved)
            //
            val cObj = Claim(title, date)
            val dao = ClaimDao().addClaim(cObj)
            //val dbObj = Database.getInstance()
            call.respondText(response, status= HttpStatusCode.OK, contentType= ContentType.Text.Plain)
            // http://localhost:8080/get?FirstName=Luke&LastName=Duggans
        }
        get("ClaimService/getAll") {
            val cList = ClaimDao().getAll()
            println("The number of claims: ${cList.size}")
            println(cList)
            // JSON Serialization/Deserialization
            val respJsonStr = Gson().toJson(cList)
            call.respondText(respJsonStr, status= HttpStatusCode.OK, contentType= ContentType.Application.Json)
        }

        this.post("/ClaimService/adc"){
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            val str = String(output)

            // JSON serialization/deserialization
            // GSON

            println("HTTP message is using POST method with /post ${contType} ${str}")
            call.respondText("The POST request was successfully processed. ")
        }
    }

}

