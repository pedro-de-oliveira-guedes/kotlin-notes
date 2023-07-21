package com.example.integration_tests.controller.cors.withjson

import com.example.integration_tests.TestConfigs
import com.example.integration_tests.test_containers.AbstractIntegrationTest
import com.example.integration_tests.vo.PersonVO
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class) // Apenas para testes de integração
@TestInstance(Lifecycle.PER_CLASS)
class PersonControllerCorsWithJson : AbstractIntegrationTest() {
	private lateinit var specification: RequestSpecification;
	private lateinit var objectMapper: ObjectMapper;
	private lateinit var person: PersonVO

	@BeforeAll
	fun setupTests() {
		objectMapper = ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		person = PersonVO()
	}

	@Test
	@Order(1)
	fun testCreate() {
		mockPerson()

		specification = RequestSpecBuilder()
			.addHeader(
				TestConfigs.HEADER_PARAM_ORIGIN,
				TestConfigs.ORIGIN_PEDRO
			)
			.setBasePath("/api/v1/person")
			.setPort(TestConfigs.SERVER_PORT)
			.addFilter(RequestLoggingFilter(LogDetail.ALL))
			.addFilter(ResponseLoggingFilter(LogDetail.ALL))
			.build();

		val content = given()
			.spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.body(person)
			.`when`()
			.post()
			.then()
			.statusCode(200)
			.extract()
			.body()
			.asString()

		val createdPerson = objectMapper.readValue(
			content,
			PersonVO::class.java
		)
		person = createdPerson;

		assertNotNull(createdPerson)
		assertNotNull(createdPerson.id)
		assertNotNull(createdPerson.firstName)
		assertNotNull(createdPerson.lastName)
		assertNotNull(createdPerson.address)
		assertNotNull(createdPerson.gender)

		assertTrue(createdPerson.id > 0)

		assertEquals("Nelson", createdPerson.firstName)
		assertEquals("Piquet", createdPerson.lastName)
		assertEquals("Brasília, DF, Brasil", createdPerson.address)
		assertEquals("Male", createdPerson.gender)
	}

	@Test
	@Order(2)
	fun testCreateWithBadOrigin() {
		mockPerson()

		specification = RequestSpecBuilder()
			.addHeader(TestConfigs.HEADER_PARAM_ORIGIN,
					   TestConfigs.BAD_ORIGIN)
			.setBasePath("/api/v1/person")
			.setPort(TestConfigs.SERVER_PORT)
			.addFilter(RequestLoggingFilter(LogDetail.ALL))
			.addFilter(ResponseLoggingFilter(LogDetail.ALL))
			.build();

		val content = given()
			.spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.body(person)
				.`when`()
			.post()
			.then()
				.statusCode(403)
			.extract()
			.body()
				.asString()

		assertEquals("Invalid CORS request", content);
	}

	@Test
	@Order(3)
	fun testFindById() {
		mockPerson()

		specification = RequestSpecBuilder()
			.addHeader(TestConfigs.HEADER_PARAM_ORIGIN,
					   TestConfigs.ORIGIN_LOCALHOST)
			.setBasePath("/api/v1/person")
			.setPort(TestConfigs.SERVER_PORT)
			.addFilter(RequestLoggingFilter(LogDetail.ALL))
			.addFilter(ResponseLoggingFilter(LogDetail.ALL))
			.build();

		val content = given()
			.spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.pathParam("id", person.id)
				.`when`()["{id}"]
			.then()
				.statusCode(200)
			.extract()
			.body()
				.asString()

		val createdPerson = objectMapper.readValue(
			content,
			PersonVO::class.java
		)

		assertNotNull(createdPerson)
		assertNotNull(createdPerson.id)
		assertNotNull(createdPerson.firstName)
		assertNotNull(createdPerson.lastName)
		assertNotNull(createdPerson.address)
		assertNotNull(createdPerson.gender)

		assertTrue(createdPerson.id > 0)

		assertEquals("Nelson", createdPerson.firstName)
		assertEquals("Piquet", createdPerson.lastName)
		assertEquals("Brasília, DF, Brasil", createdPerson.address)
		assertEquals("Male", createdPerson.gender)
	}

	@Test
	@Order(4)
	fun testFindByIdWithBadOrigin() {
		mockPerson()

		specification = RequestSpecBuilder()
			.addHeader(TestConfigs.HEADER_PARAM_ORIGIN,
					   TestConfigs.BAD_ORIGIN)
			.setBasePath("/api/v1/person")
			.setPort(TestConfigs.SERVER_PORT)
			.addFilter(RequestLoggingFilter(LogDetail.ALL))
			.addFilter(ResponseLoggingFilter(LogDetail.ALL))
			.build();

		val content = given()
			.spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.pathParam("id", person.id)
				.`when`()["{id}"]
			.then()
				.statusCode(403)
			.extract()
			.body()
				.asString()

		assertEquals("Invalid CORS request", content);
	}
	private fun mockPerson() {
		person.firstName = "Nelson"
		person.lastName = "Piquet"
		person.address = "Brasília, DF, Brasil"
		person.gender = "Male"
	}
}

