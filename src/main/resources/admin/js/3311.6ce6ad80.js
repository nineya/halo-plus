"use strict";(self["webpackChunkhalo_admin"]=self["webpackChunkhalo_admin"]||[]).push([[3311],{83311:function(t,a,e){e.r(a),e.d(a,{default:function(){return h}});var s=function(){var t=this,a=t._self._c;return a("page-view",[a("a-card",{attrs:{bodyStyle:{padding:"16px"},bordered:!1}},[a("div",{staticClass:"table-operator"},[a("a-button",{attrs:{type:"danger"},on:{click:t.handleClearActionLogs}},[t._v("清空操作日志")])],1),a("div",{staticClass:"mt-4"},[a("a-table",{attrs:{columns:t.list.columns,dataSource:t.list.data,loading:t.list.loading,pagination:!1,rowKey:t=>t.id,scrollToFirstRowOnChange:!0},scopedSlots:t._u([{key:"type",fn:function(a){return[t._v(" "+t._s(t._f("typeConvert")(a))+" ")]}},{key:"ipAddress",fn:function(e){return[a("div",{staticClass:"blur hover:blur-none transition-all"},[t._v(t._s(e))])]}},{key:"createTime",fn:function(e){return[a("a-tooltip",{attrs:{placement:"top"}},[a("template",{slot:"title"},[t._v(" "+t._s(t._f("moment")(e))+" ")]),t._v(" "+t._s(t._f("timeAgo")(e))+" ")],2)]}}])}),a("div",{staticClass:"page-wrapper"},[a("a-pagination",{staticClass:"pagination",attrs:{current:t.pagination.page,defaultPageSize:t.pagination.size,pageSizeOptions:["10","20","50","100"],total:t.pagination.total,showLessItems:"",showSizeChanger:""},on:{change:t.handlePageChange,showSizeChange:t.handlePageSizeChange}})],1)],1)])],1)},i=[],n=e(67731),o=e(73541),l=e(10438);const r=[{title:"ID",dataIndex:"id"},{title:"类型",dataIndex:"type",scopedSlots:{customRender:"type"}},{title:"关键值",dataIndex:"logKey"},{title:"内容",dataIndex:"content"},{title:"IP",dataIndex:"ipAddress",scopedSlots:{customRender:"ipAddress"}},{title:"操作时间",dataIndex:"createTime",scopedSlots:{customRender:"createTime"}}];var d={name:"ActionLog",components:{PageView:n.B4},data(){return{list:{columns:r,data:[],total:0,loading:!1,params:{page:0,size:50}}}},computed:{pagination(){return{page:this.list.params.page+1,size:this.list.params.size,total:this.list.total}}},created(){this.handleListActionLogs()},methods:{async handleListActionLogs(){try{this.list.loading=!0;const t=await o.Z.log.list(this.list.params);this.list.data=t.data.content,this.list.total=t.data.total}catch(t){this.$log.error(t)}finally{this.list.loading=!1}},handlePageChange(t=1){this.list.params.page=t-1,this.handleListActionLogs()},handlePageSizeChange(t,a){this.$log.debug(`Current: ${t}, PageSize: ${a}`),this.list.params.page=0,this.list.params.size=a,this.handleListActionLogs()},handleClearActionLogs(){const t=this;t.$confirm({title:"提示",maskClosable:!0,content:"是否确定要清空所有操作日志？",async onOk(){try{await o.Z.log.clear()}catch(a){t.$log.error("Failed to clear action logs.",a)}finally{await t.handleListActionLogs()}}})}},filters:{typeConvert(t){const a=l.Js[t];return a?a.text:t}}},c=d,g=e(1001),p=(0,g.Z)(c,s,i,!1,null,null,null),h=p.exports}}]);