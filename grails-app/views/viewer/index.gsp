<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Добро пожаловать в докат</title>
    <r:require module="pdf_viwer"/>

</head>

<body>
<div class="row-fluid">
    <div class="span3">
        <div class="well">
            <h1>Application Status</h1>
            <ul>
                <li>App version: <g:meta name="app.version"/></li>
                <li>Grails version: <g:meta name="app.grails.version"/></li>
                <li>Groovy version: ${GroovySystem.getVersion()}</li>
                <li>JVM version: ${System.getProperty('java.version')}</li>
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
            <h1>Добро пожаловать во Viewer</h1>
        </div>
    </div>



    <r:script disposition="head">
        'use strict';

        PDFJS.workerSrc = '/Docat/js/pdf_viwer/pdf.js';
        //
        // Fetch the PDF document from the URL using promises
        //
        PDFJS.getDocument('/Docat/document/download/1').then(function (pdf) {
            // Using promise to fetch the page
            pdf.getPage(1).then(function (page) {
                var scale = 1.5;
                var viewport = page.getViewport(scale);

                //
                // Prepare canvas using PDF page dimensions
                //
                var canvas = document.getElementById('the-canvas');
                var context = canvas.getContext('2d');
                canvas.height = viewport.height;
                canvas.width = viewport.width;

                //
                // Render PDF page into canvas context
                //
                var renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };
                page.render(renderContext);
            });
        });
    </r:script>
    <canvas id="the-canvas" style="border: 1px solid black;"></canvas>

</div>
</body>
</html>
