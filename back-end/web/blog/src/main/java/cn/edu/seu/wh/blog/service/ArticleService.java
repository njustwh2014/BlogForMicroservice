/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service;



import cn.edu.seu.wh.blog.model.ArticleInfo;
import cn.edu.seu.wh.blog.model.ArticleRecive;
import cn.edu.seu.wh.blog.result.ResultInfo;

import java.util.List;
import java.util.Map;

public interface ArticleService {
 public ResultInfo publish(ArticleRecive articleRecive);
 public ResultInfo view(Integer id);
 public ResultInfo getAllArticle(Integer pageNumber,Integer pageSize);
 public ResultInfo getHotArticles();
 public ResultInfo getArticlesByCategory(Integer pageNumber,Integer pageSize,Integer id);
 public ResultInfo getArticlesByTag(Integer pageNumber,Integer pageSize,Integer id);
 public ResultInfo searchArticles(Integer pageNumber,Integer pageSize,String searchData);
 public ArticleInfo getArticleInfoByid(Integer id);
 /*
  * 方法： 实现收藏功能
  * 输入参数：用户id(userid)、文章id(articleid),type:(1:收藏 0：取消收藏)
  * 返回：ok
  * */
 public ResultInfo collectArticle(Integer userid,Integer articleid,Integer type);
 public List<Map<String,Object>>  getCollectorByArticleid(Integer articleid);//获取关注该篇文章的所有用户

}
