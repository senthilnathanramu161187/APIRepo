
import static io.restassured.RestAssured.*;


import io.restassured.path.json.JsonPath;

public class OAuth {
	
	
	public static void main(String args[]) throws InterruptedException {
		
		/*
		 * System.setProperty("webdriver.chrome.driver", "E:\\new\\chromedriver.exe");
		 * WebDriver driver = new ChromeDriver();
		 */
		
		/*
		 * driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php"
		 * ); driver.manage().window().maximize();
		 * 
		 * driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(
		 * "senthu87");
		 * driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		 * Thread.sleep(2000);
		 * driver.findElement(By.xpath("//input[@name='password']")).sendKeys(
		 * "Senram1407$");
		 * driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		 * Thread.sleep(3000);
		 * driver.findElement(By.xpath("//span[contains(text(),'Send')]")).click();
		 * Thread.sleep(15000);
		 * driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		 * Thread.sleep(3000);
		 */
		String title = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjz2Nb5vwv2_zLIoEzUe2vyRuvSWJy819cC-xvaTyylCT1ZVA-7aI5Bwfcwz7Z93w&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		System.out.println("Title : " + title);
		
		String a[] = title.split("&scope=");
		System.out.println("a[0] : " + a[0] );
		String b[]=a[0].split("code=");
		String code = b[1];
		System.out.println("Code : " + code);
		
		String accessTokenRes = given().urlEncodingEnabled(false)
		.queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		System.out.println(accessTokenRes);
		
		JsonPath js = new JsonPath(accessTokenRes);
		String accessToken = js.getString("access_token");
		
		System.out.println("Access Token : " + accessToken);
		
		String course = given()
		.queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(course);
		
		
		
	}

}
