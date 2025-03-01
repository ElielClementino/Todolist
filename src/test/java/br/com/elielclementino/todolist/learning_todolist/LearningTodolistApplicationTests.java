package br.com.elielclementino.todolist.learning_todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.elielclementino.todolist.learning_todolist.entity.Todo;
import br.com.elielclementino.todolist.learning_todolist.repository.TodoRepository;

@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=update"
})

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LearningTodolistApplicationTests {
    @Autowired
	private TodoRepository todoRepository;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testListSuccess() {
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));

		webTestClient
		.get()
		.uri("/todos")
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.length()").isEqualTo(3);
	}

    @Test
	void testCreateTodoSuccess() {
		var todo = new Todo("Todo test", "Todo feito para teste", false, 1);

		webTestClient
		.post()
		.uri("/todos")
		.bodyValue(todo)
		.exchange()
		.expectBody()
		.jsonPath("$").isArray()
		.jsonPath("$.length()").isEqualTo(1)
		.jsonPath("$[0].name").isEqualTo(todo.getName())
		.jsonPath("$[0].description").isEqualTo(todo.getDescription())
		.jsonPath("$[0].accomplished").isEqualTo(todo.getAccomplished())
		.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
		.post()
		.uri("/todos")
		.bodyValue(
			new Todo("", "", false, 1)
		)
		.exchange()
		.expectStatus().isBadRequest();
	}

	@Test
	void testIfOrdenationisWorking() {
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 2));
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 3));

		webTestClient
		.get()
		.uri("/todos")
		.exchange()
		.expectBody()
		.jsonPath("$.length()").isEqualTo(3)
		.jsonPath("$[0].priority").isEqualTo(1)
		.jsonPath("$[2].priority").isEqualTo(3);
	}

    @Test
	void testUpdateSuccess() {
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));

		webTestClient
		.put()
		.uri("/todos/1")
		.bodyValue(
			new Todo("Tarefa1", "TarefaDesc1Updated", false, 2)
		)
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.length()").isEqualTo(1)
		.jsonPath("$[0].description").isEqualTo("TarefaDesc1Updated")
		.jsonPath("$[0].priority").isEqualTo(2);
	}

	@Test
	void testupdateFailure() {
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));

		webTestClient
		.put()
		.uri("/todos/2")
		.bodyValue(
			new Todo("Tarefa1", "TarefaDesc1", false, 1)
		)
		.exchange()
		.expectStatus().isBadRequest();
	}

    @Test
	void testDeleteSuccess() {
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));

		webTestClient
		.delete()
		.uri("/todos/1")
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.length()").isEqualTo(0);
	}

	@Test
	void testDeleteFailure() {
		todoRepository.save(new Todo("Tarefa1", "TarefaDesc1", false, 1));

		webTestClient
		.delete()
		.uri("/todos/3")
		.exchange()
		.expectStatus().isBadRequest();
	}
}
