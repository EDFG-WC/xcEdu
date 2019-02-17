import Vue from 'vue';
import Router from 'vue-router';
Vue.use(Router);
// 定义路由配置
let routes = []
let concat = (router) => {
  routes = routes.concat(router)
}
// // 导入路由规则
import HomeRouter from '@/module/home/router'
import CMSRouter from '@/module/cms/router'
import FuckRouter from '@/module/fuckyou/router'
// 合并路由规则
concat(HomeRouter)
concat(CMSRouter)
concat(FuckRouter)
export default routes;
