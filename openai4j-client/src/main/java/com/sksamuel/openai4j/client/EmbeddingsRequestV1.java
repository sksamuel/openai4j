package com.sksamuel.openai4j.client;

public record EmbeddingsRequestV1(String input, String model, Integer dimensions) {
   public EmbeddingsRequestV1(String input, String model) {
      this(input, model, null);
   }
}
