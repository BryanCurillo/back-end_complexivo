package com.proyecto.backend.service;
import com.proyecto.backend.model.PredictRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Service
public class PredictService {

    private final RestTemplate restTemplate;

    @Value("${url.api}")
    private String url;//"http://localhost:5000/predict";

    public PredictService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtenerPrediccion(PredictRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PredictRequest> entity = new HttpEntity<>(request, headers);

        // Aquí suponemos que la respuesta es un String (puede ser JSON o lo que devuelva tu Flask)
        String response = restTemplate.postForObject(url, entity, String.class);

        return response;
    }

    public Double obtenerOverScore(PredictRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        double score = 0.0;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PredictRequest> entity = new HttpEntity<>(request, headers);

        // Aquí suponemos que la respuesta es un String (puede ser JSON o lo que devuelva tu Flask)
        String jsonResponse = restTemplate.postForObject(url, entity, String.class);


        try {
            jsonResponse = restTemplate.postForObject(url, entity, String.class);

            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                System.err.println("La respuesta del servidor está vacía");
                return null;
            }

            JsonNode root = mapper.readTree(jsonResponse);

            JsonNode node = root.get("overall_score");
            if (node == null || !node.isNumber()) {
                System.err.println("La clave 'overall_score' no es numérica o no existe");
                return null;
            }

            score = node.asDouble();

            if (score < 0 || score > 100) {
                System.err.println("El valor de 'overall_score' está fuera del rango esperado");
                return null;
            }

            return score;

        } catch (RestClientException e) {
            System.err.println("Error en la llamada HTTP: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("Error al parsear JSON: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return null;
        }
    }
}
