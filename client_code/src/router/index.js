import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import chathelperList from '@/views/pages/chathelper/list'
import chathelperDetail from '@/views/pages/chathelper/formModel'
import chathelperAdd from '@/views/pages/chathelper/formAdd'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import tushuxinxiList from '@/views/pages/tushuxinxi/list'
import tushuxinxiDetail from '@/views/pages/tushuxinxi/formModel'
import tushuxinxiAdd from '@/views/pages/tushuxinxi/formAdd'
import storeupList from '@/views/pages/storeup/list'
import addressList from '@/views/pages/shop_address/list'
import tushufenleiList from '@/views/pages/tushufenlei/list'
import tushufenleiDetail from '@/views/pages/tushufenlei/formModel'
import tushufenleiAdd from '@/views/pages/tushufenlei/formAdd'
import cartList from '@/views/pages/shop_order/cart'
import forumList from '@/views/pages/forum/list'
import aboutusList from '@/views/pages/aboutus/list'
import aboutusDetail from '@/views/pages/aboutus/formModel'
import aboutusAdd from '@/views/pages/aboutus/formAdd'
import systemintroList from '@/views/pages/systemintro/list'
import systemintroDetail from '@/views/pages/systemintro/formModel'
import systemintroAdd from '@/views/pages/systemintro/formAdd'
import order_confirm from '@/views/pages/shop_order/confirm'
import ordersList from '@/views/pages/shop_order/list'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'chathelperList',
			component: chathelperList
		}, {
			path: 'chathelperDetail',
			component: chathelperDetail
		}, {
			path: 'chathelperAdd',
			component: chathelperAdd
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'tushuxinxiList',
			component: tushuxinxiList
		}, {
			path: 'tushuxinxiDetail',
			component: tushuxinxiDetail
		}, {
			path: 'tushuxinxiAdd',
			component: tushuxinxiAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'addressList',
			component: addressList
		}
		, {
			path: 'tushufenleiList',
			component: tushufenleiList
		}, {
			path: 'tushufenleiDetail',
			component: tushufenleiDetail
		}, {
			path: 'tushufenleiAdd',
			component: tushufenleiAdd
		}
		, {
			path: 'cartList',
			component: cartList
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'aboutusList',
			component: aboutusList
		}, {
			path: 'aboutusDetail',
			component: aboutusDetail
		}, {
			path: 'aboutusAdd',
			component: aboutusAdd
		}
		, {
			path: 'systemintroList',
			component: systemintroList
		}, {
			path: 'systemintroDetail',
			component: systemintroDetail
		}, {
			path: 'systemintroAdd',
			component: systemintroAdd
		}
		, {
			path: 'order_confirm',
			component: order_confirm
		}
		, {
			path: 'ordersList',
			component: ordersList
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
