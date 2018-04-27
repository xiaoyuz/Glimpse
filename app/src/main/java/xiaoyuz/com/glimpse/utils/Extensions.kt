package xiaoyuz.com.glimpse.utils

import java.util.*

/**
 * Generate a UUID in 32 bit reduced length.
 */
fun reducedUUID(): String {
    val uuid = UUID.randomUUID().toString()
    return StringBuilder().append(uuid.substring(14, 18))
            .append(uuid.substring(9, 13)).append(uuid.substring(0, 8))
            .append(uuid.substring(19, 23)).append(uuid.substring(24)).toString()
}