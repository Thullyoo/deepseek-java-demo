package br.thullyoo.deepseek_java_demo.controller;

import br.thullyoo.deepseek_java_demo.services.DeepSeekService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DeepSeekController {

    @Autowired
    private DeepSeekService deepSeekService;

    @GetMapping
    public String chat(@RequestParam(defaultValue = "Me diga os jogos da categoria Horror mais amados") String query) throws JsonProcessingException {
        return deepSeekService.chat(query);
    }

}
