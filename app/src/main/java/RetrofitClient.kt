import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient {

    private const val BASE_URL = "https://jrtuoenvavwyhgzxdrcj.supabase.co/"
    //key eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpydHVvZW52YXZ3eWhnenhkcmNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzE0MDAxNDAsImV4cCI6MjA4Njk3NjE0MH0.
    // P4spnf5re14iuK5-mvrjdhtiSY2p4Xp5mg6B26WaFj4
    val api: SupabaseApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseApi::class.java)
    }
}