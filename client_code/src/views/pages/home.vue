<template>
	<div>
		<div class="home_box">
			<!-- 关于我们 -->
			<div class="aboutUs_view">
				<div class="aboutUs_title"><span>{{aboutUsDetail.title}}</span></div>
				<div class="aboutUs_subtitle"><span>{{aboutUsDetail.subtitle}}</span></div>
				<div class="aboutUs_content"><div v-html="aboutUsDetail.content"></div></div>
				<div class="aboutUs_img_box">
					<mySwiper :data="aboutUsDetail.imgList" :type="3"
						:loop="false"
						:navigation="false"
						:pagination="false"
						:paginationType="1"
						:scrollbar="false"
						:slidesPerView="1"
						:spaceBetween="20"
						:autoHeight="false"
						:centeredSlides="false"
						:freeMode="false"
						:effectType="0"
						:direction="horizontal"
						:autoplay="true"
						:slidesPerColumn="1">
						<template #default="scope">
							<img class="aboutUs_img" :src="scope.row?$config.url + scope.row:''" alt="">
						</template>
					</mySwiper>
				</div>
				<div class="aboutUs_default1"></div>
				<div class="aboutUs_default2"></div>
				<div class="aboutUs_default3"></div>
				<div class="aboutUs_default4"></div>
			</div>
			<!-- 系统简介 -->
			<div class="systemInfo_view">
				<div class="systemInfo_title"><span>{{systemInfoDetail.title}}</span></div>
				<div class="systemInfo_subtitle"><span>{{systemInfoDetail.subtitle}}</span></div>
				<div class="systemInfo_content"><div v-html="systemInfoDetail.content"></div></div>
				<div class="systemInfo_img_box">
					<img class="systemInfo_img1" :src="systemInfoDetail.picture1?$config.url + systemInfoDetail.picture1:''" alt="">
					<img class="systemInfo_img2" :src="systemInfoDetail.picture2?$config.url + systemInfoDetail.picture2:''" alt="">
					<img class="systemInfo_img3" :src="systemInfoDetail.picture3?$config.url + systemInfoDetail.picture3:''" alt="">
				</div>
				<div class="systemInfo_default1"></div>
				<div class="systemInfo_default2"></div>
				<div class="systemInfo_default3"></div>
				<div class="systemInfo_default4"></div>
			</div>
			<!-- 图书信息推荐 -->
			<div class="recomList_view">
				<div class="recomList_title">
                    <span>图书信息推荐</span>
                </div>
				<div class="recommend_list_one">
					<div class="recommend_item animation_box" v-for="(item,index) in tushuxinxiRecomList" :key="index" @click="detailClick('tushuxinxi',item.id)">
						<div class="recommend_img_box">
							<img class="recommend_img" v-if="isHttp(item.tushutupian)" :src="item.tushutupian.split(',')[0]" alt="">
							<img class="recommend_img" v-else :src="item.tushutupian?$config.url + item.tushutupian.split(',')[0]:''" alt="">
						</div>
						<div class="recommend_content">
							<div class="recommend_title">
								{{item.tushumingcheng}}
							</div>
							<div class="recommend_price">
								 ￥{{item.price}}
							</div>
							<div class="recommend_bottom">
								<div class="recommend_like" v-if="item.thumbsupNumber">
									<span class="iconfont icon-thumb-up-line1 like_icon"></span>
									<div class="num">{{item.thumbsupNumber}}</div>
								</div>
								<div class="recommend_collect" v-if="item.storeupNumber">
									<el-icon><StarFilled /></el-icon>
									<div class="num">{{item.storeupNumber}}</div>
								</div>
								<div class="recommend_clickNum" v-if="item.clickNumber">
									<el-icon><View /></el-icon>
									<div class="num">{{item.clickNumber}}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="recommend_more_view" @click="moreClick('tushuxinxi')">
					<span class="recommend_more_text">查看更多</span>
					<el-icon><DArrowRight /></el-icon>
				</div>
			</div>
			<!-- 公告资讯 -->
			<div class="newsList_view">
				<div class="newsList_title">
                    <span>公告资讯</span>
                </div>
				<div class="news_list_three">
					<div class="news_left animation_box" v-if="newsList.length>0" @click="newsDetailClick(newsList[0])">
						<div class='news_img_box'>
							<img class="news_img" v-if="isHttp(newsList[0].picture)" :src="newsList[0].picture.split(',')[0]" alt="">
							<img class="news_img" v-else :src="newsList[0].picture?$config.url + newsList[0].picture.split(',')[0]:''" alt="">
						</div>
						<div class="news_content">
							<div class="news_title">{{newsList[0].title}}</div>
							<div class="news_text">{{newsList[0].introduction}}</div>
							<div class="news_time">{{newsList[0].addtime.split(' ')[0]}}</div>
						</div>
					</div>
					<div class="news_right">
						<template v-for="(item,index) in newsList" :key="index">
							<div class="news_right_item animation_box" v-if="index>0" @click="newsDetailClick(item)">
								<div class='news_img_box'>
									<img class="news_img" v-if="isHttp(item.picture)" :src="item.picture.split(',')[0]" alt="">
									<img class="news_img" v-else :src="item.picture?$config.url + item.picture.split(',')[0]:''" alt="">
								</div>
								<div class="news_content">
									<div class="news_title">{{item.title}}</div>
									<div class="news_text">{{item.introduction}}</div>
									<div class="news_time">{{item.addtime.split(' ')[0]}}</div>
								</div>
							</div>
						</template>
					</div>
				</div>
				<div class="news_more_view" @click="moreClick('news')">
					<span class="news_more_text">查看更多</span>
					<el-icon><DArrowRight /></el-icon>
				</div>
			</div>

		</div>
		<formModel ref="newsFormModelRef"></formModel>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import moment from 'moment'
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	//关于我们
	const aboutUsDetail = ref({})
	const getAboutUs = () => {
		context?.$http({
			url: 'aboutus/detail/1',
			method: 'get',
		}).then(res=>{
			res.data.data.imgList = []
			res.data.data.imgList.push(res.data.data.picture1)
			res.data.data.imgList.push(res.data.data.picture2)
			res.data.data.imgList.push(res.data.data.picture3)
			aboutUsDetail.value = res.data.data
		})
	}
	//系统简介
	const systemInfoDetail = ref({})
	const getSystemInfo = () => {
		context?.$http({
			url: 'systemintro/detail/1',
			method: 'get',
		}).then(res=>{
			systemInfoDetail.value = res.data.data
		})
	}
	//图书信息推荐
	const tushuxinxiRecomList = ref([])
	const gettushuxinxiRecomList = () => {
		let autoSortUrl = 'tushuxinxi/autoSort'
		if(context?.$toolUtil.storageGet('frontToken')){
			autoSortUrl = "tushuxinxi/autoSort2"
		}
		let params = {
			page: 1,
			limit: 8
		}
		context?.$http({
			url: autoSortUrl,
			method: 'get',
			params: params
		}).then(res => {
			tushuxinxiRecomList.value = res.data.data.list
			tushuxinxiRecomList.value.forEach(item=>{
				if(!isHttp(item.tushutupian)){
					item.imgUrls = item.tushutupian.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	//公告资讯弹窗
	import formModel from './news/formModel'
	const newsFormModelRef = ref(null)
	//公告资讯
	const newsList = ref([])
	const getNewsList = () => {
		context?.$http({
			url: 'news/list',
			method: 'get',
			params:{
				page:1,
				limit: 8
			}
		}).then(res=>{
			newsList.value = res.data.data.list
		})
	}
	const newsDetailClick = (item) => {
		if (item && item.id){
			newsFormModelRef.value.init(item.id)
		}
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		getAboutUs()
		getSystemInfo()
		//图书信息推荐
		gettushuxinxiRecomList()
		//公告资讯
		getNewsList()
	}
	init()
</script>

<style lang="scss">
	.home_box {
	}

	.aboutUs_view {
		.aboutUs_title {
		}

		.aboutUs_subtitle {
		}
		.aboutUs_content {
		}
		.aboutUs_img_box {
			.aboutUs_img {
				object-fit: cover;
				width: 100%;
				height: 400px;
			}
		}
		// 自定义盒子一
		.aboutUs_default1 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// 自定义盒子二
		.aboutUs_default2 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// 自定义盒子三
		.aboutUs_default3 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// 自定义盒子四
		.aboutUs_default4 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
	}
	.systemInfo_view {
		.systemInfo_title {
		}

		.systemInfo_subtitle {
		}
		.systemInfo_content {
		}
		.systemInfo_img_box {
			.systemInfo_img1 {
			}

			.systemInfo_img2 {
			}

			.systemInfo_img3 {
			}
		}
		// 自定义盒子一
		.systemInfo_default1 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// 自定义盒子二
		.systemInfo_default2 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// 自定义盒子三
		.systemInfo_default3 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// 自定义盒子四
		.systemInfo_default4 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
	}
	// 推荐
	.recomList_view {
		.recomList_title {
		}
		// list
		.recommend_list_one {
			.recommend_item {
			}
			.recommend_img_box {
				.recommend_img {
				}
			}
			.recommend_content {
				.recommend_title {
				}
				.recommend_price {
				}
				.recommend_bottom {
					.recommend_like {
						.like_icon {
						}
						.num {
						}
					}
					.recommend_collect {
						.el-icon {
						}
						.num {
						}
					}
					.recommend_clickNum {
						color: #999;
						display: flex;
						font-size: 16px;
						align-items: center;
						.el-icon {
							margin: 0 4px 0 0;
							color: inherit;
						}
						.num {
							color: inherit;
						}
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1.05) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.recommend_more_view {
			cursor: pointer;
			padding: 5px 20px;
			margin: 20px auto;
			color: #333;
			background: #eee;
			display: inline-block;
			width: auto;
			font-size: 16px;
			text-align: center;
			.recommend_more_text {
			}
			.el-icon {
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {

		.newsList_title {
		}
		// list
		.news_list_three {
			.news_left {
				.news_img_box {
					.news_img {
					}
				}
				.news_content {
					.news_title {
					}
					.news_text {
					}
					.news_time {
					}
				}
			}
			.news_right {
				.news_right_item {
					.news_img_box {
						.news_img {
						}
					}
					.news_content {
						.news_title {
						}
						.news_text {
						}
						.news_time {
						}
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.news_more_view {
			.news_more_text {
			}
			.el-icon {
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {

		.homeList_title {
		}
		// list
		.home_list_five {
			.item1 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item1:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item2 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item2:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item3 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item3:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item4 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item4:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item5 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item5:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item6 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item6:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item7 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item7:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item8 {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.item8:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.homeList_more_view {
			cursor: pointer;
			padding: 5px 20px;
			margin: 0px auto;
			color: #333;
			background: #f6f6f6;
			display: inline-block;
			width: auto;
			font-size: 16px;
			text-align: center;
			.homeList_more_text {
			}
			.el-icon {
			}
		}
	}
	// 首页展示
</style>
<style>
.home_box{
    width: 100%;
    margin: 0px auto;
    display: flex;
    flex-wrap: wrap;
}


/* 总盒子 */
.aboutUs_view {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    padding: 60px calc((100% - 1200px) / 2);
    order: 2;
    background: url(http://clfile.zggen.cn/20240827/1bd5afcf734c48c69604ca1506c77130.png) repeat-x center top,url(http://clfile.zggen.cn/20240827/eb6616240bd646e39013d590ec4eedfc.png) repeat-x center bottom,#fff7ec;
    justify-content: space-between;
}
/* 标题 */
.aboutUs_view .aboutUs_title{
    width: 100%;
    font-size: 32px;
    text-align: center;
    background: url(http://clfile.zggen.cn/20240826/f47067c7d4b24bb1ae7d7473c9958ff5.png) no-repeat center top;
    padding: 88px 0px 0px;
    color: rgb(51, 51, 51);
}
/* 副标题 */
.aboutUs_view .aboutUs_subtitle{
    width: 100%;
    display: block;
    font-size: 16px;
    color: rgb(153, 153, 153);
    text-align: center;
    line-height: 40px;
}
/* 内容 */
.aboutUs_view .aboutUs_content{
    width: 49%;
    padding: 0px;
    border: 0px solid rgb(238, 238, 238);
    margin: 20px 0px;
    font-size: 16px;
    color: rgb(102, 102, 102);
    line-height: 30px;
    text-indent: 2em;
}
.aboutUs_view .aboutUs_img_box{
    width: 49%; 
    margin: 20px 0px;
}
/* 自定义盒子 */
.aboutUs_view .aboutUs_default1{
    width: 100px;
    height: 100px;
    background: rgb(213, 161, 177);
    margin: 0px auto;
    display: none;
}

/* 总盒子 */
.systemInfo_view {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    padding: 60px calc((100% - 1200px) / 2);
    order: 4;
    background: url(http://clfile.zggen.cn/20240827/1bd5afcf734c48c69604ca1506c77130.png) repeat-x center top,url(http://clfile.zggen.cn/20240827/eb6616240bd646e39013d590ec4eedfc.png) repeat-x center bottom,#fff7ec;
}
/* 标题 */
.systemInfo_view .systemInfo_title{
    width: 100%;
    font-size: 32px;
    text-align: center;
    background: url(http://clfile.zggen.cn/20240826/f47067c7d4b24bb1ae7d7473c9958ff5.png) no-repeat center top;
    padding: 88px 0px 0px;
    color: rgb(51, 51, 51);
}
/* 副标题 */
.systemInfo_view .systemInfo_subtitle{
    width: 100%;
    display: block;
    font-size: 16px;
    color: rgb(153, 153, 153);
    text-align: center;
    line-height: 40px;
}
/* 内容 */
.systemInfo_view .systemInfo_content{
    width: 100%;
    border: 0px solid rgb(238, 238, 238);
    margin: 20px 0px 40px;
    padding: 0px;
    font-size: 16px;
    color: rgb(102, 102, 102);
    line-height: 30px;
    text-indent: 2em;
}
/* 图片 样式一 盒子 */
.systemInfo_view .systemInfo_img_box{
    padding: 0px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
}
.systemInfo_view .systemInfo_img_box .systemInfo_img1{
    width: 30%;
    height: 360px;
    object-fit: cover;
}
.systemInfo_view .systemInfo_img_box .systemInfo_img2{
    width: 30%;
    height: 240px;
    object-fit: cover;
}
.systemInfo_view .systemInfo_img_box .systemInfo_img3{
    width: 30%;
    height: 360px;
    object-fit: cover;
}

/* 总盒子 */
.recomList_view {
    width: 100%;
    order: 1;
    padding: 60px 0px;
    background: url(http://clfile.zggen.cn/20240826/bc4c83fba6264523acc08f621080d2ff.jpg) center top / cover no-repeat;
    text-align: center;
}
/* 标题 */
.recomList_view .recomList_title{
    width: 100%;
    text-align: center;
    font-size: 36px;
    color: rgb(51, 51, 51);
    margin: 0px 0px 20px;
    background: url(http://clfile.zggen.cn/20240826/f47067c7d4b24bb1ae7d7473c9958ff5.png) center top no-repeat;
    padding: 88px 0px 0px;
}
/* 样式一 总盒子 */
.recomList_view .recommend_list_one{
    width: 1220px;
    display: flex;
    flex-wrap: wrap;
    margin: 0px auto;
}
/* item */
.recomList_view .recommend_list_one .recommend_item{
    width: calc(25% - 20px);
    margin: 0px 10px 20px;
    background: rgb(255, 255, 255);
    cursor: pointer;
}
/* 图片 盒子 */
.recomList_view .recommend_list_one .recommend_item .recommend_img_box{
    width: 100%;
    height: 280px;
    overflow: hidden;
    margin: 0px 0px 5px;
}
/* 图片 */
.recomList_view .recommend_list_one .recommend_item .recommend_img_box .recommend_img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}

/* 内容区 盒子 */
.recomList_view .recommend_list_one .recommend_item .recommend_content{
    width: 100%;
}
/* 标题 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_title{
    font-size: 16px;
    text-align: center;
    font-weight: 600;
    color: rgb(0, 0, 0);
    width: 100%;
    line-height: 1.5;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}
/* 价格 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_price{
    width: 100%;
    padding: 0 10px;
    color: rgb(255, 0, 0);
    text-align: right;
    font-size: 16px;
}

/* 底部 小元素 总盒子 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_bottom{
    width: 100%;
    display: flex;
    align-items: center;
    padding: 0 10px;
}
/* 点赞 盒子 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_bottom .recommend_like{
    display: flex;
    align-items: center;
    color: rgb(153, 153, 153);
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
/* 图标 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_bottom .like_icon{
    color: inherit;
    margin: 0px 4px 0px 0px;
}
/* 关注 盒子 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_bottom .recommend_collect{
    display: flex;
    align-items: center;
    color: rgb(153, 153, 153);
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
/* 图标 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_bottom .recommend_collect .el-icon-star-on{
    color: inherit;
    margin: 0px 4px 0px 0px;
}
/* 数量 */
.recomList_view .recommend_list_one .recommend_item .recommend_content .recommend_bottom .recommend_collect .num{
    color: inherit;
}

/* 总盒子 */
.newsList_view {
    width: 100%;
    order: 3;
    padding: 50px 0px;
    text-align: center;
}
/* 标题 */
.newsList_view .newsList_title{
    width: 100%;
    text-align: center;
    font-size: 32px;
    color: rgb(51, 51, 51);
    margin: 0px 0px 50px;
    background: url(http://clfile.zggen.cn/20240826/f47067c7d4b24bb1ae7d7473c9958ff5.png) no-repeat center top;
    padding: 88px 0px 0px;
}
/* 样式三盒子 */
.newsList_view .news_list_three{
    display: flex;
    align-items: center;
    width: 1200px;
    margin: 0px auto;
    justify-content: space-between;
}

/* 左边盒子 */
.newsList_view .news_list_three .news_left{
    width: 48%;
    background: rgb(255, 255, 255);
    cursor:pointer;
}
/* 图片盒子 */
.newsList_view .news_list_three .news_left .news_img_box{
    width: 100%;
}
/* 图片 */
.newsList_view .news_list_three .news_left .news_img_box .news_img{
    width: 100%;
    height: 380px;
    object-fit: cover;
}
.newsList_view .news_list_three .news_left .news_content{
    display: none;
}
.newsList_view .news_list_three .news_left .news_content .news_title{
    font-size: 18px;
    text-align: center;
    font-weight: bold;
    color: rgb(0, 0, 0);
    width: 100%;
    line-height: 40px;
}
.newsList_view .news_list_three .news_left .news_content .news_text{
    font-size: 14px;
    line-height: 1.5;
}
.newsList_view .news_list_three .news_left .news_content .news_time{
    color: rgb(153, 153, 153);
    width: 100%;
    text-align: right;
}

/* 右边盒子 */
.newsList_view .news_list_three .news_right{
    width: 48%;
    height: 380px;
}
/* item */
.newsList_view .news_list_three .news_right .news_right_item{
    width: 100%;
    cursor:pointer;
}
.newsList_view .news_list_three .news_right .news_right_item .news_img_box{
    width: 30%;
    display: none;
}
.newsList_view .news_list_three .news_right .news_right_item .news_img_box .news_img{
    width: 100%;
    object-fit: cover;
    height: 150px;
}
.newsList_view .news_list_three .news_right .news_right_item .news_content{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    line-height: 40px;
}
.newsList_view .news_list_three .news_right .news_right_item .news_content .news_title{
    width: calc(100% - 110px);
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    text-align: left;
    font-size: 15px;
}
.newsList_view .news_list_three .news_right .news_right_item .news_content .news_text{
    font-size: 14px;
    line-height: 1.5;
    display: none;
}
.newsList_view .news_list_three .news_right .news_right_item .news_content .news_time{
    width: 110px;
    text-align: right;
    display: inline-block;
    font-size: 14px;
    color: rgb(153, 153, 153);
}
.newsList_view .news_list_three .news_right .news_right_item:hover .news_content .news_title{
    color: #feb043;
}

/* 选取第一个元素 */
.newsList_view .news_list_three .news_right .news_right_item:first-child{
    display: flex;
    justify-content: space-between;
   flex-wrap: wrap;
   border-bottom:1px solid #e8e8e8;
   padding-bottom:20px;
   margin-bottom:20px;
}
.newsList_view .news_list_three .news_right .news_right_item:first-child .news_img_box{
    width: 25%;
    display: block;
}
.newsList_view .news_list_three .news_right .news_right_item:first-child .news_img_box .news_img{
    width: 100%;
    object-fit: cover;
    height: 85px;
}
.newsList_view .news_list_three .news_right .news_right_item:first-child  .news_content{
    width: 73%;
    display: flex;
   flex-wrap: wrap;
}
.newsList_view .news_list_three .news_right .news_right_item:first-child .news_content .news_title{
    width:100%;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    text-align: left;
    font-size: 18px;
}
.newsList_view .news_list_three .news_right .news_right_item:first-child  .news_content .news_text{
    font-size: 15px;
    line-height: 24px;
    display: block;
    text-align: left;
    width:100%;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
}
.newsList_view .news_list_three .news_right .news_right_item:first-child .news_content .news_time{
    width: 100%;
    text-align: right;
}

/* 查看更多 */
.newsList_view .news_more_view{
    width: auto;
    text-align: center;
    background: rgb(246, 246, 246);
    margin: 40px auto 20px;
    cursor: pointer;
    display: inline-block;
    font-size: 16px;
    color: rgb(51, 51, 51);
    padding: 5px 20px;
}
.newsList_view .news_more_view .news_more_text{
}
.newsList_view .news_more_view .el-icon-d-arrow-right{
}

</style>