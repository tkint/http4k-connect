dependencies {
    api(project(":http4k-connect-amazon-core"))
    api(Libs.http4k_serverless_lambda)

    testImplementation(Libs.aws_lambda_java_events)
    testImplementation(project(path = ":http4k-connect-core", configuration = "testArtifacts"))
    testImplementation(project(path = ":http4k-connect-amazon-core", configuration = "testArtifacts"))
}
