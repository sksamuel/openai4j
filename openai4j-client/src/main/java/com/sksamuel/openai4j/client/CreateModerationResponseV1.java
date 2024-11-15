package com.sksamuel.openai4j.client;

import java.util.List;
import java.util.Map;

public record CreateModerationResponseV1(
   String id,
   String model,
   List<ModerationResult> results
) {
}

record ModerationResult(
   boolean flagged,
   Map<String, Boolean> categories,
   Map<String, Double> category_scores,
   Map<String, List<String>> category_applied_input_types
) {
}
