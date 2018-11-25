package org.gardella.underarmour.textservice.repos;

import org.gardella.underarmour.textservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);


}
