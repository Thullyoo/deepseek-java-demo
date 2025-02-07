package br.thullyoo.deepseek_java_demo.services;

import br.thullyoo.deepseek_java_demo.model.DeepSeekMessage;
import br.thullyoo.deepseek_java_demo.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DeepSeekService {

    @Value("${api.key}")
    private String apikey;

    @Value("${api.base.url}")
    private String baseUrl;

    public String chat(String query) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        var headers = new HttpHeaders();

        headers.setBearerAuth(apikey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var deepSeekAssistant = new DeepSeekMessage("system", "Você é um especialista em jogos eletrônicos");
        var deepSeekPrompt = new DeepSeekMessage("user", query);

        var deepSeekPromptRequest = new Message("deepseek-chat", false,
                List.of(deepSeekAssistant, deepSeekPrompt));

        ObjectMapper objectMapper = new ObjectMapper();
        var prompt = objectMapper.writeValueAsString(deepSeekPromptRequest);

        var httpRequest = new HttpEntity<>(prompt, headers);

        var response = restTemplate.exchange(baseUrl, HttpMethod.POST, httpRequest, String.class);

        if (response.getStatusCode().value() == 200){
            return  response.getBody();
        } else {
            throw new RuntimeException("Error no DeepSeek");
        }
    }
}
