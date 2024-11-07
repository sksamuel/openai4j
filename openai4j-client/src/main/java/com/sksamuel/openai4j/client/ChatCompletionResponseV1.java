package com.sksamuel.openai4j.client;

import java.util.List;

/**
 * @param id      The unique identifier of the chat completion.
 * @param choices The list of choices that
 */
public record ChatCompletionResponseV1(String id, List<Choice> choices, long created, String model, String service_tier,
                                       String system_fingerprint, String object, Usage usage) {
}

record Choice(String finish_reason, int index, Message message) {
}
