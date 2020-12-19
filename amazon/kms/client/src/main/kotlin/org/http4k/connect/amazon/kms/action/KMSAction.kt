package org.http4k.connect.amazon.kms.action

import org.http4k.connect.amazon.AwsJsonAction
import org.http4k.connect.amazon.kms.KMSMoshi
import org.http4k.connect.amazon.model.AwsService
import kotlin.reflect.KClass

abstract class KMSAction<R : Any>(clazz: KClass<R>) : AwsJsonAction<R>(AwsService.of("TrentService"), clazz, KMSMoshi)