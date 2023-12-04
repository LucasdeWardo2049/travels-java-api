# API de Viagens 🌍✈️

Bem-vindo ao repositório da nossa incrível API de Viagens! Este projeto faz parte do trabalho final da disciplina de Desenvolvimento de Aplicações Distribuídas no curso de Análise de Sistemas do IFAM-CMC. A API permite gerenciar dados de viagens, realizar operações CRUD, e até mesmo calcular estatísticas sobre os tickets de viagem. 🚗🚆

## Funcionalidades Principais 🚀

### 1. Criar Viagem

**Endpoint:** `POST /api-travels/travels`

Crie uma nova viagem com facilidade! Basta fornecer os detalhes da viagem no corpo da requisição, como no exemplo abaixo:

```
{   
   "id": 1,
   "orderNumber": "220788",
   "amount": "22.88",
   "startDate": "2019-09-11T09:59:51.312Z",
   "type": "ONE-WAY"
}
```

#### Postman:

![Criar Viagem](https://github.com/AdemarCastro/travels-java-api/assets/25653698/7fbef45e-8485-4a0b-84a4-36e54b918209)

#### Respostas:

- ✅ 201: Viagem criada com sucesso.
- ❌ 400: JSON inválido.
- ❌ 422: Campos não parseáveis ou data de início posterior à data de fim (para viagens de ida e volta).

### 2. Atualizar Viagem

**Endpoint:** `PUT /api-travels/travels/{id}`

Atualize informações de uma viagem existente enviando um objeto modificado no corpo da requisição:

```
{   
   "id": 1,
   "orderNumber": "220788",
   "amount": "30.06",
   "startDate": "2019-09-11T09:59:51.312Z",
   "type": "ONE-WAY"
}
```

#### Postman:

![Atualizar Viagem](https://github.com/AdemarCastro/travels-java-api/assets/25653698/6c7d9c6a-b60f-4db7-911c-c5b363c0948e)

#### Respostas:

- ✅ 200: Atualização bem-sucedida.
- ❌ 400: JSON inválido.
- ❌ 404: Tentativa de atualizar um registro inexistente.
- ❌ 422: Campos não parseáveis (JSON mal formatado).

### 3. Listar Viagens

**Endpoint:** `GET /api-travels/travels`

Recupere uma lista de todas as viagens criadas.

#### Postman:

![Listar Viagens](https://github.com/AdemarCastro/travels-java-api/assets/25653698/2d2b8ae8-67d1-4d24-84a4-14bb42133f9a)

#### Respostas:

- ✅ 200: Viagens encontradas.
- ❌ 404: Nenhuma viagem encontrada.

### 4. Remover Todas as Viagens

**Endpoint:** `DELETE /api-travels/travels`

Remova todas as viagens com uma requisição de corpo vazio e receba um tranquilo 204.

#### Postman:

![Remover Todas as Viagens](https://github.com/AdemarCastro/travels-java-api/assets/25653698/8f455518-4628-4f3b-8131-e7ed7053d152)

### 5. Estatísticas de Viagens

**Endpoint:** `GET /api-travels/statistics`

Receba estatísticas úteis sobre as viagens criadas, como a soma total, média, máximo, mínimo e o número total de viagens.

```json
{   
   "sum": "150.06",
   "avg": "75.3",
   "max": "120.0",
   "min": "30.06",
   "count": "2"
}
```

Todos os campos do tipo BigDecimal possuem apenas duas casas decimais.

#### Postman:

![Estatísticas de Viagens](https://github.com/AdemarCastro/travels-java-api/assets/25653698/d2f8acb5-0622-48ae-8440-97fd1757e9aa)

## [Prefixos dos Commits](https://github.com/JuniorLima22/padroes-e-nomenclaturas-no-git#prefixos-dos-commits)
- 📚 [DOCS]: apenas mudanças de documentação.
- ✨ [FEAT]: Adição de uma nova feature ao projeto, componente, etc.
- 🐞 [FIX]: Correção de um bug.
- ⚡ [PERF]: Melhoria de performance.
- 🛠️ [REFACTOR]: Refatoração do código que não adiciona uma funcionalidade nem corrige um bug.
- 🎨 [STYLE]: Mudanças no código que não afetam seu significado (espaço em branco, formatação, ponto e vírgula, etc).
- 🧪 [TEST]: Adição ou correção de testes.
- 🚀 [IMPROVEMENT]: Melhoria em algo já existente, seja de performance, escrita, layout, etc.

## Mãos à Obra! 👨‍💻👩‍💻

Siga as instruções detalhadas acima para implementar essas funcionalidades incríveis e fazer parte do desenvolvimento desta API excepcional! 🛠️✨

## Contato
Email: ademar.castro.curriculo@gmail.com <br>
LinkedIn: [Meu LinkedIn](https://www.linkedin.com/in/ademar-castro-8bb95b256/)
