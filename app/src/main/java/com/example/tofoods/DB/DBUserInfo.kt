package com.example.tofoods.DB

import android.provider.BaseColumns

object DBUserInfo {
    class UserTable : BaseColumns {
        companion object {
            val TABLE_NAME = "name"
            val COL_FULLNAME = "fullname"
            val COL_EMAIL = "email"
            val COL_NOHP = "nohp"
            val COL_PASS = "pass"
            val COL_JUMLAH = "jumlah"
        }
    }
}