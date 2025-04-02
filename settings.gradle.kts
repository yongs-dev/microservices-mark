rootProject.name = "microservices-mark"

include(
    "common-config",
    "app-config-data",
    "twitter-to-kafka-service",
    "kafka:kafka-model",
    "kafka:kafka-admin",
    "kafka:kafka-producer"
)
