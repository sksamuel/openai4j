package com.sksamuel.openai4j.client

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forOne
import io.kotest.matchers.shouldBe

class ListModelsTest : FunSpec() {
   init {
      test("list models") {
         val openai = VertxOpenAIClient(apiKey)
         val models = openai.listModels().get()
         models.data.size shouldBe 20
         models.data.forOne {
            it.id shouldBe  "tts-1"
            it.owned_by shouldBe "openai-internal"
         }
      }
   }
}
