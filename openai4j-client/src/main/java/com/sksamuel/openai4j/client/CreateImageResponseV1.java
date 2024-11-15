package com.sksamuel.openai4j.client;

import java.util.List;

public record CreateImageResponseV1(long created,
                                    List<Image> data) {
}

record Image(String url, String b64_json, String revised_prompt) {
}
