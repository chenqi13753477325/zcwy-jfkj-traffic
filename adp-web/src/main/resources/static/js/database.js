/**
 * Created by Administrator on 2018/1/10 0010.
 */

Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
};
//提示信息
var lang={};

var basic_param={
    language: {
        "sProcessing":   "处理中...",
        "sLengthMenu":   "每页 _MENU_ 项",
        "sZeroRecords":  "没有匹配结果",
        "sInfo":         "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
        "sInfoEmpty":    "当前显示第 0 至 0 项，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix":  "",
        "sSearch":       "搜索:",
        "sUrl":          "",
        "sEmptyTable":     "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands":  ",",
        "oPaginate": {
            "sFirst":    "首页",
            "sPrevious": "上页",
            "sNext":     "下页",
            "sLast":     "末页",
            "sJump":     "跳转"
        },
        "oAria": {
            "sSortAscending":  ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    },  //提示信息
    autoWidth: false,  //禁用自动调整列宽
    stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
    processing: true,  //隐藏加载提示,自行处理
    serverSide: true,  //启用服务器端分页
    searching: false,  //禁用原生搜索
    //orderMulti: false,  //启用多列排序
    order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
    renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
    pagingType: "full_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
    bSort: true,
    columnDefs: [{
        "targets": 'nosort',  //列的样式名
        "orderable": false    //包含上样式名‘nosort’的禁止排序
    }]
};

//列表头按钮
var toolAddBtn='<div style="text-align: right;width: 100%;"><button type="button" class="layui-btn layui-btn-small layui-btn-normal" id="toolAddBtn"><i class="layui-icon">&#xe654;</i> 添加</button>';
var toolDelBtn='<button type="button" class="layui-btn layui-btn-small layui-btn-warm" id="toolDelBtn"><i class="layui-icon">&#x1006;</i> 批量删除</button>';
var reloadBtn='<button type="button" class="layui-btn layui-btn-small" id="reloadBtn" onclick="location.reload();"><i class="layui-icon">&#x1002;</i> 刷新</button></div>';

//layer 提示框
layui.use('layer', function() {
    var layer = layui.layer;
})
