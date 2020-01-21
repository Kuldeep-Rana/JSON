package json.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SchemaValidator {
	static InputStream schemaIn = null;
	static InputStream json = null;
	public static void main(String[] args) throws IOException {

		System.out.println("trying to validate valid json");
		schemaIn = getResouceAsStream("Person.json");
		json = getResouceAsStream("person_valid_json.json");
		
		validate(schemaIn, json);
		
		closeStream();
		
		System.out.println("trying to validate invalid json");
		
		schemaIn = getResouceAsStream("Person.json");
		json = getResouceAsStream("person_invalid_json.json");
		
		validate(schemaIn, json );
		
		
		
	}

	private static void closeStream() throws IOException {
		json .close();
		schemaIn.close();
		
	}

	private static void validate(InputStream schemaIn, InputStream json) {
		try {

			JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaIn));

			JSONObject jsonSubject = new JSONObject(new JSONTokener(json));

			Schema schema = SchemaLoader.load(jsonSchema);
			
			schema.validate(jsonSubject);
			
			System.out.println("validation successful");
			
		} catch (ValidationException e) {
			System.out.println("schema validation failed");
			e.printStackTrace();

		}
	}

	private static InputStream getResouceAsStream(String path) throws FileNotFoundException {
		File file = new File(path);
		return new FileInputStream(file);
	}
}
