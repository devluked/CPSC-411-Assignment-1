package org.csuf.cpsc411.Dao.claim

import org.csuf.cpsc411.Dao.Dao
import org.csuf.cpsc411.Dao.Database
import java.util.*

class ClaimDao : Dao() {

    fun addClaim(cObj : Claim) {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "insert into claim (id, title, date, isSolved) values ('${UUID.randomUUID()}', '${cObj.title}', '${cObj.date}', '${false}')"

        // 3. submit the sql statement
        conn?.exec(sqlStmt)
    }

    fun getAll() : List<Claim> {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "select id, title, date, isSolved from claim"

        // 3. submit the sql statement
        var claimList : MutableList<Claim> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        // 4. Convert into Kotlin object format
        while (st!!.step()) {
            val id = st.columnString(0)
            val title = st.columnString(1)
            val date = st.columnString(2)
            val isSolved = st.columnNull(3)
            claimList.add(Claim(title, date))

        }
        return claimList
    }
}