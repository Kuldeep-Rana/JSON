package json.demo.model;

import java.io.Serializable;

import com.github.reinert.jjschema.Attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable{

	private static final long serialVersionUID = 7998525368121585012L;

	@Attributes(required = true, description = "street")
	private String street;
	
	@Attributes(required = true)
	private String state;
	
	@Attributes(required = true)
	private String country;
	
	@Attributes(required =  true, pattern = "\\D(\\d{5})\\D")
	private int zip;
}
