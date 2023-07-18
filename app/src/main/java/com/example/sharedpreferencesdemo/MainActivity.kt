package com.example.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreferencesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("myPrefs",MODE_PRIVATE)
      //Create
        create()

       //delete
        val editor = sharedPreferences.edit()
        editor.remove("username")
        editor.apply()

        //getShared pref
        val username =sharedPreferences.getString("username","no username exists!")
        binding.txtResult.text= username


    }
    private fun create(){
        //create
        val editor=sharedPreferences.edit()
        editor.putString("username","peterparker0712")
        editor.apply()
        Toast.makeText(applicationContext,"Shared pref Created",Toast.LENGTH_LONG).show()



        //check if first time to load
        var firstTime =sharedPreferences.getBoolean("firstTime",true)
        if(firstTime){
            //set to false
            val editor= sharedPreferences.edit()
            editor.putBoolean("firstTime",false)
            editor.apply()
            //go to screen
            val intent =Intent(this,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}