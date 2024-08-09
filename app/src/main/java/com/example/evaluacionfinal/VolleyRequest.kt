import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class VolleyRequest(context: Context) {
    private val queue = Volley.newRequestQueue(context)
    private val url = "http://localhost/php/consultas.php" // Modifica la URL según la ubicación de tu servidor local

    fun fetchDataFromDatabase(onSuccess: (JSONArray) -> Unit, onError: () -> Unit) {
        val request = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                onSuccess(response)
            },
            Response.ErrorListener {
                onError()
            }
        )

        queue.add(request)
    }
}