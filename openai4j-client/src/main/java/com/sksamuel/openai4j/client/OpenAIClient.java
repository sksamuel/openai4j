package com.sksamuel.openai4j.client;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.CompletableFuture;

public interface OpenAIClient {

   CompletableFuture<ModelsResponseV1> listModels();

   CompletableFuture<EmbeddingsResponseV1> createEmbedding(EmbeddingsRequestV1 request);

   CompletableFuture<ChatCompletionResponseV1> chatCompletion(ChatCompletionRequestV1 request) throws JsonProcessingException;

   CompletableFuture<CreateImageResponseV1> createImage(CreateImageRequestV1 request) throws JsonProcessingException;

   CompletableFuture<CreateImageVariationResponseV1> createImageVariation(CreateImageVariationRequestV1 request) throws JsonProcessingException;

   CompletableFuture<ListFilesResponseV1> listFiles(ListFilesRequestV1 request) throws JsonProcessingException;

   CompletableFuture<File> retrieveFile(String fileId) throws JsonProcessingException;

   CompletableFuture<CreateModerationResponseV1> createModeration(CreateModerationRequestV1 request) throws JsonProcessingException;

   CompletableFuture<DeleteFineTunedModelResponseV1> deleteFineTunedModel(String model) throws JsonProcessingException;

   CompletableFuture<GetAssistantResponseV1> getAssistant(String assistantId) throws JsonProcessingException;

   CompletableFuture<DeleteAssistantResponseV1> deleteAssistant(String assistantId) throws JsonProcessingException;
}
