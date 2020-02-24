/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package cn.edu.seu.wh.blog.dao.provider;


import cn.edu.seu.wh.blog.model.ArticleTag;

import java.text.MessageFormat;
import java.util.List;


/**
 * @program:blog
 * @description:
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-21 11:32
 **/
public class ArticleTagDaoProvider {
    public String addArticleTagBatch(List<ArticleTag> list){
        StringBuilder sb=new StringBuilder();
        sb.append("insert into articletag ");
        sb.append("(articleid,tagid) ");
        sb.append("values ");
        MessageFormat mf=new MessageFormat(" #'{' list[{0}].articleid},#'{'list[{0}].tagid}");
        for(int i=0;i<list.size();i++) {
            sb.append("(");
            String str = mf.format(new Object[]{i});
            sb.append(str);
            sb.append(")");
            if(i<list.size()-1){
                sb.append(",");
            }

        }
        return sb.toString();
    }
}
