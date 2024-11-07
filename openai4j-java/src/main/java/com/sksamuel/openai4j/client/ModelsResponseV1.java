package com.sksamuel.openai4j.client;

import java.util.List;

record Model(String id, String object, Long created, String owned_by) {
}

public record ModelsResponseV1(String object, List<Model> data) {
}


