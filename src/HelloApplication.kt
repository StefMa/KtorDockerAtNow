import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.html.respondHtml
import io.ktor.http.URLProtocol
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.html.*

val counterFile = createTempFile().apply {
    writeText("1")
}

val css = """

    body {
        background-color: #5e92f3;
    }

    .centered {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
""".trimIndent()

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title { text("ASAP Counter") }
                    style { unsafe { raw(css) } }
                    meta {
                        name = "viewport"
                        content = "width=device-width, initial-scale=1.0, user-scalable=no"
                    }
                }
                body {
                    div {
                        classes = setOf("centered")
                        p {
                            val fileText = counterFile.readText()
                            text("ASAP count: $fileText")
                        }
                        form {
                            action = "/pressed"
                            method = FormMethod.post
                            button {
                                type = ButtonType.submit
                                text("ASAP!!!!!111oneeleven")
                            }
                        }
                    }
                }
            }
        }
        post("/pressed") {
            with(counterFile) {
                val currentText = readText()
                val newFileContent = currentText.toInt() + 1
                writeText(newFileContent.toString())
            }
            // Text in file was increased
            // Redirect back to the "main page"
            call.respondRedirect {
                // If we deploy to now.sh we have to change the port and protocol
                if (host.contains("now")) {
                    port = 443
                    protocol = URLProtocol.HTTPS
                }
                path("")
            }
        }
    }
}
