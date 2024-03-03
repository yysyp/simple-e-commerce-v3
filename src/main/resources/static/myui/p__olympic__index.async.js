"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[48],{93417:function(L,D,r){r.r(D),r.d(D,{default:function(){return O}});var y=r(15009),h=r.n(y),T=r(97857),i=r.n(T),U=r(99289),d=r.n(U),C=r(26514),c=r(53914),f=r(1524),P=r(88245),v=r(39285),I=r(62435),E=r(86250),M=r(15867),p=r(86074),b=C.Z.OlympicController.queryOlympicWinnerList,g=[{title:"Athlete",dataIndex:"athlete",sorter:function(a,n){var e;return(e=a.athlete)===null||e===void 0?void 0:e.localeCompare(n.athlete)},filters:!0},{title:"age",dataIndex:"age",sorter:function(a,n){return a.age-n.age},filters:!0,valueType:"number"},{title:"country",dataIndex:"country",sorter:function(a,n){var e;return(e=a.country)===null||e===void 0?void 0:e.localeCompare(n.country)},filters:!0,onFilter:!0,valueType:"text",valueEnum:{"United States":{text:"United States"},Russia:{text:"Russia"},Australia:{text:"Australia"},Canada:{text:"Canada"},Norway:{text:"Norway"},China:{text:"China"}}},{title:"year",dataIndex:"year",sorter:function(a,n){return a.year-n.year},filters:!0,onFilter:!0,hideInSearch:!0,hideInTable:!0,valueEnum:{}},{title:"date",dataIndex:"date",sorter:function(a,n){var e=new Date(a.date).getTime(),t=new Date(n.date).getTime();return e-t},filters:!0,hideInSearch:!0,valueType:"date"},{title:"sport",dataIndex:"sport",sorter:function(a,n){var e;return(e=a.sport)===null||e===void 0?void 0:e.localeCompare(n.sport)},filters:!0,hideInSearch:!0},{title:"total",dataIndex:"total",sorter:function(a,n){return a.total-n.total},filters:!0,valueType:"number",hideInSearch:!0}],x=function(){var o=d()(h()().mark(function a(n,e,t){var s,l,u;return h()().wrap(function(m){for(;;)switch(m.prev=m.next){case 0:return console.log("fetching backend data ||params:"+(0,c.ZP)(n)+"	||sorter:"+(0,c.ZP)(e)+"	||filter:"+(0,c.ZP)(t)),m.next=3,b(i()(i()({},n),{},{sorter:e,filter:t}));case 3:return s=m.sent,l=s.data,u=s.success,m.abrupt("return",{data:(l==null?void 0:l.list)||[],success:u});case 7:case"end":return m.stop()}},a)}));return function(n,e,t){return o.apply(this,arguments)}}();function O(){var o=(0,I.useRef)();return(0,p.jsxs)(f._z,{header:{title:"Olympic Info"},children:[(0,p.jsx)(E.Z,{gap:"small",wrap:"wrap",children:(0,p.jsx)(M.ZP,{type:"primary",onClick:function(){var n;console.log("===>ref="+(0,c.ZP)(o.current)+"	||params="+(0,c.ZP)(params)),(n=o.current)===null||n===void 0||n.reload()},children:"Primary Button"})}),(0,p.jsx)(P.Z,{tabs:{type:"card"},children:(0,p.jsx)(P.Z.TabPane,{tab:"tab1",children:(0,p.jsx)(v.Z,{columns:g,request:x,search:{collapsed:!1,collapseRender:function(){return null}},pagination:{defaultPageSize:10},rowKey:"id",actionRef:o,sticky:{offsetHeader:48},columnsState:{defaultValue:{country:{show:!1},age:{show:!1}}}})},"tab1")})]})}},26514:function(L,D,r){r.d(D,{Z:function(){return n}});var y={};r.r(y),r.d(y,{queryOlympicWinnerList:function(){return P}});var h={};r.r(h),r.d(h,{addUser:function(){return M},deleteUser:function(){return o},getUserDetail:function(){return b},modifyUser:function(){return x},queryUserList:function(){return I}});var T=r(15009),i=r.n(T),U=r(97857),d=r.n(U),C=r(99289),c=r.n(C),f=r(3835);function P(e,t){return v.apply(this,arguments)}function v(){return v=c()(i()().mark(function e(t,s){return i()().wrap(function(u){for(;;)switch(u.prev=u.next){case 0:return u.abrupt("return",(0,f.request)("/api/v1/queryOlympicWinnerList",d()({method:"GET",params:d()({},t)},s||{})));case 1:case"end":return u.stop()}},e)})),v.apply(this,arguments)}function I(e,t){return E.apply(this,arguments)}function E(){return E=c()(i()().mark(function e(t,s){return i()().wrap(function(u){for(;;)switch(u.prev=u.next){case 0:return u.abrupt("return",(0,f.request)("/api/v1/queryUserList",d()({method:"GET",params:d()({},t)},s||{})));case 1:case"end":return u.stop()}},e)})),E.apply(this,arguments)}function M(e,t){return p.apply(this,arguments)}function p(){return p=c()(i()().mark(function e(t,s){return i()().wrap(function(u){for(;;)switch(u.prev=u.next){case 0:return u.abrupt("return",(0,f.request)("/api/v1/user",d()({method:"POST",headers:{"Content-Type":"application/json"},data:t},s||{})));case 1:case"end":return u.stop()}},e)})),p.apply(this,arguments)}function b(e,t){return g.apply(this,arguments)}function g(){return g=c()(i()().mark(function e(t,s){var l;return i()().wrap(function(_){for(;;)switch(_.prev=_.next){case 0:return l=t.userId,_.abrupt("return",(0,f.request)("/api/v1/user/".concat(l),d()({method:"GET",params:d()({},t)},s||{})));case 2:case"end":return _.stop()}},e)})),g.apply(this,arguments)}function x(e,t,s){return O.apply(this,arguments)}function O(){return O=c()(i()().mark(function e(t,s,l){var u;return i()().wrap(function(m){for(;;)switch(m.prev=m.next){case 0:return u=t.userId,m.abrupt("return",(0,f.request)("/api/v1/user/".concat(u),d()({method:"PUT",headers:{"Content-Type":"application/json"},params:d()({},t),data:s},l||{})));case 2:case"end":return m.stop()}},e)})),O.apply(this,arguments)}function o(e,t){return a.apply(this,arguments)}function a(){return a=c()(i()().mark(function e(t,s){var l;return i()().wrap(function(_){for(;;)switch(_.prev=_.next){case 0:return l=t.userId,_.abrupt("return",(0,f.request)("/api/v1/user/".concat(l),d()({method:"DELETE",params:d()({},t)},s||{})));case 2:case"end":return _.stop()}},e)})),a.apply(this,arguments)}var n={UserController:h,OlympicController:y}}}]);