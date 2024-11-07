package com.sksamuel.openai4j.client;

import java.util.List;

record Model(String id, String object, Long created, String owned_by) {
}

public record V1ModelsResponse(String object, List<Model> data) {
}


