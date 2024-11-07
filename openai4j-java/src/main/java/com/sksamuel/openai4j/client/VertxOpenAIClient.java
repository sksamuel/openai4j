package com.sksamuel.openai4j.client;

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
