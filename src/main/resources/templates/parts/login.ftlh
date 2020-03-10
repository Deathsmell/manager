<#macro login path >
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="exampleInputEmail1"> User Name :</label>
            <input type="text" name="username" class="form-control" placeholder="Name"/>
        </div>
        <div class="form-group row">
            <label for="exampleInputPassword1"> Password: </label>
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
        <#if path != "/login">
            <div class="form-group row">
                <label for="exampleInputEmail1"> Email: </label>
                <input type="email" name="mail" class="form-control" placeholder="some@some.com"/>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Sign In"/>
        <#if path == "/login">
            <a href="/registration"><input type="button" value="Registration"></a>
        <#else >
            <a href="/login"><input type="button" value="Logging"></a>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Sing Out">
    </form>
</#macro>