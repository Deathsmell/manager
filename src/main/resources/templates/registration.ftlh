<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <h1>Add new user</h1>
    <#if message??>
        ${message}
    </#if>
    <@l.login "/registration"/>
</@c.page>