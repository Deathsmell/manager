<#macro masterTable masters url>
    <#if masters??>

        <div class="col-sm">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action active" id="list-home-list"
                   data-toggle="list"
                   href="/cashboxes" role="tab" aria-controls="home">Все</a>
                <#list masters as master>
                    <form action="/cashboxes">
                        <button class="list-group-item list-group-item-action"
                                name="master"
                                value="${master.id?c}"
                                type="submit">${master.name}</button>
                    </form>
                </#list>

            </div>
        </div>

    </#if>
</#macro>