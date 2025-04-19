package com.example.smartdailycompanion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdailycompanion.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setGreeting()
        setQuote()

        binding.cardTodo.setOnClickListener {
            startActivity(Intent(this, TodoActivity::class.java))
        }

        binding.cardHabits.setOnClickListener {

        }

        binding.cardJournal.setOnClickListener {

        }

        binding.cardAdd.setOnClickListener {

        }
    }

    fun setGreeting(){
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour){
            in 5..11 -> "Good Morning!"
            in 12..16 -> "Good Afternoon!"
            in 17..20 -> "Good Evening!"
            else -> "Good Night!"
        }
        binding.tvGreeting.text = greeting
    }

    fun setQuote(){
        val request = Request.Builder().url("https://zenquotes.io/api/today").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{
                    binding.tvQuote.text = "Failed to get quote."
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let{ json ->
                    val quoteArray = JSONArray(json)
                    val quoteObj = quoteArray.getJSONObject(0)
                    val quote = quoteObj.getString("q")
                    val author = quoteObj.getString("a")
                    runOnUiThread{
                        binding.tvQuote.text = "“$quote”\n— $author"
                    }
                }
            }


        })
    }
}
