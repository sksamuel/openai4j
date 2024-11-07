package com.sksamuel.openai4j.client

val apiKey = object {}.javaClass.getResourceAsStream("/openai.key").bufferedReader().readText().trim()
