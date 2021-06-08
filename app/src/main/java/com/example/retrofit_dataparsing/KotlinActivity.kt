package com.example.retrofit_dataparsing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit_dataparsing.utils.DistrictModel
import kotlin.collections.ArrayList

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val countryList = ArrayList<DistrictModel>()
        val list = ArrayList<String>()
        list.add("Asad1")
        list.add("Asad2")
        list.add("Asad3")
        list.add("Asad4")
        list.add("Asad5")
        for (i in list.indices) {
            println("my name: " + list[i])
        }



    }

    fun MyIn(number:Int) = number in 1..3
}