<#import "parts/common.ftlh" as c>
<#import "parts/mastersTable.ftlh" as mt>
<#import "parts/pager.ftlh" as p>
<#--<#import "parts/login.ftlh" as l>-->
<#--<#import "parts/clientform.ftlh" as f>-->
<#--<#include "parts/security.ftlh">-->

<@c.page>
    <div>
        <h1 class="text-center mt-3">Cashboxes list</h1>
    </div>

    <div class="form-group row ">
        <div class="container mt-4">
            <div class="row">
                <div class="col-sm">
                    <form class="ml-3" action="/cashboxes" method="get" id="searchCashboxes">
                        <input type="text" class="form-control pr-3 mt-5"
                               name="name" placeholder="Введите имя организации"
                                <#if RequestParameters.name??>
                                    value="${RequestParameters.name}"
                                </#if>
                        >
                        <input type="text" class="form-control pr-3 mt-5"
                               name="address" placeholder="Введите адрес кассы"
                                <#if RequestParameters.address??>
                                    value="${RequestParameters.address}"
                                </#if>
                        >
                    </form>
                    <#if masters??>
                </div>
                <div class="col-sm">
                    <div class="list-group" id="list-tab" role="tablist">
                        <button class="list-group-item list-group-item-action  <#if !RequestParameters.master??>active</#if>"
                                form="searchCashboxes">Все
                        </button>
                        <#list masters as master>
                            <button class="list-group-item
                                           list-group-item-action
                                           <#if RequestParameters.master?? && RequestParameters.master == master.id?c>active</#if>"
                                    name="master"
                                    form="searchCashboxes"
                                    value="${master.id?c}"
                                    type="submit">${master.name}</button>
                        </#list>

                    </div>
                </div>
                <#else>
                    <div class="row mt-3">
                        <div class="col col-lg-3">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </#if>

            </div>
        </div>
    </div>
    <br/>


    <#if cashboxes??>
        <@p.pager url cashboxes/>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col" class="text-center">Client names</th>
                <th scope="col" class="text-center">Model names</th>
                <th scope="col" class="text-center">Address</th>
                <th scope="col" class="text-center">Master names</th>
            </tr>
            </thead>
            <tbody>
            <#list cashboxes.content as cashbox>
                <tr>
                    <th scope="row">${cashbox.client.name}</th>
                    <td class="text-center">${cashbox.nameModel!''}</td>
                    <td class="text-center">${cashbox.address!''}</td>
                    <td class="text-center">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <#if cashbox.master??>
                                    ${cashbox.master.name}
                                <#else >
                                    Don't been set master name
                                </#if>
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <#if masters??>
                                    <#list masters as master>
                                        <form action="/cashboxes/setMaster" method="post">
                                            <input type="hidden" value="${master.id?c}" name="master"/>
                                            <input type="hidden" value="${cashbox.id?c}" name="cashbox"/>
                                            <button class="dropdown-item" type="submit">${master.name}</button>
                                        </form>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </td>
                    <#--                    <td class="text-right"><a href="/upload/${cashbox.id}">upload</a></td>-->
                    <#--                    <td class="text-right"><a href="/delete/${cashbox.id}">delete</a></td>-->
                </tr>
            </#list>
            </tbody>
            <#--<#if client.filename??>
                <img src="/img/${client.filename}">
            <#else >no img
            </#if>-->
        </table>
        <@p.pager url cashboxes/>
    </#if>
</@c.page>