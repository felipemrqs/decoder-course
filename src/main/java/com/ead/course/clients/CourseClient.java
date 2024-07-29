package com.ead.course.clients;

import com.ead.course.dtos.UserDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class CourseClient {

    private final RestTemplate restTemplate;

    public CourseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Page<UserDto> getAllUsersByCourse(UUID courseId, Pageable pageable) {
        String REQUEST_URI = "http://localhost:8081";
        String url = REQUEST_URI + "/users?courseId=" + courseId + "?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(":", ",");
        ParameterizedTypeReference<Page<UserDto>> responseType = new ParameterizedTypeReference<Page<UserDto>>() {};
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }
}
