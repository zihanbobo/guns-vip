layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 商品表管理
     */
    var QxProduct = {
        tableId: "qxProductTable"
    };

    /**
     * 初始化表格的列
     */
    QxProduct.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'productName', sort: true, title: '商品名称'},
            {field: 'categoryId', sort: true, title: '品类ID'},
            {field: 'price', sort: true, title: '兑换金币价格'},
            {field: 'stock', sort: true, title: '库存数量'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxProduct.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxProduct.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxProduct.openAddDlg = function () {
        func.open({
            title: '添加商品表',
            content: Feng.ctxPath + '/qxProduct/add',
            tableId: QxProduct.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxProduct.openEditDlg = function (data) {
        func.open({
            title: '修改商品表',
            content: Feng.ctxPath + '/qxProduct/edit?id=' + data.id,
            tableId: QxProduct.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxProduct.exportExcel = function () {
        var checkRows = table.checkStatus(QxProduct.tableId);
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
    QxProduct.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxProduct/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxProduct.tableId);
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
        elem: '#' + QxProduct.tableId,
        url: Feng.ctxPath + '/qxProduct/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxProduct.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxProduct.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxProduct.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxProduct.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxProduct.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxProduct.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxProduct.onDeleteItem(data);
        }
    });
});
