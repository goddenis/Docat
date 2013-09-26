<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<r:require module="bootstrap" />
<title><g:layoutTitle default="ДокАТ" /></title>


<g:layoutHead />
<r:layoutResources />
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar">11111</span> <span class="icon-bar">22222</span>
					<span class="icon-bar">33333</span>
				</button>
				<g:link class="brand" url="">Docat grails</g:link>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						Logged in as <a href="#" class="navbar-link">Username</a>
					</p>
					<ul class="nav">
						<li <g:if test="${!controllerName }"> class="active"</g:if>><a
							href="${createLink(uri: '/')}">Home</a></li>
						<li
							<g:if test="${controllerName == 'document'}"> class="active"</g:if>><g:link
								controller="document" action="list">Документы</g:link></li>
						<li
							<g:if test="${controllerName == 'docCategory'}"> class="active"</g:if>><g:link
								controller="docCategory" action="list"> Документы по категориям </g:link>
						</li>
                        <li>
                            <g:form class="form-search" controller="search" oninput="submit">
                                <g:textField name="query" class="input-medium search-query" ></g:textField>
                            </g:form>
                        </li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">

		<g:layoutBody />
		<r:layoutResources />
	</div>
</body>
</html>
