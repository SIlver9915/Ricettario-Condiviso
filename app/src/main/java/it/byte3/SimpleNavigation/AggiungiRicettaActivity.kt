package it.byte3.SimpleNavigation

import Ricetta
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class AggiungiRicettaActivity : AppCompatActivity() {
    private lateinit var titoloEditText: TextInputEditText
    private lateinit var descEditText: TextInputEditText
    private lateinit var tempoEditText: TextInputEditText
    private lateinit var difficoltaGroup: RadioGroup
    private val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpydHVvZW52YXZ3eWhnenhkcmNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzE0MDAxNDAsImV4cCI6MjA4Njk3NjE0MH0.P4spnf5re14iuK5-mvrjdhtiSY2p4Xp5mg6B26WaFj4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportActionBar?.title = "AggiungiRicetta"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        titoloEditText = findViewById(R.id.titoloEditText)
        descEditText = findViewById(R.id.descEditText)
        tempoEditText = findViewById(R.id.tempoEditText)
        difficoltaGroup = findViewById(R.id.difficoltaGroup)

        findViewById<Button>(R.id.btnSalva).setOnClickListener {
            salvaRicetta()
        }


    }

    fun onAnnullaButtonClick(view: View){
        val intent = Intent(this, ListaRicetteActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun salvaRicetta() {
        val titolo = titoloEditText.text.toString().trim()
        val desc = descEditText.text.toString().trim()
        val tempo = tempoEditText.text.toString().trim()

        // Validazione
        var valid = true
        if (titolo.isEmpty()) {
            titoloEditText.error = "Campo obbligatorio"
            valid = false
        }
        if (desc.isEmpty()) {
            descEditText.error = "Campo obbligatorio"
            valid = false
        }
        if (tempo.isEmpty() || tempo.toIntOrNull() == null || tempo.toInt() <= 0) {
            tempoEditText.error = "Inserisci un numero maggiore di 0"
            valid = false
        }
        val rbSelezionato = difficoltaGroup.checkedRadioButtonId
        if (rbSelezionato == -1) {
            Toast.makeText(this, "Seleziona una difficoltà", Toast.LENGTH_SHORT).show()
            valid = false
        }

        if (!valid) return

        val difficoltaSelezionata = when (rbSelezionato) {
            R.id.rbFacile -> "Facile"
            R.id.rbMedia -> "Media"
            R.id.rbDifficile -> "Difficile"
            else -> ""
        }

        val nuovaRicetta = Ricetta(
            titolo = titolo,
            descrizione = desc,
            difficolta = difficoltaSelezionata,
            tempo_minuti = tempo.toInt()
        )

        // Invio a Supabase
        lifecycleScope.launch {
            try {
                RetrofitClient.api.insertRicetta(
                    apiKey = API_KEY,
                    auth = "Bearer $API_KEY",
                    ricetta = nuovaRicetta
                )
                Toast.makeText(this@AggiungiRicettaActivity, "Ricetta salvata!", Toast.LENGTH_SHORT).show()
                finish() // torna alla ListaRicetteActivity
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@AggiungiRicettaActivity, "Errore durante il salvataggio", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


