/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service.impl;

import cn.edu.seu.wh.blog.dao.TagDao;
import cn.edu.seu.wh.blog.model.Tag;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {
    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);
    @Autowired
    TagDao tagDao;

    @Override
    public ResultInfo getAllTag() {
        List<Tag> tags=tagDao.getAllTag();
        return ResultInfo.ok(tags);
    }

    @Override
    public ResultInfo getTagByCategory(Integer id) {
        try{
            List<Map<String,Object>> tags=tagDao.getTagByCategoryid(id);
            return ResultInfo.ok(tags);
        }catch (Exception e){
            logger.error("根据Categoryid获取tag出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"根据分类获取标签时服务器出现异常");
        }
    }
}
