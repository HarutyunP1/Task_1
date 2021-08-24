package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredUtils {

    //Sets Base URI
    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    //Sets base path
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    //Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    //Reset base path
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }

    //Sets ContentType
    public static void setContentType(ContentType Type) {
        given().contentType(Type);
    }

    //get request specifications
    public static RequestSpecification getRequestSpecifications() {
        return RestAssured.given();
    }

    //reset existing instance
    public void restAssuredReset() {
        RestAssured.reset();
    }

    //add query param to request specifications
    public void addQueryParam(RequestSpecification requestSpecification, String queryParam, String queryParamValue) {
        requestSpecification.queryParam(queryParam, queryParamValue);
    }

    //add headers to request specifications
    public void addHeaderToRequest(RequestSpecification requestSpecification, String header, String headerValue) {
        requestSpecification.header(header, headerValue);
    }

    //add body to request specifications
    public void addBodyToApi(RequestSpecification requestSpecification, String body) {
        requestSpecification.body(body);
    }


    //get Api Response
    public static Response getApiResponse(Method type, RequestSpecification requestSpecification) {
        Response response;
        if (type == Method.GET) {
            response = requestSpecification.get();
        } else if (type == Method.POST) {
            response = requestSpecification.post();
        } else {
            response = null;
        }
        return response;
    }

    //Returns JsonPath object
    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
        //System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }
}