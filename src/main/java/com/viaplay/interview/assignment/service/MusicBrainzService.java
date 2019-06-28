package com.viaplay.interview.assignment.service;

import com.viaplay.interview.assignment.domain.DiscogProfile;
import com.viaplay.interview.assignment.domain.MusicBrainz;
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
public class MusicBrainzService {


    private static final Logger log = LoggerFactory.getLogger(MusicBrainzService.class);
    public static MusicBrainz NOT_FOUND = MusicBrainz.builder().sortName("").build();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.musicbrainz.enpoint.prefix}")
    private String musicbrainzsEndpointPrefix;

    @Cacheable(value = "musicBrainz", unless = "#result.sortName.isEmpty()")
    public MusicBrainz find(String id) {
        String musicBrainzEndpoint = String.format("%s/ws/2/artist/%s?&fmt=json&inc=url-rels+release-groups",
                musicbrainzsEndpointPrefix, id);

        MusicBrainz musicBrainz;

        try {
            musicBrainz = restTemplate.getForObject(musicBrainzEndpoint, MusicBrainz.class);
        } catch (Exception ex) {
            log.error("calling musicBrainz api error", ex);
            musicBrainz = NOT_FOUND;
        }
        return musicBrainz;
    }
}
