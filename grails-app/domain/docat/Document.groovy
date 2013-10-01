package docat

class Document {
	static solrable = true
	String name
	DocCategory category
	String attachedFileName

	static constraints = {
        attachedFileName nullable: true
	}
}
