import pojo.*;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddAPI obj = new AddAPI();
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		List<String> typ = new ArrayList<>();
		typ.add("shoe park");
		typ.add("shop");
		
		obj.setLocation(l);
		obj.setAccuracy(50);
		obj.setName("Frontline house");
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setAddress("DLF");
		obj.setTypes(typ);
		obj.setWebsite("http://google.com");
		obj.setLanguage("language");
		
		String req = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
		
		
		
		String res = given()
		.log().all()
		.queryParam("key","qaclick123")
		.body(req)
		.when()
		.post("/maps/api/place/add/json")
		.then()
		.assertThat().statusCode(200).extract().asString();
		
		System.out.println(res);
	}

}
