<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:include page="styles.jsp"/>--%>
<html>


<head>
    <title>文件上传</title>
    <form id="uploadInfo" action="fileUpload/uploadInfo/" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>
                    <h4>报文+文件进行请求</h4>
                    <textarea placeholder="输入请求报文信息" name="requestXml" rows="30" cols="130"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="file" name="file" placeholder="请选择你要上传的文件" />
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">提交</button>
                </td>
            </tr>
        </table>
    </form>
    <%--<ul class="pagination">--%>
        <%--<li><a href="#">&laquo;</a></li>--%>
        <%--<li><a href="#">1</a></li>--%>
        <%--<li><a href="#">2</a></li>--%>
        <%--<li><a href="#">3</a></li>--%>
        <%--<li><a href="#">4</a></li>--%>
        <%--<li><a href="#">5</a></li>--%>
        <%--<li><a href="#">&raquo;</a></li>--%>
    <%--</ul>--%>
    <%--<div id="pagination1" style="display:none;" class="pagina"></div>--%>
</head>
<body>
<%--<script>--%>
    <%--var start = 1;//当前页--%>
    <%--var pageSize = 10;//每页显示数--%>
    <%--$(function(){--%>
        <%--/*分页*/--%>
        <%--/*大分页器*/--%>
        <%--$("#pagination1").whjPaging({--%>
            <%--pageSizeOpt: [--%>
                <%--{value: 5, text: '5条/页', selected: true},--%>
                <%--{value: 10, text: '10条/页'},--%>
                <%--{value: 15, text: '15条/页'},--%>
                <%--{value: 20, text: '20条/页'}--%>
            <%--],--%>
            <%--totalSize: 20,--%>
            <%--totalPage: 1,--%>
            <%--firstPage: '首',--%>
            <%--previousPage: "<",//可选，上一页按钮展示文本--%>
            <%--nextPage: ">",//可选，下一页按钮展示文本--%>
            <%--lastPage: '尾',//可选，尾页按钮展示文本，默认显示文本为尾页--%>
            <%--isShowTotalSize: false,--%>
            <%--isShowTotalPage: true,--%>
            <%--isShowRefresh: false,--%>
            <%--// isShowSkip: false,--%>
            <%--// isShowPageSizeOpt: false,--%>
            <%--isShowFL: false,--%>
            <%--isResetPage: true,--%>
            <%--callBack: function (currPage, pageSize01) {--%>
                <%--// loadData(tagId,report_name,date_value,report_creator,currPage,pageSize01);--%>
                <%--// total_currPage = currPage;--%>
                <%--// console.log('第:' + currPage + '页     每页:' + pageSize01);--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    <%--//设置分页插件显示--%>
    <%--$("#pagination1").whjPaging("setPage", {currPage: currPage, totalPage: 1, totalSize: 2});--%>
    <%--// $("#pagination").whjPaging("setPage", {currPage: currPage, totalPage: res.totalPage, totalSize: Dpagesize});--%>

    <%--$("#pagination1").show();--%>
<%--</script>--%>
</body>
</html>
