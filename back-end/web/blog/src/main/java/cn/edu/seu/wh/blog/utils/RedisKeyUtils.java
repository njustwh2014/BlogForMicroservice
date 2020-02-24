/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.utils;


/*
本类用于统一产生jedis的key（为了防止不统一的key对jedis数据库产生误操作）
*/
public class RedisKeyUtils {
    private static String SPLIT = ":";

    private static String BIZ_EVENT="BizEvent";

    private static String BIZ_LIKE="LIKE";

    private static String COMMENT_LIKE="CommentLike";
    private static String VIEW="View";
    private static String COMMENT="Comment";
    /*
    * 获得评论点赞的key
    * entityid 该条评论的id
    */
    public static String getCommentLikeKey(int entityid){
         return COMMENT_LIKE+SPLIT+entityid;
    }
    public static String getBizEventKey(){
        return BIZ_EVENT;
    }
    public static String getViewKey(int entityid){
        return VIEW+SPLIT+entityid;
    }
    public static String getCommentKey(int entityid){
        return COMMENT+SPLIT+entityid;
    }


}
