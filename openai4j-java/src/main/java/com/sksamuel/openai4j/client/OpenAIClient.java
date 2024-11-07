package com.sksamuel.openai4j.client;

import java.util.concurrent.CompletableFuture;

public interface OpenAIClient {

   CompletableFuture<ModelsResponseV1> listModels();

   CompletableFuture<EmbeddingsResponseV1> createEmbedding(EmbeddingsRequestV1 request);

}
