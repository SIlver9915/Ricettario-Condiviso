package it.byte3.SimpleNavigation
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Ricetta


class RicetteAdapter(
    private var listaRicette: MutableList<Ricetta>,
    private val onDeleteClick: (Ricetta) -> Unit
) : RecyclerView.Adapter<RicetteAdapter.RicettaViewHolder>() {


    class RicettaViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titolo: TextView = itemView.findViewById(R.id.textTitolo)
        val difficolta: TextView = itemView.findViewById(R.id.textDifficolta)
        val tempo: TextView = itemView.findViewById(R.id.textTempo)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RicettaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ricetta, parent, false)

        return RicettaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RicettaViewHolder,
        position: Int
    ) {
        val ricetta = listaRicette[position]

        holder.titolo.text = ricetta.titolo
        holder.difficolta.text = ricetta.difficolta
        holder.tempo.text = "${ricetta.tempo_minuti} min"

        when (ricetta.difficolta) {
            "Facile" -> holder.difficolta.setBackgroundColor(Color.GREEN)
            "Media" -> holder.difficolta.setBackgroundColor(Color.parseColor("#FFA500"))
            "Difficile" -> holder.difficolta.setBackgroundColor(Color.RED)
        }
        holder.btnDelete.setOnClickListener {
            onDeleteClick(ricetta) // qui passa l’oggetto Ricetta all’Activity
        }
    }

    override fun getItemCount(): Int {
        return listaRicette.size
    }
    fun updateData(nuovaLista: List<Ricetta>) {
        listaRicette.clear()
        listaRicette.addAll(nuovaLista)
        notifyDataSetChanged()
    }
}


