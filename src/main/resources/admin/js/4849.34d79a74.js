(self["webpackChunkhalo_admin"]=self["webpackChunkhalo_admin"]||[]).push([[4849],{4849:function(t,e,o){"use strict";o.r(e),o.d(e,{default:function(){return S}});var n=function(){var t=this,e=t._self._c;return e("page-view",{attrs:{"sub-title":t.postToStage.inProgress?"当前内容已保存，但还未发布。":"",title:t.postToStage.title?t.postToStage.title:"新文章",affix:""}},[e("template",{slot:"extra"},[e("a-space",[e("a-button",{attrs:{loading:t.previewSaving},on:{click:t.handlePreviewClick}},[t._v("预览")]),e("a-button",{attrs:{type:"primary"},on:{click:function(e){t.postSettingVisible=!0}}},[t._v("发布")])],1)],1),e("a-row",{attrs:{gutter:12}},[e("a-col",{attrs:{span:24}},[e("div",{staticClass:"mb-4"},[e("a-input",{attrs:{placeholder:"请输入文章标题",size:"large"},model:{value:t.postToStage.title,callback:function(e){t.$set(t.postToStage,"title",e)},expression:"postToStage.title"}})],1),e("div",{style:{height:t.editorHeight},attrs:{id:"editor"}},[e("VditorEditor",{attrs:{originalContent:t.postToStage.originalContent},on:{"update:originalContent":function(e){return t.$set(t.postToStage,"originalContent",e)},"update:original-content":function(e){return t.$set(t.postToStage,"originalContent",e)},change:t.onContentChange,save:function(e){return t.handleSaveDraft()}}})],1)])],1),e("PostSettingModal",{attrs:{post:t.postToStage,savedCallback:t.onPostSavedCallback,visible:t.postSettingVisible},on:{"update:visible":function(e){t.postSettingVisible=e},onUpdate:t.onUpdateFromSetting}})],2)},i=[],a=(o(57658),o(93339)),s=o(77378),r=o(67731),u=o(3775),c=o(48478),l=o(73541),p=o(91296),d=o.n(p),g={mixins:[u.jB,u.KT,u.g3],components:{PostSettingModal:a.Z,VditorEditor:s.Z,PageView:r.B4},data(){return{postSettingVisible:!1,postToStage:{},contentChanges:0,previewSaving:!1}},beforeRouteEnter(t,e,o){const n=t.query.postId;o((async t=>{if(n){const{data:e}=await l.Z.post.get(Number(n));t.postToStage=e}}))},destroyed(){window.onbeforeunload&&(window.onbeforeunload=null)},beforeRouteLeave(t,e,o){const n=this.$createElement;this.contentChanges<=1?o():this.$confirm({title:"当前页面数据未保存，确定要离开吗？",content:()=>n("div",{style:"color:red;"},["如果离开当面页面，你的数据很可能会丢失！"]),onOk(){o()},onCancel(){o(!1)}})},mounted(){window.onbeforeunload=function(t){return t=t||window.event,t&&(t.returnValue="当前页面数据未保存，确定要离开吗？"),"当前页面数据未保存，确定要离开吗？"}},beforeMount(){document.addEventListener("keydown",this.onRegisterSaveShortcut)},beforeDestroy(){document.removeEventListener("keydown",this.onRegisterSaveShortcut)},methods:{onRegisterSaveShortcut(t){!t.ctrlKey&&!t.metaKey||t.altKey||t.shiftKey||83!==t.keyCode||(t.preventDefault(),t.stopPropagation(),this.handleSaveDraft())},handleSaveDraft:d()((async function(){if(this.postToStage.id)try{const{data:t}=await l.Z.post.updateDraftById(this.postToStage.id,this.postToStage.originalContent,this.postToStage.content,!0);this.postToStage.inProgress=t.inProgress,this.handleRestoreSavedStatus(),this.$message.success({content:"内容已保存",duration:.5})}catch(t){this.$log.error("Failed to update post content",t)}else await this.handleCreatePost()}),300),async handleCreatePost(){this.postToStage.title||(this.postToStage.title=(0,c._)(new Date,"YYYY-MM-DD-HH-mm-ss"));try{this.postToStage.keepRaw=!0;const{data:t}=await l.Z.post.create(this.postToStage);this.postToStage=t,this.handleRestoreSavedStatus();const e=this.$router.history.current.path;this.$router.push({path:e,query:{postId:this.postToStage.id}}).catch((t=>t)),this.$message.success({content:"文章已创建",duration:.5})}catch(t){this.$log.error("Failed to create post",t)}},async handlePreviewClick(){if(this.previewSaving=!0,this.postToStage.id){const{data:t}=await l.Z.post.updateDraftById(this.postToStage.id,this.postToStage.originalContent,this.postToStage.content,!0);this.postToStage.inProgress=t.inProgress}else await this.handleCreatePost();await this.handleOpenPreview()},async handleOpenPreview(){try{const t=await l.Z.post.getPreviewLinkById(this.postToStage.id);window.open(t,"_blank"),this.handleRestoreSavedStatus()}catch(t){this.$log.error("Failed to get preview link",t)}finally{setTimeout((()=>{this.previewSaving=!1}),400)}},handleRestoreSavedStatus(){this.contentChanges=0},onContentChange({originalContent:t,renderContent:e}){this.contentChanges++,this.postToStage.originalContent=t,this.postToStage.content=e},onPostSavedCallback(){this.contentChanges=0,this.$router.push({name:"PostList"})},onUpdateFromSetting(t){this.postToStage=t}}},h=g,f=o(1001),v=(0,f.Z)(h,n,i,!1,null,null,null),S=v.exports},91296:function(t,e,o){var n="Expected a function",i=NaN,a="[object Symbol]",s=/^\s+|\s+$/g,r=/^[-+]0x[0-9a-f]+$/i,u=/^0b[01]+$/i,c=/^0o[0-7]+$/i,l=parseInt,p="object"==typeof o.g&&o.g&&o.g.Object===Object&&o.g,d="object"==typeof self&&self&&self.Object===Object&&self,g=p||d||Function("return this")(),h=Object.prototype,f=h.toString,v=Math.max,S=Math.min,y=function(){return g.Date.now()};function w(t,e,o){var i,a,s,r,u,c,l=0,p=!1,d=!1,g=!0;if("function"!=typeof t)throw new TypeError(n);function h(e){var o=i,n=a;return i=a=void 0,l=e,r=t.apply(n,o),r}function f(t){return l=t,u=setTimeout(m,e),p?h(t):r}function w(t){var o=t-c,n=t-l,i=e-o;return d?S(i,s-n):i}function b(t){var o=t-c,n=t-l;return void 0===c||o>=e||o<0||d&&n>=s}function m(){var t=y();if(b(t))return k(t);u=setTimeout(m,w(t))}function k(t){return u=void 0,g&&i?h(t):(i=a=void 0,r)}function P(){void 0!==u&&clearTimeout(u),l=0,i=c=a=u=void 0}function $(){return void 0===u?r:k(y())}function j(){var t=y(),o=b(t);if(i=arguments,a=this,c=t,o){if(void 0===u)return f(c);if(d)return u=setTimeout(m,e),h(c)}return void 0===u&&(u=setTimeout(m,e)),r}return e=C(e)||0,T(o)&&(p=!!o.leading,d="maxWait"in o,s=d?v(C(o.maxWait)||0,e):s,g="trailing"in o?!!o.trailing:g),j.cancel=P,j.flush=$,j}function T(t){var e=typeof t;return!!t&&("object"==e||"function"==e)}function b(t){return!!t&&"object"==typeof t}function m(t){return"symbol"==typeof t||b(t)&&f.call(t)==a}function C(t){if("number"==typeof t)return t;if(m(t))return i;if(T(t)){var e="function"==typeof t.valueOf?t.valueOf():t;t=T(e)?e+"":e}if("string"!=typeof t)return 0===t?t:+t;t=t.replace(s,"");var o=u.test(t);return o||c.test(t)?l(t.slice(2),o?2:8):r.test(t)?i:+t}t.exports=w}}]);