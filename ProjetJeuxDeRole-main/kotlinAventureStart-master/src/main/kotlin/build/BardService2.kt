package build

import com.google.gson.JsonParser
import java.net.InetSocketAddress
import java.net.ProxySelector
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class BardService2 {
    val apiKey = "AIzaSyBj4OwrCrbwv4271N1CgBr23xXd6fCs3aY"
    fun fetchHistoire(prompt: String): String {
        val gpt3Endpoint = "https://bard.ai/v1/text/generate"
        val proxyHost = "172.16.0.51";
        val proxyPort = 8080;
        val proxy = ProxySelector.of(InetSocketAddress.createUnresolved(proxyHost, proxyPort));



        val requestBody = """
     "{\"prompt\": \"${prompt}\"}"
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

        print(response.body())
//        val jsonObject = JsonParser.parseString(response.body()).asJsonObject
//        val assistantContent = jsonObject.getAsJsonArray("choices").get(0).asJsonObject
//            .getAsJsonObject("message")
//            .get("content")
//            .asString
//
//        println(assistantContent)
//        return assistantContent
        return " "


    }

}