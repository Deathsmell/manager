<#import "parts/common.ftlh" as c>
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
                    <form class="ml-3" action="/cashboxes/filtr" method="get">
                        <input type="text" class="form-control pr-3" name="name" placeholder="Введите имя организации">
                        <input type="text" class="form-control pr-3" name="address" placeholder="Введите адрес кассы">
                        <div class="row mt-3">
                            <div class="col col-lg-3">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                            <div class="col">
                                <input type="checkbox" class="form-check-input" name="isCase" id="check">
                                <label class="form-check-label" for="check">
                                    Регистр важен
                                </label>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col-sm">
                    <div class="list-group" id="list-tab" role="tablist">
                        <a class="list-group-item list-group-item-action active" id="list-home-list"
                           data-toggle="list"
                           href="#list-home" role="tab" aria-controls="home">Здесь</a>
                        <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list"
                           href="#list-profile" role="tab" aria-controls="profile">Будут</a>
                        <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list"
                           href="#list-messages" role="tab" aria-controls="messages">Названия</a>
                        <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list"
                           href="#list-settings" role="tab" aria-controls="settings">КСА</a>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <br/>


    <#if cashboxes??>
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
            <#list cashboxes as cashbox>
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
                                            <input type="hidden" value="${master}" name="master">
                                            <input type="hidden" value="${cashbox}" name="cashbox">
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
    </#if>
</@c.page>