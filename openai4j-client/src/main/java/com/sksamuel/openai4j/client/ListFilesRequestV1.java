package com.sksamuel.openai4j.client;

public record ListFilesRequestV1(String purpose, int limit, String order, String after) {
}
