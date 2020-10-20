package com.mayburger.gojekuiclone.util

import android.annotation.SuppressLint
import android.content.Context
import java.util.*


class StringProvider(private var context: Context?) {


    private var mStringMap: HashMap<String, String>? = null

    init {
//        initRawString()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var ins: StringProvider? = null

        @Synchronized
        fun getInstance(): StringProvider {
            return ins!!
        }

        fun init(context: Context) {
            ins = StringProvider(context)
        }
    }

    fun getString(key: String): String {
//        val jsonString = context?.resources?.getIdentifier("locale_", "raw", context?.packageName)?.let {
//            context?.resources?.openRawResource(it)?.bufferedReader().use { it?.readText() }
//        }
//        val jsonElement = Gson().fromJson(jsonString, JsonElement::class.java)
//        val json = jsonElement.asJsonObject
//        for (entry in json.entrySet()) {
//            if (entry.key == key) {
//                return entry.value.asString
//            }
//        }
        return ""
    }
//
//    fun initRawString(): String {
//        return try {
//            val jsonString = context?.resources?.openRawResource(R.raw.locale_id)
//                    ?.bufferedReader().use { it?.readText() }
//            val jsonElement = Gson().fromJson<JsonElement>(jsonString, JsonElement::class.java)
//            val json = jsonElement.asJsonObject
//            for (entry in json.entrySet()) {
//            }
//            ""
//        } catch (e: Exception) {
//            e.printStackTrace()
//            ""
//        }
//    }

}
