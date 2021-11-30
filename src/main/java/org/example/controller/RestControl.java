package org.example.controller;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestControl {


    private RestTemplate restTemplate;
    private String URL = "http://91.241.64.178:7081/api/users";
    private HttpHeaders headers;

    @Autowired
    public RestControl(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie", String.join(";", restTemplate.headForHeaders(URL).get("Set-Cookie")));
    }

    @GetMapping("/api/users")
    public String getAllUsers() {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.GET, entity, String.class).getBody();
    }

    @PostMapping("/api/users")
    public String saveUser() {
        User user = new User(3L, "James", "Brown", (byte) 35);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
    }

    @PutMapping("/api/users")
    public String updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 35);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL + "/" + 3L, HttpMethod.DELETE, entity, String.class).getBody();
    }
}