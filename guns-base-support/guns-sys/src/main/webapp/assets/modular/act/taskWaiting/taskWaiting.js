layui.use(['table', 'admin', 'ax', 'func', 'layer', 'laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var laydate = layui.laydate;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var layer = layui.layer;

    /**
     * 管理
     */
    var Task = {
        tableId: "taskTable"
    };

    /**
     * 初始化表格的列
     */
    Task.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'ID_', hide: true, title: ''},
            {field: 'DGRM_RESOURCE_NAME_', hide: true, title: ''},
            {field: 'TASK_DEF_KEY_', hide: true, title: ''},
            {field: 'TASK_DEF_KEY_', hide: true, title: ''},
            {field: 'PRIORITY_', hide: true, title: ''},
            {field: 'TENANT_ID_', hide: true, title: ''},
            {field: 'SUSPENSION_STATE_', hide: true, title: ''},
            {field: 'REV_', hide: true, title: ''},
            {field: 'EXECUTION_ID_', hide: true, title: ''},
            {field: 'PROC_INST_ID_', hide: true, title: ''},

            {field: 'PNAME_', sort: true, title: '流程名称'},
            {field: 'INITATOR', sort: true, title: '申请人'},
            {field: 'ASSIGNEE_', sort: true, title: '当前节点(待办人)'},
            {field: 'NAME_', sort: true, title: '当前任务'},
            {field: 'CREATE_TIME_', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}

        ]];
    };

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

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Task.tableId,
        url: Feng.ctxPath + '/taskWaiting/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Task.initColumn()
    });

    /**
     * 点击查询按钮
     */
    Task.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        queryData['lastStart'] = $("#lastStart").val();
        queryData['lastEnd'] = $("#lastEnd").val();
        table.reload(Task.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    /**
     * 点击委派
     *
     * @param data 点击按钮时候的行数据
     */
    Task.openDelegateDlg = function (data) {
        layer.open({
            type: 2,
            title: '指定委派对象',
            area: ['800px', '400px'],
            content: Feng.ctxPath + '/taskWaiting/delegatePage?ID_=' + data.ID_,
            end: function () {
                Task.search();
            }
        });
    };

    /**
     * 点击办理
     *
     * @param data 点击按钮时候的行数据
     */
    Task.onHandleItem = function (data) {
        func.open({
            title: '指定委派对象',
            content: Feng.ctxPath + '/taskWaiting/handlePage?ID_=' + data.ID_ + "&DGRM_RESOURCE_NAME_=" + data.DGRM_RESOURCE_NAME_ + "&PROC_INST_ID_=" + data.PROC_INST_ID_,
            tableId: Task.tableId
        });
    };

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Task.search();
    });

    // 工具条点击事件
    table.on('tool(' + Task.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delegate') {
            Task.openDelegateDlg(data);
        } else if (layEvent === 'handle') {
            Task.onHandleItem(data);
        }
    });
});
