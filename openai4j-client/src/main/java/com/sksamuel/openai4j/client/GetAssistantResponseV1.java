package com.sksamuel.openai4j.client;

public record GetAssistantResponseV1(
   String id,
   String object,
   String name,
   long created_at,
   String model,
   String description,
   String instructions,
   double top_p,
   double temperature,
   String response_format
) {
}
