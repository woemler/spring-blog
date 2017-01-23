package me.woemler.springblog.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import me.woemler.springblog.models.User;

/**
 * @author woemler
 */
public class UserRepositoryImpl implements UserDetailsService {

    @Autowired private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), User.class);
    }
}
