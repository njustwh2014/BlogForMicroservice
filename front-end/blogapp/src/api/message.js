import request from '@/request'

//获取当前用户是否有未读消息
export function getMessageStatus(userid) {
  return request({
    url: `/message/unreadstatus/${userid}`,
    method: 'get'
  })
}

//获取该用户所有未读消息
export function getAllUnreadMessage(userid) {
  return request({
    url: `/message/unreadmessage/${userid}`,
    method: 'get'
  })
}

//根据batch获取用户历史消息
export function getAllReadMessage(userid,index,batchsize) {
  return request({
    url: '/message/readmessage/',
    method: 'post',
    data:{
      userid:userid,
      index:index,
      batchsize:batchsize
    },
  })
}

//将消息置为已读
export function changeMessageStatus(messageid) {
  return request({
    url: `/message/changemessage/${messageid}`,
    method: 'get'
  })
}