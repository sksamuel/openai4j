package com.sksamuel.openai4j.client;

import java.util.List;

public record ListFilesResponseV1(List<File> data, String object) {
}

record File(
   String id,
   int bytes,
   long created_at,
   String filename,
   String object,
   String purpose
) {
}
