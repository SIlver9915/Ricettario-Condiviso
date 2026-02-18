data class Ricetta(
    val id: Long? = null,
    val titolo: String,
    val descrizione: String,
    val difficolta: String,
    val tempo_minuti: Int,
    val created_at: String? = null
)