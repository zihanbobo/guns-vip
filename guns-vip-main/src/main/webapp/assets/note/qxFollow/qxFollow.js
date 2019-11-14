layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 用户关注关系表管理
     */
    var QxFollow = {
        tableId: "qxFollowTable"
    };

    /**
     * 初始化表格的列
     */
    QxFollow.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'followerId', sort: true, title: '关注者ID'},
            {field: 'followeeId', sort: true, title: '被关注者'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxFollow.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxFollow.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxFollow.openAddDlg = function () {
        func.open({
            title: '添加用户关注关系表',
            content: Feng.ctxPath + '/qxFollow/add',
            tableId: QxFollow.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxFollow.openEditDlg = function (data) {
        func.open({
            title: '修改用户关注关系表',
            content: Feng.ctxPath + '/qxFollow/edit?id=' + data.id,
            tableId: QxFollow.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxFollow.exportExcel = function () {
        var checkRows = table.checkStatus(QxFollow.tableId);
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
    QxFollow.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxFollow/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxFollow.tableId);
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
        elem: '#' + QxFollow.tableId,
        url: Feng.ctxPath + '/qxFollow/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxFollow.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxFollow.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxFollow.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxFollow.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxFollow.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxFollow.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxFollow.onDeleteItem(data);
        }
    });
});
