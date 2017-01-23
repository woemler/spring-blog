package me.woemler.springblog.repositories;

import me.woemler.springblog.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author woemler
 */
public interface UserRepository extends MongoRepository<User, String>, UserDetailsService {
}
