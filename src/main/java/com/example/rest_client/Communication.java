package com.example.rest_client;

import com.example.rest_client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final RestTemplate restTemplate;

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String URL = "http://localhost:4048/spring_boot_rest/api/users";

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }

    public User getUser(int id) {
        return restTemplate.getForObject(URL + "/" + id, User.class);
    }

    public void saveUser(User user) {
        int id = user.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, user, String.class);
            System.out.println("New user was added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, user);
            System.out.println("User with ID " + id + " was updated");
        }
    }

    public void deleteUser(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with ID " + id + " was deleted from DB");
    }

}