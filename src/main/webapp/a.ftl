<!DOCTYPE  html  PUBLIC  "-//W3C//DTD  XHTML  1.0  Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>活码活动列表</title>
    <meta  http-equiv="content-type"  content="multipart/form-data;text/html;  charset=UTF-8"/>
    <script  src="/scripts/boot.js"  type="text/javascript"></script>
    <script  src="/scripts/webutils.js"  type="text/javascript"></script>
    <script  src="/scripts/select2.min.js"  type="text/javascript"></script>
    <script  src="/scripts/select2.min.css"  type="text/javascript"></script>
</head>
<body>

<style  type="text/css">
    html,  body  {
        margin:  0;
        padding:  0;
        border:  0;
        width:  100%;
        height:  100%;
        overflow:  hidden;
    }

</style>

<div  class="mini-toolbar"  style="padding:2px;border-bottom:0;">
    <table  style="width:100%;">
        <tr>
            <td  style="width:100%;">
                <a  class="mini-button"  iconCls="icon-add"  onclick="addActivity('new')">增加</a>
            </td>
            <td  style="width:100%;">
                <a  class="mini-button"  iconCls="icon-add"  onclick="batchAddFollower()">批量分配负责人</a>
            </td>
        </tr>
    </table>
</div>


<div  class="mini-toolbar"  style="padding:2px;border-bottom:0;">
    <form  id="searchForm">
        <table>
            <tr>
                <td>活动名称：</td>
                <td><input  id="search_activityName"  class="mini-textbox"  emptyText="请输入活动名称"  style="width:150px;"
                            onenter="onKeyEnter"/></td>
                <td>活动状态：</td>
                <td  colspan="3">
                    <input  id="search_activityStatus"  class="mini-combobox"  allowInput="false"  value="all"
                            data="[{id:  'all',  text:  '全部'},{id:  'INIT',  text:  '待执行'},{id:  'CREATE_GROUP_FAIL',  text:  '建群失败'},{id:  'CREATE_GROUP_SUCCESS',text:  '建群成功'},
                                                        {id:  'ADD_MEMBER_SKIP',  text:  '拉人跳过'},{id:  'ADD_MEMBER_SUCCESS',  text:  '拉人成功'},{id:  'ADD_MEMBER_FAIL',text:  '拉人失败'},
                                                        {id:  'SYNC_GROUP_SUCCESS',  text:  '同步群成功'},{id:  'SYNC_GROUP_FAIL',  text:  '同步群失败'},
                                                        {id:  'CREATE_LIVE_SUCCESS',text:  '生成活码成功'},{id:  'CREATE_LIVE_FAIL',text:  '生成活码失败'},
                                                        {id:  'ONLINE',  text:  '进行中'},{id:  'OFFLINE',  text:  '下线'}]"
                            textField="text"  valueField="id"  required="true">
                </td>
                <td>负责人：</td>
                <td><input  id="search_activity_follower"  class="mini-textbox"  emptyText="请输负责人名称"  style="width:150px;"  onenter="onKeyEnter"/></td>
                <td  style="white-space:nowrap;"  align="right">
                    <a  class="mini-button"  iconCls="icon-search"  onclick="activitySearch()">查询</a>&nbsp;
                    <a  class="mini-button"  iconCls="icon-redo"  onclick="clearSearchFrom()">重置</a>&nbsp;
                </td>
            </tr>

        </table>
    </form>
</div>

<!--撑满页面-->
<div  cl ass="mini-fit">
    <div id="activityList" class="mini-datagrid" style="width:100%;height:100%;"
         idField="id" url="/nbmp/admin/wechat/livecode/activityList"
         sizeList="[20,50]" pageSize="20" allowResize="false" multiSelect="false" allowAlternating="true">
        <div property="columns">
            <div field="activityId" width="50" headerAlign="center" align="center">活动id</div>
            <div field="activityNo" width="100" headerAlign="center" align="center">活动编码</div>
            <div field="activityName" width="100" headerAlign="center" align="center">活动名称</div>
            <div field="channelNum" width="50" headerAlign="center" align="center">渠道数量</div>
            <div field="groupCount" width="50" headerAlign="center" align="center">群数量</div>
            <div field="activityStatus" width="80" headerAlign="center" renderer="statusRenderer" align="center">活动状态</div>
            <div field="courseTime" width="80" headerAlign="center" renderer="ondayRenderer" align="center">开课时间</div>
            <div field="expireTime" width="100" headerAlign="center" renderer="ondayRenderer" align="center">过期时间</div>
            <div field="adviserNames" width="100" headerAlign="center" align="center">顾问负责人</div>
            <div field="assistantAdvisers" width="100" headerAlign="center" align="center">助理顾问负责人</div>
            <div field="navyNames" width="100" headerAlign="center" align="center">群众负责人</div>
            <div field="createTime" width="100" headerAlign="center" renderer="ondayRenderer" align="center">创建时间</div>
            <div name="operation" headerAlign="center" width="150" align="center" align="center">操作</div>
        </div>
    </div>
</div>

<div id="qrCodeShowWin" class="mini-window" showMaxButton="true" ,showCollapseButton="true" ,showShadow="true"
     style="width:80%;height:80%;"
     ,bodyStyle="padding=10px" ,
     showModal="true" ,allowResize="true" ,allowDrag="true" ,showCloseButton="true" allowResize="true">
</div>

