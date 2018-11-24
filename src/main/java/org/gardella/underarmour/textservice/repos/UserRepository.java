package org.gardella.underarmour.textservice.repos;

import org.gardella.underarmour.textservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);


}
