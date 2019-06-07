package com.stackroute.muzixservice.prefill;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerDemo implements CommandLineRunner {


    @Value("${track2.id}")
    private String id;
    @Value("${track2.name}")
    private String name;
    @Value("${track2.comment}")
    private String comment;

    @Autowired
    MuzixRepository muzixRepository;
    Track track=new Track();

    @Override
    public void run(String... args) throws Exception {

        track.setTrackId(id);
        track.setTrackName(name);
        track.setTrackComments(comment);
        muzixRepository.save(track);

    }
}
