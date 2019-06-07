package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MuzixServiceTest {

    private Track track;
    private Optional optional ;

    @Mock
    private MuzixRepository muzixRepository;

    @InjectMocks
    private MuzixServiceImpl muzixService;

    List<Track> list;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

        track=new Track();
        track.setTrackId("1");
        track.setTrackName("Album1");
        track.setTrackComments("melody songs");
        list=new ArrayList<>();
        list.add(track);
        optional=Optional.of(track);
    }

    @After
    public void tearDown()
    {
        track=null;
        optional=null;
    }

    @Test
    public void testSaveTrack() throws TrackAlreadyExistsException
    {
        when(muzixRepository.save((Track)any())).thenReturn(track);
        Track savedTrack= muzixService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that Repository save method is only called once
        verify(muzixRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistsException
    {
        when(muzixRepository.save(track)).thenReturn(null);
        when(muzixRepository.existsById(track.getTrackId())).thenReturn(true);
        Track savedTrack= muzixService.saveTrack(track);

        verify(muzixRepository,times(1)).save(track);
        verify(muzixRepository,times(1)).existsById(track.getTrackId());


    }
    @Test
    public void getAllTracks() {

        muzixRepository.save(track);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(list);
        List<Track> userlist = muzixService.getAllTracks();
        Assert.assertEquals(list,userlist);

        verify(muzixRepository,times(1)).save(track);
        verify(muzixRepository,times(1)).findAll();
    }

    @Test
    public void testUpdateTrackComments() throws TrackNotFoundException
    {
        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        track.setTrackComments("new songs");
        Track track1=muzixService.updateTrackComments(track.getTrackId(),track.getTrackComments());
        Assert.assertEquals("new songs",track1.getTrackComments());

        verify(muzixRepository,times(1)).save(track);
        verify(muzixRepository,times(2)).findById(track.getTrackId());
    }
    @Test(expected = TrackNotFoundException.class)
    public void testUpdateTrackCommentsFailure() throws TrackNotFoundException
    {
        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.empty());
        track.setTrackComments("new songs");
        Track track1=muzixService.updateTrackComments(track.getTrackId(),track.getTrackComments());


        verify(muzixRepository,times(1)).save(track);
        verify(muzixRepository,times(2)).findById(track.getTrackId());
    }


    @Test
    public void deleteTrack() throws TrackNotFoundException{


        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        Track deleteTrack=muzixService.deleteTrack("1");
        Assert.assertEquals("1",deleteTrack.getTrackId());

        verify(muzixRepository,times(2)).findById(track.getTrackId());
        verify(muzixRepository,times(1)).deleteById(track.getTrackId());
    }

    @Test(expected = TrackNotFoundException.class)
    public void deleteTrackFailure() throws TrackNotFoundException{


        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.empty());
        Track deleteTrack=muzixService.deleteTrack("1");

        verify(muzixRepository,times(2)).findById(track.getTrackId());
        verify(muzixRepository,times(1)).deleteById(track.getTrackId());
    }

   /* @Test
    public void findTrackByName() throws  TrackNotFoundException{

        when(muzixRepository.findTrackByName(track.getTrackName())).thenReturn(track);
        Track foundTrack=muzixService.findTrackByName(track.getTrackName());
        Assert.assertEquals("1",foundTrack.getTrackId());

        verify(muzixRepository,times(1)).findTrackByName(track.getTrackName());

    }

    @Test(expected = TrackNotFoundException.class)
    public void findTrackByNameFailure() throws  TrackNotFoundException{

        when(muzixRepository.findTrackByName(track.getTrackName())).thenReturn(null);
        Track foundTrack=muzixService.findTrackByName(track.getTrackName());

        verify(muzixRepository,times(1)).findTrackByName(track.getTrackName());

    }*/

}