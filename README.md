# Libby's Personal Project

## Sleep Tracking Journal 

*Why am I making this?*

<br>

As a student, it can be hard to get the correct hours of sleep and be able to say definitively how many hours of sleep you've gotten.
That's why I decided to create a sleep tracker journal, where one can track the hours of sleep they have been getting to stay healthy. 
In addition, I'd be able to log my dreams and rate the quality of sleep I've had. Did I wake up in the middle of the night? Did I sleep through my alarm? Any student will understand the struggle of not being able to sleep well. If I am more informed about my own sleep quality, I will also be able to make healthier decisions. 

*My journal will include:*
- Ability to log the number of hours slept
- Ability to rate your sleep quality
- Ability to track your dreams by writing a note  
- Ability to go back and view all entries
- Ability to see the average sleep rating + number of hours you've slept
- Ability to sort sleep entries by highest rating and highest sleep quality

#### My X and Y: 
- X = sleepEntry Y = sleepJournal

#### What I want to be able to do as a user:

##### Add multiple sleep entries to a sleep journal:
*As a user, I want to be able to log multiple sleep entries to my sleep journal over time so that I can track my sleep patterns on different days.*

##### List all sleep entries in the journal:
*As a user, I want to be able to view a list of all sleep entries in my sleep journal that I have added so that I can easily review and compare my sleep history.*

##### Log total hours slept and sleep quality for a specific date:
*As a user, I want to log the number of hours I slept, rate the quality, as well as add notes of my sleep for a specific date so I can track how well I’m sleeping each night.*

##### Sort sleep entries by ratings and number of hours slept 
*As a user, I want to be able to see the entries in which I have had the highest number of hours of sleep and the highest ratings of sleep.*

##### View average sleep hours and quality:
*As a user, I want to be able to see the average hours I’ve slept and the average sleep quality of my entries, so I can analyze my overall sleep patterns and make adjustments if needed.*

##### Save SleepJournal:
*As a user, when I select the quit option from the application menu, I want to be reminded to save my to-do list and have the option to do so or not.*

##### Load SleepJournal:
*As a user, when I start the application, I want to be given the option to load my sleepJournal*

## Instructions for End User

- You can generate the first required action related to the user story "adding multiple entries to a sleep journal" by clicking add entry
- You can generate the second reqiured action related to the user story "viewing average hours and average sleep quality of entries" by clicking "view average hours" in the main menu
- You can generate a third action related to the user story "sorting the entries by highest number of hours and highest sleep rating" by clicking the sort by... block in view all entries 
- You can locate my visual component by saving an entry and exiting out of the app as well
- You can save the state of my application by pressing save when adding a new entry
- You can reload the state of my application by pressing load all entries in the view all entries page 

#### Source code used: 
University of British Columbia. (n.d.). TellerApp. GitHub. https://github.students.cs.ubc.ca/CPSC210/TellerApp
University of British Columbia. (n.d.). JsonSerializationDemo. GitHub. https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

#### Phase 4: Task 2:
-   Tue Nov 26 16:09:10 PST 2024
-   SleepJournal initialized.
-   Tue Nov 26 16:09:10 PST 2024
-   SleepJournal initialized.
-   Tue Nov 26 16:09:10 PST 2024
-   SleepEntry added:
 -  Date : 2024-08-09
 -  Rating : 8.0
-   Tue Nov 26 16:09:10 PST 2024
-   SleepEntry added:
 -  Date : 2024-09-09
 -  Rating : 2.0
-   Tue Nov 26 16:09:10 PST 2024
-   SleepEntry added:
-   Date : 2024-11-26
-   Rating : 2.0
-   Tue Nov 26 16:09:10 PST 2024
-   Calculated average hours slept: 9.0
-   Tue Nov 26 16:09:10 PST 2024
-   Calculated average sleep rating: 4
-   Tue Nov 26 16:09:15 PST 2024
-   SleepJournal initialized.
-   Tue Nov 26 16:09:15 PST 2024
-   SleepEntry added:
-   Date : 2024-08-09
-   Rating : 8.0
-   Tue Nov 26 16:09:15 PST 2024
-   Tue Nov 26 16:09:15 PST 2024
-   Accessed all SleepEntries.
-   Tue Nov 26 16:09:17 PST 2024
-   SleepJournal sorted by hours slept.
-   Tue Nov 26 16:09:17 PST 2024
-   Accessed all SleepEntries.
-   Tue Nov 26 16:09:18 PST 2024
-   SleepJournal sorted by sleep rating.
-   Tue Nov 26 16:09:18 PST 2024
-   Accessed all SleepEntries.
-   Tue Nov 26 16:09:23 PST 2024
-   Calculated average hours slept: 9.0
-   Tue Nov 26 16:09:23 PST 2024
-   Calculated average sleep rating: 4


#### Phase 4: Task 3:
If I had more time to work on this project, one area I would focus on refactoring is the separation of concerns and reducing coupling between classes like MainWindow and the specific panels (AddEntryPanel, MainMenuPanel, AverageStatsPanel, ViewEntriesPanel). Right now, MainWindow directly interacts with multiple panels, which could make the code harder to maintain and extend in the future if I decide to add or modify panels. To improve this, I would introduce a PanelManager or Controller class to handle the lifecycle of these panels and delegate the responsibility of switching and updating panels. This would make MainWindow more focused on initializing and displaying the user interface, aligning better with the Single Responsibility Principle.
<br>
Additionally, I would refactor the interaction between JsonWriter/JsonReader and the panels (AddEntryPanel, ViewEntriesPanel, etc.). These classes currently handle the persistence logic but are directly tied to specific panels, which creates unnecessary coupling. I would centralize this logic by introducing a PersistenceManager class. This way, all persistence-related tasks are handled in one place, making the design more modular. It would also make it easier to update how data is stored or retrieved without impacting the individual panels. These changes would improve the overall maintainability and scalability of the application while keeping the responsibilities of each class more clearly defined.