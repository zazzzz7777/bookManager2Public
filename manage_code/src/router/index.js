	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import aboutus from '@/views/aboutus/list'
	import address from '@/views/address/list'
    import menu_manage from '@/views/menu_manage/list'
	import storeup from '@/views/storeup/list'
	import users from '@/views/users/list'
	import cart from '@/views/cart/list'
	import discusstushuxinxi from '@/views/discusstushuxinxi/list'
	import forum from '@/views/forum/list'
	import chathelper from '@/views/chathelper/list'
	import systemintro from '@/views/systemintro/list'
	import tushufenlei from '@/views/tushufenlei/list'
	import chat from '@/views/chat/list'
	import yonghu from '@/views/yonghu/list'
	import orders from '@/views/orders/list'
	import config from '@/views/config/list'
	import tushuxinxi from '@/views/tushuxinxi/list'
	import usersCenter from '@/views/users/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/news',
			name: '公告资讯',
			component: news
		}
		,{
			path: '/aboutus',
			name: '发展历程',
			component: aboutus
		}
		,{
			path: '/address',
			name: '地址',
			component: address
		}
        ,{
            path: '/menu',
            name: '菜单权限管理',
            component: menu_manage
        }
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/cart',
			name: '购物车',
			component: cart
		}
		,{
			path: '/discusstushuxinxi',
			name: '图书信息评论-评论',
			component: discusstushuxinxi
		}
		,{
			path: '/forum',
			name: '我的发布',
			component: forum
		}
		,{
			path: '/chathelper',
			name: '聊天助手',
			component: chathelper
		}
		,{
			path: '/systemintro',
			name: '网站介绍',
			component: systemintro
		}
		,{
			path: '/tushufenlei',
			name: '图书分类',
			component: tushufenlei
		}
		,{
			path: '/chat',
			name: '客服聊天',
			component: chat
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/orders',
			name: '订单管理',
			component: orders
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/tushuxinxi',
			name: '图书信息',
			component: tushuxinxi
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
