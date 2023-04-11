"use strict";(self["webpackChunkhalo_admin"]=self["webpackChunkhalo_admin"]||[]).push([[764],{71764:function(t,e,a){a.r(e),a.d(e,{default:function(){return k}});var s=function(){var t=this,e=t._self._c;return e("page-view",[e("a-row",{attrs:{gutter:12,align:"middle",type:"flex"}},[e("a-col",{staticClass:"pb-3",attrs:{span:24}},[e("a-card",{attrs:{bodyStyle:{padding:"16px"},bordered:!1}},[e("div",{staticClass:"table-page-search-wrapper"},[e("a-form",{attrs:{layout:"inline"}},[e("a-row",{attrs:{gutter:48}},[e("a-col",{attrs:{md:6,sm:24}},[e("a-form-item",{attrs:{label:"关键词："}},[e("a-input",{attrs:{allowClear:""},on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleQuery.apply(null,arguments)}},model:{value:t.list.params.keyword,callback:function(e){t.$set(t.list.params,"keyword",e)},expression:"list.params.keyword"}})],1)],1),e("a-col",{attrs:{md:6,sm:24}},[e("a-form-item",{attrs:{label:"分组："}},[e("a-select",{attrs:{allowClear:""},on:{change:function(e){return t.handleQuery()}},model:{value:t.list.params.team,callback:function(e){t.$set(t.list.params,"team",e)},expression:"list.params.team"}},t._l(t.computedTeams,(function(a,s){return e("a-select-option",{key:s,attrs:{value:a}},[t._v(" "+t._s(a)+" ")])})),1)],1)],1),e("a-col",{attrs:{md:6,sm:24}},[e("span",{staticClass:"table-page-search-submitButtons"},[e("a-space",[e("a-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("查询")]),e("a-button",{on:{click:function(e){return t.handleResetParam()}}},[t._v("重置")])],1)],1)])],1)],1)],1),e("div",{staticClass:"table-operator mb-0"},[e("a-dropdown",{scopedSlots:t._u([{key:"overlay",fn:function(){return[e("a-menu",[e("a-menu-item",{key:"single",on:{click:function(e){return t.handleOpenForm({})}}},[t._v(" 添加")]),e("a-menu-item",{key:"batch",on:{click:function(e){t.attachmentSelectModal.visible=!0}}},[t._v(" 批量添加")])],1)]},proxy:!0}])},[e("a-button",{attrs:{icon:"plus",type:"primary"}},[t._v(" 添加 "),e("a-icon",{attrs:{type:"down"}})],1)],1),e("a-button",{directives:[{name:"show",rawName:"v-show",value:t.list.selected.length,expression:"list.selected.length"}],attrs:{icon:"check-circle",type:"primary"},on:{click:t.handleSelectAll}},[t._v(" 全选 ")]),e("a-button",{directives:[{name:"show",rawName:"v-show",value:t.list.selected.length,expression:"list.selected.length"}],attrs:{icon:"delete",type:"danger"},on:{click:t.handleDeletePhotoInBatch}},[t._v(" 删除 ")]),e("a-button",{directives:[{name:"show",rawName:"v-show",value:t.list.selected.length,expression:"list.selected.length"}],attrs:{icon:"delete"},on:{click:t.handleOpenUpdateTeamForm}},[t._v(" 更改分组 ")]),e("a-button",{directives:[{name:"show",rawName:"v-show",value:t.list.selected.length,expression:"list.selected.length"}],attrs:{icon:"close"},on:{click:function(e){t.list.selected=[]}}},[t._v(" 取消")])],1)])],1),e("a-col",{attrs:{span:24}},[e("a-spin",{attrs:{spinning:t.list.loading}},[e("div",{staticClass:"grid grid-cols-2 gap-x-2 gap-y-3 sm:grid-cols-3 md:grid-cols-6 xl:grid-cols-8 2xl:grid-cols-10",attrs:{role:"list"}},t._l(t.list.data,(function(a,s){return e("div",{key:s,staticClass:"relative cursor-pointer overflow-hidden rounded-sm border-solid bg-white transition-all hover:shadow-sm",class:""+(t.isItemSelect(a)?"border-blue-600":"border-white"),on:{click:function(e){return t.handleItemClick(a)},mouseenter:function(e){return t.$set(a,"hover",!0)},mouseleave:function(e){return t.$set(a,"hover",!1)}}},[e("div",{staticClass:"group aspect-w-10 aspect-h-7 block w-full overflow-hidden bg-white"},[e("img",{staticClass:"pointer-events-none overflow-hidden object-cover transition-opacity group-hover:opacity-70",attrs:{alt:a.name,src:a.thumbnail,loading:"lazy"}})]),e("a-tooltip",{attrs:{title:a.name}},[e("div",{staticClass:"block truncate p-1.5 text-xs font-medium text-gray-500"},[e("span",{staticClass:"mr-1"},[t._v(" "+t._s(a.name)+" ")]),a.team?e("span",[t._v("#"+t._s(a.team))]):t._e()])]),e("a-icon",{directives:[{name:"show",rawName:"v-show",value:!t.isItemSelect(a)&&a.hover,expression:"!isItemSelect(photo) && photo.hover"}],staticClass:"absolute top-1 right-1 cursor-pointer font-bold transition-all",style:{fontSize:"20px",color:"rgb(37 99 235)"},attrs:{theme:"twoTone",type:"plus-circle"},on:{click:function(e){return e.stopPropagation(),t.handleSelect(a)}}}),e("a-icon",{directives:[{name:"show",rawName:"v-show",value:t.isItemSelect(a),expression:"isItemSelect(photo)"}],staticClass:"absolute top-1 right-1 cursor-pointer font-bold transition-all",style:{fontSize:"20px",color:"rgb(37 99 235)"},attrs:{theme:"twoTone",type:"check-circle"}})],1)})),0)])],1)],1),e("div",{staticClass:"page-wrapper"},[e("a-pagination",{staticClass:"pagination",attrs:{current:t.pagination.page,defaultPageSize:t.pagination.size,pageSizeOptions:["50","100","150","200"],total:t.pagination.total,showLessItems:"",showSizeChanger:""},on:{change:t.handlePageChange,showSizeChange:t.handlePageSizeChange}})],1),e("div",{staticStyle:{position:"fixed",bottom:"30px",right:"30px"}},[e("a-button",{attrs:{icon:"setting",shape:"circle",size:"large",type:"primary"},on:{click:function(e){t.optionFormVisible=!0}}})],1),e("a-modal",{attrs:{afterClose:()=>t.optionFormVisible=!1,title:"页面设置"},scopedSlots:t._u([{key:"footer",fn:function(){return[e("a-button",{key:"submit",attrs:{type:"primary"},on:{click:function(e){return t.handleSaveOptions()}}},[t._v("保存")])]},proxy:!0}]),model:{value:t.optionFormVisible,callback:function(e){t.optionFormVisible=e},expression:"optionFormVisible"}},[e("a-form",{attrs:{layout:"vertical"}},[e("a-form-item",{attrs:{help:"* 需要主题进行适配",label:"页面标题："}},[e("a-input",{model:{value:t.options.photos_title,callback:function(e){t.$set(t.options,"photos_title",e)},expression:"options.photos_title"}})],1),e("a-form-item",{attrs:{label:"每页显示条数："}},[e("a-input-number",{staticStyle:{width:"100%"},model:{value:t.options.photos_page_size,callback:function(e){t.$set(t.options,"photos_page_size",e)},expression:"options.photos_page_size"}})],1)],1)],1),e("a-modal",{attrs:{title:"更改分组"},scopedSlots:t._u([{key:"footer",fn:function(){return[e("ReactiveButton",{attrs:{errored:t.updateTeamForm.saveErrored,loading:t.updateTeamForm.saving,erroredText:"更改失败",loadedText:"更改成功",text:"确定"},on:{callback:t.handleUpdateTeamInBatchCallback,click:t.handleUpdateTeamInBatch}}),e("a-button",{on:{click:function(e){t.updateTeamForm.visible=!1}}},[t._v("关闭")])]},proxy:!0}]),model:{value:t.updateTeamForm.visible,callback:function(e){t.$set(t.updateTeamForm,"visible",e)},expression:"updateTeamForm.visible"}},[e("a-form",{attrs:{layout:"vertical"}},[e("a-form-item",{attrs:{label:"分组名称："}},[e("a-auto-complete",{ref:"teamInput",staticStyle:{width:"100%"},attrs:{dataSource:t.computedTeams,allowClear:""},model:{value:t.updateTeamForm.team,callback:function(e){t.$set(t.updateTeamForm,"team",e)},expression:"updateTeamForm.team"}})],1)],1)],1),e("PhotoFormModal",{attrs:{photo:t.list.current,teams:t.computedTeams,visible:t.formVisible},on:{"update:visible":function(e){t.formVisible=e},succeed:t.onSaveSucceed},scopedSlots:t._u([{key:"extraFooter",fn:function(){return[e("a-button",{attrs:{disabled:t.selectPreviousButtonDisabled},on:{click:t.handleSelectPrevious}},[t._v("上一项")]),e("a-button",{attrs:{disabled:t.selectNextButtonDisabled},on:{click:t.handleSelectNext}},[t._v("下一项")])]},proxy:!0}])}),e("AttachmentSelectModal",{attrs:{visible:t.attachmentSelectModal.visible},on:{"update:visible":function(e){return t.$set(t.attachmentSelectModal,"visible",e)},confirm:t.handleAttachmentSelected}})],1)},i=[],o=a(9355),l=function(){var t=this,e=t._self._c;return e("a-modal",{attrs:{afterClose:t.onClosed,maskClosable:!1,width:680,destroyOnClose:""},scopedSlots:t._u([{key:"title",fn:function(){return[t._v(" "+t._s(t.form.model.id?"修改":"添加")+"图片 "),t.loading?e("a-icon",{attrs:{type:"loading"}}):t._e()]},proxy:!0},{key:"footer",fn:function(){return[t._t("extraFooter"),e("ReactiveButton",{attrs:{errored:t.form.saveErrored,loading:t.form.saving,erroredText:"保存失败",loadedText:"保存成功",text:"保存"},on:{callback:t.handleSaveCallback,click:t.handleSave}}),e("a-button",{attrs:{disabled:t.loading},on:{click:function(e){t.modalVisible=!1}}},[t._v(" 关闭")])]},proxy:!0}],null,!0),model:{value:t.modalVisible,callback:function(e){t.modalVisible=e},expression:"modalVisible"}},[e("a-form-model",{ref:"photoForm",attrs:{"label-col":t.form.labelCol,model:t.form.model,rules:t.form.rules,"wrapper-col":t.form.wrapperCol,labelAlign:"left"}},[e("a-form-model-item",{attrs:{label:"图片地址：",prop:"url"}},[e("a-space",{attrs:{direction:"vertical"}},[e("img",{staticClass:"w-1/2 cursor-pointer",staticStyle:{"border-radius":"4px"},attrs:{src:t.form.model.url||"/images/placeholder.jpg"},on:{click:function(e){t.attachmentSelectModal.visible=!0}}}),e("a-input",{attrs:{"allow-clear":"",placeholder:"点击封面图选择图片，或者输入外部链接"},model:{value:t.form.model.url,callback:function(e){t.$set(t.form.model,"url",e)},expression:"form.model.url"}})],1)],1),e("a-form-model-item",{attrs:{label:"缩略图地址：",prop:"thumbnail"}},[e("a-input",{model:{value:t.form.model.thumbnail,callback:function(e){t.$set(t.form.model,"thumbnail",e)},expression:"form.model.thumbnail"}})],1),e("a-form-model-item",{attrs:{label:"图片名称：",prop:"name"}},[e("a-input",{model:{value:t.form.model.name,callback:function(e){t.$set(t.form.model,"name",e)},expression:"form.model.name"}})],1),e("a-form-model-item",{attrs:{label:"拍摄日期：",prop:"takeTime"}},[e("a-date-picker",{staticStyle:{width:"100%"},attrs:{defaultValue:t.takeTimeDefaultValue,format:"YYYY-MM-DD HH:mm:ss",showTime:""},on:{change:t.onTakeTimeChange,ok:t.onTakeTimeChange}})],1),e("a-form-model-item",{attrs:{label:"拍摄地点：",prop:"location"}},[e("a-input",{model:{value:t.form.model.location,callback:function(e){t.$set(t.form.model,"location",e)},expression:"form.model.location"}})],1),e("a-form-model-item",{attrs:{label:"分组：",prop:"team"}},[e("a-auto-complete",{staticStyle:{width:"100%"},attrs:{dataSource:t.teams,allowClear:""},model:{value:t.form.model.team,callback:function(e){t.$set(t.form.model,"team",e)},expression:"form.model.team"}})],1),e("a-form-model-item",{attrs:{label:"描述：",prop:"description"}},[e("a-input",{model:{value:t.form.model.description,callback:function(e){t.$set(t.form.model,"description",e)},expression:"form.model.description"}})],1)],1),e("AttachmentSelectModal",{attrs:{multiSelect:!1,visible:t.attachmentSelectModal.visible},on:{"update:visible":function(e){return t.$set(t.attachmentSelectModal,"visible",e)},confirm:t.handleAttachmentSelected}})],1)},n=[],r=a(48478),d=a(73541),c={name:"PhotoFormModal",props:{visible:{type:Boolean,default:!0},loading:{type:Boolean,default:!1},photo:{type:Object,default:()=>{}},teams:{type:Array,default:()=>[]}},data(){return{form:{model:{},rules:{url:[{required:!0,message:"* 图片地址不能为空",trigger:["change"]}],thumbnail:[{required:!0,message:"* 缩略图地址不能为空",trigger:["change"]}],name:[{required:!0,message:"* 图片名称不能为空",trigger:["change"]}]},saving:!1,saveErrored:!1,labelCol:{sm:{span:4},xs:{span:24}},wrapperCol:{sm:{span:20},xs:{span:24}}},attachmentSelectModal:{visible:!1}}},computed:{modalVisible:{get(){return this.visible},set(t){this.$emit("update:visible",t)}},takeTimeDefaultValue(){if(this.form.model.takeTime){const t=new Date(this.form.model.takeTime);return(0,r._)(t,"YYYY-MM-DD HH:mm:ss")}return(0,r._)(new Date,"YYYY-MM-DD HH:mm:ss")}},watch:{modalVisible(t){t&&(this.form.model=Object.assign({},this.photo))},photo:{deep:!0,handler(t){this.form.model=Object.assign({},t)}}},methods:{onClosed(){},handleAttachmentSelected({raw:t}){if(t.length){const{path:e,thumbPath:a,name:s}=t[0];this.$set(this.form.model,"url",e),this.$set(this.form.model,"thumbnail",a),this.$set(this.form.model,"name",s)}this.attachmentSelectModal.visible=!1},onTakeTimeChange(t){this.form.model.takeTime=t.valueOf()},handleSave(){const t=this;t.$refs.photoForm.validate((async e=>{if(e){t.form.saving=!0;try{if(t.form.model.id)await d.Z.photo.update(t.form.model.id,t.form.model);else{const{data:e}=await d.Z.photo.create(t.form.model);this.form.model=e}}catch(a){t.form.saveErrored=!0,this.$log.error("Failed to save this photo",a)}finally{setTimeout((()=>{t.form.saving=!1}),400)}}}))},handleSaveCallback(){this.form.saveErrored?this.form.saveErrored=!1:this.$emit("succeed",this.form.model)}}},m=c,h=a(1001),p=(0,h.Z)(m,l,n,!1,null,null,null),u=p.exports,f=a(20629),v=a(3775),b={mixins:[v.jB,v.KT],components:{PageView:o.B4,PhotoFormModal:u},data(){return{list:{data:[],loading:!1,params:{page:0,size:50,sort:["createTime,desc","id,asc"],keyword:null,team:void 0},total:0,hasPrevious:!1,hasNext:!1,selected:[],current:{}},attachmentSelectModal:{visible:!1},updateTeamForm:{team:void 0,visible:!1,saving:!1,saveErrored:!1},formVisible:!1,teams:[],options:{},optionFormVisible:!1}},created(){this.handleListPhotos(),this.handleListPhotoTeams(),this.handleListOptions()},computed:{pagination(){return{page:this.list.params.page+1,size:this.list.params.size,total:this.list.total}},computedTeams(){return this.teams.filter((t=>""!==t))},isItemSelect(){return function(t){return this.list.selected.findIndex((e=>e.id===t.id))>-1}},selectPreviousButtonDisabled(){const t=this.list.data.findIndex((t=>t.id===this.list.current.id));return 0===t&&!this.list.hasPrevious},selectNextButtonDisabled(){const t=this.list.data.findIndex((t=>t.id===this.list.current.id));return t===this.list.data.length-1&&!this.list.hasNext}},methods:{...(0,f.nv)(["refreshOptionsCache"]),async handleListPhotos(){try{this.list.loading=!0;const t=await d.Z.photo.list(this.list.params);this.list.data=t.data.content,this.list.total=t.data.total,this.list.hasPrevious=t.data.hasPrevious,this.list.hasNext=t.data.hasNext}catch(t){this.$log.error("Failed to get photos",t)}finally{this.list.loading=!1}},handleListPhotoTeams(){d.Z.photo.listTeams().then((t=>{this.teams=t.data}))},handlePageChange(t=1){this.list.params.page=t-1,this.handleListPhotos()},handlePageSizeChange(t,e){this.$log.debug(`Current: ${t}, PageSize: ${e}`),this.list.params.page=0,this.list.params.size=e,this.handleListPhotos()},handleQuery(){this.handlePageChange(1)},handleResetParam(){this.list.params.keyword=void 0,this.list.params.team=void 0,this.handlePageChange(1),this.handleListPhotoTeams()},handleItemClick(t){this.list.selected.length<=0?this.handleOpenForm(t):this.isItemSelect(t)?this.handleUnselect(t):this.handleSelect(t)},handleOpenForm(t){this.list.current=t,this.formVisible=!0},handleSelect(t){this.list.selected=[...this.list.selected,t]},handleUnselect(t){this.list.selected=this.list.selected.filter((e=>e.id!==t.id))},handleSelectAll(){this.list.selected=this.list.data},async handleAttachmentSelected({raw:t}){if(!t.length)return;const e=t.reverse().map((t=>({name:t.name,url:t.path,thumbnail:t.thumbPath})));try{await d.Z.photo.createInBatch(e),this.$message.success("添加成功")}catch(a){this.$log.error("Failed to create photos in batch",a)}finally{await this.handleListPhotos(),this.handleListPhotoTeams()}},async handleDeletePhotoInBatch(){if(this.list.selected.length<=0)return void this.$message.warn("你还未选择任何图片，请至少选择一个！");const t=this;this.$confirm({title:"确定要批量删除选中的图片吗？",content:"一旦删除不可恢复，请谨慎操作",async onOk(){try{const e=t.list.selected.map((t=>t.id));await d.Z.photo.deleteInBatch(e),t.list.selected=[],t.$message.success("删除成功")}catch(e){t.$log.error("Failed to delete selected photos",e)}finally{await t.handleListPhotos(),t.handleListPhotoTeams()}}})},async handleUpdateTeamInBatch(){const t=this.list.selected.map((t=>({...t,team:this.updateTeamForm.team})));try{this.updateTeamForm.saving=!0,await d.Z.photo.updateInBatch(t),this.$message.success("更改成功")}catch(e){this.updateTeamForm.saveErrored=!0,this.$log.error("Failed to change team in batch",e)}finally{setTimeout((()=>{this.updateTeamForm.saving=!1}),400)}},handleUpdateTeamInBatchCallback(){this.updateTeamForm.saveErrored?this.updateTeamForm.saveErrored=!1:(this.updateTeamForm.visible=!1,this.updateTeamForm.team=void 0,this.list.selected=[],this.handleListPhotos())},handleOpenUpdateTeamForm(){this.updateTeamForm.visible=!0,this.$nextTick((()=>{this.$refs.teamInput.focus()}))},async onSaveSucceed(t){await this.handleListPhotos(),this.list.current=t,this.handleListPhotoTeams()},handleListOptions(){d.Z.option.listAsMapViewByKeys(["photos_page_size","photos_title"]).then((t=>{this.options=t.data}))},handleSaveOptions(){d.Z.option.saveMapView(this.options).then((()=>{this.$message.success("保存成功！"),this.optionFormVisible=!1})).finally((()=>{this.handleListOptions(),this.refreshOptionsCache()}))},async handleSelectPrevious(){const t=this.list.data.findIndex((t=>t.id===this.list.current.id));t>0?this.list.current=this.list.data[t-1]:0===t&&this.list.hasPrevious&&(this.list.params.page--,await this.handleListPhotos(),this.list.current=this.list.data[this.list.data.length-1])},async handleSelectNext(){const t=this.list.data.findIndex((t=>t.id===this.list.current.id));t<this.list.data.length-1?this.list.current=this.list.data[t+1]:t===this.list.data.length-1&&this.list.hasNext&&(this.list.params.page++,await this.handleListPhotos(),this.list.current=this.list.data[0])}}},g=b,y=(0,h.Z)(g,s,i,!1,null,null,null),k=y.exports}}]);