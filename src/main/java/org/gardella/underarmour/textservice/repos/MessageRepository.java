package org.gardella.underarmour.textservice.repos;

import org.gardella.underarmour.textservice.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {



}
