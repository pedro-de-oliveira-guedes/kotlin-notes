package com.example

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import javax.security.auth.Subject

@RestController
class GreetingController {
    val id: AtomicLong = AtomicLong()

    @RequestMapping("/greet")
    fun meetAndGreet(@RequestParam(value = "subject", defaultValue = "Peter") subject: String?): Greeting {
        return Greeting("Hello, $subject!", id.incrementAndGet())
    }
}