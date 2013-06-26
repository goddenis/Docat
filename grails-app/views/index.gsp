<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Добро пожаловать в докат</title>
</head>
<body>


	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<h1>Application Status</h1>
				<ul>
					<li>App version: <g:meta name="app.version" /></li>
					<li>Grails version: <g:meta name="app.grails.version" /></li>
					<li>Groovy version: ${GroovySystem.getVersion()}</li>
					<li>JVM version: ${System.getProperty('java.version')}</li>
					<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
					<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
					<li>Domains: ${grailsApplication.domainClasses.size()}</li>
					<li>Services: ${grailsApplication.serviceClasses.size()}</li>
					<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
				</ul>
				<h1>Installed Plugins</h1>
				<ul>
					<g:each var="plugin"
						in="${applicationContext.getBean('pluginManager').allPlugins}">
						<li>
							${plugin.name} - ${plugin.version}
						</li>
					</g:each>
				</ul>

			</div>
		</div>

		<div class="span9">
			<div class="hero-unit">
				<h1>Добро пожаловать в ДокАТ</h1>
				<p>ДокАт -это справочная система по научно технической
					документайии. Кроме того мы предлогаем набор инструментов для
					обсуждения НТД и храненая заметок по часто используимым документам</p>
				<p>
					<g:link class="btn btn-primary bln-large" url="">Узнать больше</g:link>
				</p>
			</div>
			<div>
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c"
						in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link
								controller="${c.logicalPropertyName}">
								${c.fullName}
							</g:link></li>
					</g:each>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
