package docat

class DocCategory {
    static def solarable = false
	String name
	
	@Override
	public String toString() {
		return name
	}
	
    static constraints = {
    }
}
