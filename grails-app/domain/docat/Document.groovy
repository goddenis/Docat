package docat

class Document {
	
	String name
	DocCategory category
	String attachedFileName

	static constraints = {
        attachedFileName nullable: true
	}
}
