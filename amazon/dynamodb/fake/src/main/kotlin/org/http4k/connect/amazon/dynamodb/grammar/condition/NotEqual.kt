package org.http4k.connect.amazon.dynamodb.grammar.condition

import org.http4k.connect.amazon.dynamodb.grammar.Expr
import org.http4k.connect.amazon.dynamodb.grammar.ExprFactory
import org.http4k.connect.amazon.dynamodb.grammar.binaryExpr
import parser4k.Parser
import parser4k.ref

object NotEqual : ExprFactory {
    override operator fun invoke(parser: () -> Parser<Expr>): Parser<Expr> =
        binaryExpr(ref(parser), "<>") { attr1, attr2 ->
            Expr {
                attr1.eval(it) != attr2.eval(it)
            }
        }
}