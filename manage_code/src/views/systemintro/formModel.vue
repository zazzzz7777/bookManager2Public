<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="80%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row>
					<el-col :span="12">
						<el-form-item label="标题" prop="title">
							<el-input class="list_inp" v-model="form.title" placeholder="标题"
								 type="text" 								:readonly="!isAdd||disabledForm.title?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="副标题" prop="subtitle">
							<el-input class="list_inp" v-model="form.subtitle" placeholder="副标题"
								 type="text" 								:readonly="!isAdd||disabledForm.subtitle?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item prop="picture1"
									  label="图片1"
						>
							<uploads
								:disabled="!isAdd||disabledForm.picture1?true:false"
								action="file/upload"

								tip="请上传图片1"
								:limit="3"
								style="width: 100%;text-align: left;"
								:fileUrls="form.picture1?form.picture1:''" 
								@change="picture1UploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item prop="picture2"
									  label="图片2"
						>
							<uploads
								:disabled="!isAdd||disabledForm.picture2?true:false"
								action="file/upload"

								tip="请上传图片2"
								:limit="3"
								style="width: 100%;text-align: left;"
								:fileUrls="form.picture2?form.picture2:''" 
								@change="picture2UploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item prop="picture3"
									  label="图片3"
						>
							<uploads
								:disabled="!isAdd||disabledForm.picture3?true:false"
								action="file/upload"

								tip="请上传图片3"
								:limit="3"
								style="width: 100%;text-align: left;"
								:fileUrls="form.picture3?form.picture3:''" 
								@change="picture3UploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="内容" prop="content">
							<editor :value="form.content" placeholder="请输入内容" :readonly="!isAdd||disabledForm.content?true:false"
								class="list_editor" @change="(e)=>editorChange(e,'content')"></editor>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
	//基础信息
	const tableName = 'systemintro'
	const formName = '网站介绍'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
		title : false,
		subtitle : false,
		content : false,
		picture1 : false,
		picture2 : false,
		picture3 : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
	//表单验证
	//匹配整数
	const validateIntNumber = (rule, value, callback) => {
		if (!value) {
			callback();
		} else if (!context?.$toolUtil.isIntNumer(value)) {
			callback(new Error("请输入整数"));
		} else {
			callback();
		}
	}
	//匹配数字
	const validateNumber = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isNumber(value)) {
			callback(new Error("请输入数字"));
		} else {
			callback();
		}
	}
	//匹配手机号码
	const validateMobile = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isMobile(value)) {
			callback(new Error("请输入正确的手机号码"));
		} else {
			callback();
		}
	}
	//匹配电话号码
	const validatePhone = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isPhone(value)) {
			callback(new Error("请输入正确的电话号码"));
		} else {
			callback();
		}
	}
	//匹配邮箱
	const validateEmail = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isEmail(value)) {
			callback(new Error("请输入正确的邮箱地址"));
		} else {
			callback();
		}
	}
	//匹配身份证
	const validateIdCard = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.checkIdCard(value)) {
			callback(new Error("请输入正确的身份证号码"));
		} else {
			callback();
		}
	}
	//匹配网站地址
	const validateUrl = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isURL(value)) {
			callback(new Error("请输入正确的URL地址"));
		} else {
			callback();
		}
	}
	const rules = ref({
		title: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		subtitle: [
		],
		content: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		picture1: [
		],
		picture2: [
		],
		picture3: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//图片1上传回调
	const picture1UploadSuccess=(e)=>{
		form.value.picture1 = e
	}
	//图片2上传回调
	const picture2UploadSuccess=(e)=>{
		form.value.picture2 = e
	}
	//图片3上传回调
	const picture3UploadSuccess=(e)=>{
		form.value.picture3 = e
	}
	//methods

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			title: '',
			subtitle: '',
			content: '',
			picture1: '',
			picture2: '',
			picture3: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			res.data.data.content = res.data.data.content?(res.data.data.content.replace(reg,'../../../bookManager/file')):'';
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
			// getInfo()
			for(let x in row){
				if(x=='title'){
					form.value.title = row[x];
					disabledForm.value.title = true;
					continue;
				}
				if(x=='subtitle'){
					form.value.subtitle = row[x];
					disabledForm.value.subtitle = true;
					continue;
				}
				if(x=='content'){
					form.value.content = row[x];
					disabledForm.value.content = true;
					continue;
				}
				if(x=='picture1'){
					form.value.picture1 = row[x];
					disabledForm.value.picture1 = true;
					continue;
				}
				if(x=='picture2'){
					form.value.picture2 = row[x];
					disabledForm.value.picture2 = true;
					continue;
				}
				if(x=='picture3'){
					form.value.picture3 = row[x];
					disabledForm.value.picture3 = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
			formVisible.value = true
		}

	}
	//初始化
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
		if(form.value.picture1!=null) {
			form.value.picture1 = form.value.picture1.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(form.value.picture2!=null) {
			form.value.picture2 = form.value.picture2.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(form.value.picture3!=null) {
			form.value.picture3 = form.value.picture3.replace(new RegExp(context?.$config.url,"g"),"");
		}
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		if(type.value == 'cross'){
			if(crossColumnName.value!=''){
				if(!crossColumnName.value.startsWith('[')){
					for(let o in objcross){
						if(o == crossColumnName.value){
							objcross[o] = crossColumnValue.value
						}
					}
					//修改跨表数据
					changeCrossData(objcross)
				}else{
					crossUserId = user.value.id
					crossRefId = objcross['id']
					crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
				}
			}
		}
		formRef.value.validate((valid)=>{
			if(valid){
				if(crossUserId&&crossRefId){
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(res=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}
		})
	}
	//修改跨表数据
	const changeCrossData = async (row)=>{
		await context?.$http({
			url: `${crossTable.value}/update`,
			method: 'post',
			data: row
		}).then(res=>{})
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
		// form item
		:deep(.el-form-item) {
			//label
			.el-form-item__label {
			}
			// 内容盒子
			.el-form-item__content {
				// 输入框
				.list_inp {
				}
				// 富文本
				.list_editor {
				}
				//图片上传样式
				.el-upload-list  {
					//提示语
					.el-upload__tip {
					}
					//外部盒子
					.el-upload--picture-card {
						//图标
						.el-icon{
						}
					}
					.el-upload-list__item {
					}
				}
			}
		}
	}
	// 按钮盒子
	.formModel_btn_box {
		.cancel_btn {
		}
		.cancel_btn:hover {
		}
		
		.confirm_btn {
		}
		.confirm_btn:hover {
		}
	}
</style>
