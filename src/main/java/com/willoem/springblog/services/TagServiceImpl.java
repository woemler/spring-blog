
package com.willoem.springblog.services;

import com.willoem.springblog.dao.TagDAO;
import com.willoem.springblog.models.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    
    @Autowired
    private TagDAO tagDao;
    
    @Override
    public List<Tag> getAllTags(){
        return tagDao.getAllTags();
    }
    
    @Override
    public Tag getTagByName(String tag){
        return tagDao.getTagByName(tag);
    }
    
    @Override
    public Tag getTagById(Integer id){
        return tagDao.getTagById(id);
    }
    
    @Override
    public Integer getTagCount(){
        return tagDao.getTagCount();
    }
    
    @Override
    public void saveTag(Tag tag){
        tagDao.saveTag(tag);
    }
    
    @Override
    public void updateTag(Tag tag){
        tagDao.updateTag(tag);
    }
    
    @Override
    public void deleteTag(Integer id){
        tagDao.deleteTag(id);
    }
    
}
