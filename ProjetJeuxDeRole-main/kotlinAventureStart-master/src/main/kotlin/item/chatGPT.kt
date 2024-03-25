import java.net.InetSocketAddress
import java.net.ProxySelector
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import com.google.gson.*

class GPTService {
    val apiKey = "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"
    fun fetchHistoire(prompt: String):String {
        val gpt3Endpoint = "https://api.openai.com/v1/chat/completions"
        val proxyHost = "172.16.0.51";
        val proxyPort = 8080;
        val proxy = ProxySelector.of(InetSocketAddress.createUnresolved(proxyHost, proxyPort));

        val messages = """
        [
            {"role": "system", "content": "Vous êtes un assistant de discussion."},
            {"role": "user", "content": "$prompt"}
        ]
        """

        val requestBody = """
        {
            "model": "gpt-3.5-turbo",
            "messages": $messages,
            "max_tokens": 100
        }
        """


        val request = HttpRequest.newBuilder()
            .uri(URI.create(gpt3Endpoint))
            .header("Authorization", "Bearer ${apiKey}")
            .header("Content-Type", "application/json")
            .POST(
                HttpRequest.BodyPublishers.ofString(requestBody)
            )
            .build();
        val client = HttpClient.newBuilder()
            .proxy(proxy)
            .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());

        val jsonObject = JsonParser.parseString(response.body()).asJsonObject
        val assistantContent = jsonObject.getAsJsonArray("choices").get(0).asJsonObject
            .getAsJsonObject("message")
            .get("content")
            .asString

        println(assistantContent)
        return assistantContent


    }
}