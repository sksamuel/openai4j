package com.sksamuel.openai4j.client;

public record CreateImageRequestV1(String prompt,
                                   String model,
                                   int n,
                                   String quality,
                                   String response_format,
                                   String size,
                                   String style,
                                   String user
) {
}

