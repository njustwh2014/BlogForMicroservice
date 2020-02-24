/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.controller;


import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categorys", method = RequestMethod.GET)
    public ResultInfo getAllCaregory(){
        return categoryService.getAllCategory();
    }

//    @RequestMapping(value="/categorys/${id}",method = RequestMethod.GET)
//    public ResultInfo getAllArticleByCategory(@PathVariable Integer id){
//        return categoryService.getAllArtilcesByCategory(id);
//    }


}
