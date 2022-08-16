package com.orbirpinar.student.management.Seeder;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SeederRestClientConfig {


    @Value("${seeder-base-url}")
    private String BASE_URL;



    @Bean
    @Qualifier("SeederRestTemplate")
    public RestTemplate seederRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        restTemplate.setInterceptors(interceptors);
        restTemplate.setUriTemplateHandler(new RootUriTemplateHandler(BASE_URL));


        return restTemplate;
    }


    public static class SeederRestTemplateInterceptor implements ClientHttpRequestInterceptor {


        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte  [] body, ClientHttpRequestExecution execution) throws IOException, IOException {
            request.getHeaders().add("accept", "application/json");
            request.getHeaders().add("content-type", "application/json");


            return execution.execute(request, body);

        }
    }
}

