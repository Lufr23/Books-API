package principal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Mainbooks {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search a book: ");
        var book = scanner.nextLine();

        try {
            String encodedBook = URLEncoder.encode(book, "UTF-8");
            var url = "https://www.googleapis.com/books/v1/volumes?q=" + encodedBook + "&apikey=AIzaSyAGWupli654PoUm68E5WB6DnzExMbfq2sE";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }
}