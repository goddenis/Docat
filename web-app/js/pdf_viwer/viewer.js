'use strict';

function renderDoc(pdfUrl) {
    if (!pdfUrl) {
        pdfUrl = '/Docat/helloworld2.pdf'
    }
    PDFJS.workerSrc = '/Docat/js/pdf_viwer/pdf.js';

    PDFJS.getDocument(pdfUrl).then(function (pdf) {
        pdf.getPage(1).then(function (page) {
            var scale = 2;
            var viewport = page.getViewport(scale);

            var canvas = document.getElementById('the-canvas');
            var context = canvas.getContext('2d');
            canvas.height = viewport.height;
            canvas.width = viewport.width;

            var renderContext = {
                canvasContext: context,
                viewport: viewport
            };
            page.render(renderContext);
        });
    });
}