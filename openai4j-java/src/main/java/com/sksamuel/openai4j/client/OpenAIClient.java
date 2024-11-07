package com.sksamuel.openai4j.client;

import java.util.concurrent.CompletableFuture;

public interface OpenAIClient {
   CompletableFuture<V1ModelsResponse> listModels();
}
