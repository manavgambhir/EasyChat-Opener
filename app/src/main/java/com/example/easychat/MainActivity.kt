package com.example.easychat

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var num:String="0"
        if(intent.action==Intent.ACTION_PROCESS_TEXT){
            num=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(num.isDigitsOnly()){
            startWhatsapp(num);
        }
        else{
            Toast.makeText(this,"Please Enter a valid Number",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWhatsapp(num: String) {
        var intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data:String = if(num[0]=='+'){
            num.substring(1)
        }
        else if(num.length==10){
            "91" + num
        }
        else{
            num
        }
        intent.data = Uri.parse("https://wa.me/$data")
//        if(packageManager.resolveActivity(intent,0)!=null){
//            startActivity(intent)
//        }else{
//            Toast.makeText(this,"Please install Whatsapp and try again!!",Toast.LENGTH_SHORT).show()
//        }
        try{
            startActivity(intent);
        }catch (e: ActivityNotFoundException){
            Toast.makeText(this,"Please install Whatsapp and try again!!",Toast.LENGTH_SHORT).show()
        }
//        finish()

//        try {
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
//        }
    }
}