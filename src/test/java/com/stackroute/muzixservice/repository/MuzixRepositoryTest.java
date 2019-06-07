package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MuzixRepositoryTest {

    @Autowired
    private MuzixRepository muzixRepository;
    private Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId("2");
        track.setTrackName("Album1");
        track.setTrackComments("Old");
    }

    @After
    public void tearDown()
    {

        muzixRepository.deleteAll();
        track=null;
    }


    @Test
    public void testSaveTrack()
    {
        muzixRepository.save(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals("2", fetchTrack.getTrackId());

    }

    @Test
    public void testSaveTrackFailure() {
        Track testtrack = new Track("3", "Album3", "Old songs");
        muzixRepository.save(testtrack);
        Track fetchTrack = muzixRepository.findById(testtrack.getTrackId()).get();
        Assert.assertNotEquals(testtrack, track);
    }

    @Test
    public void testGetAllTracks() {
        Track track = new Track("5", "Album5", "popsongs");
        Track track1 = new Track("6", "Album6", "melodysongs");
        muzixRepository.save(track);
        muzixRepository.save(track1);

        List<Track> trackList = muzixRepository.findAll();
        Assert.assertEquals("Album6", trackList.get(1).getTrackName());
    }

    @Test
    public void testDeleteTrack()
    {
        Track track=new Track("7","Album7","long drive");
        muzixRepository.save(track);
        muzixRepository.deleteById(track.getTrackId());
        Optional optional=muzixRepository.findById(track.getTrackId());
        Assert.assertEquals(Optional.empty(),optional);
    }

    @Test
    public void testDeleteTrackFailure()
    {
        Track track1=new Track("8","Album8","long drive");
        muzixRepository.save(track1);
        muzixRepository.deleteById(track1.getTrackId());
        Optional optional=muzixRepository.findById(track.getTrackId());
        boolean value=optional.isPresent();
        Assert.assertNotEquals(Optional.empty(),value);
    }

  /*  @Test
    public void testTrackByName()
    {
        Track track=new Track("8","Album8","Night time");
        muzixRepository.save(track);
        Track fetchTrack=muzixRepository.findTrackByName("Album8");
        Assert.assertEquals("8",fetchTrack.getTrackId());
    }
    @Test
    public void testTrackByNameFailure()
    {
        Track track=new Track("8","Album8","Night time");
        muzixRepository.save(track);
        Track fetchTrack=muzixRepository.findTrackByName("Album8");
        Assert.assertNotEquals("9",fetchTrack.getTrackId());
    }*/
}
