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
}
