package org.http4k.connect.amazon.cognito.action

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import org.http4k.connect.Http4kConnectAction
import org.http4k.connect.RemoteFailure
import org.http4k.connect.amazon.cognito.model.UserPoolId
import org.http4k.connect.amazon.cognito.model.Username
import org.http4k.core.Method.POST
import org.http4k.core.Response
import se.ansman.kotshi.JsonSerializable

@Http4kConnectAction
@JsonSerializable
data class AdminDeleteUser(
    val Username: Username,
    val UserPoolId: UserPoolId
) : CognitoAction<Unit>(Unit::class) {
    override fun toResult(response: Response) = with(response) {
        when {
            status.successful -> Success(Unit)
            else -> Failure(RemoteFailure(POST, uri(), status, bodyString()))
        }
    }
}
