package com.lrtech.toDoList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.lrtech.toDoList.dto.TodoDto;
import com.lrtech.toDoList.dto.UserDtoResponse;
import com.lrtech.toDoList.dto.auth.LoginDto;
import com.lrtech.toDoList.dto.auth.LoginResponseDto;
import com.lrtech.toDoList.service.TodoService;
import com.lrtech.toDoList.service.exceptions.ResourceNotFoundException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @Import(SecurityConfig.class)
class ToDoListApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private TodoService todoService;
	

	//testando metodos q retornam as aexceções certas 
	@Test
	public void testarMetodoDeFindById(){
		
		var id = 999l;
		assertThrows(ResourceNotFoundException.class, () -> todoService.getById(id));



	}

	@SuppressWarnings("null")
	private String obterToken() {
    return webTestClient
            .post()
            .uri("auth/login")
            .bodyValue(new LoginDto("meroteste", "123456")) // Email e senha de um usuário de teste
            .exchange()
            .expectStatus().isOk()
            .expectBody(LoginResponseDto.class)
            .returnResult()
            .getResponseBody()
            .token(); // Pega o token do response
}


	// é importante ter testes. sempre um cenário de sucesso e um de erro
	// @Test se o retorno fosse uma lista mas como retorna um item só
	// void testCreateTodoSucess() {
	// var todo = new Todo("mero","meroteste", false,1);
	// webTestClient
	// .post()
	// .uri("/todos")
	// .bodyValue(todo)
	// .exchange()
	// .expectStatus().isCreated()
	// .expectBody()
	// .jsonPath("$").isArray()
	// .jsonPath("S.length()").isEqualTo(1)
	// .jsonPath("$[0].nome").isEqualTo(todo.getNome())
	// .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
	// .jsonPath("$[0].realizado").isEqualTo(todo.getRealizado())
	// .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());

	// }

	////////////////// TESTES DO POST
	@Test // junit 5 , ja vem por causa do spring-boot-starter-test
	void testCreateTodoSucess() {
		var token = obterToken();
		var todo = new TodoDto("mero", "meroteste", false, 1, new UserDtoResponse(1l, "Dragon"));
		webTestClient // 
				.post()
				.uri("/todos")
				.header("Authorization", "Bearer " + token)
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				// .jsonPath("$").isNotEmpty()
				// .jsonPath("S.length()").isEqualTo(1)
				.jsonPath("$.nome").isEqualTo(todo.getNome())
				.jsonPath("$.descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$.realizado").isEqualTo(todo.getRealizado())
				.jsonPath("$.prioridade").isEqualTo(todo.getPrioridade())
				.jsonPath("$.userDto.id").isEqualTo(todo.getUserDto().getId())
				.jsonPath("$.userDto.nome")
				.value(nome -> assertEquals(todo.getUserDto().getNome().toLowerCase(), nome.toString().toLowerCase()));

	}

	@Test
	void testCreateTodofailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(new TodoDto("", "", false, 0, new UserDtoResponse(1l, "Dragon")))
				.exchange()
				.expectStatus().is4xxClientError(); // 422 precisamente

	}

	////////////// TESTES DO GET
	@Test
	void testGetSucess() {
		Long id = 1L;
		var token = obterToken();
		webTestClient
				.get()
				.uri("/todos/{id}", id)
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.id").isEqualTo(id);

	}

	@Test
	void testGetFailure() {
		var token = obterToken();
		Long id = 999L;

		webTestClient
				.get()
				.uri("todos/{id}", id)
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void testGetTodoByNameSuccess() {
		String todoName = "mero"; // Nome esperado (pode estar em qualquer case)
		// uri("/todos/nome?nome={nome}", todoName) - outra meneira de passar a uri
		var token = obterToken();
		webTestClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/todos/nome")
						.queryParam("nome", todoName)
						.build())
				 .header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$[0].nome")
				.value(name -> assertEquals(todoName.toLowerCase(), name.toString().toLowerCase()));// Normaliza o case antes de
																																														// comparar valu atribui
																																														// naturalmente a
																																														// propriedade
		// .jsonPath("$.descricao")
		// .value(descricao -> assertEquals("meroteste", descricao));

	}

	@Test
	void testGetTodoByNameFailure() {
		String todoName = "joao"; // Nome esperado (pode estar em qualquer case)
		var token = obterToken();

		webTestClient
				.get()
				.uri("/todos/nome?nome={nome}", todoName)
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isNotFound();

	}

	////////////////////////// TESTES DO DELETE
	@Test
	void testDeleteTodoSucess() {
		Long id = 1l;
		var token = obterToken();
		webTestClient
				.delete()
				.uri("/todos/{id}", id)
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isNoContent();

	}

	@Test
	void testDeleteFailure() {
		Long id = 999l; // Nome esperado (pode estar em qualquer case)
		var token = obterToken();

		webTestClient
				.delete()
				.uri("/todos/{id}", id)
				.header("Authorization", "Bearer " + token)
				.exchange()
				.expectStatus().isNotFound();
		// Normaliza o case antes de comparar value atribui naturalmente a propriedade
		// .jsonPath("$.descricao")
		// .value(descricao -> assertEquals("meroteste", descricao));

	}

	@Test
	void testPutSucess() {
		Long id = 1l;
		var token = obterToken();
		var todo = new TodoDto("joao", "meroteste", false, 1, new UserDtoResponse(1l, "Dragon"));
		webTestClient
				.put()
				.uri("/todos/{id}", id)
				.header("Authorization", "Bearer " + token)
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.nome").isEqualTo(todo.getNome())
				.jsonPath("$.descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$.realizado").isEqualTo(todo.getRealizado())
				.jsonPath("$.prioridade").isEqualTo(todo.getPrioridade())
				.jsonPath("$.userDto.id").isEqualTo(todo.getUserDto().getId())
				.jsonPath("$.userDto.nome")
				.value(nome -> assertEquals(todo.getUserDto().getNome().toLowerCase(), nome.toString().toLowerCase()));
	}

	@Test
	void testPutFailure(){
	// campos vazios
	Long id= 1l;
	var token = obterToken();
	var todo = new TodoDto("","", false,1,new UserDtoResponse(1l, "Dragon"));
	webTestClient
	.put()
	.uri("/todos/{id}",id)
	.header("Authorization", "Bearer " + token)
	.bodyValue(todo)
	.exchange()
	.expectStatus().is4xxClientError();
	// teste id invalido
	Long id1= 999l;
	var todo1 = new TodoDto("meroshow","teste1", false,1,new UserDtoResponse(1l, "Dragon"));
	webTestClient
	.put()
	.uri("/todos/{id}",id1)
	.header("Authorization", "Bearer " + token)
	.bodyValue(todo1)
	.exchange()
	.expectStatus().isNotFound();
	}
}
