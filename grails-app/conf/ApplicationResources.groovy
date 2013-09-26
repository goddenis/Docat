modules = {
	application { resource url:'js/application.js' }

	pdf_viwer {
		resource url:'js/pdf_viwer/pdf.js', disposition:"head"
		}
    viewer {
        dependsOn 'pdf_viwer'
        resource url:'js/pdf_viwer/viewer.js', disposition: "head"
    }
}