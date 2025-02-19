# Produtos Service (MS)

### Mensageria
Este microsserviço escuta mensagens dos seguintes tópicos:
* <code>novo-pedido-topic</code>
* <code>entrega-concluida-topic</code>

E produz mensagens para estes:
* <code>estoque-insuficiente-topic</code>
* <code>estoque-reservado-topic</code>

### Environment Vars

* PRODUTO_PROFILE     _(dev)_
* PRODUTO_API_PORT    _(8081)_
* PRODUTO_DB_USER     _(postgres)_
* PRODUTO_DB_PASSWORD _(postgres)_
* PRODUTO_DB_HOST     _(postgresql://localhost)_
* PRODUTO_DB_PORT     _(5432)_
* PRODUTO_BROKER_HOST _(localhost)_
* PRODUTO_BROKER_PORT _(9092)_

