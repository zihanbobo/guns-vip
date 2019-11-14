layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 推文表管理
     */
    var QxTweet = {
        tableId: "qxTweetTable"
    };

    /**
     * 初始化表格的列
     */
    QxTweet.initColumn = function () {
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
            {field: 'images', sort: true, title: '图片 图片地址1;图片地址2;图片地址3'},
            {field: 'title', sort: true, title: '推文标题'},
            {field: 'content', sort: true, title: '推文内容'},
            {field: 'canComment', sort: true, title: '是否允许评价'},
            {field: 'favoriteCount', sort: true, title: '点赞数'},
            {field: 'commentCount', sort: true, title: '评论数'},
            {field: 'giftCount', sort: true, title: '已赠送礼物数'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxTweet.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxTweet.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxTweet.openAddDlg = function () {
        func.open({
            title: '添加推文表',
            content: Feng.ctxPath + '/qxTweet/add',
            tableId: QxTweet.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxTweet.openEditDlg = function (data) {
        func.open({
            title: '修改推文表',
            content: Feng.ctxPath + '/qxTweet/edit?id=' + data.id,
            tableId: QxTweet.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxTweet.exportExcel = function () {
        var checkRows = table.checkStatus(QxTweet.tableId);
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
    QxTweet.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxTweet/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxTweet.tableId);
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
        elem: '#' + QxTweet.tableId,
        url: Feng.ctxPath + '/qxTweet/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxTweet.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxTweet.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxTweet.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxTweet.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxTweet.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxTweet.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxTweet.onDeleteItem(data);
        }
    });
});
