# Rockwell Technical Assessment

## Scenario
Build a simple web application that will act as a task scheduler for simple tasks.
Specifically, the task is to ping a website and scrape the headers (or first 1000 characters).
The application should take a Cron syntax expression and a URL as input, and then execute the job according to the schedule.

## Considerations
- Use any language (C#, Java, Javascript, Go, etc.)
- Provide a web API (REST, GraphQL, etc.)
- You can build the application to run locally, or in a public cloud (Azure, AWS, GCP) using whatever technology stacks are available for you in that environment.
- You do not need to include authentication.
- UI is mandatory (Angular is a plus)
- You must keep your code in a source control repository (GitHub, Bitbucket, etc.)
- Do not build a Cron expression parser, there are many to choose from.
