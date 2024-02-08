# School-App

This project contains a Java application that performs school-app functions.

## Prerequisites

Make sure you have the following software installed on your computer before proceeding:

- Java Development Kit (JDK)
- PostgreSQL database server
- Eclipse IDE

- About PostgreSQL Installation:
During the installation you may get following error:

"Problem running post-install step. Installation may not complete correctly
The database cluster initialisation failed."

To install PostgreSQL without any error, do the following:
1. If you have a 3rd party Antivirus Software, pause it's protection temporarily.
2. During installation, when it asks you for language, select "English - United States".

The installation should be complete without any error.

## Steps

### Importing the Project

1. Open Eclipse IDE.
2. Select the **File** menu and choose the **Import** option.
3. In the opened window, select **Existing Maven Projects** and click **Next**.
4. Specify the directory where the project is located (Root Directory) and click **Finish**.
5. Eclipse will import the Maven project and add it to the project explorer.

### Lombok Installation

1. Ensure that Lombok is installed in your IDE.
2. If Lombok is not installed, follow these steps:
   - Close your IDE.
   - Add the Lombok library to your IDE.
   - Start your IDE.

### Loading Dependencies and Building the Project

After importing the project, Eclipse will automatically load the dependencies using Maven. This process may take some time and runs in the background. To compile your Maven project, follow these steps:

1. Right-click on the project and select **Run As** from the context menu, then click on **Maven Install**.
2. When the Maven project build process is complete, you will see a successful message in the console.

### PostgreSQL Integration

To use the PostgreSQL database server, follow these steps:

1. Open the `application.properties` file located under `src/main/resources`.
2. Update your database connection information according to your PostgreSQL server (e.g., URL, username, password, etc.).
3. Save the changes.

- Note that these database connection information is only for you. To keep changes for a specific file only in local and not track them
without adding it into gitignore file, use following command on git bash:

```git update-index --assume-unchanged path/to/application.properties```

The path of it is most likely : `src/main/resources/application.properties`

### Creating the PostgreSQL Database
To create the PostgreSQL database:
 1.Open a SQL client tool.
 2.Connect to your PostgreSQL database server.
 3.Execute the following SQL command to create the school_app database:
   **CREATE DATABASE school_app;**

### Importing Dummy Data into PostgreSQL

Once the database has been created using the scripts from the school-app-db-script folder, you can observe how the project operates with mock data by executing the dummy_data.sql script. This script populates your database with sample data, providing a realistic preview of the application's functionality. To import this dummy data, follow these steps:

1. Open a SQL client tool.
2. Connect to your PostgreSQL database server.
3. Select the school-app database.
4. Locate and open the dummy_data.sql file from the project's school-app-db-script folder.
5. Execute the SQL statements within the **dummy_data.sql** file to populate the database with dummy data.
By following these instructions, you can quickly set up your environment to reflect a working state of the application, making it easier to test functionalities, debug, and demonstrate features without the need to manually enter data.

### Running the Application

1. There should be a Java class representing the entry point of the application under `src/main/java/tr.edu.school.schoolapp.SchoolAppApplication` class.
2. To run the application, follow these steps:
   - Locate the entry point class and right-click on it.
   - From the context menu, select **Run As**, and then click on **Java Application**.
3. When the application runs successfully, you will see the relevant output in the console.

### Accessing the Application

When the application is running, you can access the expected screens by navigating to `localhost:8080` in your web browser.

> Note: Make sure the application is running on the specified port, which is `8080` by default.
