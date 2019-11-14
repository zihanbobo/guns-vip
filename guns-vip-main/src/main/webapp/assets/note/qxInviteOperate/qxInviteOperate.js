layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 约单操作表管理
     */
    var QxInviteOperate = {
        tableId: "qxInviteOperateTable"
    };

    /**
     * 初始化表格的列
     */
    QxInviteOperate.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'userId', sort: true, title: '用户ID'},
            {field: 'inviteId', sort: true, title: '约单ID'},
            {field: 'type', sort: true, title: '操作类型 操作类型：0-确认开始；1-确认结束；2-确认取消；'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxInviteOperate.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxInviteOperate.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxInviteOperate.openAddDlg = function () {
        func.open({
            title: '添加约单操作表',
            content: Feng.ctxPath + '/qxInviteOperate/add',
            tableId: QxInviteOperate.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxInviteOperate.openEditDlg = function (data) {
        func.open({
            title: '修改约单操作表',
            content: Feng.ctxPath + '/qxInviteOperate/edit?id=' + data.id,
            tableId: QxInviteOperate.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxInviteOperate.exportExcel = function () {
        var checkRows = table.checkStatus(QxInviteOperate.tableId);
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
    QxInviteOperate.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxInviteOperate/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxInviteOperate.tableId);
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
        elem: '#' + QxInviteOperate.tableId,
        url: Feng.ctxPath + '/qxInviteOperate/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxInviteOperate.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxInviteOperate.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxInviteOperate.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxInviteOperate.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxInviteOperate.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxInviteOperate.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxInviteOperate.onDeleteItem(data);
        }
    });
});
