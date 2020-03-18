<#import "parts/common.ftlh" as c>
<#--<#import "parts/login.ftlh" as l>-->
<#--<#import "parts/clientform.ftlh" as f>-->
<#--<#include "parts/security.ftlh">-->

<@c.page>
    <div>
        <h1 class="text-center mt-3">List clients</h1>
    </div>
    <br/>
<#--    <#if !isAdmin>-->
<#--        <@f.clientform/>-->
<#--    </#if>-->

    <#if clients??>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col" class="text-center">Название фирмы</th>
                <th scope="col" class="text-center">УНП</th>
                <th scope="col" class="text-center">mail</th>
                <th scope="col" class="text-center"></th>
            </tr>
            </thead>
            <tbody>
            <#list clients as client>
                <tr>
                    <th scope="row">${client.id}</th>
                    <td class="text-center">${client.name!''}</td>
                    <td class="text-center">${client.vat!''}</td>
                    <td class="text-center">${client.mail!''}</td>
                    <td class="text-right"><a href="/upload/${client.id}">upload</a></td>
                    <td class="text-right"><a href="/delete/${client.id}">delete</a></td>
                </tr>
            </#list>
            </tbody>
            <#--<#if client.filename??>
                <img src="/img/${client.filename}">
            <#else >no img
            </#if>-->
        </table>
    </#if>
</@c.page>