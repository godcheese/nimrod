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
var _4=$("<span class=\"checkbox inputbox\">"+"<span class=\"checkbox-inner\">"+"<svg xml:space=\"preserve\" focusable=\"false\" version=\"1.1\" viewBox=\"0 0 24 24\"><path d=\"M4.1,12.7 9,17.6 20.3,6.3\" fill=\"none\" stroke=\"white\"></path></svg>"+"</span>"+"<input type=\"checkbox\" class=\"checkbox-value\">"+"</span>").insertAfter(_3);
var t=$(_3);
t.addClass("checkbox-f").hide();
var _5=t.attr("name");
if(_5){
t.removeAttr("name").attr("checkboxName",_5);
_4.find(".checkbox-value").attr("name",_5);
}
return _4;
};
function _6(_7){
var _8=$.data(_7,"checkbox");
var _9=_8.options;
var _a=_8.checkbox;
var _b="_easyui_checkbox_"+(++_1);
var _c=_a.find(".checkbox-value").attr("id",_b);
_c.unbind(".checkbox").bind("change.checkbox",function(e){
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
$(_7).checkbox("setValue",_9.value);
_d(_7,_9.checked);
_e(_7,_9.disabled);
};
function _f(_10){
var _11=$.data(_10,"checkbox");
var _12=_11.options;
var _13=_11.checkbox;
_13.unbind(".checkbox").bind("click.checkbox",function(){
if(!_12.disabled){
_d(_10,!_12.checked);
}
});
};
function _14(_15){
var _16=$.data(_15,"checkbox");
var _17=_16.options;
var _18=_16.checkbox;
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
var _1b=$.data(_19,"checkbox");
var _1c=_1b.options;
var _1d=_1b.checkbox;
_1d.find(".checkbox-value")._propAttr("checked",_1a);
var _1e=_1d.find(".checkbox-inner").css("display",_1a?"":"none");
if(_1a){
_1e.addClass("checkbox-checked");
}else{
_1e.removeClass("checkbox-checked");
}
if(_1c.checked!=_1a){
_1c.checked=_1a;
_1c.onChange.call(_19,_1a);
$(_19).closest("form").trigger("_change",[_19]);
}
};
function _e(_1f,_20){
var _21=$.data(_1f,"checkbox");
var _22=_21.options;
var _23=_21.checkbox;
var rv=_23.find(".checkbox-value");
_22.disabled=_20;
if(_20){
$(_1f).add(rv)._propAttr("disabled",true);
_23.addClass("checkbox-disabled");
$(_21.label).addClass("textbox-label-disabled");
}else{
$(_1f).add(rv)._propAttr("disabled",false);
_23.removeClass("checkbox-disabled");
$(_21.label).removeClass("textbox-label-disabled");
}
};
$.fn.checkbox=function(_24,_25){
if(typeof _24=="string"){
return $.fn.checkbox.methods[_24](this,_25);
}
_24=_24||{};
return this.each(function(){
var _26=$.data(this,"checkbox");
if(_26){
$.extend(_26.options,_24);
}else{
_26=$.data(this,"checkbox",{options:$.extend({},$.fn.checkbox.defaults,$.fn.checkbox.parseOptions(this),_24),checkbox:_2(this)});
}
_26.options.originalChecked=_26.options.checked;
_6(this);
_f(this);
_14(this);
});
};
$.fn.checkbox.methods={options:function(jq){
var _27=jq.data("checkbox");
return $.extend(_27.options,{value:_27.checkbox.find(".checkbox-value").val()});
},setValue:function(jq,_28){
return jq.each(function(){
$(this).val(_28);
$.data(this,"checkbox").checkbox.find(".checkbox-value").val(_28);
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
var _29=$(this).checkbox("options");
_d(this,_29.originalChecked);
});
}};
$.fn.checkbox.parseOptions=function(_2a){
var t=$(_2a);
return $.extend({},$.parser.parseOptions(_2a,["label","labelPosition","labelAlign",{labelWidth:"number"}]),{value:(t.val()||undefined),checked:(t.attr("checked")?true:undefined),disabled:(t.attr("disabled")?true:undefined)});
};
$.fn.checkbox.defaults={width:20,height:20,value:null,disabled:false,checked:false,label:null,labelWidth:"auto",labelPosition:"before",labelAlign:"left",onChange:function(_2b){
}};
})(jQuery);

