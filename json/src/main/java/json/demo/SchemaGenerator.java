package json.demo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.reinert.jjschema.JsonSchemaGenerator;
import com.github.reinert.jjschema.SchemaGeneratorBuilder;

import json.demo.model.Address;
import json.demo.model.Person;

public class SchemaGenerator {
	private static ObjectMapper mapper = new ObjectMapper();
	public static final String JSON_SCHEMA_DRAFT4_VALUE = "http://json-schema.org/draft-04/schema#";
	public static final String JSON_SCHEMA_ELEMENT = "$schema";

	static {
		// required for pretty printing
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public static void main(String[] args) throws JsonProcessingException {

		JsonSchemaGenerator v4generator = SchemaGeneratorBuilder.draftV4Schema().build();

		JsonNode schemaNode = v4generator.generateSchema(Person.class);

		((ObjectNode) schemaNode).put(JSON_SCHEMA_ELEMENT, JSON_SCHEMA_DRAFT4_VALUE);

		prettyPrintSchema(schemaNode);
		
		System.out.println("========================== generating sample json =========================");
		
		generateSampleJSON();
	}

	private static void prettyPrintSchema(JsonNode schema) throws JsonProcessingException {
		System.out.println(mapper.writeValueAsString(schema));
	}
	
	
	private static void generateSampleJSON() throws JsonProcessingException {
		
		List<Address> addressList = Arrays.asList(
			new Address("street 1", "state 1", "country 1", 11111),
			new Address("street 2", "state 2", "country 2", 22222),
			new Address("street 3", "state 3", "country 3", 33333)
		);
		
		Person person = new Person(1,"Tom","M",addressList);
		
		System.out.println(mapper.writeValueAsString(person));
	}
	
}
