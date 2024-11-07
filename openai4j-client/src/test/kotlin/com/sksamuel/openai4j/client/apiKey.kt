package com.sksamuel.openai4j.client

val apiKey: String
   get() {
      val key = System.getenv("OPENAI_API_KEY")
      return if (key.isNullOrBlank()) {
         object {}.javaClass.getResourceAsStream("/openai.key").bufferedReader().readText().trim()
      } else key.trim()
   }
