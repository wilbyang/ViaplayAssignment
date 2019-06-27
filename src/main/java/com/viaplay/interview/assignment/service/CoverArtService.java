package com.viaplay.interview.assignment.service;

import com.viaplay.interview.assignment.domain.ReleaseArtCover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Author: boyang, created on Jun
 */
@Service
public class CoverArtService {

    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);
    private static final ReleaseArtCover NOT_FOUND = ReleaseArtCover.builder()
            .images(Collections.emptyList())
            .build();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.coverart.enpoint.prefix}")
    private String discogsEndpointPrefix;

    @Cacheable(value = "coverArt", unless = "#result.images.size() == 0")
    public ReleaseArtCover findReleaseCover(String id) {
        // get profile, example endpoint http://coverartarchive.org/release-group/f32fab67-77dd-3937-addc-9062e28e4c37

        String discogsEndpoint = String.format("%s/%s",
                discogsEndpointPrefix, id);

        ReleaseArtCover releaseArtCover;

        try {
            releaseArtCover = restTemplate.getForObject(discogsEndpoint, ReleaseArtCover.class);
        } catch (HttpClientErrorException ex) {
            releaseArtCover = NOT_FOUND;
        }
        return releaseArtCover;
    }
}
