package com.sksamuel.openai4j.client

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CreateEmbeddingsTest : FunSpec() {

   init {
      test("create embeddings") {
         val openai = VertxOpenAIClient(apiKey)
         val resp =
            openai.createEmbedding(EmbeddingsRequestV1("embed me please darling", "text-embedding-ada-002")).get()
         resp.data.size shouldBe 1
         resp.data.first().embedding.take(10) shouldBe listOf(
            -0.044288147,
            -0.014967259,
            -0.015220716,
            -0.013393163,
            -0.0078838235,
            0.03302935,
            -0.008757581,
            -0.004468834,
            -0.023224598,
            -6.6323834E-4
         )
         resp.model shouldBe "text-embedding-ada-002"
         resp.usage shouldBe Usage(/* prompt_tokens = */ 4, /* total_tokens = */ 4)

      }

      test("create embeddings should support dimensions") {
         val openai = VertxOpenAIClient(apiKey)
         val resp =
            openai.createEmbedding(EmbeddingsRequestV1("embed me please darling", "text-embedding-3-small", 5)).get()
         resp.data.first().embedding.size shouldBe 5
      }
   }
}
