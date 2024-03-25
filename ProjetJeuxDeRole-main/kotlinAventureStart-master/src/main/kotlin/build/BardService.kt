import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL

class BardAPI {

    fun lance(prompt:String) {
        // Obtenez un jeton d'accès à l'API de Bard
        val token = "606154590396"

        // Créez une connexion HTTP
        val url = URL("https://bard.ai/v1/text/generate")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Authorization", "Bearer $token")
        connection.setRequestProperty("Content-Type", "application/json")

        // Envoyez une requête à l'API de Bard
        val body = "{\"prompt\": \"${prompt}\"}"
        val writer = PrintWriter(connection.outputStream)
        writer.print(body)
        writer.flush()

        // Obtenez la réponse de l'API de Bard
        val responseCode = connection.responseCode
        if (responseCode == 200) {
            // Le poème généré par Bard
            val reader = InputStreamReader(connection.inputStream)
            val poem: String = reader.readText()
            println(poem)
        } else {
            // Une erreur s'est produite
            println("Erreur : " + connection.responseMessage)
        }
    }
}