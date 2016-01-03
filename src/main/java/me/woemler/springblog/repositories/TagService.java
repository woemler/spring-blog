package me.woemler.springblog.repositories;

import me.woemler.springblog.models.Tag;
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
public class TagService {
	
	private TagRepository tagRepository;

	@Autowired
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Transactional(readOnly = true)
	public Tag findByTagName(String slug){
		return tagRepository.findByTagName(slug);
	}
	
	@Transactional(readOnly = true)
	public List<String> findTagNamesByFragment(String fragment){
		return tagRepository.findDistincTagNameByFragment(fragment);
	}
	
	@Transactional(readOnly = true)
	public Tag findById(Integer id){
		return tagRepository.findOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<Tag> findAll(){
		return tagRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Tag> findPaged(Pageable pageable){
		return tagRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public long count(){
		return tagRepository.count();
	}
	
	@Transactional
	public Tag save(Tag tag){
		return tagRepository.save(tag);
	}
	
	@Transactional
	public void delete(Integer id){
		tagRepository.delete(id);	
	}
	
	@Transactional
	public void deleteAll(){
		tagRepository.deleteAll();
	}
	
}
