/**
 * EasyUI for jQuery 1.7.1
 * 
 * Copyright (c) 2009-2019 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the freeware license: http://www.jeasyui.com/license_freeware.php
 * To use it on other terms please contact us: info@jeasyui.com
 *
 */
(function($){
var _1=1;
function _2(_3){
var _4=$("<span class=\"switchbutton\">"+"<span class=\"switchbutton-inner\">"+"<span class=\"switchbutton-on\"></span>"+"<span class=\"switchbutton-handle\"></span>"+"<span class=\"switchbutton-off\"></span>"+"<input class=\"switchbutton-value\" type=\"checkbox\">"+"</span>"+"</span>").insertAfter(_3);
var t=$(_3);
t.addClass("switchbutton-f").hide();
var _5=t.attr("name");
if(_5){
t.removeAttr("name").attr("switchbuttonName",_5);
_4.find(".switchbutton-value").attr("name",_5);
}
_4.bind("_resize",function(e,_6){
if($(this).hasClass("easyui-fluid")||_6){
_7(_3);
}
return false;
});
return _4;
};
function _7(_8,_9){
var _a=$.data(_8,"switchbutton");
var _b=_a.options;
var _c=_a.switchbutton;
if(_9){
$.extend(_b,_9);
}
var _d=_c.is(":visible");
if(!_d){
_c.appendTo("body");
}
_c._size(_b);
if(_b.label&&_b.labelPosition){
if(_b.labelPosition=="top"){
_a.label._size({width:_b.labelWidth},_c);
}else{
_a.label._size({width:_b.labelWidth,height:_c.outerHeight()},_c);
_a.label.css("lineHeight",_c.outerHeight()+"px");
}
}
var w=_c.width();
var h=_c.height();
var w=_c.outerWidth();
var h=_c.outerHeight();
var _e=parseInt(_b.handleWidth)||_c.height();
var _f=w*2-_e;
_c.find(".switchbutton-inner").css({width:_f+"px",height:h+"px",lineHeight:h+"px"});
_c.find(".switchbutton-handle")._outerWidth(_e)._outerHeight(h).css({marginLeft:-_e/2+"px"});
_c.find(".switchbutton-on").css({width:(w-_e/2)+"px",textIndent:(_b.reversed?"":"-")+_e/2+"px"});
_c.find(".switchbutton-off").css({width:(w-_e/2)+"px",textIndent:(_b.reversed?"-":"")+_e/2+"px"});
_b.marginWidth=w-_e;
_10(_8,_b.checked,false);
if(!_d){
_c.insertAfter(_8);
}
};
function _11(_12){
var _13=$.data(_12,"switchbutton");
var _14=_13.options;
var _15=_13.switchbutton;
var _16=_15.find(".switchbutton-inner");
var on=_16.find(".switchbutton-on").html(_14.onText);
var off=_16.find(".switchbutton-off").html(_14.offText);
var _17=_16.find(".switchbutton-handle").html(_14.handleText);
if(_14.reversed){
off.prependTo(_16);
on.insertAfter(_17);
}else{
on.prependTo(_16);
off.insertAfter(_17);
}
var _18="_easyui_switchbutton_"+(++_1);
_15.find(".switchbutton-value")._propAttr("checked",_14.checked).attr("id",_18);
_15.removeClass("switchbutton-disabled").addClass(_14.disabled?"switchbutton-disabled":"");
_15.removeClass("switchbutton-reversed").addClass(_14.reversed?"switchbutton-reversed":"");
if(_14.label){
if(typeof _14.label=="object"){
_13.label=$(_14.label);
_13.label.attr("for",_18);
}else{
$(_13.label).remove();
_13.label=$("<label class=\"textbox-label\"></label>").html(_14.label);
_13.label.css("textAlign",_14.labelAlign).attr("for",_18);
if(_14.labelPosition=="after"){
_13.label.insertAfter(_15);
}else{
_13.label.insertBefore(_12);
}
_13.label.removeClass("textbox-label-left textbox-label-right textbox-label-top");
_13.label.addClass("textbox-label-"+_14.labelPosition);
}
}else{
$(_13.label).remove();
}
_10(_12,_14.checked);
_19(_12,_14.readonly);
$(_12).switchbutton("setValue",_14.value);
};
function _10(_1a,_1b,_1c){
var _1d=$.data(_1a,"switchbutton");
var _1e=_1d.options;
_1e.checked=_1b;
var _1f=_1d.switchbutton.find(".switchbutton-inner");
var _20=_1f.find(".switchbutton-on");
var _21=_1e.reversed?(_1e.checked?_1e.marginWidth:0):(_1e.checked?0:_1e.marginWidth);
var dir=_20.css("float").toLowerCase();
var css={};
css["margin-"+dir]=-_21+"px";
_1c?_1f.animate(css,200):_1f.css(css);
var _22=_1f.find(".switchbutton-value");
var ck=_22.is(":checked");
$(_1a).add(_22)._propAttr("checked",_1e.checked);
if(ck!=_1e.checked){
_1e.onChange.call(_1a,_1e.checked);
}
};
function _23(_24,_25){
var _26=$.data(_24,"switchbutton");
var _27=_26.options;
var _28=_26.switchbutton;
var _29=_28.find(".switchbutton-value");
if(_25){
_27.disabled=true;
$(_24).add(_29)._propAttr("disabled",true);
_28.addClass("switchbutton-disabled");
}else{
_27.disabled=false;
$(_24).add(_29)._propAttr("disabled",false);
_28.removeClass("switchbutton-disabled");
}
};
function _19(_2a,_2b){
var _2c=$.data(_2a,"switchbutton");
var _2d=_2c.options;
_2d.readonly=_2b==undefined?true:_2b;
_2c.switchbutton.removeClass("switchbutton-readonly").addClass(_2d.readonly?"switchbutton-readonly":"");
};
function _2e(_2f){
var _30=$.data(_2f,"switchbutton");
var _31=_30.options;
_30.switchbutton.unbind(".switchbutton").bind("click.switchbutton",function(){
if(!_31.disabled&&!_31.readonly){
_10(_2f,_31.checked?false:true,true);
}
});
};
$.fn.switchbutton=function(_32,_33){
if(typeof _32=="string"){
return $.fn.switchbutton.methods[_32](this,_33);
}
_32=_32||{};
return this.each(function(){
var _34=$.data(this,"switchbutton");
if(_34){
$.extend(_34.options,_32);
}else{
_34=$.data(this,"switchbutton",{options:$.extend({},$.fn.switchbutton.defaults,$.fn.switchbutton.parseOptions(this),_32),switchbutton:_2(this)});
}
_34.options.originalChecked=_34.options.checked;
_11(this);
_7(this);
_2e(this);
});
};
$.fn.switchbutton.methods={options:function(jq){
var _35=jq.data("switchbutton");
return $.extend(_35.options,{value:_35.switchbutton.find(".switchbutton-value").val()});
},resize:function(jq,_36){
return jq.each(function(){
_7(this,_36);
});
},enable:function(jq){
return jq.each(function(){
_23(this,false);
});
},disable:function(jq){
return jq.each(function(){
_23(this,true);
});
},readonly:function(jq,_37){
return jq.each(function(){
_19(this,_37);
});
},check:function(jq){
return jq.each(function(){
_10(this,true);
});
},uncheck:function(jq){
return jq.each(function(){
_10(this,false);
});
},clear:function(jq){
return jq.each(function(){
_10(this,false);
});
},reset:function(jq){
return jq.each(function(){
var _38=$(this).switchbutton("options");
_10(this,_38.originalChecked);
});
},setValue:function(jq,_39){
return jq.each(function(){
$(this).val(_39);
$.data(this,"switchbutton").switchbutton.find(".switchbutton-value").val(_39);
});
}};
$.fn.switchbutton.parseOptions=function(_3a){
var t=$(_3a);
return $.extend({},$.parser.parseOptions(_3a,["onText","offText","handleText",{handleWidth:"number",reversed:"boolean"},"label","labelPosition","labelAlign",{labelWidth:"number"}]),{value:(t.val()||undefined),checked:(t.attr("checked")?true:undefined),disabled:(t.attr("disabled")?true:undefined),readonly:(t.attr("readonly")?true:undefined)});
};
$.fn.switchbutton.defaults={handleWidth:"auto",width:60,height:30,checked:false,disabled:false,readonly:false,reversed:false,onText:"ON",offText:"OFF",handleText:"",value:"on",label:null,labelWidth:"auto",labelPosition:"before",labelAlign:"left",onChange:function(_3b){
}};
})(jQuery);

