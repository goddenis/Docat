package docat

class DocumentController {

	static scaffold =true
	
    def index() {
		redirect(action: "list")
	}
}
