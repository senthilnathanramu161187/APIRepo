import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Place {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//Add place API
		
		String addReq = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -12.38,\r\n"
				+ "    \"lng\": 46.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"MDV\",\r\n"
				+ "  \"phone_number\": \"(+91) 123 4567 8765\",\r\n"
				+ "  \"address\": \"1134, ABCDef\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"Dummy location 1\",\r\n"
				+ "    \"Dummy location 2\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://abc.com\",\r\n"
				+ "  \"language\": \"Tamil\"\r\n"
				+ "}\r\n"
				+ "";
		
		System.out.println("*************** ADD PLACE ***************");
		//System.out.println("*************** ADD PLACE ***************");
		String addRresp = given().log().all()
		.queryParam("key","qaclick123")
		.body(addReq)
		.when()
		.post("/maps/api/place/add/json")
		.then().log().all()
		.assertThat().statusCode(200)
		.extract().asString();
		
		System.out.println(addRresp);
		
		JsonPath jp = new JsonPath(addRresp);
		String place_id = jp.getString("place_id");
		System.out.println("Place id : " + place_id);
		
		System.out.println("*************** Get PLACE ***************");
		String getResp = given().log().all()
		.queryParam("key","qaclick123")
		.queryParam("place_id", place_id)
		.when()
		.get("/maps/api/place/get/json")
		.then().log().all()
		.assertThat().statusCode(200).extract().asString();
		
		String update_address = "New Address 123456";
		
		
		System.out.println("*************** Update PLACE ***************");
		String updateReq = "{\r\n"
				+ "\"place_id\":\""+ place_id + "\",\r\n"
				+ "\"address\":\""+ update_address +"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "";
		
		given()
		.log().all()
		.queryParam("key","qaclick123")
		.queryParam("place_id",place_id)
		.body(updateReq)
		.when()
		.put("maps/api/place/update/json")
		.then().log().all()
		.assertThat().statusCode(200).extract().asString();
		
		String getResp2 = given().log().all()
				.queryParam("key","qaclick123")
				.queryParam("place_id", place_id)
				.when()
				.get("/maps/api/place/get/json")
				.then().log().all()
				.assertThat().statusCode(200).extract().asString();
		
		JsonPath jp1 = new JsonPath(getResp2);
		
		String respAddress = jp1.getString("address");
		System.out.println("***** Updated address :   " + respAddress);
		String respPhone = jp1.getString("phone_number");
		System.out.println("***** Updated Phone :  " + respPhone);
		
		System.out.println("*************** Compare  ***************");
		
		if(update_address.equalsIgnoreCase(respAddress))
			System.out.println("Address matched");
		else
			System.out.println("Address does not match");
		
		System.out.println("*************** Delete  ***************");
		
		//System.out.println("*************** Delete  ***************");
		
		given()
		.log()
		.all()
		.queryParam("Key", "qaclick123")
		.body("{\r\n"
				+ "    \"place_id\":\"" +place_id+ "\"\r\n"
				+ "}\r\n"
				+ "")
		.when()
		.post("/maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200);
		
		System.out.println("*************** Get PLACE ***************");
		given().log().all()
		.queryParam("key","qaclick123")
		.queryParam("place_id", place_id)
		.when()
		.get("/maps/api/place/get/json")
		.then().log().all()
		.assertThat().statusCode(404);
		
		
		System.out.println("*************** End of program ***************");
	}

}
