package com.sksamuel.openai4j.client;

import java.util.List;

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

