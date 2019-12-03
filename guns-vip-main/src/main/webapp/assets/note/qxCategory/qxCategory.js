layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 商品品类表管理
     */
    var QxCategory = {
        tableId: "qxCategoryTable"
    };

    /**
     * 初始化表格的列
     */
    QxCategory.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'name', sort: true, title: '品类名称'},
            {field: 'orderNo', sort: true, title: '排序'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxCategory.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxCategory.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxCategory.openAddDlg = function () {
        func.open({
            title: '添加商品品类表',
            content: Feng.ctxPath + '/qxCategory/add',
            tableId: QxCategory.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxCategory.openEditDlg = function (data) {
        func.open({
            title: '修改商品品类表',
            content: Feng.ctxPath + '/qxCategory/edit?id=' + data.id,
            tableId: QxCategory.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxCategory.exportExcel = function () {
        var checkRows = table.checkStatus(QxCategory.tableId);
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
    QxCategory.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxCategory/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxCategory.tableId);
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
        elem: '#' + QxCategory.tableId,
        url: Feng.ctxPath + '/qxCategory/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxCategory.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxCategory.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxCategory.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxCategory.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxCategory.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxCategory.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxCategory.onDeleteItem(data);
        }
    });
});
