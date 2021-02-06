package platform.api;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import platform.api.support.RandomEmployee;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ApiStepDefinitions {
    private Response response;
    private RandomEmployee employee;
    private byte[] image;

    @Before("@api")
    public void setup() {
        response = null;
        employee = null;
        image = null;
    }

    @After("@api")
    public void tearDown(Scenario scenario) {
        if (response != null) {
            String attachmentName = String.join("_", scenario.getName().split("\\s+"));
            attachmentName = attachmentName + "_" + scenario.getStatus();
            scenario.attach(response.prettyPrint(), "text/plain", attachmentName);
        }
        if (employee != null) {
            scenario.attach(employee.toString(), "text/plain", employee.getName());
        }
        if (image != null) {
            scenario.attach(image, "image/png", UUID.randomUUID().toString());
        }
    }

    @Given("um empregado gerado randomicamente")
    public void createRandomEmployee() {
        this.employee = new RandomEmployee();
    }

    @Given("o nome do empregado é alterado")
    public void editEmployee(List<Map<String, String>> replacements) {
        String name = replacements.get(0)
                .get("employee_name")
                .replace("{UUID}", "" + UUID.randomUUID());
        int salary = Integer.parseInt(replacements.get(0).get("employee_salary"));
        int age = Integer.parseInt(replacements.get(0)
                .get("employee_age")
                .replace("{random_age}", "" + new Random().nextInt(99)));
        String profileImage = replacements.get(0).get("profile_image");
        employee = new RandomEmployee(name, salary, age, profileImage);
    }

    @When("uma requisição {string} é efetuada para {string}")
    public void makeRequest(String method, String endpoint) {
        if (employee != null) endpoint = endpoint.replaceAll("\\{id}", "" + employee.getId());
        int intermittentErrorStatusCode = 429;
        do {
            switch (method) {
                case "GET":
                    this.response = RestAssured.when().get(endpoint);
                    break;
                case "POST":
                    this.response = RestAssured.given()
                            .contentType(ContentType.JSON)
                            .and()
                            .body(employee)
                            .when()
                            .post(endpoint);
                    if (response.getStatusCode() == intermittentErrorStatusCode) break;
                    employee.setId(JsonPath.from(response.asString()).getInt("data.id"));
                    break;
                case "PUT":
                    employee = new RandomEmployee();
                    this.response = RestAssured.given()
                            .contentType(ContentType.JSON)
                            .and()
                            .body(employee)
                            .when()
                            .put(endpoint);
                    break;
                case "DELETE":
                    this.response = RestAssured.when().delete(endpoint);
                    break;
                default:
                    throw new IllegalArgumentException("method: " + method + ", endpoint: " + endpoint);
            }
        } while (response.getStatusCode() == intermittentErrorStatusCode);

    }

    @Then("o status da resposta é {int}")
    public void assertStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("o corpo de resposta deve conter o schema")
    public void assertSchemaValidation(String schema) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    @Then("a imagem deve ser baixada usando o campo {string}")
    public void assertImageIsDownloaded(String jsonPath) {
        URL url;
        ByteArrayOutputStream out = null;
        try {
            url = new URL(JsonPath.from(response.asString()).getString(jsonPath));
            InputStream in = new BufferedInputStream(url.openStream());
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(out);
        image = out.toByteArray();
    }
}
