# Olympics_Hartree_Task

### Project Overview

This project is a comprehensive platform for managing and simulating Olympic events. It allows for country and Olympian registration, event management, and medal tracking through various REST API endpoints.

### Project Logic

- **Country and Olympian Registration:** Countries can register and log in, after which they can register Olympians under their banner.
- **Event Management:** New events can be added and viewed. Registered Olympians can be signed up for these events by their respective countries.
- **Event Simulation:** Events are run through a random selection process that determines the winners. Medals are awarded based on performance, with automatic updates to Olympians' and countries' medal counts, ranks, and positions.
- **Data Integrity:** The system ensures that events cannot be rerun incorrectly by updating the status of each event once completed. Additionally, the project can be reset to its initial state with no points assigned to any country or Olympian, ensuring a fresh start for testing and verification.

### Technologies Used

- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **API:** RESTful Services

### Details in SIMULATION.txt
