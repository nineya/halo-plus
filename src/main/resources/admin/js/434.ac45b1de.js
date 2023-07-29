"use strict";(self["webpackChunkhalo_admin"]=self["webpackChunkhalo_admin"]||[]).push([[434],{77434:function(e,t,a){a.r(t),a.d(t,{default:function(){return _}});var i=function(){var e=this,t=e._self._c;return t("page-view",{scopedSlots:e._u([{key:"extra",fn:function(){return[t("a-space",[t("a-button",{attrs:{type:"primary"},on:{click:function(t){e.form.visible=!0}}},[e._v("添加")])],1)]},proxy:!0}])},[t("LinkCreateModal",{attrs:{form_:e.form,teams:e.computedTeams},on:{"update:form_":function(t){e.form=t},close:function(t){e.form.visible=!1,e.form.model={}},createOrUpdateLink:e.handleCreateOrUpdateLink,saved:e.handleSavedCallback}}),t("a-row",{attrs:{gutter:12}},[t("a-col",{staticClass:"pb-3",attrs:{span:24}},[0===e.linkTeam.length?t("a-empty"):t("draggable",e._b({staticClass:"list-group",attrs:{list:e.linkTeam,group:"pull: 'false', put: false",handle:".mover"},on:{update:e.handleUpdateInBatch}},"draggable",e.dragOptions,!1),[t("transition-group",{attrs:{type:"transition"}},e._l(e.linkTeam,(function(a){return t("a-card",{key:a.team,staticStyle:{"margin-bottom":"10px"},attrs:{bodyStyle:{padding:"16px"}},scopedSlots:e._u([{key:"title",fn:function(){return[e._v(" "+e._s(a.team?a.team:"默认分组")+" "),t("a-icon",{staticClass:"cursor-move mover ml-1 list-group-item",attrs:{type:"bars"}})]},proxy:!0}],null,!0)},[t("draggable",e._b({attrs:{list:a.links,group:"link"},on:{add:function(t){e.modal.lastAdd=a},remove:function(t){return e.handleRemove(t,a)},update:e.handleUpdateInBatch}},"draggable",e.dragOptions,!1),[t("transition-group",{staticClass:"grid grid-cols-1 gap-4 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4",attrs:{type:"transition"}},e._l(a.links,(function(a){return t("div",{key:a.name,staticClass:"cursor-move relative flex items-center space-x-3 rounded border border-solid border-gray-300 bg-white px-2 py-2 shadow-sm hover:border-gray-400 hover:shadow"},[a.logo?t("div",{staticClass:"flex-shrink-0"},[t("a-avatar",{staticClass:"h-12 w-12 rounded-full",attrs:{src:a.logo,size:"large"}})],1):e._e(),t("div",{staticClass:"flex flex-col gap-y-1.5 overflow-hidden"},[t("p",{staticClass:"mb-0 truncate text-sm font-medium text-gray-900 truncate"},[e._v(" "+e._s(a.name)+" ")]),t("p",{staticClass:"mb-0 truncate text-sm text-gray-500"},[e._v(e._s(a.description))])]),t("div",{staticClass:"absolute top-2 right-2 cursor-pointer hover:text-blue-600"},[t("a-dropdown",{scopedSlots:e._u([{key:"overlay",fn:function(){return[t("a-menu",[t("a-menu-item",{on:{click:function(t){return e.handleEdit(a)}}},[e._v(" 编辑")]),t("a-menu-item",{on:{click:function(t){return e.handleDeleteLink(a.id)}}},[e._v(" 删除")])],1)]},proxy:!0}],null,!0)},[t("div",{staticStyle:{width:"30px",display:"flex","justify-content":"flex-end"}},[t("a-icon",{attrs:{type:"more"}})],1)])],1)])})),0)],1)],1)})),1)],1)],1)],1),t("div",{staticStyle:{position:"fixed",bottom:"30px",right:"30px"}},[t("a-button",{attrs:{icon:"setting",shape:"circle",size:"large",type:"primary"},on:{click:function(t){e.optionsModal.visible=!0}}})],1),t("a-modal",{attrs:{afterClose:()=>e.optionsModal.visible=!1,title:"页面设置"},model:{value:e.optionsModal.visible,callback:function(t){e.$set(e.optionsModal,"visible",t)},expression:"optionsModal.visible"}},[t("template",{slot:"footer"},[t("a-button",{key:"submit",attrs:{type:"primary"},on:{click:function(t){return e.handleSaveOptions()}}},[e._v("保存")])],1),t("a-form",{attrs:{layout:"vertical"}},[t("a-form-item",{attrs:{help:"* 需要主题进行适配",label:"页面标题："}},[t("a-input",{model:{value:e.optionsModal.data.links_title,callback:function(t){e.$set(e.optionsModal.data,"links_title",t)},expression:"optionsModal.data.links_title"}})],1)],1)],2)],1)},o=[],s=(a(57658),a(67731)),l=a(20629),r=a(9980),n=a.n(r),m=a(3775),d=a(73541),c=function(){var e=this,t=e._self._c;return t("a-modal",{attrs:{visible:e.form.visible,title:e.title,closable:!1,maskClosable:!1},scopedSlots:e._u([{key:"footer",fn:function(){return[t("ReactiveButton",{attrs:{errored:e.form.errored,loading:e.form.saving,erroredText:"保存失败",loadedText:"保存成功",text:"保存",type:"primary"},on:{callback:function(t){return e.$emit("saved")},click:e.handleCreateOrUpdateLink}}),t("a-button",{on:{click:function(t){return e.$emit("close")}}},[e._v("取消")])]},proxy:!0}])},[t("a-form-model",{ref:"linkForm",attrs:{model:e.form.model,rules:e.rules,layout:"horizontal"}},[t("a-form-model-item",{attrs:{label:"网站名称：",prop:"name"}},[t("a-input",{model:{value:e.form.model.name,callback:function(t){e.$set(e.form.model,"name",t)},expression:"form.model.name"}})],1),t("a-form-model-item",{attrs:{help:"* 需要加上 http://",label:"网站地址：",prop:"url"}},[t("a-input",{model:{value:e.form.model.url,callback:function(t){e.$set(e.form.model,"url",t)},expression:"form.model.url"}})],1),t("a-form-model-item",{attrs:{label:"Logo：",prop:"logo"}},[t("a-input",{model:{value:e.form.model.logo,callback:function(t){e.$set(e.form.model,"logo",t)},expression:"form.model.logo"}})],1),t("a-form-model-item",{attrs:{label:"分组：",prop:"team"}},[t("a-auto-complete",{attrs:{dataSource:e.teams,allowClear:""},model:{value:e.form.model.team,callback:function(t){e.$set(e.form.model,"team",t)},expression:"form.model.team"}})],1),t("a-form-model-item",{attrs:{label:"描述：",prop:"description"}},[t("a-input",{attrs:{autoSize:{minRows:5},type:"textarea"},model:{value:e.form.model.description,callback:function(t){e.$set(e.form.model,"description",t)},expression:"form.model.description"}})],1)],1)],1)},p=[],h={name:"LinkCreateModal",props:{form_:Object,teams:Array},data(){return{rules:{name:[{required:!0,message:"* 友情链接名称不能为空",trigger:["change"]},{max:255,message:"* 友情链接名称的字符长度不能超过 255",trigger:["change"]}],url:[{required:!0,message:"* 友情链接地址不能为空",trigger:["change"]},{max:1023,message:"* 友情链接地址的字符长度不能超过 1023",trigger:["change"]},{type:"url",message:"* 友情链接地址格式有误",trigger:["change"]}],logo:[{max:1023,message:"* 友情链接 Logo 的字符长度不能超过 1023",trigger:["change"]}],description:[{max:255,message:"* 友情链接描述的字符长度不能超过 255",trigger:["change"]}],team:[{max:255,message:"* 友情链接分组的字符长度 255",trigger:["change"]}]}}},computed:{form:{get(){return this.form_},set(e){this.$emit("update:form",e)}},title(){return this.isUpdateMode?"修改友情链接":"添加友情链接"},isUpdateMode(){return!!this.form.model.id},dragOptions(){return{animation:200,disabled:!1,ghostClass:"ghost"}}},methods:{handleCreateOrUpdateLink(){const e=this;e.$refs.linkForm.validate((t=>{t&&e.$emit("createOrUpdateLink")}))}}},u=h,f=a(1001),g=(0,f.Z)(u,c,p,!1,null,"146d196c",null),k=g.exports,v=a(45361),b={mixins:[m.jB,m.KT],components:{LinkCreateModal:k,PageView:s.B4,draggable:n()},data(){return{modal:{toDelete:[],visible:!1,newIndex:null,lastAdd:null,lastRemove:null},table:{data:[],loading:!1},form:{visible:!1,model:{},saving:!1,errored:!1},optionsModal:{visible:!1,data:{}},teams:[],linkTeam:[]}},computed:{isUpdateMode(){return!!this.form.model.id},computedTeams(){return this.teams.filter((e=>""!==e))},dragOptions(){return{animation:200,disabled:!1,ghostClass:"ghost"}}},created(){this.handleListLinks(),this.handleListLinkTeams(),this.handleListOptions()},methods:{...(0,l.nv)(["refreshOptionsCache"]),getPriority(){const e=[];for(const a of this.linkTeam)for(const t of a.links)t.team=a.team,e.push(t);let t=e.length;for(const a of e)a.priority=t--;return e},handleUpdateInBatch(){const e=this.getPriority();d.Z.link.updateInBatch(e).finally((()=>{this.table.loading=!1}))},removeConfirm(){v.Z.confirm({title:"确定移出分组吗",content:"移出最后一个链接后，该分组将消失。确定要移出分组吗？",onCancel:()=>{this.recoverTeam()},onOk:()=>{this.removeTeam()}})},removeTeam(){this.linkTeam.splice(this.linkTeam.indexOf(this.modal.lastRemove),1),this.modal.newIndex=null},recoverTeam(){const e=this.modal.lastAdd.links.splice(this.modal.newIndex,1);this.modal.lastRemove.links.push(e[0]),this.modal.newIndex=null},handleRemove(e,t){this.modal.lastRemove=t,0===t.links.length&&(this.modal.newIndex=e.newIndex,this.removeConfirm()),this.handleUpdateInBatch()},splitIntoTeam(e){const t=new Map;for(const a of e)if(t.has(a.team)){const e=t.get(a.team);e.links.push(a),e.priority<a.priority&&(e.priority=a.priority)}else{const e={team:a.team,priority:a.priority,links:[a]};t.set(a.team,e)}this.linkTeam=Array.from(t.values()).sort(((e,t)=>t.priority-e.priority))},handleListLinks(){this.table.loading=!0,d.Z.link.list().then((e=>{this.table.data=e.data,this.table.data.sort(((e,t)=>t.priority-e.priority)),this.splitIntoTeam(this.table.data)})).finally((()=>{this.table.loading=!1}))},handleListLinkTeams(){d.Z.link.listTeams().then((e=>{this.teams=e.data}))},handleListOptions(){d.Z.option.listAsMapViewByKeys(["links_title"]).then((e=>{this.optionsModal.data=e.data}))},handleEdit(e){this.form.visible=!0,this.form.model=Object.assign({},e)},handleDeleteLink(e){d.Z.link.delete(e).then((()=>{this.$message.success("删除成功！")})).finally((()=>{this.handleListLinks(),this.handleListLinkTeams()}))},handleCreateOrUpdateLink(){if(this.form.saving=!0,this.isUpdateMode){let e,t,a;for(const o of this.linkTeam){if(e&&t)break;if(o.team===this.form.model.team){for(let e of o.links)if(e.id===this.form.model.id)return void d.Z.link.update(this.form.model.id,this.form.model).catch((()=>{this.form.errored=!0})).finally((()=>{setTimeout((()=>{this.form.saving=!1}),400)}));e=o}else for(let e=0;e<o.links.length;e++)if(o.links[e].id===this.form.model.id){t=o,a=e;break}}e||(e={links:[],priority:-1,team:this.form.model.team},this.linkTeam.push(e)),e.links.push(this.form.model),t.links.splice(a,1);const i=this.getPriority();d.Z.link.updateInBatch(i).catch((()=>{this.form.errored=!0})).finally((()=>{setTimeout((()=>{this.form.saving=!1}),400)}))}else d.Z.link.create(this.form.model).catch((()=>{this.form.errored=!0})).finally((()=>{setTimeout((()=>{this.form.saving=!1}),400)}))},handleSavedCallback(){this.form.errored?this.form.errored=!1:(this.form.model={},this.handleListLinks(),this.handleListLinkTeams(),this.form.visible=!1)},handleSaveOptions(){d.Z.option.saveMapView(this.optionsModal.data).then((()=>{this.$message.success("保存成功！"),this.optionsModal.visible=!1})).finally((()=>{this.handleListOptions(),this.refreshOptionsCache()}))}}},y=b,x=(0,f.Z)(y,i,o,!1,null,null,null),_=x.exports}}]);