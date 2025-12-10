package com.egov.commandservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MainRestController
{
    private static final Logger logger = LoggerFactory.getLogger(MainRestController.class);

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    Producer producer;

    @PostMapping("create/profile")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) throws JsonProcessingException {
        logger.info("Received request to Create Profile");
        profileRepository.save(profile);
        logger.info("Profile Saved Successfully in MongoDB");

        ProfileEvent profileEvent = new ProfileEvent();
        profileEvent.setProfile(profile);
        profileEvent.setType("CREATE");

        producer.pubProfileEvent(profileEvent);
        logger.info("Profile Created CDC to Kafka");

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }


}
