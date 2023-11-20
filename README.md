# BâtisPro Inventory Management System

This project is aimed at developing an application for BâtisPro, a company specializing in construction equipment rental, to streamline their inventory management process. The application will allow employees to efficiently manage equipment, track availability, handle rentals, and generate detailed usage reports.

## User Stories

- **Stock Manager**
  - As a stock manager, I want to be able to add a new piece of equipment to the inventory, including details like name, available quantity, and daily rental cost.
  - As a stock manager, I want the ability to update existing equipment information, including available quantity and rental cost.

- **General User**
  - As a user, I want to search for specific equipment in the stock to check its availability and rental cost.
  - As a stock manager, I want to record equipment rentals, including client information, rental duration, and equipment details.
  - As a user, I want to view the rental history for specific equipment.

## Additional Requirements

### Unit Testing

- Developers are required to create unit tests for each class to ensure the proper functioning of core functionalities.
- Unit tests should cover scenarios such as adding equipment, updating information, searching for equipment, and recording rentals.
- Automatic execution of unit tests during each project build is necessary to ensure code stability.

### Database Migration with Liquibase

- Liquibase will be used to manage versioned database schema changes.
- Every new application version release must be accompanied by a Liquibase script to update the existing database schema.
- Liquibase scripts should be designed for automatic execution during the deployment of a new application version.
- Prior to applying migrations to the production database, thorough testing on a test environment is mandatory to prevent any adverse impacts.

## Getting Started

To start using the application, follow these steps:

1. Clone this repository to your local machine.
2. Install the required dependencies (list dependencies and how to install them).
3. Set up the database and run the migration scripts (provide instructions).
4. Start the application using the command (provide the command).

## Contributing

We welcome contributions! If you want to contribute to the project, follow these steps:

1. Fork this repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Make your changes and commit them (`git commit -m 'Add your feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Create a pull request explaining your changes.

## License

This project is licensed under the [LICENSE NAME] - see the [LICENSE.md](link to license file) file for details.

## Acknowledgments

- Mention any contributors or resources that were helpful or inspiring for this project.
