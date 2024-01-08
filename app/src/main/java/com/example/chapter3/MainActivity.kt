package com.example.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url("http://192.168.200.123:8080")
            .build()

        val callback = object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Client", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    Log.e("Client", "${response.body?.string()}")
                }
            }
        }

        val response = client.newCall(request).enqueue(callback)

        /*Thread {
            try {
                val socket = Socket("192.168.200.123", 8080)
                val printer = PrintWriter(socket.getOutputStream())
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                printer.println("GET / HTTP/1.1")
                printer.println("Host: 192.168.200.123:8080")
                printer.println("User-Agent: android")
                printer.println("\r\n")
                printer.flush()

                var input: String? = "-1"
                while(input != null) {
                    input = reader.readLine()
                    input?.let { Log.e("Client", it ) }
                }

                reader.close()
                printer.close()
                socket.close()
            }catch (e: IOException) {
                Log.e("Client", e.toString())
            }
        }.start()*/
    }
}