layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 礼物表管理
     */
    var QxGift = {
        tableId: "qxGiftTable"
    };

    /**
     * 初始化表格的列
     */
    QxGift.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'image', sort: true, title: '图片'},
            {field: 'price', sort: true, title: '对应金币'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxGift.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxGift.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxGift.openAddDlg = function () {
        func.open({
            title: '添加礼物表',
            content: Feng.ctxPath + '/qxGift/add',
            tableId: QxGift.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxGift.openEditDlg = function (data) {
        func.open({
            title: '修改礼物表',
            content: Feng.ctxPath + '/qxGift/edit?id=' + data.id,
            tableId: QxGift.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxGift.exportExcel = function () {
        var checkRows = table.checkStatus(QxGift.tableId);
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
    QxGift.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxGift/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxGift.tableId);
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
        elem: '#' + QxGift.tableId,
        url: Feng.ctxPath + '/qxGift/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxGift.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxGift.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxGift.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxGift.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxGift.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxGift.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxGift.onDeleteItem(data);
        }
    });
});
