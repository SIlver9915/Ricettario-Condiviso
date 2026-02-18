package it.byte3.SimpleNavigation


import Ricetta
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlin.collections.mutableListOf

class ListaRicetteActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RicetteAdapter
    private val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpydHVvZW52YXZ3eWhnenhkcmNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzE0MDAxNDAsImV4cCI6MjA4Njk3NjE0MH0.P4spnf5re14iuK5-mvrjdhtiSY2p4Xp5mg6B26WaFj4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ricette)
        recyclerView = findViewById(R.id.recyclerViewRicette)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RicetteAdapter(mutableListOf()) { ricetta ->
            eliminaRicetta(ricetta) // callback al click
        }
        recyclerView.adapter = adapter
        supportActionBar?.title = "Ricettario Condiviso"

        caricaRicette()

    }

    fun onNavigateButtonClick(view: View) {
        val intent = Intent(this, AggiungiRicettaActivity::class.java)
        startActivity(intent)
    }

    private fun caricaRicette() {
        lifecycleScope.launch {
            try {
                val ricette = RetrofitClient.api.getRicette(
                    apiKey = API_KEY,
                    auth = "Bearer $API_KEY"
                )
                // Aggiorna la RecyclerView con i dati ricevuti
                adapter.updateData(ricette)
            } catch (e: Exception) {
                // Gestisci l'errore (es. mostra un Toast)
                e.printStackTrace()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        caricaRicette()
    }


    private fun eliminaRicetta(ricetta: Ricetta) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.deleteRicetta(
                    apiKey = API_KEY,
                    auth = "Bearer $API_KEY",
                    id = "eq.${ricetta.id}"
                )

                if (response.isSuccessful) {
                    caricaRicette() // ✅ tienila
                    Toast.makeText(this@ListaRicetteActivity, "Ricetta eliminata", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ListaRicetteActivity, "Errore eliminazione", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@ListaRicetteActivity, "Errore di rete", Toast.LENGTH_SHORT).show()
            }
        }
    }



}