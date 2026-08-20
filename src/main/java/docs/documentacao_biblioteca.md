# SISTEMA DE GERENCIAMENTO DE BIBLIOTECA

## DOCUMENTAÇÃO DO PROJETO

| Campo | Informação |
|---|---|
| **Autor(es)** | Lucas De Montaigne Teixeira Brito |
| **Disciplina** | Engenharia de Software |
| **Instituição** | Faculdade Senai - Fatesg |
| **Versão** | 1.0 |
| **Data** | 17/08/2026 |
| **Status** | Em desenvolvimento |

---

# 1. Introdução

## 1.1 Objetivo

O objetivo do software de gerenciamento de biblioteca é organizar e facilitar o gerenciamento dos dados relacionados aos clientes, livros, empréstimos, devoluções e multas. O sistema busca centralizar essas informações, tornando o controle das operações da biblioteca mais eficiente e reduzindo possíveis erros no gerenciamento dos dados.

## 1.2 Escopo

O sistema terá como objetivo gerenciar as principais operações de uma biblioteca, incluindo o cadastro e gerenciamento de clientes e livros, realização e registro de empréstimos, controle de devoluções e cálculo de multas.

Não fazem parte do escopo inicial do projeto funcionalidades como pagamentos online, integração com sistemas externos, empréstimos entre diferentes bibliotecas e gerenciamento de funcionários fora das funções relacionadas ao sistema.

## 1.3 Público-alvo

O sistema será destinado principalmente a clientes (leitores) e bibliotecários.

Para os clientes, o sistema permitirá o acompanhamento de seus empréstimos, devoluções e possíveis multas. Para os bibliotecários, o sistema facilitará o gerenciamento de livros, clientes, empréstimos, devoluções e multas, mantendo essas operações registradas e organizadas.

---

# 2. Requisitos Funcionais

## RF-001 — Cadastro de livros

**Descrição:**  
O sistema deve permitir que o bibliotecário cadastre novos livros, armazenando as informações necessárias para sua identificação e controle.

**Prioridade:** Alta

**Critérios de aceitação:**

- Permitir que o bibliotecário informe os dados do livro.
- Validar os dados obrigatórios do cadastro.
- Registrar o livro no sistema.
- Informar ao bibliotecário se o cadastro foi realizado com sucesso.

## RF-002 — Busca de livros

**Descrição:**  
O sistema deve permitir que o bibliotecário consulte livros cadastrados por meio do nome do livro.

**Prioridade:** Alta

**Critérios de aceitação:**

- Permitir que o bibliotecário informe o nome do livro para pesquisa.
- Exibir as informações do livro encontrado.
- Informar quando nenhum livro correspondente for encontrado.

## RF-003 — Cadastro de clientes

**Descrição:**  
O sistema deve permitir que o bibliotecário cadastre novos clientes, armazenando as informações necessárias para sua identificação e controle.

**Prioridade:** Alta

**Critérios de aceitação:**

- Permitir que o bibliotecário informe os dados do cliente.
- Validar os dados obrigatórios do cadastro.
- Registrar o cliente no sistema.
- Informar ao bibliotecário se o cadastro foi realizado com sucesso.

## RF-004 — Busca de clientes

**Descrição:**  
O sistema deve permitir que o bibliotecário consulte clientes cadastrados por meio do nome do cliente.

**Prioridade:** Alta

**Critérios de aceitação:**

- Permitir que o bibliotecário informe o nome do cliente para pesquisa.
- Exibir as informações do cliente encontrado.
- Informar quando nenhum cliente correspondente for encontrado.

## RF-005 — Empréstimo de livros

**Descrição:**  
O sistema deve permitir que o bibliotecário registre o empréstimo de um livro para um cliente, atualizando a disponibilidade do livro e registrando as informações do empréstimo.

**Prioridade:** Alta

**Critérios de aceitação:**

- Permitir que o bibliotecário selecione um cliente cadastrado.
- Permitir que o bibliotecário selecione um livro cadastrado.
- Verificar se o livro está disponível para empréstimo.
- Registrar o empréstimo do livro para o cliente.
- Alterar o status do livro para indisponível.
- Informar ao bibliotecário quando o empréstimo for realizado com sucesso.
- Impedir o empréstimo de livros indisponíveis.

## RF-006 — Devolução de livros

**Descrição:**  
O sistema deve permitir que o bibliotecário registre a devolução de um livro emprestado, atualizando sua disponibilidade e as informações relacionadas ao empréstimo.

**Prioridade:** Alta

**Critérios de aceitação:**

- Permitir que o bibliotecário selecione o cliente responsável pelo empréstimo.
- Permitir que o bibliotecário selecione o livro a ser devolvido.
- Verificar se o livro possui um empréstimo ativo.
- Registrar a devolução do livro.
- Alterar o status do livro para disponível.
- Atualizar as informações relacionadas ao empréstimo.
- Informar ao bibliotecário quando a devolução for realizada com sucesso.

---

# 3. Regras de Negócio

## RN-001 — Prazo para devolução

**Descrição:**  
Cada livro emprestado deverá ser devolvido pelo cliente no prazo máximo de 28 dias a partir da data do empréstimo.

## RN-002 — Multa por atraso

**Descrição:**  
Em caso de atraso na devolução, será aplicada uma multa correspondente a R$ 1,00 por dia e por item.

---

# 4. Requisitos Não Funcionais

## RNF-001 — Interação via terminal

**Descrição:**  
O sistema deve permitir que o bibliotecário interaja com o sistema por meio do terminal.

**Prioridade:** Alta

**Critérios de aceitação:**

- O sistema deve apresentar as opções disponíveis através do terminal.
- O bibliotecário deve conseguir selecionar as operações pelo terminal.

## RNF-002 — Tempo de resposta

**Descrição:**  
O sistema deve responder às operações realizadas pelo bibliotecário em até 1 segundo.

**Prioridade:** Alta

**Critérios de aceitação:**

- O sistema deve informar o resultado da operação após seu processamento com tempo de resposta inferior a 1 segundo.

### Resumo dos Requisitos Não Funcionais

| ID | Categoria | Descrição | Prioridade |
|---|---|---|---|
| RNF-001 | Interação | Interação do sistema por meio do terminal. | Alta |
| RNF-002 | Desempenho | Tempo de resposta inferior a 1 segundo. | Alta |

---

# 5. Entidades Principais

| Entidade | Responsabilidade |
|---|---|
| **Livro** | Armazena e gerencia as informações e a disponibilidade de um livro. |
| **Leitor** | Armazena os dados do leitor e seus empréstimos e débitos. |
| **Empréstimo** | Registra e controla o empréstimo e a devolução de um livro. |
| **Multa** | Registra e controla os valores gerados por atrasos na devolução. |

---

# 6. Diagrama de Classes

> **[Insira aqui o diagrama de classes.]**

**Figura 1 — Diagrama de classes do sistema.**

---

# 7. Histórico de Alterações

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.1 | 17/08/2026 | Documento inicial. | Lucas De Montaigne |

---

# 8. Observações

O projeto ainda está em desenvolvimento. Novas funcionalidades poderão ser adicionadas futuramente, como a persistência das informações em arquivos JSON, permitindo que os dados de livros, clientes e empréstimos sejam mantidos mesmo após o encerramento do sistema.
