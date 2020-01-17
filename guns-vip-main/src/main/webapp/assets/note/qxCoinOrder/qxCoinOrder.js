layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 用户金币订单表管理
     */
    var QxCoinOrder = {
        tableId: "qxCoinOrderTable"
    };

    /**
     * 初始化表格的列
     */
    QxCoinOrder.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'sn', sort: true, title: '订单号'},
            {field: 'mobile', sort: true, title: '用户账号'},
            {field: 'coins', sort: true, title: '金币'},
            {field: 'amount', sort: true, title: '金额'},
            {field: 'type', sort: true, title: '充值类型', templet: function(d) {
            	// 0-微信；1-支付宝；2-平台
            	if (d.type == 0) {
            		return '微信';
            	} else if (d.type == 1) {
            		return '支付宝';
            	} else if (d.type == 2) {
            		return '平台';
            	}
            }},
            {field: 'status', sort: true, title: '状态', templet: function(d) {
            	// 状态：0-待支付；1-已支付;2-已取消
            	if (d.status == 0) {
            		return '待支付';
            	} else if (d.status == 1) {
            		return '已支付';
            	} else if (d.status == 2) {
            		return '已取消';
            	}
            }},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxCoinOrder.search = function () {
        var queryData = {};
        queryData['mobile'] = $("#mobile").val();
        table.reload(QxCoinOrder.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxCoinOrder.openAddDlg = function () {
        func.open({
            title: '添加用户金币订单表',
            content: Feng.ctxPath + '/qxCoinOrder/add',
            tableId: QxCoinOrder.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxCoinOrder.openEditDlg = function (data) {
        func.open({
            title: '修改用户金币订单表',
            content: Feng.ctxPath + '/qxCoinOrder/edit?id=' + data.id,
            tableId: QxCoinOrder.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxCoinOrder.exportExcel = function () {
        var checkRows = table.checkStatus(QxCoinOrder.tableId);
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
    QxCoinOrder.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxCoinOrder/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxCoinOrder.tableId);
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
        elem: '#' + QxCoinOrder.tableId,
        url: Feng.ctxPath + '/qxCoinOrder/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxCoinOrder.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxCoinOrder.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxCoinOrder.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxCoinOrder.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxCoinOrder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxCoinOrder.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxCoinOrder.onDeleteItem(data);
        }
    });
});
