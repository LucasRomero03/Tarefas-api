package com.lrtech.toDoList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.lrtech.toDoList.dto.UserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTests {
  
  @Autowired
  private WebTestClient webTestClient;

  @Test // junit 5 , ja vem por causa do  spring-boot-starter-test
	void testCreateUserSucess() {
		var user = new UserDto("mero","meroteste@gmail.com", "12345678910");
		webTestClient // -> webtestclient do Spring web flux 
			.post()
			.uri("/usuarios")
			.bodyValue(user)
			.exchange()
			.expectStatus().isCreated()
			.expectBody()
				//.jsonPath("$").isNotEmpty()
				//.jsonPath("S.length()").isEqualTo(1)
				.jsonPath("$.nome").isEqualTo(user.getNome())
				.jsonPath("$.email").isEqualTo(user.getEmail())
				.jsonPath("$.senha").isEqualTo(user.getSenha());
			
	}
  @Test 
  void testGetAllUsersSucess(){
    webTestClient
    .get()
    .uri("/usuarios")
    .exchange()
    .expectStatus().isOk()
    .expectBody()
      .jsonPath("$").isArray();

  }
//   @Test
// void testGetAllUsersPaginatedSuccess() {
//     webTestClient
//         .get()
//         .uri(uriBuilder -> uriBuilder
//             .path("/usuarios")
//             .queryParam("page", 0)
//             .queryParam("size", 2)
//             .build())
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody()
//             .jsonPath("$.content").isArray() // Verifica que 'content' é um array
//             .jsonPath("$.content.length()").isEqualTo(2) // Verifica que a página tem 2 elementos
//             .jsonPath("$.content[0].id").isNotEmpty() // Valida que o primeiro usuário tem um 'id'
//             .jsonPath("$.content[0].nome").isNotEmpty() // Valida que o 'nome' do primeiro usuário não é vazio
//             .jsonPath("$.page").isEqualTo(0) // Valida o número da página
//             .jsonPath("$.size").isEqualTo(2) // Verifica o tamanho da página
//             .jsonPath("$.totalElements").isNotEmpty(); // Valida que 'totalElements' existe
// }

}
