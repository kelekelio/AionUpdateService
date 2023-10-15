# Use the official Alpine Linux as the base image
FROM postgres:14.1-alpine

# Set environment variables
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password

# Expose PostgreSQL port
EXPOSE 5432

# Create the two databases ("aion_1" and "aion_2") during container initialization
COPY init.sql /docker-entrypoint-initdb.d/