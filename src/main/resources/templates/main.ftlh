<#import "parts/common.ftlh" as c>

<@c.page>
    <h1>
        Это начальная страница
    </h1>
    <h3>
        Тут планируется простенькое отображение списка возможностей и немного навигации или логи
    </h3>
    <div class="mt-3">
            <a href="/list/format">
                <button class="btn btn-outline-danger mr-2" type="submit">Форматировать txt исходник</button>
            </a>
        <a href="/list/create">
            <button class="btn btn-outline-danger pr-3" type="submit">Создать дб из txt</button>
        </a>
    </div>
    <div class="mt-3">
        <#if fileExists>
            <form action="/readExcel">
                <button class="btn btn-primary btn-lg active" type="submit">Читать сущности из Excel</button>
            </form>
        <#else>
            <form action="/createExcel">
                <button class="btn btn-primary btn-lg active" type="submit">Сделать Excel.xls</button>
            </form>

        </#if>
    </div>




</@c.page>