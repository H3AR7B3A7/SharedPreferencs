package be.dog.d.steven.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        save_btn.setOnClickListener{
            saveData()
        }
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY",null)
        val savedBoolean=sharedPreferences.getBoolean("BOOLEAN_KEY",false)

        return_tv.text = savedString
        bool_switch.isChecked = savedBoolean
    }

    private fun saveData() {
        val insertedText= insert_et.text.toString()
        return_tv.text = insertedText

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", insertedText)
            putBoolean("BOOLEAN_KEY", bool_switch.isChecked)
        }.apply()

        Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show()
    }
}