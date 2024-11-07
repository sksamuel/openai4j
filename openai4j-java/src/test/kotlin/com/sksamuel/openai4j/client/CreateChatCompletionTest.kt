package com.sksamuel.openai4j.client

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class CreateChatCompletionTest : FunSpec() {
   init {
      test("chat completion") {
         val openai = VertxOpenAIClient(apiKey)
         val resp = openai.chatCompletion(
            ChatCompletionRequestV1(
               listOf(
                  Message("system", "You are a helpful assistant"),
                  Message("user", "Hello!")
               ),
               "gpt-4o",
               100,
               1,
               false,
               null,
               null,
               null,
               null,
            )
         ).get()
         resp.model shouldBe "gpt-4o-2024-08-06"
         resp.`object` shouldBe "chat.completion"
         resp.id.shouldNotBeNull()
         resp.system_fingerprint.shouldNotBeNull()
         resp.choices.single().index shouldBe 0
         resp.choices.single().finish_reason shouldBe "stop"
         resp.choices.single().message.role shouldBe "assistant"
         resp.choices.single().message.content shouldBe "Hello! How can I assist you today?"
         resp.usage.prompt_tokens shouldBe 18
      }
   }
}
