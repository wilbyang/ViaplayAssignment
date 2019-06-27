package com.viaplay.interview.assignment.integration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viaplay.interview.assignment.AssignmentApplication;
import com.viaplay.interview.assignment.domain.Artist;
import com.viaplay.interview.assignment.web.mvc.ResultBean;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Author: boyang, created on Jun
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssignmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
    @Test
    public void givenApplication_whenAccessHome_then404NotFound() {
        int statusCode = RestAssured.get("http://localhost:8080/").statusCode();
        assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }

    @Test
    public void givenApplication_whenAccessApi_thenReturnCorrectData() {

        RestAssured.get("http://localhost:8080/api/v1/artists/f27ec8db-af05-4f36-916e-3d57f91ecf5e").then().body("data.mbid", new ResponseAwareMatcher<Response>() {
            public Matcher<?> matcher(Response response) {
                return equalTo("f27ec8db-af05-4f36-916e-3d57f91ecf5e");
            }
        });
    }
}
