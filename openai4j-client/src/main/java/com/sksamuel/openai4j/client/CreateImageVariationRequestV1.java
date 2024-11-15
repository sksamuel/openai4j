package com.sksamuel.openai4j.client;

public record CreateImageVariationRequestV1(String image, // must be square
                                            String model,
                                            int n,
                                            String response_format,
                                            String size, // Must be one of 256x256, 512x512, or 1024x1024
                                            String user
) {
}

