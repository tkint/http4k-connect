package org.http4k.connect.amazon.s3

import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import dev.forkhandles.result4k.Success
import org.http4k.connect.successValue
import org.http4k.core.HttpHandler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID

abstract class S3BucketContract(private val http: HttpHandler) {
    abstract val aws: AwsEnvironment

    private val bucket = BucketName(UUID.randomUUID().toString())

    private val s3Bucket by lazy {
        S3.Bucket.Http(bucket, http, aws.scope, { aws.credentials })
    }

    private val key = BucketKey(UUID.randomUUID().toString())

    open fun setup() {}

    @BeforeEach
    fun cleanup() {
        setup()
        s3Bucket.delete(key).successValue()
        s3Bucket.delete().successValue()
    }

    @Test
    fun `bucket key lifecycle`() {
        with(s3Bucket) {
            val newKey = BucketKey(UUID.randomUUID().toString())

            assertThat(create(), equalTo(Success(Unit)))
            assertThat(list().successValue().contains(key), equalTo(false))
            assertThat(get(key).successValue(), absent())
            assertThat(set(key, "hello".byteInputStream()), equalTo(Success(Unit)))
            assertThat(String(get(key).successValue()!!.readBytes()), equalTo("hello"))
            assertThat(list().successValue().contains(key), equalTo(true))
            assertThat(set(key, "there".byteInputStream()), equalTo(Success(Unit)))
            assertThat(String(get(key).successValue()!!.readBytes()), equalTo("there"))

            assertThat(copy(key, newKey), equalTo(Success(Unit)))
            assertThat(String(get(newKey).successValue()!!.readBytes()), equalTo("there"))
            assertThat(list().successValue().contains(newKey), equalTo(true))
            assertThat(delete(newKey), equalTo(Success(Unit)))
            assertThat(delete(key), equalTo(Success(Unit)))
            assertThat(get(key).successValue(), absent())
            assertThat(list().successValue().contains(key), equalTo(false))
            assertThat(delete(), equalTo(Success(Unit)))
        }
    }
}
