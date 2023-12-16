"use strict";(self["webpackChunkhalo_admin"]=self["webpackChunkhalo_admin"]||[]).push([[5760],{20102:function(t,e,a){a.d(e,{Z:function(){return c}});var s=function(){var t=this,e=t._self._c;return e("a-modal",{attrs:{destroyOnClose:"",title:"评论回复"},on:{close:t.onClose},scopedSlots:t._u([{key:"footer",fn:function(){return[e("ReactiveButton",{attrs:{errored:t.submitErrored,loading:t.submitting,erroredText:"回复失败",loadedText:"回复成功",text:"回复",type:"primary"},on:{callback:t.handleSubmitCallback,click:t.handleSubmit}})]},proxy:!0}]),model:{value:t.modalVisible,callback:function(e){t.modalVisible=e},expression:"modalVisible"}},[e("a-form-model",{ref:"replyCommentForm",attrs:{model:t.model,rules:t.rules,layout:"vertical"}},[e("a-form-model-item",{attrs:{prop:"content"}},[e("a-input",{ref:"contentInput",attrs:{autoSize:{minRows:8},type:"textarea"},model:{value:t.model.content,callback:function(e){t.$set(t.model,"content",e)},expression:"model.content"}})],1)],1)],1)},n=[],i=a(73541),o={name:"CommentReplyModal",props:{visible:{type:Boolean,default:!0},comment:{type:Object,default:null},targetId:{type:Number,default:0},target:{type:String,required:!0,validator:t=>-1!==["post","sheet","journal"].indexOf(t)}},data(){return{model:{},submitting:!1,submitErrored:!1,rules:{content:[{required:!0,message:"* 内容不能为空",trigger:["change"]}]}}},computed:{modalVisible:{get(){return this.visible},set(t){this.$emit("update:visible",t)}}},watch:{modalVisible(t){t&&this.$nextTick((()=>{this.$refs.contentInput.focus()}))}},methods:{handleSubmit(){const t=this;t.$refs.replyCommentForm.validate((async e=>{if(e)try{t.submitting=!0,t.model.postId=t.targetId,t.comment&&(t.model.parentId=t.comment.id),await i.Z.comment.create(`${t.target}s`,t.model)}catch(a){t.submitErrored=!0}finally{setTimeout((()=>{t.submitting=!1}),400)}}))},handleSubmitCallback(){this.submitErrored?this.submitErrored=!1:(this.model={},this.modalVisible=!1,this.$emit("succeed"))},onClose(){this.model={},this.modalVisible=!1}}},l=o,r=a(1001),u=(0,r.Z)(l,s,n,!1,null,null,null),c=u.exports},55760:function(t,e,a){a.r(e),a.d(e,{default:function(){return _}});var s=function(){var t=this,e=t._self._c;return e("page-view",[e("div",{staticClass:"card-container"},[e("a-tabs",{attrs:{type:"card"},model:{value:t.activeKey,callback:function(e){t.activeKey=e},expression:"activeKey"}},t._l(t.panes,(function(a){return e("a-tab-pane",{key:a.key,attrs:{tab:a.title}},[e("comment-tab",{attrs:{target:a.key,defaultStatus:t.defaultStatus}})],1)})),1)],1)])},n=[],i=(a(57658),a(67731)),o=function(){var t=this,e=t._self._c;return e("div",{staticClass:"comment-tab-wrapper"},[e("a-card",{attrs:{bodyStyle:{padding:0},bordered:!1}},[e("div",{staticClass:"table-page-search-wrapper"},[e("a-form",{attrs:{layout:"inline"}},[e("a-row",{attrs:{gutter:48}},[e("a-col",{attrs:{md:6,sm:24}},[e("a-form-item",{attrs:{label:"关键词："}},[e("a-input",{on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleQuery()}},model:{value:t.list.params.keyword,callback:function(e){t.$set(t.list.params,"keyword",e)},expression:"list.params.keyword"}})],1)],1),e("a-col",{attrs:{md:6,sm:24}},[e("a-form-item",{attrs:{label:"评论状态："}},[e("a-select",{attrs:{allowClear:"",placeholder:"请选择评论状态"},on:{change:function(e){return t.handleQuery()}},model:{value:t.list.params.status,callback:function(e){t.$set(t.list.params,"status",e)},expression:"list.params.status"}},t._l(Object.keys(t.commentStatuses),(function(a){return e("a-select-option",{key:a,attrs:{value:a}},[t._v(" "+t._s(t.commentStatuses[a].text)+" ")])})),1)],1)],1),e("a-col",{attrs:{md:12,sm:24}},[e("span",{staticClass:"table-page-search-submitButtons"},[e("a-space",[e("a-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("查询")]),e("a-button",{on:{click:function(e){return t.handleResetParam()}}},[t._v("重置")])],1)],1)])],1)],1)],1),e("div",{staticClass:"table-operator"},[e("a-dropdown",{directives:[{name:"show",rawName:"v-show",value:null!=t.list.params.status&&""!==t.list.params.status&&!t.isMobile(),expression:"list.params.status != null && list.params.status !== '' && !isMobile()"}],scopedSlots:t._u([{key:"overlay",fn:function(){return[e("a-menu",[t.list.params.status===t.commentStatuses.AUDITING.value?e("a-menu-item",{key:"1",on:{click:function(e){return t.handleChangeStatusInBatch(t.commentStatuses.PUBLISHED.value)}}},[t._v(" 通过 ")]):t._e(),[t.commentStatuses.PUBLISHED.value,t.commentStatuses.AUDITING.value].includes(t.list.params.status)?e("a-menu-item",{key:"2",on:{click:function(e){return t.handleChangeStatusInBatch(t.commentStatuses.RECYCLE.value)}}},[t._v(" 移到回收站 ")]):t._e(),t.list.params.status===t.commentStatuses.RECYCLE.value?e("a-menu-item",{key:"3",on:{click:t.handleDeleteInBatch}},[t._v(" 永久删除 ")]):t._e()],1)]},proxy:!0}])},[e("a-button",[t._v(" 批量操作 "),e("a-icon",{attrs:{type:"down"}})],1)],1)],1),e("div",{staticClass:"mt-4"},[t.isMobile()?e("a-list",{attrs:{dataSource:t.list.data,loading:t.list.loading,pagination:!1,itemLayout:"vertical",size:"large"},scopedSlots:t._u([{key:"renderItem",fn:function(a,s){return[e("a-list-item",{key:s,scopedSlots:t._u([{key:"actions",fn:function(){return[e("a-dropdown",{attrs:{trigger:["click"],placement:"topLeft"},scopedSlots:t._u([{key:"overlay",fn:function(){return[e("a-menu",[a.status===t.commentStatuses.AUDITING.value?e("a-menu-item",{on:{click:function(e){return t.handleChangeStatus(a.id,t.commentStatuses.PUBLISHED.value)}}},[t._v(" 通过 ")]):t._e(),a.status===t.commentStatuses.AUDITING.value?e("a-menu-item",{on:{click:function(e){return t.handlePublishAndReply(a)}}},[t._v(" 通过并回复 ")]):a.status===t.commentStatuses.PUBLISHED.value?e("a-menu-item",{on:{click:function(e){return t.handleOpenReplyModal(a)}}},[t._v(" 回复 ")]):a.status===t.commentStatuses.RECYCLE.value?e("a-menu-item",[e("a-popconfirm",{attrs:{title:"你确定要还原该评论？",cancelText:"取消",okText:"确定"},on:{confirm:function(e){return t.handleChangeStatus(a.id,t.commentStatuses.PUBLISHED.value)}}},[t._v(" 还原 ")])],1):t._e(),[t.commentStatuses.PUBLISHED.value,t.commentStatuses.AUDITING.value].includes(a.status)?e("a-menu-item",[e("a-popconfirm",{attrs:{title:"你确定要将该评论移到回收站？",cancelText:"取消",okText:"确定"},on:{confirm:function(e){return t.handleChangeStatus(a.id,t.commentStatuses.RECYCLE.value)}}},[t._v(" 回收站 ")])],1):a.status===t.commentStatuses.RECYCLE.value?e("a-menu-item",[e("a-popconfirm",{attrs:{title:"你确定要永久删除该评论？",cancelText:"取消",okText:"确定"},on:{confirm:function(e){return t.handleDelete(a.id)}}},[t._v(" 删除 ")])],1):t._e()],1)]},proxy:!0}],null,!0)},[e("span",[e("a-icon",{attrs:{type:"bars"}})],1)])]},proxy:!0},{key:"extra",fn:function(){return[e("a-badge",{attrs:{status:t.commentStatuses[a.status].status,text:t._f("statusText")(a.status)}})]},proxy:!0}],null,!0)},[e("a-list-item-meta",{scopedSlots:t._u([{key:"description",fn:function(){return[t._v(" 发表在 "),"posts"===t.targetName?e("a",{attrs:{href:a.post.fullPath,target:"_blank"}},[t._v(" 《"+t._s(a.post.title)+"》 ")]):t._e(),"sheets"===t.targetName?e("a",{attrs:{href:a.sheet.fullPath,target:"_blank"}},[t._v(" 《"+t._s(a.sheet.title)+"》 ")]):t._e(),"journals"===t.targetName?e("a",{attrs:{href:"javascript:void(0);"}},[t._v(" 《"+t._s(t._f("moment")(a.journal.createTime))+"》 ")]):t._e()]},proxy:!0},{key:"avatar",fn:function(){return[e("a-avatar",{attrs:{src:a.avatar,size:"large"}})]},proxy:!0},{key:"title",fn:function(){return[e("div",{staticClass:"truncate"},[a.isAdmin?e("a-icon",{staticClass:"mr-2",attrs:{type:"user"}}):t._e(),a.authorUrl?e("a",{staticClass:"mr-1",attrs:{href:a.authorUrl,target:"_blank"}},[t._v(t._s(a.author))]):e("span",{staticClass:"mr-1"},[t._v(t._s(a.author))]),e("small",{staticStyle:{color:"rgba(0, 0, 0, 0.45)"}},[t._v(" "+t._s(t._f("timeAgo")(a.createTime))+" ")])],1)]},proxy:!0}],null,!0)}),e("div",{staticClass:"comment-content-wrapper",domProps:{innerHTML:t._s(t.$options.filters.markdownRender(a.content))}})],1)]}}],null,!1,1276610109)}):e("a-table",{attrs:{columns:t.columns,dataSource:t.list.data,loading:t.list.loading,pagination:!1,rowKey:t=>t.id,rowSelection:{selectedRowKeys:t.selectedRowKeys,onChange:t.onSelectionChange,getCheckboxProps:t.getCheckboxProps},scrollToFirstRowOnChange:""},scopedSlots:t._u([{key:"author",fn:function(a,s){return[s.isAdmin?e("a-icon",{staticClass:"mr-2",attrs:{type:"user"}}):t._e(),s.authorUrl?e("a",{attrs:{href:s.authorUrl,target:"_blank"}},[t._v(t._s(a))]):e("span",[t._v(t._s(a))])]}},{key:"content",fn:function(a){return[e("div",{staticClass:"comment-content-wrapper",domProps:{innerHTML:t._s(t.$options.filters.markdownRender(a))}})]}},{key:"status",fn:function(a){return[e("a-badge",{attrs:{status:t.commentStatuses[a].status,text:t._f("statusText")(a)}})]}},"posts"===t.targetName?{key:"post",fn:function(a){return[e("a",{attrs:{href:a.fullPath,target:"_blank"}},[t._v(" "+t._s(a.title)+" ")])]}}:null,"sheets"===t.targetName?{key:"sheet",fn:function(a){return[e("a",{attrs:{href:a.fullPath,target:"_blank"}},[t._v(" "+t._s(a.title)+" ")])]}}:null,"journals"===t.targetName?{key:"journal",fn:function(a){return[e("p",{staticClass:"comment-content-wrapper",domProps:{innerHTML:t._s(a.content)}})]}}:null,{key:"createTime",fn:function(a){return[e("a-tooltip",{attrs:{placement:"top"},scopedSlots:t._u([{key:"title",fn:function(){return[t._v(" "+t._s(t._f("moment")(a))+" ")]},proxy:!0}],null,!0)},[t._v(" "+t._s(t._f("timeAgo")(a))+" ")])]}},{key:"action",fn:function(a,s){return[s.status===t.commentStatuses.AUDITING.value?e("a-dropdown",{attrs:{trigger:["click"]},scopedSlots:t._u([{key:"overlay",fn:function(){return[e("a-menu",[e("a-menu-item",{key:"1",on:{click:function(e){return t.handleChangeStatus(s.id,t.commentStatuses.PUBLISHED.value)}}},[t._v(" 通过 ")]),e("a-menu-item",{key:"2",on:{click:function(e){return t.handlePublishAndReply(s)}}},[t._v(" 通过并回复")])],1)]},proxy:!0}],null,!0)},[e("a-button",{staticClass:"!p-0",attrs:{type:"link"}},[t._v("通过")])],1):s.status===t.commentStatuses.PUBLISHED.value?e("a-button",{staticClass:"!p-0",attrs:{type:"link"},on:{click:function(e){return t.handleOpenReplyModal(s)}}},[t._v(" 回复 ")]):s.status===t.commentStatuses.RECYCLE.value?e("a-popconfirm",{attrs:{title:"你确定要还原该评论？",cancelText:"取消",okText:"确定"},on:{confirm:function(e){return t.handleChangeStatus(s.id,t.commentStatuses.PUBLISHED.value)}}},[e("a-button",{staticClass:"!p-0",attrs:{type:"link"}},[t._v("还原")])],1):t._e(),e("a-divider",{attrs:{type:"vertical"}}),[t.commentStatuses.PUBLISHED.value,t.commentStatuses.AUDITING.value].includes(s.status)?e("a-popconfirm",{attrs:{title:"你确定要将该评论移到回收站？",cancelText:"取消",okText:"确定"},on:{confirm:function(e){return t.handleChangeStatus(s.id,t.commentStatuses.RECYCLE.value)}}},[e("a-button",{staticClass:"!p-0",attrs:{type:"link"}},[t._v("回收站")])],1):s.status===t.commentStatuses.RECYCLE.value?e("a-popconfirm",{attrs:{title:"你确定要永久删除该评论？",cancelText:"取消",okText:"确定"},on:{confirm:function(e){return t.handleDelete(s.id)}}},[e("a-button",{staticClass:"!p-0",attrs:{type:"link"}},[t._v("删除")])],1):t._e()]}}],null,!0)}),e("div",{staticClass:"page-wrapper"},[e("a-pagination",{staticClass:"pagination",attrs:{current:t.pagination.page,defaultPageSize:t.pagination.size,pageSizeOptions:["10","20","50","100"],total:t.pagination.total,showLessItems:"",showSizeChanger:""},on:{change:t.handlePageChange,showSizeChange:t.handlePageSizeChange}})],1)],1)]),e("CommentReplyModal",{attrs:{comment:t.selectedComment,target:t.target,"target-id":t.targetId,visible:t.replyModalVisible},on:{"update:visible":function(e){t.replyModalVisible=e},succeed:t.onReplyModalClose}})],1)},l=[],r=a(20102),u=a(3775),c=a(73541),d=a(10438);const m=[{title:"昵称",dataIndex:"author",width:"150px",ellipsis:!0,scopedSlots:{customRender:"author"}},{title:"内容",dataIndex:"content",scopedSlots:{customRender:"content"}},{title:"状态",dataIndex:"status",width:"100px",scopedSlots:{customRender:"status"}},{title:"评论文章",dataIndex:"post",width:"200px",ellipsis:!0,scopedSlots:{customRender:"post"}},{title:"日期",dataIndex:"createTime",width:"170px",scopedSlots:{customRender:"createTime"}},{title:"操作",dataIndex:"action",width:"180px",scopedSlots:{customRender:"action"}}],h=[{title:"昵称",dataIndex:"author",width:"150px",ellipsis:!0,scopedSlots:{customRender:"author"}},{title:"内容",dataIndex:"content",scopedSlots:{customRender:"content"}},{title:"状态",dataIndex:"status",width:"100px",scopedSlots:{customRender:"status"}},{title:"评论页面",dataIndex:"sheet",width:"200px",ellipsis:!0,scopedSlots:{customRender:"sheet"}},{title:"日期",dataIndex:"createTime",width:"170px",scopedSlots:{customRender:"createTime"}},{title:"操作",dataIndex:"action",width:"180px",scopedSlots:{customRender:"action"}}],p=[{title:"昵称",dataIndex:"author",width:"150px",ellipsis:!0,scopedSlots:{customRender:"author"}},{title:"内容",dataIndex:"content",scopedSlots:{customRender:"content"}},{title:"状态",dataIndex:"status",width:"100px",scopedSlots:{customRender:"status"}},{title:"评论日志",dataIndex:"journal",width:"300px",ellipsis:!0,scopedSlots:{customRender:"journal"}},{title:"日期",dataIndex:"createTime",width:"170px",scopedSlots:{customRender:"createTime"}},{title:"操作",dataIndex:"action",width:"180px",scopedSlots:{customRender:"action"}}];var y={name:"CommentTab",components:{CommentReplyModal:r.Z},mixins:[u.jB,u.KT],props:{target:{type:String,required:!1,default:"post",validator:function(t){return-1!==["post","sheet","journal"].indexOf(t)}},defaultStatus:{type:String,default:void 0}},data(){return{commentStatuses:d.v$,list:{data:[],loading:!1,total:0,hasPrevious:!1,hasNext:!1,params:{page:0,size:10,keyword:null,status:void 0}},selectedRowKeys:[],selectedRows:[],selectedComment:{},replyModalVisible:!1}},computed:{pagination(){return{page:this.list.params.page+1,size:this.list.params.size,total:this.list.total}},columns(){return"posts"===this.targetName?m:"sheets"===this.targetName?h:"journals"===this.targetName?p:{}},targetName(){return`${this.target}s`},targetId(){return 0===Object.keys(this.selectedComment).length?0:"posts"===this.targetName?this.selectedComment.post.id:"sheets"===this.targetName?this.selectedComment.sheet.id:"journals"===this.targetName?this.selectedComment.journal.id:0}},watch:{defaultStatus(t){this.list.params.status=t,this.$nextTick((()=>{this.handleListComments()}))}},mounted(){this.list.params.status=this.defaultStatus,this.$nextTick((()=>{this.handleListComments()}))},methods:{async handleListComments(){try{this.list.loading=!0;const t=await c.Z.comment.list(this.targetName,this.list.params);if(0===t.data.content.length&&this.list.params.page>0)return this.list.params.page--,void await this.handleListComments();this.list.data=t.data.content,this.list.total=t.data.total,this.list.hasPrevious=t.data.hasPrevious,this.list.hasNext=t.data.hasNext}catch(t){this.$log.error(t)}finally{this.list.loading=!1}},handleQuery(){this.handleClearRowKeys(),this.handlePageChange(1)},async handleChangeStatus(t,e){try{await c.Z.comment.updateStatusById(this.targetName,t,e),this.$message.success("操作成功！")}catch(a){this.$log.error("Failed to change comment status",a)}finally{await this.handleListComments()}},async handleChangeStatusInBatch(t){if(this.selectedRowKeys.length)try{this.$log.debug(`commentIds: ${this.selectedRowKeys}, status: ${t}`),await c.Z.comment.updateStatusInBatch(this.targetName,this.selectedRowKeys,t),this.selectedRowKeys=[]}catch(e){this.$log.error("Failed to change comment status in batch",e)}finally{await this.handleListComments()}else this.$message.info("请至少选择一项！")},async handleDelete(t){try{await c.Z.comment.delete(this.targetName,t),this.$message.success("删除成功！")}catch(e){this.$log.error("Failed to delete comment",e)}finally{await this.handleListComments()}},async handleDeleteInBatch(){if(this.selectedRowKeys.length)try{this.$log.debug(`delete: ${this.selectedRowKeys}`),await c.Z.comment.deleteInBatch(this.targetName,this.selectedRowKeys),this.selectedRowKeys=[]}catch(t){this.$log.error("Failed to delete comments in batch",t)}finally{await this.handleListComments()}else this.$message.info("请至少选择一项！")},async handlePublishAndReply(t){await this.handleChangeStatus(t.id,this.commentStatuses.PUBLISHED.value),this.handleOpenReplyModal(t)},handleOpenReplyModal(t){this.selectedComment=t,this.replyModalVisible=!0},handlePageChange(t=1){this.list.params.page=t-1,this.handleListComments()},handlePageSizeChange(t,e){this.$log.debug(`Current: ${t}, PageSize: ${e}`),this.list.params.page=0,this.list.params.size=e,this.handleListComments()},handleResetParam(){this.list.params.keyword=null,this.list.params.status=void 0,this.handleClearRowKeys(),this.handlePageChange(1)},handleClearRowKeys(){this.selectedRowKeys=[]},onReplyModalClose(){this.selectedComment={},this.replyModalVisible=!1,this.handleListComments()},onSelectionChange(t){this.selectedRowKeys=t,this.$log.debug(`SelectedRowKeys: ${t}`)},getCheckboxProps(t){return{props:{disabled:null==this.list.params.status||""===this.list.params.status,name:t.author}}}},filters:{statusText(t){return t?d.v$[t].text:""}}},f=y,g=a(1001),v=(0,g.Z)(f,o,l,!1,null,null,null),S=v.exports,C={components:{PageView:i.B4,CommentTab:S},data(){const t=[{title:"文章",key:"post"},{title:"页面",key:"sheet"},{title:"日志",key:"journal"}];return{panes:t,activeKey:t[0].key,defaultStatus:void 0}},beforeRouteEnter(t,e,a){const s=t.query.activeKey,n=t.query.defaultStatus;a((t=>{s&&(t.activeKey=s),n&&(t.defaultStatus=n)}))},watch:{activeKey(t){const e=this.$router.history.current.path;this.$router.push({path:e,query:{activeKey:t}}).catch((t=>t))}}},k=C,b=(0,g.Z)(k,s,n,!1,null,null,null),_=b.exports}}]);