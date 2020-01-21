package json.demo.model;

import java.io.Serializable;
import java.util.List;

import com.github.reinert.jjschema.Attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable{

	private static final long serialVersionUID = -2448646209278616790L;

	@Attributes(required = true, maximum = 10000, minimum = 1, pattern = "^\\d+$")
	private int id;
	
	@Attributes(required = true, description = "person name")
	private String name;
	
	@Attributes(required = true, description = "gender")
	private String gender;
	
	@Attributes(required = true, description = "adress of person")
	private List<Address> address;
	
	
}
