layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 约单报警记录管理
     */
    var QxAlert = {
        tableId: "qxAlertTable"
    };

    /**
     * 初始化表格的列
     */
    QxAlert.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'userId', sort: true, title: '报警人ID'},
            {field: 'inviteId', sort: true, title: '约单ID'},
            {field: 'status', sort: true, title: '状态', templet: function(d) {
            	//  0-未处理；1-已处理
            	return d.status==0 ? '未处理' : '已处理';
            }},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxAlert.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxAlert.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxAlert.openAddDlg = function () {
        func.open({
            title: '添加约单报警记录',
            content: Feng.ctxPath + '/qxAlert/add',
            tableId: QxAlert.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxAlert.openEditDlg = function (data) {
        func.open({
            title: '修改约单报警记录',
            content: Feng.ctxPath + '/qxAlert/edit?id=' + data.id,
            tableId: QxAlert.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxAlert.exportExcel = function () {
        var checkRows = table.checkStatus(QxAlert.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    QxAlert.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxAlert/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxAlert.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + QxAlert.tableId,
        url: Feng.ctxPath + '/qxAlert/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxAlert.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxAlert.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxAlert.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxAlert.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxAlert.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxAlert.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxAlert.onDeleteItem(data);
        }
    });
});
