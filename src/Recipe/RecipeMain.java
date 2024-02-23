package Recipe;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class RecipeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search a recipe:");
        var recipe = scanner.nextLine();

        try{
            String recipeEncoded = URLEncoder.encode(recipe, "UTF-8");
            var url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + recipeEncoded;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
