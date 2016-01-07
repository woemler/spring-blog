package me.woemler.springblog.repositories;

import com.mongodb.DBCollection;
import me.woemler.springblog.models.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author woemler
 */
public class BlogRepositoryImpl implements CustomBlogRepository {
	
	private final MongoOperations mongoOperations;

	@Autowired
	public BlogRepositoryImpl(
			MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	private DBCollection getCollection(){
		return mongoOperations.getCollection(mongoOperations.getCollectionName(BlogPost.class));
	}

	@Override 
	public List<String> findTagsByFragment(String fragment) {
		Pattern pattern = Pattern.compile("^.*?" + fragment + ".*$", Pattern.CASE_INSENSITIVE);
		List<String> matched = new ArrayList<>();
		for (String tag: this.findAllTags()){
			if (pattern.matcher(tag).find()){
				matched.add(tag);
			}
		}
		return matched;
	}

	@Override 
	public List<String> findAllTags() {
		return getCollection().distinct("tags");
	}
}
