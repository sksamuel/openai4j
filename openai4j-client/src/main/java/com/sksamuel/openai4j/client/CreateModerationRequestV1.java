package com.sksamuel.openai4j.client;

import java.util.List;

public record CreateModerationRequestV1(List<String> inputs) {
   public CreateModerationRequestV1(String input) {
      this(List.of(input));
   }
}
