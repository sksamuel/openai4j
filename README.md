# openai4j

![main](https://github.com/sksamuel/openai4j/workflows/main/badge.svg)
[<img src="https://img.shields.io/maven-central/v/com.sksamuel.openai4j/aedile-core.svg?label=latest%20release"/>](https://central.sonatype.com/search?q=openai4j)
[<img src="https://img.shields.io/nexus/s/https/s01.oss.sonatype.org/com.sksamuel.openai4j/openai4j-cclient.svg?label=latest%20snapshot&style=plastic"/>](https://s01.oss.sonatype.org/content/repositories/snapshots/com/sksamuel/openai4j/)


Openai4j is a performant async Java client for OpenAI API supporting most of the available endpoints including chat
completion, images, emebddings, voice based on Vert.x.

## Supported Endpoints

* Models: [https://platform.openai.com/docs/api-reference/models/list](listModels), [https://platform.openai.com/docs/api-reference/models/retrieve](retrieveModel)
* Chat Completions: [https://platform.openai.com/docs/api-reference/chat/create](createChatCompletion)
* Images: createImage, createImageEdit, createImageVariation
* Embeddings: createEmbeddings
* Files: listFiles, deleteFile, retrieveFile
* Fine-tunes: deleteFineTuneModel
* Moderations: createModeration

## Getting Started

Add openai4j to your build:

implementation 'com.sksamuel.openai4j:openai4j-client:<version>'

Since all the calls are async they return responses wrapped in `CompletableFuture`'s .

## Contributions

This project is open-source and welcomes any contribution or feedback

## License

```
This software is licensed under the Apache 2 license, quoted below.

Copyright 2024 Stephen Samuel

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
