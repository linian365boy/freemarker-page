<#macro pagination id method cssClass="" btnCssClass="" pageSize='10'>
<div id='${id}'>
<#nested> <#-- 要刷新的区域 -->

<div class="${cssClass}">
    <input type="button" onclick="Pagination${id}.gotoPage('prev', '${webPager.pageInfo.currentPage}', '${totalSize}');" value="上一页" class="${btnCssClass}" />
    &nbsp;&nbsp;共 ${totalPages} 页&nbsp;&nbsp;第 ${webPager.pageInfo.currentPage} 页&nbsp;&nbsp;转到
    <input type="text" id='${id}_inputPageNo' name='${id}_inputPageNo' size="6"/>页 &nbsp;&nbsp;
    <input type="text" style="display:none"/>
    <input type="button" onclick="Pagination${id}.gotoPage('input', '${webPager.pageInfo.currentPage}', '${totalSize}');" value="确定" class="${btnCssClass}" />
    <input type="button" onclick="Pagination${id}.gotoPage('next', '${webPager.pageInfo.currentPage}', '${totalSize}');" value="下一页" class="${btnCssClass}" />
</div>

<script>
if (!this.Pagination${id} ) {
    this.Pagination${id} = {}

    // 分页处理方法
    Pagination${id}.gotoPage = function(action, c, t) {
        var cmd = action;

        // 当总记录数量小于等于每页记录数量时，分页动作无效
        if (t <= ${webPager.pageInfo.pageSize}) {
            return;
        }

        // 确定按钮处理
        if(action == 'input') {
            var inValue = $("#${id}_inputPageNo").val();

            if(inValue.match(/^[0-9]+$/) && parseInt(inValue) > 0) {
                cmd = inValue;
            }
            else {
                // TODO 提示错误信息
                return ;
            }
        }

        $('#${id}').load(
            '${method} #${id}',
            {action : cmd,
             totalSize : t,
             currentPage : c
            }
        );
    };
    
    // 刷新当前页面
    Pagination${id}.refreshPage = function() {
        //Pagination${id}.gotoPage('refresh');
        //scrollPage${id}('refresh', "${method}");
    };

    // 页码输入框keyup事件
    //Pagination${id}.keyup = function(inputElem, currentPage, totalSize) {
        //if (inputElem.value.match(/^[0-9]+$/) && parseInt(inputElem.value) > 0) {
            //Pagination${id}.gotoPage(inputElem.value, currentPage, totalSize);
        //}
    //};
    
    //$("#${id}_inputPageNo").keyup(function(event){
        //if(event.keyCode == 13) {
            //Pagination${id}.keyup(this, '${webPager.pageInfo.currentPage}', '${totalSize}');
        //}
    //});
}
</script>

</div>
</#macro>