package me.woemler.springblog.repositories;

import me.woemler.springblog.models.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author woemler
 */
@Service
public class BlogService {
	
	private BlogRepository blogRepository;

	@Autowired
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Transactional(readOnly = true)
	public BlogPost findBySlug(String slug){
		return blogRepository.findBySlug(slug);
	}
	
	@Transactional(readOnly = true)
	public BlogPost findById(Integer id){
		return blogRepository.findOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<BlogPost> findAll(){
		return blogRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<BlogPost> findPaged(Pageable pageable){
		return blogRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public long count(){
		return blogRepository.count();
	}
	
	@Transactional
	public BlogPost save(BlogPost blogPost){
		return blogRepository.save(blogPost);
	}
	
	@Transactional
	public void delete(Integer id){
		blogRepository.delete(id);	
	}
	
	@Transactional
	public void deleteAll(){
		blogRepository.deleteAll();
	}
	
}
