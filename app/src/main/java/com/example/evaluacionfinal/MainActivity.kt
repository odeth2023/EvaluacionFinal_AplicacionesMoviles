package com.example.evaluacionfinal

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue
    private lateinit var textViewData: TextView
    private val url = "http://192.168.0.188/php/consultas.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa la cola de solicitudes
        requestQueue = Volley.newRequestQueue(this)

        // Inicializa el TextView
        textViewData = findViewById(R.id.textViewData)

        // Realiza la solicitud GET
        fetchUserData()
    }

    private fun fetchUserData() {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                try {
                    val builder = StringBuilder()
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val usuario = jsonObject.getString("usuario")
                        val password = jsonObject.getString("password")
                        builder.append("Usuario: $usuario, Password: $password\n")
                    }
                    textViewData.text = builder.toString() // Muestra los datos en el TextView
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                textViewData.text = "Error: ${error.message}"
            }
        )

        // Añadir la solicitud a la cola
        requestQueue.add(jsonArrayRequest)
    }
}
