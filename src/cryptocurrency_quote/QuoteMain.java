package cryptocurrency_quote;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class QuoteMain {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a Cryptocurrency:");
        var cryptocurrence = scanner.nextLine();

        try{
            String encodeCoin = URLEncoder.encode(cryptocurrence, "UTF-8");
            var URL = "https://api.coingecko.com/api/v3/simple/price?ids=" + encodeCoin + "&vs_currencies=usd";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }
}
