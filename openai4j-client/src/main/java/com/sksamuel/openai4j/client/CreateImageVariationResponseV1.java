package com.sksamuel.openai4j.client;

import java.util.List;

public record CreateImageVariationResponseV1(long created,
                                             List<Image> data) {
}
