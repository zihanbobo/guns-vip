layui.use(['jquery', 'table', 'layer', 'laydate'], function () {

    var $ = layui.$;
    var table = layui.table;
    var laydate = layui.laydate;
    var layer = layui.layer;

    var Hitask = {
        tableId: "hitaskTable"
    };

    // 基础数据
    Hitask.initColumn = function () {
        return [[
            {type: 'numbers'},
            {field: 'ID_', hide: true, title: ''},
            {field: 'DGRM_RESOURCE_NAME_', hide: true, title: ''},
            {field: 'PROC_INST_ID_', hide: true, title: ''},
            {field: 'PNAME_', sort: true, title: '流程名称'},
            {field: 'INITATOR', sort: true, title: '申请人'},
            {field: 'ASSIGNEE_', sort: true, title: '办理人or角色'},
            {field: 'NAME_', sort: true, title: '任务节点'},
            {field: 'START_TIME_', sort: true, title: '开始时间'},
            {field: 'END_TIME_', sort: true, title: '办完时间'},
            {field: 'ZTIME', sort: true, title: '用时'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    // 定义查询事件
    Hitask.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        queryData['lastStart'] = $("#lastStart").val();
        queryData['lastEnd'] = $("#lastEnd").val();
        table.reload(Hitask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    // 表格初始化
    table.render({
        elem: '#' + Hitask.tableId,
        url: Feng.ctxPath + '/taskHistory/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Hitask.initColumn()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#lastStart',
        range: false,
        max: Feng.currentDate()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#lastEnd',
        range: false,
        max: Feng.currentDate()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Hitask.search();
    });

    // 流程信息
    Hitask.openProcessInfoDlg = function (data) {
        layer.open({
            type: 2,
            title: '流程信息',
            area: ['900px', '500px'],
            content: Feng.ctxPath + '/taskHistory/processInfo?ID_=' + data.ID_ + "&DGRM_RESOURCE_NAME_=" + data.DGRM_RESOURCE_NAME_ + "&PROC_INST_ID_=" + data.PROC_INST_ID_,
            end: function () {
                Hitask.search();
            }
        });
    };

    // 工具条点击事件
    table.on('tool(' + Hitask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'processInfo') {
            Hitask.openProcessInfoDlg(data);
        }
    });
});