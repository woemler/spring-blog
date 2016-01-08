
package me.woemler.springblog.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GitHubService {

    private static Logger logger  = LoggerFactory.getLogger(GitHubService.class);
    
    private RestTemplate restTemplate = new RestTemplate();

    private HttpEntity<String> createRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<String>(headers);
    }

    private boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return !status.equals(HttpStatus.NOT_FOUND) && (HttpStatus.Series.SERVER_ERROR.equals(series) || HttpStatus.Series.CLIENT_ERROR
            .equals(series));
    }
    
    public List<Map<String,String>> getReposByUser(String user) 
            throws MalformedURLException, Exception
    {
        List<Map<String,String>> repos = new ArrayList();
        String url = "https://api.github.com/users/" + user + "/repos";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, createRequest(), String.class);
        try {
            if (isError(response.getStatusCode())) {
                throw new RestClientException("[" + response.getStatusCode() + "] " + response.toString());
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    
}
