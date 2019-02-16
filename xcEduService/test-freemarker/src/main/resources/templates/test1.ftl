<!DOCTYPEhtml>
<html>
<head>
  <metacharset
  ="utf‐8">
  <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
<table>
  <tr>
    <td>序号</td>
    <td>姓名</td>
    <td>年龄</td>
    <td>钱包</td>
    <td>出生日期</td>
  </tr>
  <#list stus as stu>
    <tr style="border: solid yellow">
    <td>${stu_index + 1}</td>
    <td <#if stu.name == '小明'>style="background: #8b1c5b;" </#if>${stu.name}</td>
    <td>${stu.age}</td>
    <td>${stu.money}</td>
    <td>${stu.birthday?date}</td>
    </tr>
  </#list>
</table>
<br/>
遍历模型数据中的map<br/>
stu1的信息:<br/>
${stuMap['stu1'].name}<br/>
${stuMap['stu1'].age}<br/>
${stuMap.stu2.name}<br/>
${stuMap.stu2.age}<br/>
遍历key来取值<br/>
<#list stuMap?keys as k>
  <tr>
  <td>${k_index+1}</td><br/>
  <td <#if stuMap[k].name == '小红'>style="background: aquamarine" </#if>>${stuMap[k].name}</td><br/>
  <td>${stuMap[k].age}</td><br/>
  <td>${stuMap[k].money}</td><br/>
  </tr>
</#list>
</body>
</html>