Fit-AI: Your Personal Fitness Recommender 🏋️‍♂️

Fit-AI is a microservices-based application designed to provide users with personalized fitness recommendations. It leverages the power of Google's Gemini AI to generate insightful, tailored advice based on user activity logs.

🏛️ System Architecture

The application is built on a microservice architecture to ensure scalability, resilience, and maintainability. Each service has a distinct responsibility, and they communicate through a combination of synchronous REST APIs and asynchronous messaging.

Key Components

Frontend / Postman
The client interface where users interact with the application or developers test the API.

API Gateway
Single entry point for all incoming requests, routing traffic to downstream services, handling security and rate limiting.

Eureka Service Discovery
Allows services to dynamically discover and communicate with each other without hardcoded URLs.

User Service
Manages all user-related data including profiles, goals, and other relevant information. Uses PostgreSQL for persistent storage.

Activity Service
Tracks and stores user fitness activities (workouts, runs). Uses MongoDB for storage.

Kafka
Distributed messaging queue enabling asynchronous communication. Decouples Activity Service from AI Service for smooth processing.

AI Service
Core of the recommendation engine. Subscribes to Kafka topics, fetches relevant user data, communicates with Google Gemini AI, and stores/generated recommendations in MongoDB.

Google Gemini
External Large Language Model (LLM) providing intelligent, human-like fitness recommendations.

⚙️ How It Works

Request Initiation
A user logs a new fitness activity (e.g., a 30-minute run) via the frontend.

Routing
Request hits the API Gateway and is forwarded to the Activity Service.

Activity Logging
Activity Service stores activity details in MongoDB.

Event Publishing
Activity Service publishes an event (e.g., new_activity_logged) to Kafka.

Asynchronous Processing
AI Service consumes the event from Kafka without blocking the user.

AI Recommendation
AI Service constructs a prompt and sends it to Google Gemini API.

Response Handling
Gemini returns a personalized recommendation (e.g., "Great run! To improve stamina, add interval sprints twice a week.").

Storing/Delivering Recommendation
AI Service stores the recommendation in MongoDB and makes it available to the user.

💻 Tech Stack

Core Framework: Spring Boot

AI Model: Google Gemini API

Databases:

PostgreSQL (User Service)

MongoDB (Activity & AI Services)

Messaging: Apache Kafka

Service Discovery: Netflix Eureka

API Gateway: Spring Cloud Gateway

Client: Postman / [Your Frontend Framework, e.g., React, Angular]

🔧 Getting Started
Prerequisites

Java installed

Docker & Docker Compose

Maven / Gradle / NPM

Active Google Gemini API Key

Installation & Running

Clone the repository

git clone [your-repository-url]
cd [your-project-directory]


Configure Environment Variables

Create a .env file in the root directory and add credentials. Each service may also have its own application.properties or application.yml.

# Common Variables
POSTGRES_USER=youruser
POSTGRES_PASSWORD=yourpassword
MONGO_INITDB_ROOT_USERNAME=youruser
MONGO_INITDB_ROOT_PASSWORD=yourpassword
GEMINI_API_KEY=your_gemini_api_key
KAFKA_BROKER_URL=kafka:9092
EUREKA_SERVER_ADDRESS=http://eureka-server:8761/eureka


Build the Services

Navigate into each microservice directory and run:

mvn clean install


Launch with Docker Compose

From the root directory, start all services (including databases and Kafka):

docker-compose up --build -d


Verify

Check Docker logs to ensure all containers are running without errors.

Access the Eureka dashboard at http://localhost:8761
 to see registered services.
