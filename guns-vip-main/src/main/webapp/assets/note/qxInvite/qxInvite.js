layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 约单表管理
     */
    var QxInvite = {
        tableId: "qxInviteTable"
    };

    /**
     * 初始化表格的列
     */
    QxInvite.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'sn', sort: true, title: '约单单号'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'inviter', sort: true, title: '邀请人'},
            {field: 'invitee', sort: true, title: '被邀请人'},
            {field: 'inviteTime', sort: true, title: '邀请时间'},
            {field: 'inviteType', sort: true, title: '约单类型 0-主动约；1-被动约'},
            {field: 'dateTypeId', sort: true, title: '约会类型'},
            {field: 'giftId', sort: true, title: '扣款种类 礼物ID'},
            {field: 'longitude', sort: true, title: '经度'},
            {field: 'latitude', sort: true, title: '纬度'},
            {field: 'province', sort: true, title: '省'},
            {field: 'city', sort: true, title: '市'},
            {field: 'district', sort: true, title: '区县'},
            {field: 'street', sort: true, title: '街道名称'},
            {field: 'streetNumber', sort: true, title: '门牌号码'},
            {field: 'status', sort: true, title: '状态 0-待配对;1-已配对;2-进行中;3-已完成;4-已取消;5-已投诉'},
            {field: 'inviteWay', sort: true, title: '约单方式：0-单独约；1-报名约'},
            {field: 'content', sort: true, title: '约单描述'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxInvite.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxInvite.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxInvite.openAddDlg = function () {
        func.open({
            title: '添加约单表',
            content: Feng.ctxPath + '/qxInvite/add',
            tableId: QxInvite.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxInvite.openEditDlg = function (data) {
        func.open({
            title: '修改约单表',
            content: Feng.ctxPath + '/qxInvite/edit?id=' + data.id,
            tableId: QxInvite.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxInvite.exportExcel = function () {
        var checkRows = table.checkStatus(QxInvite.tableId);
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
    QxInvite.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxInvite/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxInvite.tableId);
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
        elem: '#' + QxInvite.tableId,
        url: Feng.ctxPath + '/qxInvite/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxInvite.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxInvite.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxInvite.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxInvite.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxInvite.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxInvite.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxInvite.onDeleteItem(data);
        }
    });
});
