import http from '../../../base/api/public'
import querystring from 'querystring'

let sysConfig = require('@/../config/sysConfig')
let apiUrl = sysConfig.xcApiUrlPre;


//定义方法, 请求服务端的查询接口
export const page_list = (page, size, params) => {
  //将json对象转为键值对
  //let queryString = querystring.stringify(params)
  //alert(queryString)
  //return http.requestQuickGet(apiUrl + '/cms/page/list/' + page + '/' + size + '/?' + queryString)
  //将params对象数据拼装成key/value串
  let queryString = querystring.stringify(params);
  //请求服务端的页面查询接口
  //alert(apiUrl+'/cms/page/list/'+page+'/'+size+"?"+queryString)
  //alert(queryString);
  return http.requestQuickGet(apiUrl + '/cms/page/list/' + page + '/' + size + '?' + queryString);
}
//查询页面用来查询所有页面名称的方法
export const page_getSiteList = () => {
  return http.requestQuickGet(apiUrl + '/cms/page/siteList')
}

//添加cms页面的方法
export const page_add = (params) => {
  return http.requestPost(apiUrl + '/cms/page/add', params)
}

//根据id查询页面
export const page_get = (id) => {
  return http.requestQuickGet(apiUrl + '/cms/page/get/' + id)
}

//修改页面
export const page_edit = (id, params) => {
  return http.requestPut(apiUrl + '/cms/page/edit/' + id, params)
}

//删除页面
export const page_del = (id) => {
  return http.requestDelete(apiUrl + '/cms/page/delete/' + id)
}
