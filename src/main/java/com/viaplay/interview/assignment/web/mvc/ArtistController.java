package com.viaplay.interview.assignment.web.mvc;

import com.viaplay.interview.assignment.domain.Artist;
import com.viaplay.interview.assignment.service.ArtistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: boyang, created on Jun
 */
@RestController
@RequestMapping("/api/v1")
public class ArtistController {

    private final Logger log = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistService artistService;


    @GetMapping("/artists/{id}")
    public ResultBean getProduct(@PathVariable String id) {
        log.debug("REST request to get Artist : {}", id);
        Artist artist = artistService.find(id);
        if (artist.getMbid().isEmpty()) {
            return ResultBean.error(1404, "not found");
        }
        return ResultBean.success(artist);
    }
}
