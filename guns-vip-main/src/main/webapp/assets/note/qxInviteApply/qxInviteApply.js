layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 约单报名管理
     */
    var QxInviteApply = {
        tableId: "qxInviteApplyTable"
    };

    /**
     * 初始化表格的列
     */
    QxInviteApply.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'inviteId', sort: true, title: '约单ID'},
            {field: 'userId', sort: true, title: '报名用户ID'},
            {field: 'status', sort: true, title: '状态：0-待确定；1-已选中；2-未选中'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxInviteApply.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxInviteApply.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxInviteApply.openAddDlg = function () {
        func.open({
            title: '添加约单报名',
            content: Feng.ctxPath + '/qxInviteApply/add',
            tableId: QxInviteApply.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxInviteApply.openEditDlg = function (data) {
        func.open({
            title: '修改约单报名',
            content: Feng.ctxPath + '/qxInviteApply/edit?id=' + data.id,
            tableId: QxInviteApply.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxInviteApply.exportExcel = function () {
        var checkRows = table.checkStatus(QxInviteApply.tableId);
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
    QxInviteApply.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxInviteApply/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxInviteApply.tableId);
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
        elem: '#' + QxInviteApply.tableId,
        url: Feng.ctxPath + '/qxInviteApply/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxInviteApply.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxInviteApply.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxInviteApply.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxInviteApply.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxInviteApply.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxInviteApply.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxInviteApply.onDeleteItem(data);
        }
    });
});
