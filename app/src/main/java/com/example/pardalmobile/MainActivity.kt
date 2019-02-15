package com.example.pardalmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_lab4.*

class MainActivity : AppCompatActivity() {

    companion object{
        private const val SHARED_PREFS = "sharedPrefs"
        private const val TEXT = "text"
    }

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lab4)
        sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        login.setOnClickListener{ openProfileActivity()}
        loadData()
    }

    private fun openProfileActivity(){
        val nextPage = Intent(this, ProfileActivity::class.java)
        nextPage.putExtra(SHARED_PREFS , email.text.toString())
        startActivity(nextPage)
    }

    private fun saveData(){
        val editor = sp.edit()
        editor.putString(TEXT, email.text.toString())
        editor.apply()
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData(){
        email.setText(sp.getString(TEXT, ""))
    }

    override fun onPause(){
        super.onPause()
        saveData()
    }

}