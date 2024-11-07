package com.sksamuel.openai4j.client;

import java.util.List;

public record ChatCompletionRequestV1(List<Message> messages, String model, int max_completion_tokens, int n,
                                      boolean logprobs, Integer top_logprobs, Double temperature, Double top_p,
                                      Boolean parallel_tool_calls) {
}

record Message(String role, String content) {
}
