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
            -0.044324964,
            -0.014930655,
            -0.015197513,
            -0.013402898,
            -0.007878956,
            0.033010226,
            -0.008779598,
            -0.0044131493,
            -0.02317654,
            -0.0007167615,
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
