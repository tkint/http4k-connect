package org.http4k.connect.google.analytics.model

import dev.forkhandles.values.NonBlankStringValueFactory
import dev.forkhandles.values.StringValue

class TrackingId private constructor(value: String) : StringValue(value) {
    companion object : NonBlankStringValueFactory<TrackingId>(::TrackingId)
}
