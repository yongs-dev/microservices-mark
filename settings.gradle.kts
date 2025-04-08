rootProject.name = "microservices-mark"

include(
    "app-config-data",
    "common-config",
    "config-server",
    "kafka:kafka-model",
    "kafka:kafka-admin",
    "kafka:kafka-producer",
    "kafka:kafka-consumer",
    "kafka-to-elastic-service",
    "playground",
    "twitter-to-kafka-service"
)
