package org.http4k.connect.amazon.dynamodb.endpoints

import org.http4k.connect.amazon.AmazonJsonFake
import org.http4k.connect.amazon.dynamodb.DynamoTable
import org.http4k.connect.amazon.dynamodb.action.Query
import org.http4k.connect.amazon.dynamodb.action.QueryResponse
import org.http4k.connect.storage.Storage

fun AmazonJsonFake.query(tables: Storage<DynamoTable>) = route<Query> {
    // todo
    QueryResponse()
}
