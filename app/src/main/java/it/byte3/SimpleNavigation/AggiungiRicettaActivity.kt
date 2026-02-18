package it.byte3.SimpleNavigation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

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

//        findViewById<Button>(R.id.btnSalva).setOnClickListener {
//            salvaRicetta()
//        }


    }

    fun onAnnullaButtonClick(view: View){
        val intent = Intent(this, ListaRicetteActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}

