package com.example.integration_tests.swagger

import com.example.integration_tests.ConfigTest
import com.example.integration_tests.test_containers.AbstractIntegrationTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest : AbstractIntegrationTest() {
	@Test
	fun shouldDisplaySwaggerUiPage() {
		val content = given()
			.basePath("/swagger-ui/index.html")
			.port(ConfigTest.SERVER_PORT)
				.`when`()
			.get()
			.then()
				.statusCode(200)
			.extract()
			.body()
				.asString()

		assertTrue(content.contains("Swagger UI"));
	}
}

