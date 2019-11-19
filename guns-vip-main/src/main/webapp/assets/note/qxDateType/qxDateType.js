layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var QxDateType = {
        tableId: "qxDateTypeTable"
    };

    /**
     * 初始化表格的列
     */
    QxDateType.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'name', sort: true, title: '名称'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxDateType.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxDateType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxDateType.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/qxDateType/add',
            tableId: QxDateType.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxDateType.openEditDlg = function (data) {
        func.open({
            title: '修改',
            content: Feng.ctxPath + '/qxDateType/edit?id=' + data.id,
            tableId: QxDateType.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxDateType.exportExcel = function () {
        var checkRows = table.checkStatus(QxDateType.tableId);
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
    QxDateType.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxDateType/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxDateType.tableId);
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
        elem: '#' + QxDateType.tableId,
        url: Feng.ctxPath + '/qxDateType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxDateType.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxDateType.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxDateType.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxDateType.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxDateType.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxDateType.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxDateType.onDeleteItem(data);
        }
    });
});
