
package com.willoem.springblog.dao;

import com.willoem.springblog.models.Tag;
import java.util.List;

public interface TagDAO {
    
    List<Tag> getAllTags();
    Tag getTagByName(String tag);
    Tag getTagById(Integer id);
    Integer getTagCount();
    void saveTag(Tag tag);
    void updateTag(Tag tag);
    void deleteTag(Integer id);
    
}
