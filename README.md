# 🏨 Sistema de Gerenciamento de Hotel - Transilvânia

# 📑 Sumário

- [📖 Sobre o Projeto](#-sobre-o-projeto)
- [🎯 Objetivos do Sistema](#-objetivos-do-sistema)
- [⚙️ Funcionalidades Implementadas](#️-funcionalidades-implementadas)
- [🧠 Arquitetura e Lógica Aplicada](#-arquitetura-e-lógica-aplicada)
- [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [🔄 Fluxo do Sistema](#-fluxo-do-sistema)
- [📂 Estrutura de Dados](#-estrutura-de-dados)
- [📌 Operações do Sistema](#-operações-do-sistema)
- [🚀 Possíveis Melhorias Futuras](#-possíveis-melhorias-futuras)
- [📄 Licença](#-licença)
- [👥 Créditos & Contatos](#-créditos--contatos)

---

## 📖 Sobre o Projeto

O **Sistema de Gerenciamento de Hotel Transilvânia** é uma aplicação desenvolvida em **Java puro**, com foco em lógica de programação e manipulação de estruturas básicas.

O sistema simula o funcionamento de um hotel real, permitindo:

- Controle de hóspedes
- Gerenciamento de quartos
- Registro de consumo de frigobar
- Processamento de check-out com cálculo de custos

⚠️ Restrição importante do projeto:
> Foi desenvolvido **sem uso de ArrayList, banco de dados ou coleções**, utilizando apenas **vetores, matrizes, tipos primitivos e String**.

---

## 🎯 Objetivos do Sistema

- Aplicar conceitos fundamentais de lógica de programação
- Trabalhar com vetores e matrizes de forma estruturada
- Simular regras reais de um sistema de hotel
- Controlar estados (ocupado/disponível)
- Implementar operações completas de CRUD manualmente

---

## ⚙️ Funcionalidades Implementadas

✔ Cadastro de hóspedes com número de reserva automático  
✔ Cancelamento de reservas com liberação de quarto  
✔ Listagem de todos os hóspedes cadastrados  
✔ Consulta individual por número do quarto  
✔ Edição de dados do hóspede  
✔ Registro de consumo do frigobar por quarto  
✔ Cálculo automático de consumo total  
✔ Sistema completo de check-out com resumo da conta  
✔ Liberação automática do quarto após saída  

---

## 🧠 Arquitetura e Lógica Aplicada

O sistema foi construído utilizando lógica estruturada baseada em:

- Vetores unidimensionais para armazenamento de dados
- Matrizes para controle de consumo
- Controle manual de índices
- Estrutura de repetição (`while`) para menu interativo
- Estrutura de decisão (`switch-case`) para operações

### 🔹 Controle de Hóspedes

Cada hóspede ocupa **3 posições consecutivas no vetor**:

```
[Reserva, Quarto, Nome]
```

Exemplo:

```
[1, 101, "João"]
```

---

### 🔹 Controle de Quartos

```java
boolean[] statusQuarto
```

- `true` → ocupado  
- `false` → disponível  

---

### 🔹 Controle do Frigobar

```java
float[][] frigobar
```

- Linhas → Quartos  
- Colunas → Produtos  

Cada posição representa:

```
Quantidade consumida de um produto em um quarto
```

---

## 🛠️ Tecnologias Utilizadas

- Java
- Programação Estruturada
- Vetores (Arrays)
- Matrizes
- Scanner (entrada de dados)
- Terminal/Console

---

## 🔄 Fluxo do Sistema

1. Usuário acessa menu principal
2. Escolhe uma operação
3. Sistema valida dados
4. Executa lógica correspondente
5. Atualiza estruturas (vetores/matriz)
6. Exibe resultado
7. Retorna ao menu

---

## 📂 Estrutura de Dados

```java
String[] hospedes = new String[300];
boolean[] statusQuarto = new boolean[100];
float[][] frigobar = new float[100][6];
```

### 📌 Organização:

| Estrutura | Função |
|----------|--------|
| `hospedes` | Armazena dados dos hóspedes |
| `statusQuarto` | Controla ocupação |
| `frigobar` | Armazena consumo |
| `produtos` | Lista de itens disponíveis |

---

## 📌 Operações do Sistema

### 1️⃣ Reservar Quarto
- Verifica disponibilidade
- Cadastra hóspede
- Marca quarto como ocupado

---

### 2️⃣ Cancelar Reserva
- Remove dados do hóspede
- Libera quarto
- Zera consumo

---

### 3️⃣ Listar Reservas
- Exibe todos os quartos ocupados

---

### 4️⃣ Consultar Hóspede
- Busca por número do quarto
- Retorna dados completos

---

### 5️⃣ Editar Hóspede
- Permite alterar nome e reserva
- Atualiza vetor manualmente

---

### 6️⃣ Registrar Consumo do Frigobar
- Recebe produto + quantidade
- Atualiza matriz de consumo

---

### 7️⃣ Check-out

Calcula:

- 💰 Valor das diárias
- 🧃 Consumo do frigobar
- 🧾 Total geral

Exibe:

- Nome
- Quarto
- Total de dias
- Consumo
- Valor final

Finaliza:

- Libera quarto
- Remove hóspede
- Zera consumo

---

## 🚀 Possíveis Melhorias Futuras

- Uso de orientação a objetos (classes)
- Persistência em banco de dados
- Interface gráfica (JavaFX / Swing)
- Controle de login de funcionários
- Relatórios administrativos
- Sistema de reservas online
- API REST

---

## 📄 Licença

Projeto desenvolvido para fins educacionais.

---

## 👥 Créditos & Contatos

**Mateus Todeschini**  
GitHub: https://github.com/Todeschiniii  
