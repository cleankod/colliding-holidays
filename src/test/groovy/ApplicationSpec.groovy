import spock.lang.Specification

class ApplicationSpec extends Specification {
    def "application has a greeting"() {
        setup:
        def app = new Application()

        when:
        def result = app.greeting

        then:
        result == "Hello BS!"
    }
}
