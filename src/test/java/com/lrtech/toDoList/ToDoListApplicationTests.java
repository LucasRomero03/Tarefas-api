// package com.lrtech.toDoList;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.test.web.reactive.server.WebTestClient;

// import com.lrtech.toDoList.dto.TodoDto;

// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// class ToDoListApplicationTests {
	
// 	@Autowired
// 	private WebTestClient webTestClient;
	
//  // é importante ter testes. sempre um cenário de sucesso e um de erro 
// 	// @Test se o retorno fosse uma lista mas como retorna um item só 
// 	// void testCreateTodoSucess() {
// 	// 	var todo = new Todo("mero","meroteste", false,1);
// 	// 	webTestClient
// 	// 		.post()
// 	// 		.uri("/todos")
// 	// 		.bodyValue(todo)
// 	// 		.exchange()
// 	// 		.expectStatus().isCreated()
// 	// 		.expectBody()
// 	// 		.jsonPath("$").isArray()
// 	// 		.jsonPath("S.length()").isEqualTo(1)
// 	// 		.jsonPath("$[0].nome").isEqualTo(todo.getNome())
// 	// 		.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
// 	// 		.jsonPath("$[0].realizado").isEqualTo(todo.getRealizado())
// 	// 		.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
			
// 	// }

// 	@Test // junit 5 , ja vem por causa do  spring-boot-starter-test
// 	void testCreateTodoSucess() {
// 		var todo = new TodoDto("mero","meroteste", false,1);
// 		webTestClient // -> webtestclient do Spring web flux 
// 			.post()
// 			.uri("/todos")
// 			.bodyValue(todo)
// 			.exchange()
// 			.expectStatus().isCreated()
// 			.expectBody()
// 				//.jsonPath("$").isNotEmpty()
// 				//.jsonPath("S.length()").isEqualTo(1)
// 				.jsonPath("$.nome").isEqualTo(todo.getNome())
// 				.jsonPath("$.descricao").isEqualTo(todo.getDescricao())
// 				.jsonPath("$.realizado").isEqualTo(todo.getRealizado())
// 				.jsonPath("$.prioridade").isEqualTo(todo.getPrioridade());
			
// 	}
// 	@Test
// 	void testCreateTodofailure() {
// 			webTestClient
// 				.post()
// 				.uri("/todos")
// 				.bodyValue(new TodoDto("","",false,0))
// 				.exchange()
// 				.expectStatus().isBadRequest();
				
// 	}
// @Test
// void testGetTodoByNameSuccess() {
//     String todoName = "mero";  // Nome esperado (pode estar em qualquer case)

//     webTestClient
//         .get()
//         .uri("/todos/nome?nome={nome}", todoName)
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody()
// 				.jsonPath("$").isArray()
//         .jsonPath("$[0].nome")
//         .value(name -> assertEquals(todoName.toLowerCase(), name.toString().toLowerCase())) ;// Normaliza o case antes de comparar value atribui naturalmente a propriedade
// 				//.jsonPath("$.descricao")
// 					//.value(descricao -> assertEquals("meroteste", descricao));
					
// }
// @Test
// void testGetTodoByNameFailure() {
//     String todoName = "joao";  // Nome esperado (pode estar em qualquer case)
		
//     webTestClient
//         .get()
//         .uri("/todos/nome?nome={nome}", todoName)
//         .exchange()
//         .expectStatus().is5xxServerError()
//         .expectBody()
// 				.jsonPath("$.status").isEqualTo(500);
//         // Normaliza o case antes de comparar value atribui naturalmente a propriedade
// 				//.jsonPath("$.descricao")
// 					//.value(descricao -> assertEquals("meroteste", descricao));
					
// }
// @Test
// void testDeleteTodoSucess(){
// 	Long id = 1l;
// 	webTestClient
// 		.delete()
// 		.uri("/todos/{id}",id)
// 		.exchange()
// 		.expectStatus().isNoContent();


// }
// @Test
// void testDeleteFailure() {
//     Long id = 999l; // Nome esperado (pode estar em qualquer case)
		
//     webTestClient
// 		.delete()
// 		.uri("/todos/{id}",id)
// 		.exchange()
//         .expectStatus().is5xxServerError()
//         .expectBody()
// 				.jsonPath("$.status").isEqualTo(500);
//         // Normaliza o case antes de comparar value atribui naturalmente a propriedade
// 				//.jsonPath("$.descricao")
// 					//.value(descricao -> assertEquals("meroteste", descricao));
					
// }
// @Test
// void testPutSucess(){
// 	Long id= 1l;
// 	var todo = new TodoDto("joao","meroteste", false,1);
// 	webTestClient
// 		.put()
// 		.uri("/todos/{id}",id)
// 		.bodyValue(todo)
// 		.exchange()
// 		.expectStatus().isOk()
// 		.expectBody()
// 		.jsonPath("$.nome").isEqualTo(todo.getNome())
// 				.jsonPath("$.descricao").isEqualTo(todo.getDescricao())
// 				.jsonPath("$.realizado").isEqualTo(todo.getRealizado())
// 				.jsonPath("$.prioridade").isEqualTo(todo.getPrioridade());
// }

// @Test
// void testPutFailure(){
// 	// campos vazios
// 	Long id= 1l;
// 	var todo = new TodoDto("","", false,1);
// 	webTestClient
// 		.put()
// 		.uri("/todos/{id}",id)
// 		.bodyValue(todo)
// 		.exchange()
// 		.expectStatus().isBadRequest();
// // teste id invalido
// 		Long id1= 999l;
// 	var todo1 = new TodoDto("meroshow","teste1", false,1);
// 	webTestClient
// 		.put()
// 		.uri("/todos/{id}",id1)
// 		.bodyValue(todo1)
// 		.exchange()
// 		.expectStatus().is5xxServerError()
// 		.expectBody()
// 				.jsonPath("$.status").isEqualTo(500);
// }
// }


