package com.example.pardalmobile

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_profile_lab4.*

class ProfileActivity : AppCompatActivity() {

    companion object{
        private const val PROFILE_ACTIVITY = "PROFILE_ACTIVITY"
        private const val SHARED_PREFS = "sharedPrefs"
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_lab4)
        val intent = intent
        profileEmail.setText(intent.getStringExtra(SHARED_PREFS))
        imageButton.setOnClickListener{ dispatchTakePictureIntent()}
        goChat.setOnClickListener{ openChatActivity()}
    }

    private fun openChatActivity(){
        val nextPage = Intent(this, ChatActivity::class.java)
        startActivity(nextPage)
    }

    private fun dispatchTakePictureIntent(){
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imgBitmap = data?.extras?.get("data")
            if (imgBitmap is Bitmap)
                imageButton.setImageBitmap(imgBitmap)
        }
        Log.e(PROFILE_ACTIVITY, "In function: onActivityResult")
    }

    override fun onStart(){
        super.onStart()
        Log.e(PROFILE_ACTIVITY, "In function: onStart")
    }

    override fun onResume(){
        super.onResume()
        Log.e(PROFILE_ACTIVITY, "In function: onResume")
    }

    override fun onPause(){
        super.onPause()
        Log.e(PROFILE_ACTIVITY, "In function: onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.e(PROFILE_ACTIVITY, "In function: onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.e(PROFILE_ACTIVITY, "In function: onDestroy")
    }

}