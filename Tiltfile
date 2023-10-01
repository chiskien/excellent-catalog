# Build
custom_build (
    # Name of the container image
    ref = 'excellent-bookshop',
    # Command to build the container image
    command = 'mvn spring-boot:build-image -D imageName=${EXPECTED_REF}',
    # Files to watch that trigger a new build
    deps = ['src', 'pom.xml']
)

# Deploy
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

# Manage
k8s_resource('excellent-bookshop', port_forwards=['9001'])