"use strict";(self["webpackChunkhalo_admin"]=self["webpackChunkhalo_admin"]||[]).push([[5353],{35353:function(e,t,a){a.r(t),a.d(t,{default:function(){return u}});var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"option-tab-wrapper"},[t("a-card",{attrs:{bodyStyle:{padding:0},bordered:!1}},[t("div",{staticClass:"table-page-search-wrapper"},[t("a-form",{attrs:{layout:"inline"}},[t("a-row",{attrs:{gutter:48}},[t("a-col",{attrs:{md:6,sm:24}},[t("a-form-item",{attrs:{label:"关键词："}},[t("a-input",{on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery()}},model:{value:e.queryParam.keyword,callback:function(t){e.$set(e.queryParam,"keyword",t)},expression:"queryParam.keyword"}})],1)],1),t("a-col",{attrs:{md:6,sm:24}},[t("a-form-item",{attrs:{label:"类型："}},[t("a-select",{attrs:{allowClear:"",placeholder:"请选择类型"},on:{change:function(t){return e.handleQuery()}},model:{value:e.queryParam.type,callback:function(t){e.$set(e.queryParam,"type",t)},expression:"queryParam.type"}},e._l(Object.keys(e.optionType),(function(a){return t("a-select-option",{key:a,attrs:{value:a}},[e._v(e._s(e.optionType[a].text)+" ")])})),1)],1)],1),t("a-col",{attrs:{md:12,sm:24}},[t("span",{staticClass:"table-page-search-submitButtons"},[t("a-space",[t("a-button",{attrs:{type:"primary"},on:{click:function(t){return e.handleQuery()}}},[e._v("查询")]),t("a-button",{on:{click:function(t){return e.handleResetParam()}}},[e._v("重置")])],1)],1)])],1)],1)],1),t("div",{staticClass:"table-operator"},[t("a-button",{attrs:{icon:"plus",type:"primary"},on:{click:e.handleOpenFormModal}},[e._v("新增")])],1),t("div",{staticClass:"mt-4"},[t("a-table",{attrs:{columns:e.columns,dataSource:e.formattedData,loading:e.loading,pagination:!1,rowKey:e=>e.id,scrollToFirstRowOnChange:!0},scopedSlots:e._u([{key:"type",fn:function(a){return t("span",{},[e._v(" "+e._s(a.text)+" ")])}},{key:"createTime",fn:function(a){return t("span",{},[t("a-tooltip",{attrs:{placement:"top"}},[t("template",{slot:"title"},[e._v(" "+e._s(e._f("moment")(a))+" ")]),e._v(" "+e._s(e._f("timeAgo")(a))+" ")],2)],1)}},{key:"updateTime",fn:function(a){return t("span",{},[t("a-tooltip",{attrs:{placement:"top"}},[t("template",{slot:"title"},[e._v(" "+e._s(e._f("moment")(a))+" ")]),e._v(" "+e._s(e._f("timeAgo")(a))+" ")],2)],1)}},{key:"action",fn:function(a,o){return t("span",{},[t("a-button",{staticClass:"!p-0",attrs:{type:"link"},on:{click:function(t){return e.handleOpenEditFormModal(o)}}},[e._v("编辑")]),t("a-divider",{attrs:{type:"vertical"}}),t("a-popconfirm",{attrs:{title:"你确定要永久删除该变量？",cancelText:"取消",okText:"确定"},on:{confirm:function(t){return e.handleDeleteOption(o.id)}}},[t("a-button",{staticClass:"!p-0",attrs:{type:"link"}},[e._v("删除")])],1)],1)}}])}),t("div",{staticClass:"page-wrapper"},[t("a-pagination",{staticClass:"pagination",attrs:{current:e.pagination.page,defaultPageSize:e.pagination.size,pageSizeOptions:["10","20","50","100"],total:e.pagination.total,showLessItems:"",showSizeChanger:""},on:{change:e.handlePaginationChange,showSizeChange:e.handlePaginationChange}})],1)],1)]),t("a-modal",{attrs:{afterClose:e.onFormClose,title:e.formTitle},model:{value:e.form.visible,callback:function(t){e.$set(e.form,"visible",t)},expression:"form.visible"}},[t("template",{slot:"footer"},[t("ReactiveButton",{attrs:{errored:e.form.saveErrored,loading:e.form.saving,erroredText:"保存失败",loadedText:"保存成功",text:"保存"},on:{callback:e.handleSaveOrUpdateCallback,click:e.handleSaveOrUpdate}})],1),e.form.model.type===e.optionType.INTERNAL.value?t("a-alert",{attrs:{banner:"",closable:"",message:"注意：在不知道系统变量的具体用途时，请不要随意修改！"}}):e._e(),t("a-form-model",{ref:"optionForm",attrs:{model:e.form.model,rules:e.form.rules,layout:"vertical"}},[t("a-form-model-item",{attrs:{label:"Key：",prop:"key"}},[t("a-input",{ref:"keyInput",model:{value:e.form.model.key,callback:function(t){e.$set(e.form.model,"key",t)},expression:"form.model.key"}})],1),t("a-form-model-item",{attrs:{label:"Value：",prop:"value"}},[t("a-input",{attrs:{autoSize:{minRows:5},type:"textarea"},model:{value:e.form.model.value,callback:function(t){e.$set(e.form.model,"value",t)},expression:"form.model.value"}})],1)],1)],2)],1)},i=[],n=a(73541),r=a(20629);const s=[{title:"Key",dataIndex:"key",ellipsis:!0,scopedSlots:{customRender:"key"}},{title:"Value",dataIndex:"value",ellipsis:!0,scopedSlots:{customRender:"value"}},{title:"类型",dataIndex:"typeProperty",width:"100px",scopedSlots:{customRender:"type"}},{title:"创建时间",dataIndex:"createTime",width:"200px",scopedSlots:{customRender:"createTime"}},{title:"更新时间",dataIndex:"updateTime",width:"200px",scopedSlots:{customRender:"updateTime"}},{title:"操作",dataIndex:"action",width:"120px",scopedSlots:{customRender:"action"}}];var l={name:"OptionsList",data(){return{optionType:{INTERNAL:{value:"INTERNAL",text:"系统"},CUSTOM:{value:"CUSTOM",text:"自定义"}},columns:s,pagination:{page:1,size:10,sort:null,total:1},queryParam:{page:0,size:10,sort:null,keyword:null,type:null},loading:!1,options:[],form:{visible:!1,model:{},rules:{key:[{required:!0,message:"* Key 不能为空",trigger:["change"]}],value:[{required:!0,message:"* Value 不能为空",trigger:["change"]}]},saving:!1,saveErrored:!1}}},computed:{formattedData(){return this.options.map((e=>(e.typeProperty=this.optionType[e.type],e)))},formTitle(){return this.form.model.id?"编辑":"新增"}},beforeMount(){this.handleListOptions()},methods:{...(0,r.nv)(["refreshOptionsCache"]),handleListOptions(){this.loading=!0,this.queryParam.page=this.pagination.page-1,this.queryParam.size=this.pagination.size,this.queryParam.sort=this.pagination.sort,n.Z.option.listAsView(this.queryParam).then((e=>{if(0===e.data.content.length&&this.pagination.page>0)return this.pagination.page--,void this.handleListOptions();this.options=e.data.content,this.pagination.total=e.data.total})).finally((()=>{this.loading=!1}))},handleQuery(){this.handlePaginationChange(1,this.pagination.size)},handleDeleteOption(e){n.Z.option.delete(e).then((()=>{this.$message.success("删除成功！")})).finally((()=>{this.handleListOptions(),this.refreshOptionsCache()}))},handleOpenFormModal(){this.form.visible=!0,this.$nextTick((()=>{this.$refs.keyInput.focus()}))},handleOpenEditFormModal(e){this.form.model=e,this.form.visible=!0,this.$nextTick((()=>{this.$refs.keyInput.focus()}))},handlePaginationChange(e,t){this.$log.debug(`Current: ${e}, PageSize: ${t}`),this.pagination.page=e,this.pagination.size=t,this.handleListOptions()},handleResetParam(){this.queryParam.keyword=null,this.queryParam.type=null,this.handlePaginationChange(1,this.pagination.size)},onFormClose(){this.form.visible=!1,this.form.model={}},handleSaveOrUpdate(){const e=this;e.$refs.optionForm.validate((t=>{t&&(e.form.saving=!0,e.form.model.id?n.Z.option.update(e.form.model.id,e.form.model).catch((()=>{e.form.saveErrored=!0})).finally((()=>{setTimeout((()=>{e.form.saving=!1}),400)})):(e.form.model.type=e.optionType.CUSTOM.value,n.Z.option.create(e.form.model).catch((()=>{e.form.saveErrored=!0})).finally((()=>{setTimeout((()=>{e.form.saving=!1}),400)}))))}))},handleSaveOrUpdateCallback(){this.form.saveErrored?this.form.saveErrored=!1:(this.form.model={},this.form.visible=!1,this.handleListOptions(),this.refreshOptionsCache())}}},p=l,d=a(1001),m=(0,d.Z)(p,o,i,!1,null,null,null),u=m.exports}}]);