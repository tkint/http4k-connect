package org.http4k.connect.amazon.secretsmanager

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.http4k.connect.amazon.model.SecretId
import org.http4k.connect.amazon.model.VersionId
import org.http4k.connect.amazon.model.VersionStage
import org.http4k.format.AutoMappingConfiguration
import org.http4k.format.AwsCoreJsonAdapterFactory
import org.http4k.format.ConfigurableMoshi
import org.http4k.format.asConfigurable
import org.http4k.format.value
import org.http4k.format.withAwsCoreMappings
import org.http4k.format.withStandardMappings
import se.ansman.kotshi.KotshiJsonAdapterFactory

object SecretsManagerMoshi : ConfigurableMoshi(
    Moshi.Builder()
        .add(KotshiSecretsManagerJsonAdapterFactory)
        .add(AwsCoreJsonAdapterFactory())
        .asConfigurable()
        .withStandardMappings()
        .withAwsCoreMappings()
        .withSecretsManagerMappings()
        .done()
)

fun <T> AutoMappingConfiguration<T>.withSecretsManagerMappings() = apply {
    value(SecretId)
    value(VersionId)
    value(VersionStage)
}

@KotshiJsonAdapterFactory
abstract class SecretsManagerJsonAdapterFactory : JsonAdapter.Factory

