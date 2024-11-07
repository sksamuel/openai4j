package com.sksamuel.openai4j.client;

import java.util.List;

public record EmbeddingsResponseV1(String object, List<Embedding> data, String model, Usage usage) {
}

record Embedding(String object, List<Double> embedding, int index) {
}

record Usage(int prompt_tokens, int total_tokens) {
}
