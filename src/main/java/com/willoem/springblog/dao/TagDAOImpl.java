
package com.willoem.springblog.dao;

import com.willoem.springblog.models.Tag;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDAOImpl implements TagDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public List<Tag> getAllTags(){
        return getCurrentSession()
                .createQuery("from Tag order by tagName")
                .list();
    }
    
    @Override
    public Tag getTagByName(String tag){
        List<Tag> tags = (List<Tag>) getCurrentSession()
                .createQuery("from Tag t where t.tagName = :tag")
                .setParameter("tag", tag)
                .list();
        Tag tagObj;
        if (tags.size() > 0){
            tagObj = tags.get(0);
        }
        else {
            tagObj = null;
        }
        return tagObj;
    }
    
    @Override
    public Tag getTagById(Integer id){
        List<Tag> tags = getCurrentSession()
                .createQuery("from Tag t where t.tagId = :id")
                .setParameter("id", id)
                .list();
        Tag tagObj;
        if (tags.size() > 0){
            tagObj = tags.get(0);
        }
        else {
            tagObj = null;
        }
        return tagObj;
    }
    
    @Override
    public Integer getTagCount(){
        return ((Long) getCurrentSession()
                .createQuery("select count(*) from Tag")
                .uniqueResult())
                .intValue();
    }
    
    @Override
    public void saveTag(Tag tag){
        getCurrentSession().save(tag);
    }
    
    @Override
    public void updateTag(Tag tag){
        getCurrentSession().update(tag);
    }
    
    @Override
    public void deleteTag(Integer id){
        getCurrentSession().delete(this.getTagById(id));
    }
    
}
