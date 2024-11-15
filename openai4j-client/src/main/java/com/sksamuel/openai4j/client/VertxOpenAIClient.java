package com.sksamuel.openai4j.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class VertxOpenAIClient implements OpenAIClient {

   private final String apiKey;
   private final Vertx vertx;
   private final WebClient client;
   private final ObjectMapper mapper;

   public VertxOpenAIClient(String apiKey) {
      this(apiKey, Vertx.vertx());
   }

   public VertxOpenAIClient(String apiKey, Vertx vertx) {
      this.apiKey = apiKey;
      this.vertx = vertx;
      this.mapper = new ObjectMapper();
      this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      this.client =
         WebClient.create(vertx, new WebClientOptions()
            .setTryUseCompression(true));

   }

   @Override
   public CompletableFuture<ModelsResponseV1> listModels() {
      return client
         .request(HttpMethod.GET, createRequestOptions("/v1/models"))
         .send()
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, ModelsResponseV1.class));
   }

   @Override
   public CompletableFuture<EmbeddingsResponseV1> createEmbedding(EmbeddingsRequestV1 request) {
      return client
         .request(HttpMethod.POST, createRequestOptions("/v1/embeddings"))
         .sendJson(request)
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, EmbeddingsResponseV1.class));
   }

   @Override
   public CompletableFuture<ChatCompletionResponseV1> chatCompletion(ChatCompletionRequestV1 request) throws JsonProcessingException {
      return client
         .request(HttpMethod.POST, createRequestOptions("/v1/chat/completions"))
         .putHeader(HttpHeaders.CONTENT_TYPE.toString(), "application/json")
         .sendBuffer(Buffer.buffer(mapper.writeValueAsBytes(request)))
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, ChatCompletionResponseV1.class));
   }

   @Override
   public CompletableFuture<CreateImageResponseV1> createImage(CreateImageRequestV1 request) throws JsonProcessingException {
      return client
         .request(HttpMethod.POST, createRequestOptions("/v1/images/generations"))
         .putHeader(HttpHeaders.CONTENT_TYPE.toString(), "application/json")
         .sendBuffer(Buffer.buffer(mapper.writeValueAsBytes(request)))
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, CreateImageResponseV1.class));
   }

   @Override
   public CompletableFuture<CreateImageVariationResponseV1> createImageVariation(CreateImageVariationRequestV1 request) throws JsonProcessingException {
      return client
         .request(HttpMethod.POST, createRequestOptions("/v1/images/variations"))
         .putHeader(HttpHeaders.CONTENT_TYPE.toString(), "multipart/form-data")
         .sendBuffer(Buffer.buffer(mapper.writeValueAsBytes(request)))
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, CreateImageVariationResponseV1.class));
   }

   @Override
   public CompletableFuture<ListFilesResponseV1> listFiles(ListFilesRequestV1 request) throws JsonProcessingException {
      return client
         .request(HttpMethod.GET, createRequestOptions("/v1/files"))
         .addQueryParam("purpose", request.purpose())
         .addQueryParam("purposes", request.purpose())
         .addQueryParam("order", request.order())
         .addQueryParam("after", request.after())
         .send()
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, ListFilesResponseV1.class));
   }

   @Override
   public CompletableFuture<File> retrieveFile(String fileId) throws JsonProcessingException {
      return client
         .request(HttpMethod.GET, createRequestOptions("""
            /v1/files/${fileId}
            """))
         .send()
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, File.class));
   }

   @Override
   public CompletableFuture<CreateModerationResponseV1> createModeration(CreateModerationRequestV1 request) throws JsonProcessingException {
      return client
         .request(HttpMethod.GET, createRequestOptions("/v1/moderations"))
         .putHeader(HttpHeaders.CONTENT_TYPE.toString(), "application/json")
         .sendBuffer(Buffer.buffer(mapper.writeValueAsBytes(request)))
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, CreateModerationResponseV1.class));
   }

   @Override
   public CompletableFuture<DeleteFineTunedModelResponseV1> deleteFineTunedModel(String model) throws JsonProcessingException {
      return client
         .request(HttpMethod.DELETE, createRequestOptions("""
            /v1/models/${model}
            """))
         .putHeader(HttpHeaders.CONTENT_TYPE.toString(), "application/json")
         .send()
         .toCompletionStage()
         .toCompletableFuture()
         .thenApply(response -> marshall(response, DeleteFineTunedModelResponseV1.class));
   }

   private <T> T marshall(HttpResponse<Buffer> response, Class<T> type) {
      try {
         System.out.println(response.bodyAsString());
         return mapper.readValue(response.bodyAsBuffer().getBytes(), type);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private RequestOptions createRequestOptions(String path) {
      return new RequestOptions()
         .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
         .setHost("api.openai.com")
         .setPort(443)
         .setSsl(true)
         .setURI(path);
   }
}
