layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 约单评价表管理
     */
    var QxInviteComment = {
        tableId: "qxInviteCommentTable"
    };

    /**
     * 初始化表格的列
     */
    QxInviteComment.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'commenterId', sort: true, title: '评价者ID'},
            {field: 'commenteeId', sort: true, title: '被评价者ID'},
            {field: 'inviteId', sort: true, title: '约单ID'},
            {field: 'level', sort: true, title: '评价等级 0-不满意；1-一般；2-满意'},
            {field: 'content', sort: true, title: '评价内容'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxInviteComment.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxInviteComment.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxInviteComment.openAddDlg = function () {
        func.open({
            title: '添加约单评价表',
            content: Feng.ctxPath + '/qxInviteComment/add',
            tableId: QxInviteComment.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxInviteComment.openEditDlg = function (data) {
        func.open({
            title: '修改约单评价表',
            content: Feng.ctxPath + '/qxInviteComment/edit?id=' + data.id,
            tableId: QxInviteComment.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxInviteComment.exportExcel = function () {
        var checkRows = table.checkStatus(QxInviteComment.tableId);
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
    QxInviteComment.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxInviteComment/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxInviteComment.tableId);
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
        elem: '#' + QxInviteComment.tableId,
        url: Feng.ctxPath + '/qxInviteComment/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxInviteComment.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxInviteComment.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxInviteComment.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxInviteComment.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxInviteComment.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxInviteComment.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxInviteComment.onDeleteItem(data);
        }
    });
});
