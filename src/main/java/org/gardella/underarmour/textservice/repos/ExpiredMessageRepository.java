package org.gardella.underarmour.textservice.repos;

import org.gardella.underarmour.textservice.models.ExpiredMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpiredMessageRepository extends JpaRepository<ExpiredMessage, Integer> {



}
