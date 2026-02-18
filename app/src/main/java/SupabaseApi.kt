import retrofit2.http.*
import retrofit2.Response
interface SupabaseApi {
    // Leggi tutte le ricette, ordinate per data di creazione
    @GET("rest/v1/ricette?order=created_at.desc")
    suspend fun getRicette(
        @Header("apikey") apiKey: String,
        @Header("Authorization") auth: String
    ): List<Ricetta>
    // Inserisci una nuova ricetta
    @POST("rest/v1/ricette")
    suspend fun insertRicetta(
        @Header("apikey") apiKey: String,
        @Header("Authorization") auth: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Prefer") prefer: String = "return=representation",
        @Body ricetta: Ricetta
    ): List<Ricetta>
    // Elimina una ricetta per id
    @DELETE("rest/v1/ricette")
    suspend fun deleteRicetta(
        @Header("apikey") apiKey: String,
        @Header("Authorization") auth: String,
        @Query("id") id: String // es: "eq.42"
    ): Response<Unit>
}