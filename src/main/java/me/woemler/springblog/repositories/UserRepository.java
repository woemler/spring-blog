package me.woemler.springblog.repositories;

import me.woemler.springblog.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author woemler
 */
public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
