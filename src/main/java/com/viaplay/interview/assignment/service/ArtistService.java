package com.viaplay.interview.assignment.service;

import com.viaplay.interview.assignment.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: boyang, created on Jun
 */
@Service
public class ArtistService {

    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);
    public static Artist NOT_FOUND = Artist.builder().mbid("").build();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscogsService discogsService;

    @Autowired
    private CoverArtService coverArtService;
    @Autowired
    private MusicBrainzService musicBrainzService;

    @Cacheable(value = "artist",unless = "#result.mbid.isEmpty()")
    public Artist find(String id) {

        MusicBrainz musicBrainz = musicBrainzService.find(id);
        if (musicBrainz.getSortName().isEmpty()) {
            return NOT_FOUND;
        }
        // get albums,

        List<Album> album = musicBrainz.getReleaseGroups().stream().
                filter(releaseGroup -> releaseGroup.getPrimaryType().equals("Album"))
                .map(releaseGroup -> {
                    //// get image url, Todo:this part we might need multithreading to do this
                    ReleaseArtCover releaseArtCover = coverArtService.findReleaseCover(releaseGroup.getId());
                    Optional<Cover> first = releaseArtCover
                            .getImages().stream()
                            .findFirst();

                    String image = first.isPresent() ? first.get().getImage() : "";

                    return Album.builder()
                            .id(releaseGroup.getId())
                            .title(releaseGroup.getTitle())
                            .image(image)
                            .build();
                })
                .collect(Collectors.toList());

        DiscogProfile profile = discogsService.findDiscogProfile(15885);


        Artist artist = Artist.builder()
                .mbid(id)
                .description(profile.getProfile())
                .albums(album)
                .build();
        return artist;
    }
}
