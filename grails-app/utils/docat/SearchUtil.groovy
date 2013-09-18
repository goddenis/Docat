package docat
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

/**
 * Created with IntelliJ IDEA.
 * User: Usov
 * Date: 18.09.13
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class SearchUtil {
    private static url = "http://192.168.33.113:8080"
    private static path = "/solr/collection1/select"
    private static def query = [q: "*", wt: "json", indent: "true"]

    public static def search() {
        try {
            def ret = null
            def http = new HTTPBuilder(url)

            http.request(Method.GET, ContentType.JSON) {
                uri.path = path
                uri.query = query
                response.success = { resp, reader ->
                    println "response status: ${resp.statusLine}"
                    println 'Headers: -----------'
                    resp.headers.each { h ->
                        println " ${h.name} : ${h.value}"
                    }

                    ret = reader?.get("response")?.get("docs")

                    println 'Response data: -----'
                    println ret
                    println '--------------------'
                }

            }
            return ret

        } catch (groovyx.net.http.HttpResponseException ex) {
            ex.printStackTrace()
            return null
        } catch (java.net.ConnectException ex) {
            ex.printStackTrace()
            return null
        }
    }
}
