package it.byte3.SimpleNavigation

import androidx.recyclerview.widget.RecyclerView

class RicetteAdapter : RecyclerView.Adapter<RicetteAdapter.RicettaViewHolder>() {

    private var listaRicette: List<Ricetta> = listOf()

    fun updateData(nuovaLista: List<Ricetta>) {
        listaRicette = nuovaLista
        notifyDataSetChanged()
    }

    inner class RicettaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titolo: TextView = itemView.findViewById(R.id.textTitolo)
        val difficolta: TextView = itemView.findViewById(R.id.textDifficolta)
        val tempo: TextView = itemView.findViewById(R.id.textTempo)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RicettaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ricetta, parent, false)
        return RicettaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RicettaViewHolder, position: Int) {
        val ricetta = listaRicette[position]

        holder.titolo.text = ricetta.titolo
        holder.difficolta.text = ricetta.difficolta
        holder.tempo.text = "${ricetta.tempo} min"

        when (ricetta.difficolta) {
            "Facile" -> holder.difficolta.setBackgroundColor(Color.GREEN)
            "Media" -> holder.difficolta.setBackgroundColor(Color.parseColor("#FFA500"))
            "Difficile" -> holder.difficolta.setBackgroundColor(Color.RED)
        }
    }

    override fun getItemCount(): Int = listaRicette.size
}