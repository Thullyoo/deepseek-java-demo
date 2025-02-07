package br.thullyoo.deepseek_java_demo.model;

import java.util.List;

public record Message(String model, boolean stream, List<DeepSeekMessage> messages) {
}
