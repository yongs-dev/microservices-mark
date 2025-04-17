rootProject.name = "microservices-mark"

include(
    "app-config-data",
    "common-config",
    "common-util",
    "config-server",
    "elastic:elastic-config",
    "elastic:elastic-index-client",
    "elastic:elastic-model",
    "elastic:elastic-query-client",
    "elastic-query-service",
    "elastic-query-service-common",
    "elastic-query-web-client",
    "elastic-query-web-client-common",
    "kafka:kafka-model",
    "kafka:kafka-admin",
    "kafka:kafka-producer",
    "kafka:kafka-consumer",
    "kafka-to-elastic-service",
    "playground",
    "twitter-to-kafka-service"
)
