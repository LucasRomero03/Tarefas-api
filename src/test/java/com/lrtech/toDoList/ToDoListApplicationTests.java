package com.lrtech.toDoList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.lrtech.toDoList.dto.TodoDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ToDoListApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
 // é importante ter testes. sempre um cenário de sucesso e um de erro 
	// @Test se o retorno fosse uma lista 
	// void testCreateTodoSucess() {
	// 	var todo = new Todo("mero","meroteste", false,1);
	// 	webTestClient
	// 		.post()
	// 		.uri("/todos")
	// 		.bodyValue(todo)
	// 		.exchange()
	// 		.expectStatus().isCreated()
	// 		.expectBody()
	// 		.jsonPath("$").isArray()
	// 		.jsonPath("S.length()").isEqualTo(1)
	// 		.jsonPath("$[0].nome").isEqualTo(todo.getNome())
	// 		.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
	// 		.jsonPath("$[0].realizado").isEqualTo(todo.getRealizado())
	// 		.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
			
	// }

	@Test
	void testCreateTodoSucess() {
		var todo = new TodoDto("mero","meroteste", false,1);
		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isCreated()
			.expectBody()
				//.jsonPath("$").isNotEmpty()
				//.jsonPath("S.length()").isEqualTo(1)
				.jsonPath("$.nome").isEqualTo(todo.getNome())
				.jsonPath("$.descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$.realizado").isEqualTo(todo.getRealizado())
				.jsonPath("$.prioridade").isEqualTo(todo.getPrioridade());
			
	}
	@Test
	void testCreateTodofailure() {
			webTestClient
				.post()
				.uri("/todos")
				.bodyValue(new TodoDto("","",false,0))
				.exchange()
				.expectStatus().isBadRequest();
				
	}
	// @Test
	// void testeGetTodoByName(){
	// 	webTestClient
	// 	.get()
	// 	.uri("/todos/nome")
	// 	.exchange()
	// 	.expectStatus().isOk()
	// 	.expectBody()
	// 		.jsonPath("$.content")

	// }
}
