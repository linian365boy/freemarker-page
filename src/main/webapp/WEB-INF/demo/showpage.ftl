<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
  <script src="../js/jquery-1.4.3.min.js" type="text/javascript"></script>
  
  <#include "/WEB-INF/component/pagination.ftl" /> <#-- 引入分页组件 -->
  <link href="../css/pagination.css" rel="stylesheet" type="text/css"> 
  </head>
  <body>
  <form id="form1">
  <#-- 使用分页组件 -->
  <@pagination id="PageTable" btnCssClass="btn_history" method="PageAction.action">
    <div class="numbertable">
      <table class="history_table">
        <tr height="30px;" style="background-color:#f3f3f3;">
          <td class="td_bold">ID</td>
          <td class="td_bold">NAME</td>
          <td class="td_bold">TEL</td>
          <td class="td_bold">AGE</td>
        </tr>
        <#list data as map>
        <tr height="30px;">
          <td class="td_normal">${map['ID']}</td>
          <td class="td_normal">${map['NAME']}</td>
          <td class="td_normal">${map['TEL']}</td>
          <td class="td_normal">${map['AGE']}</td>
        </tr>
        </#list>
      </table>
    </div>
  </@pagination>
  <#-- 分页组件结束 -->
  
  </form>
  </body>
</html>