package APIAutomation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APITestCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Define the API endpoint
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

        // Send GET request
        Response response = RestAssured.get(url);

        // Verify response status code is 200
        assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        // Parse JSON response
        JsonPath jsonPath = response.jsonPath();

        // Validate that there are 3 BPIs: USD, GBP, EUR
        assertTrue(jsonPath.getMap("bpi").containsKey("USD"), "USD BPI not found");
        assertTrue(jsonPath.getMap("bpi").containsKey("GBP"), "GBP BPI not found");
        assertTrue(jsonPath.getMap("bpi").containsKey("EUR"), "EUR BPI not found");

        // Validate GBP description
        String gbpDescription = jsonPath.getString("bpi.GBP.description");
        assertEquals(gbpDescription, "British Pound Sterling", "GBP description does not match");

        System.out.println("API Test Passed: Response contains USD, GBP, EUR, and GBP description is correct.");
    }


	}


