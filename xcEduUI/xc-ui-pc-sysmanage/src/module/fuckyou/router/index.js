import Home from '@/module/home/page/home.vue';
import fuck_thing from '@/module/fuckyou/page/fuck_thing.vue';

export default [{
  path: '/dick',
  component: Home,
  name: 'Reading内容管理',
  hidden: false,
  children: [
    {
      path: '/dick/fuck/thing',
      name: 'Reading日程表',
      component: fuck_thing,
      hidden: false
    }
  ]
}]
