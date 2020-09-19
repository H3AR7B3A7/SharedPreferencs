package be.dog.d.steven.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isNightModeOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val sharedPrefsEdit:SharedPreferences.Editor=sharedPreferences.edit()

        loadData()

        save_btn.setOnClickListener{
            saveData()
        }

        theme_switch.setOnClickListener(View.OnClickListener{
            if (isNightModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("BOOLEAN_KEY",false)
                sharedPrefsEdit.apply()
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("BOOLEAN_KEY",true)
                sharedPrefsEdit.apply()
            }
        })

    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY",null)
        isNightModeOn = sharedPreferences.getBoolean("BOOLEAN_KEY",false)

        return_tv.text = savedString
        theme_switch.isChecked = isNightModeOn

        if (isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun saveData() {
        val insertedText= insert_et.text.toString()
        return_tv.text = insertedText

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", insertedText)
            putBoolean("BOOLEAN_KEY", theme_switch.isChecked)
        }.apply()

        Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show()
    }
}