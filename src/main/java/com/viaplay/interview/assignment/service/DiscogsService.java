package com.viaplay.interview.assignment.service;

import com.viaplay.interview.assignment.domain.DiscogProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Author: boyang, created on Jun
 */
@Service
public class DiscogsService {

    private static final Logger log = LoggerFactory.getLogger(DiscogsService.class);
    public static DiscogProfile NOT_FOUND = DiscogProfile.builder().profile("").build();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.discogs.enpoint.prefix}")
    private String discogsEndpointPrefix;

    @Cacheable(value = "discog", unless = "#result.profile.isEmpty()")
    public DiscogProfile findDiscogProfile(Integer id) {
        // get profile
        String discogsEndpoint = String.format("%s/artists/%d",
                discogsEndpointPrefix, id);

        DiscogProfile profile;

        try {
            profile = restTemplate.getForObject(discogsEndpoint, DiscogProfile.class);
        } catch (HttpClientErrorException ex) {
            profile = NOT_FOUND;
        }
        return profile;
    }
}
