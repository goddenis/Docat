'use strict';

PDFJS.disableWorker = true;

var pdfDoc = null,
    pageNum = 1,
    scale = 0.8,
    canvas ,
    ctx ;

function renderPage(num) {
    // Using promise to fetch the page
    pdfDoc.getPage(num).then(function(page) {
        var viewport = page.getViewport(scale);
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        // Render PDF page into canvas context
        var renderContext = {
            canvasContext: ctx,
            viewport: viewport
        };
        page.render(renderContext);
    });

    // Update page counters
    document.getElementById('page_num').textContent = pageNum;
    document.getElementById('page_count').textContent = pdfDoc.numPages;
}

function renderDoc(pdfUrl) {
    if (!pdfUrl) {
        //pdfUrl = '/Docat/helloworld2.pdf'
        return
    }
    canvas = document.getElementById('the-canvas'),
    ctx = canvas.getContext('2d');

    PDFJS.workerSrc = '/Docat/js/pdf_viwer/pdf.js';

    PDFJS.getDocument(pdfUrl).then(function (_pdfDoc) {
        pdfDoc = _pdfDoc;
        renderPage(pageNum);
    });

//
//    PDFJS.getDocument(pdfUrl).then(function (pdf) {
//        pdf.getPage(1).then(function (page) {
//            var scale = 2;
//            var viewport = page.getViewport(scale);
//
//            canvas.height = viewport.height;
//            canvas.width = viewport.width;
//
//            var renderContext = {
//                canvasContext: context,
//                viewport: viewport
//            };
//            page.render(renderContext);
//        });
//    });
}
function goPrevious() {
    if (pageNum <= 1)
        return;
    pageNum--;
    renderPage(pageNum);
}

//
// Go to next page
//
function goNext() {
    if (pageNum >= pdfDoc.numPages)
        return;
    pageNum++;
    renderPage(pageNum);
}