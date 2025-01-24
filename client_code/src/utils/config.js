const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '论坛交流',
					icon: '',
					child:[

						{
							name:'论坛交流',
							url:'/index/forumList'
						},
					]
				},
				{
					name: '商品信息管理',
					icon: '',
					child:[

						{
							name:'图书信息',
							url:'/index/tushuxinxiList'
						},
					]
				},
				{
					name: '公告资讯管理',
					icon: '',
					child:[

						{
							name:'公告资讯',
							url:'/index/newsList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "基于Java的在线图书推荐系统"
        } 
    }
}
export default config
