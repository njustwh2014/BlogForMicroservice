import request from '@/request'


export function getCommentsByArticle(id) {
  return request({
    url: `/comments/article/${id}`,
    method: 'get'
  })
}

export function publishComment(comment) {
  return request({
    url: '/comments/publish',
    method: 'post',
    data: comment
  })
}
export function like(type,entityid,userid){
  return request({
    url:'/comments/like',
    method:'post',
    data:{
      type:type,
      entityid:entityid,
      userid:userid
    }
  })
}