<script type="text/javascript">
    mini.parse();
    ///////////////数据初始化///////////////////
    var activityList = mini.get("activityList");
    activityList.on("drawcell", function (e) {
        var record = e.record, column = e.column, field = e.field, value = e.value;
        if (column.name == "operation") {
            var activityStatus =  e.row.activityStatus;
            var activityId =  e.row.activityId;
            var showHtml = "<input type='button' style='background: #e5fef6' onclick='showDetail(\"" + activityId + "\");'   value='查看活码'/>";
            var addHtml = "<input type='button' style='background: #e5fef6' onclick='addActivity(\"edit\",\"" + activityId + "\");'   value='编辑'/>";

            if(activityStatus == "ONLINE"){
                e.cellHtml = "<div>" + showHtml + "&nbsp;&nbsp; " + addHtml + "</div>";
            }else{
                e.cellHtml = "<div></div>";
            }
            e.cellStyle = "text-align:center;";

        }
    });
    activityList.load();

    function showDetail(activityId) {
        console.log("showActivity detail")
        var url = "/nbmp/admin/wechat/livecode/page/showActivityDetailPage?activityId="+activityId;
        var title = "活动详情";
        openActivityDtailWin(url, title)
    }

    function openActivityDtailWin(url,title){
        console.log("openActivityDtailWin");
        mini.open({
            url: url,
            title: title,
            width: 1024,
            height: 800,
            ondestroy: function (action) {
                //var iframe = this.getIFrameEl();
                activityList.reload();
            }
        });
    }


    function addActivity(action,activityId){
        console.log("addActivity");
        var url = "/nbmp/admin/wechat/livecode/page/createActivityPage";
        var title = "新增活动";
        var data = {action: action,activityId:activityId};

        openActivityWin(url,title,data);
    }
    function batchAddFollower(){
        var win = new LiveFollowerSelectWindow();
        win.set({
            url: "/nbmp/admin/wechat/livecode/selectActivityFollowers",
            title: "选择负责人",
            width: 1080,
            height: 600
        });
        win.show();
        //初始化数据
        win.search();
        win.setData(null, function (action) {
            if (action == "ok") {
                var rows = win.getData();//获取数据
                //todo BEE-7839 判断顾问是否存在
                for (var i = 0; i < rows.length; i++) {
                    var succFlag = addActivityFollowerTr(rows[i].mallUserId, rows[i].userName, '', true);
                    if (!succFlag) {
                        return
                    }
                }

            }
        });
    }
    function deleteActivity(activityId){
        var url = "/nbmp/admin/wechat/livecode/deleteActivity?activityId="+activityId;
        $.ajax({
            url: url,
            type: 'get',
            cache: false,
            success: function (r) {
                activitySearch();
            },
            error: function (jqXHR) {
                alert(jqXHR.message);
            }
        });
    }

    function changeStatus(activityId, toStatus) {
        var url = "/nbmp/admin/wechat/livecode/changeStatus?activityId=" + activityId + "&toActivityStatus=" + toStatus;
        $.ajax({
            url: url,
            type: 'get',
            cache: false,
            success: function (r) {
                activitySearch();
            },
            error: function (jqXHR) {
                alert(jqXHR.message);
            }
        });

    }

    function openActivityWin(url, title, data) {
        console.log("openActivityWin");
        mini.open({
            url: url,
            title: title,
            width: "100%", height: "100%",
            onload: function () {
                console.log(url);
                console.log(title);
                console.log(data);
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                //var iframe = this.getIFrameEl();
                activityList.reload();

            }
        });
    }


    var qrCodeWin = mini.get("qrCodeShowWin");

    function showQrCode(activityId) {
        qrCodeWin.set({
            title: "活码展示",
            url: "/nbmp/admin/wechat/livecode/page/activityQrCodePage?activityId=" + activityId,
            width: 800,
            height: 480,
            onload: function (e) {
                var ifrm = this.getIFrameEl();
            },
            ondestroy: function (e) {
                alert("ondestroy");
            }
        });
        qrCodeWin.showAtPos("center", "middle");

    }

    function activitySearch(){
        var activityName = mini.get("search_activityName") != null ? mini.get("search_activityName").getValue() : "";
        var activityStatus = mini.get("search_activityStatus") != null ? mini.get("search_activityStatus").getValue() : "";
        var activityFollower = mini.get("search_activity_follower") != null ? mini.get("search_activity_follower").getValue() : "";

        console.log(activityName+","+activityStatus);
        if (activityStatus=="all"){
            activityStatus="";
        }
        activityList.load({
            "activityName": activityName,
            "activityStatus": activityStatus,
            "activityFollower":activityFollower
        });

    }

    function clearSearchFrom() {
        var form = new mini.Form("#searchForm");
        form.clear();
        mini.get("activityStatus").setValue("all");
    }

    //时间格式
    function ondayRenderer(e) {
        var value = new Date(e.value);
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
    }

    function statusRenderer(e) {
        var statusNodeEnum = [{id: 'INIT', text: '待执行'},{id: 'CREATE_GROUP_FAIL', text: '建群失败'},
            {id: 'CREATE_GROUP_SUCCESS',text: '建群成功'}, {id: 'ADD_MEMBER_SKIP', text: '拉人跳过'},
            {id: 'ADD_MEMBER_SUCCESS', text: '拉人成功'},{id: 'ADD_MEMBER_FAIL',text: '拉人失败'},
            {id: 'SYNC_GROUP_SUCCESS', text: '同步群成功'},{id: 'SYNC_GROUP_FAIL', text: '同步群失败'},
            {id: 'CREATE_LIVE_SUCCESS',text: '生成活码成功'},{id: 'CREATE_LIVE_FAIL',text: '生成活码失败'},
            {id: 'ONLINE', text: '进行中'},{id: 'OFFLINE', text: '下线'},{id: 'UNKNOWN',text: '未知'}];
        for (var i = 0, l = statusNodeEnum.length; i < l; i++) {
            var g = statusNodeEnum[i];
            if (g.id === e.value) return g.text;
        }
        return "未知";
    }

</script>

</body>
</html>