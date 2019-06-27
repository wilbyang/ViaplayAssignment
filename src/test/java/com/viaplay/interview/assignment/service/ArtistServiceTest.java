package com.viaplay.interview.assignment.service;

import com.viaplay.interview.assignment.domain.Artist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: boyang, created on Jun
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;

    @Test
    public void findOne() {
        Artist artist = artistService.find("f27ec8db-af05-4f36-916e-3d57f91ecf5e");
        Assert.assertNotNull(artist);
    }

    @Test
    public void findOneNotExist() {
        Artist artist = artistService.find("f27ec8db-af05-4f36-916e-3d57f91ecf5f");
        Assert.assertTrue(artist.getMbid().isEmpty());
    }
}