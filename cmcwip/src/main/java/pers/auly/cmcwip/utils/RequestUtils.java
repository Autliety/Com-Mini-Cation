package pers.auly.cmcwip.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestUtils {
    
    private static final String OPEN_API_HOST = "https://api.weixin.qq.com/";

    public static String openApiGet(String apiUrl, MultiValueMap<String, String> payload) {
        URI requestUri = UriComponentsBuilder.fromHttpUrl(OPEN_API_HOST)
            .pathSegment(apiUrl)
            .queryParams(payload)
            .build().toUri();
    
        HttpRequest request = HttpRequest.newBuilder()
            .uri(requestUri)
            .GET()
            .build();
    
        try {
            return HttpClient.newHttpClient()
                .send(request, BodyHandlers.ofString())
                .body();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
