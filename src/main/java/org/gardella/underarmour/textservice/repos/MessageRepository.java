package org.gardella.underarmour.textservice.repos;

import org.gardella.underarmour.textservice.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {



}
