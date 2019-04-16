/**
 * EasyUI for jQuery 1.7.6
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
var _4=$("<span class=\"radiobutton inputbox\">"+"<span class=\"radiobutton-inner\" style=\"display:none\"></span>"+"<input type=\"radio\" class=\"radiobutton-value\">"+"</span>").insertAfter(_3);
var t=$(_3);
t.addClass("radiobutton-f").hide();
var _5=t.attr("name");
if(_5){
t.removeAttr("name").attr("radiobuttonName",_5);
_4.find(".radiobutton-value").attr("name",_5);
}
return _4;
};
function _6(_7){
var _8=$.data(_7,"radiobutton");
var _9=_8.options;
var _a=_8.radiobutton;
var _b="_easyui_radiobutton_"+(++_1);
var _c=_a.find(".radiobutton-value").attr("id",_b);
_c.unbind(".radiobutton").bind("change.radiobutton",function(e){
return false;
});
if(_9.label){
if(typeof _9.label=="object"){
_8.label=$(_9.label);
_8.label.attr("for",_b);
}else{
$(_8.label).remove();
_8.label=$("<label class=\"textbox-label\"></label>").html(_9.label);
_8.label.css("textAlign",_9.labelAlign).attr("for",_b);
if(_9.labelPosition=="after"){
_8.label.insertAfter(_a);
}else{
_8.label.insertBefore(_7);
}
_8.label.removeClass("textbox-label-left textbox-label-right textbox-label-top");
_8.label.addClass("textbox-label-"+_9.labelPosition);
}
}else{
$(_8.label).remove();
}
$(_7).radiobutton("setValue",_9.value);
_d(_7,_9.checked);
_e(_7,_9.disabled);
};
function _f(_10){
var _11=$.data(_10,"radiobutton");
var _12=_11.options;
var _13=_11.radiobutton;
_13.unbind(".radiobutton").bind("click.radiobutton",function(){
if(!_12.disabled){
_d(_10,true);
}
});
};
function _14(_15){
var _16=$.data(_15,"radiobutton");
var _17=_16.options;
var _18=_16.radiobutton;
_18._size(_17,_18.parent());
if(_17.label&&_17.labelPosition){
if(_17.labelPosition=="top"){
_16.label._size({width:_17.labelWidth},_18);
}else{
_16.label._size({width:_17.labelWidth,height:_18.outerHeight()},_18);
_16.label.css("lineHeight",_18.outerHeight()+"px");
}
}
};
function _d(_19,_1a){
if(_1a){
var f=$(_19).closest("form");
var _1b=$(_19).attr("radiobuttonName");
f.find(".radiobutton-f[radiobuttonName=\""+_1b+"\"]").each(function(){
if(this!=_19){
_1c(this,false);
}
});
_1c(_19,true);
}else{
_1c(_19,false);
}
function _1c(b,c){
var _1d=$(b).radiobutton("options");
var _1e=$(b).data("radiobutton").radiobutton;
_1e.find(".radiobutton-inner").css("display",c?"":"none");
_1e.find(".radiobutton-value")._propAttr("checked",c);
if(_1d.checked!=c){
_1d.checked=c;
_1d.onChange.call($(b)[0],c);
$(b).closest("form").trigger("_change",[$(b)[0]]);
}
};
};
function _e(_1f,_20){
var _21=$.data(_1f,"radiobutton");
var _22=_21.options;
var _23=_21.radiobutton;
var rv=_23.find(".radiobutton-value");
_22.disabled=_20;
if(_20){
$(_1f).add(rv)._propAttr("disabled",true);
_23.addClass("radiobutton-disabled");
$(_21.label).addClass("textbox-label-disabled");
}else{
$(_1f).add(rv)._propAttr("disabled",false);
_23.removeClass("radiobutton-disabled");
$(_21.label).removeClass("textbox-label-disabled");
}
};
$.fn.radiobutton=function(_24,_25){
if(typeof _24=="string"){
return $.fn.radiobutton.methods[_24](this,_25);
}
_24=_24||{};
return this.each(function(){
var _26=$.data(this,"radiobutton");
if(_26){
$.extend(_26.options,_24);
}else{
_26=$.data(this,"radiobutton",{options:$.extend({},$.fn.radiobutton.defaults,$.fn.radiobutton.parseOptions(this),_24),radiobutton:_2(this)});
}
_26.options.originalChecked=_26.options.checked;
_6(this);
_f(this);
_14(this);
});
};
$.fn.radiobutton.methods={options:function(jq){
var _27=jq.data("radiobutton");
return $.extend(_27.options,{value:_27.radiobutton.find(".radiobutton-value").val()});
},setValue:function(jq,_28){
return jq.each(function(){
$(this).val(_28);
$.data(this,"radiobutton").radiobutton.find(".radiobutton-value").val(_28);
});
},enable:function(jq){
return jq.each(function(){
_e(this,false);
});
},disable:function(jq){
return jq.each(function(){
_e(this,true);
});
},check:function(jq){
return jq.each(function(){
_d(this,true);
});
},uncheck:function(jq){
return jq.each(function(){
_d(this,false);
});
},clear:function(jq){
return jq.each(function(){
_d(this,false);
});
},reset:function(jq){
return jq.each(function(){
var _29=$(this).radiobutton("options");
_d(this,_29.originalChecked);
});
}};
$.fn.radiobutton.parseOptions=function(_2a){
var t=$(_2a);
return $.extend({},$.parser.parseOptions(_2a,["label","labelPosition","labelAlign",{labelWidth:"number"}]),{value:(t.val()||undefined),checked:(t.attr("checked")?true:undefined),disabled:(t.attr("disabled")?true:undefined)});
};
$.fn.radiobutton.defaults={width:20,height:20,value:null,disabled:false,checked:false,label:null,labelWidth:"auto",labelPosition:"before",labelAlign:"left",onChange:function(_2b){
}};
})(jQuery);

