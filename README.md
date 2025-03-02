# 📌 Todolist  
Um gerenciador de tarefas desenvolvido em Java.

## 📖 Sumário  
1. [📜 Funcionalidades](#-funcionalidades)  
2. [🛠 Tecnologias](#-tecnologias)  
3. [🚀 Como rodar o projeto](#-como-rodar-o-projeto)  
4. [🔗 Endpoints](#-endpoints)  
5. [🧪 Testes](#-testes)  

## 📜 Funcionalidades  
- Criar, editar e excluir tarefas
- Listar tarefas ordenadas por prioridade e nome.

## 🛠 Tecnologias  
- **Java 17**  
- **Spring Boot 3.4.3**  
- **PostgreSQL**  
- **Maven**  

## 🚀 Como rodar o projeto  
### 📌 Pré-requisitos  
- Ter o **Java 17** instalado  
- Ter o **Maven** configurado  
- Ter o **Docker Compose** instalado  

### 📦 Compilar o projeto  
```sh
mvn clean install  
```

### 🛢️ Rodar o banco com Docker  
```sh
docker compose up -d  
```

### ▶️ Executar a aplicação  
```sh
java -jar target/learning-todolist-0.0.1-SNAPSHOT.jar
```

## 🔗 Endpoints  
A documentação completa dos endpoints está disponível no **Swagger**:  
🔗 [Swagger UI](http://localhost:8080/swagger/index.html)  

### 📋 Listar Tarefas  
- **Método:** `GET`  
- **URL:** `http://localhost:8080/todos`  
- **Descrição:** Retorna todos os todos cadastrados, ordenados por prioridade e nome.  

### ✏️ Atualizar Tarefa  
- **Método:** `PUT`  
- **URL:** `http://localhost:8080/todos/{id}`  
- **Descrição:** Atualiza um todo pelo ID e retorna a lista completa com a nova versão.  
- **Exemplo de Corpo da Requisição:**  
```json
{
  "name": "Nova Tarefa",
  "priority": 1
}  
```

### ❌ Excluir Tarefa  
- **Método:** `DELETE`  
- **URL:** `http://localhost:8080/todos/{id}`  
- **Descrição:** Remove um todo pelo ID e retorna a lista atualizada.  

### ➕ Criar Tarefa  
- **Método:** `POST`  
- **URL:** `http://localhost:8080/todos`  
- **Descrição:** Cria um novo todo se os dados enviados estiverem corretos.  
- **Exemplo de Corpo da Requisição:**  
```json
{
  "name": "Estudar Spring Boot",
  "priority": 2
}  
```

## 🧪 Testes  
O projeto conta com testes de integração para todos os endpoints. Para rodá-los, utilize o comando:  
```sh
mvn test  
```
Os testes foram desenvolvidos utilizando **JUnit** e utilizam um banco **H2** para simular a integração com o banco de dados.

---  
📌 **Observação:** Verifique o arquivo `.env.example` essas são as dependências de variáveis de ambiente do projeto. Mude o nome do arquivo para `.env` e adicione as suas credenciais.
