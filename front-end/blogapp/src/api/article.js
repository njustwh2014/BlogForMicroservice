import request from '@/request'


export function getArticles(page) {
  return request({
    url: '/articles/getall',
    method: 'post',
    data: {
      pageNumber: page.pageNumber,
      pageSize: page.pageSize
      // name: page.name,
      // sort: page.sort,
      // year: query.year,
      // month: query.month,
      // tagId: query.tagId,
      // categoryId: query.categoryId
    }
  })
}

//搜索
export function search(page,searchData){
  return request({
    url:'/articles/search',
    method:'post',
    data:{
      pageNumber: page.pageNumber,
      pageSize: page.pageSize,
      searchData:searchData
    }
  })

}

export function getHotArticles() {
  return request({
    url: '/articles/hot',
    method: 'get'
  })
}

export function getNewArtices() {
  return request({
    url: '/articles/new',
    method: 'get'
  })
}

export function viewArticle(id) {
  return request({
    url: `/articles/view/${id}`,
    method: 'get'
  })
}

export function getArticlesByCategory(page,id) {
  return request({
    url: '/articles/category',
    method: 'post',
    data:{
      pageNumber: page.pageNumber,
      pageSize: page.pageSize,
      id:id
    }
  })
}

export function getArticlesByTag(page,id) {
  return request({
    url: '/articles/tag',
    method: 'post',
    data:{
      pageNumber: page.pageNumber,
      pageSize: page.pageSize,
      id:id
    }
  })
}


export function publishArticle(article) {
  return request({
    url: '/articles/publish',
    method: 'post',
    data: article
  })
}

export function listArchives() {
  return request({
    url: '/articles/listArchives',
    method: 'get'
  })
}

export function getArticleById(id) {
  return request({
    url: `/articles/${id}`,
    method: 'get'
  })
}

export function collectArticle(userid,articleid,type) {
  return request({
    url:'/articles/collect',
    method:'post',
    data:{
      userid:userid,
      articleid:articleid,
      type:type
    }
  })
}
