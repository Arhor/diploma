package org.arhor.diploma.startup

import org.arhor.diploma.commons.ActionResult
import org.arhor.diploma.commons.ActionResult.Success
import org.arhor.diploma.commons.Verifiable
import org.springframework.stereotype.Component

@Component
class SimpleStartupVerifier : Verifiable {

    override val priority: Int = 0

    override fun verify(): ActionResult<String> {
        return Success("Simple startup verification complete. It should be always first.")
    }
}