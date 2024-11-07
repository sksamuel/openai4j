package com.sksamuel.openai4j.client;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.CompletableFuture;

public interface OpenAIClient {

   CompletableFuture<ModelsResponseV1> listModels();

   CompletableFuture<EmbeddingsResponseV1> createEmbedding(EmbeddingsRequestV1 request);

   CompletableFuture<ChatCompletionResponseV1> chatCompletion(ChatCompletionRequestV1 request) throws JsonProcessingException;
}
