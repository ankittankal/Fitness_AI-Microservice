Fit-AI: Your Personal Fitness Recommender 🏋️‍♂️

Fit-AI is a microservices-based application designed to provide users with personalized fitness recommendations. It leverages the power of Google's Gemini AI to generate insightful and tailored advice based on user activity logs.

🏛️ System Architecture

The application is built on a microservice architecture to ensure scalability, resilience, and maintainability. Each service has a distinct responsibility, and they communicate with each other through a combination of synchronous REST APIs and asynchronous messaging.

The key components are:

Frontend / Postman: The client interface where users interact with the application or where developers test the API.

API Gateway: The single entry point for all incoming requests. It routes traffic to the appropriate downstream service, handling concerns like security and rate limiting.

Eureka Service Discovery: Allows services to dynamically discover and communicate with each other without hardcoded URLs, making the system more robust.

User Service: Manages all user-related data, such as profiles, goals, and preferences. It uses a PostgreSQL database for persistent storage.

Activity Service: Responsible for tracking and storing user fitness activities, like workouts and runs. It uses a MongoDB database.

Kafka: A distributed messaging queue that enables asynchronous communication. It decouples the Activity Service from the AI Service, ensuring that recommendation generation doesn't block the user's actions.

AI Service: The core of our recommendation engine. It listens for new activities from Kafka, fetches relevant user data, and communicates with Google's Gemini API to generate personalized fitness advice. It also uses a MongoDB database, likely for caching or storing generated recommendations.

Google's Gemini: The external Large Language Model (LLM) that provides the intelligent, human-like fitness recommendations.

⚙️ How It Works
The data flows through the system in a clean, event-driven manner:

Request Initiation: A user logs a new fitness activity (e.g., a 30-minute run) through the frontend.

Routing: The request first hits the API Gateway, which forwards it to the Activity Service.

Activity Logging: The Activity Service saves the new activity details to its MongoDB database.

Event Publishing: After successfully saving the activity, the Activity Service publishes an event (e.g., new_activity_logged) to a specific topic in Kafka.

Asynchronous Processing: The AI Service, which subscribes to this Kafka topic, consumes the event message. This process happens in the background without making the user wait.

AI Recommendation: It then constructs a detailed prompt using the activity data and sends it to the Google Gemini API.

Response Handling: Gemini processes the prompt and returns a personalized recommendation (e.g., "Great run! To improve your stamina, consider adding interval sprints twice a week.").

Storing/Delivering the Recommendation: The AI Service receives the recommendation and can store it in its database and make it available to the user.

💻 Tech Stack
Core Framework: [Springboot]

AI Model: Google Gemini API

Databases:

PostgreSQL: For the User Service (structured, relational data)

MongoDB: For the Activity and AI Services (flexible, document-based data)

Messaging: Apache Kafka

Service Discovery: Netflix Eureka

API Gateway: Spring Cloud Gateway

Client: Postman / [Your Frontend Framework, e.g., React, Angular]

🔧 Getting Started
Follow these steps to get the project running on your local machine.

Prerequisites

Java installed

Docker and Docker Compose

Maven / Gradle / NPM

An active Google Gemini API key

Installation & Running

Clone the repository:

git clone []
cd [your-project-directory]
Configure Environment Variables:
Create a .env file in the root directory and add your credentials. Each service may have its own configuration file (application.properties or .yml).



# Common variables

POSTGRES_USER=youruser

POSTGRES_PASSWORD=yourpassword

MONGO_INITDB_ROOT_USERNAME=youruser

MONGO_INITDB_ROOT_PASSWORD=yourpassword

GEMINI_API_KEY=your_gemini_api_key

KAFKA_BROKER_URL=kafka:9092

EUREKA_SERVER_ADDRESS=http://eureka-server:8761/eureka

Build the Services:

Navigate into each microservice directory and run the build command. For a Maven project, it would be:

mvn clean install

Launch with Docker Compose:
From the root directory, start all the services (including databases and Kafka).

Bash

docker-compose up --build -d
This command will build the Docker images for each service and start them in detached mode.

Verify:
Check the Docker logs to ensure all containers are running without errors. You can access the Eureka dashboard at http://localhost:8761 to see if all services have registered successfully.
