package com.example.myapplication

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class CompanyEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "comprice"
            val COLUMN_ID = "id"
            val COLUMN_COMPANY = "company"
            val PRICE = "price"
        }
    }
}
