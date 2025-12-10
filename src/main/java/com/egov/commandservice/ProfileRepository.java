package com.egov.commandservice;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface ProfileRepository extends MongoRepository<Profile, String>
{



}
