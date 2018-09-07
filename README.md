Journal - A Planning Tool

This journal's aim is to help users plan their time effectively, stay on task, and reflect on their experiences. 


Installation (note that Java must be installed): 
1. Create a "Journal" folder
2. Create a "src" folder inside "Journal"
3. Create a "com" folder inside "src"
4. Create a "jou" folder inside "com"
5. Create a "main" folder inside "jou"
6. Copy all the files in this github repository into the "main" folder
7. Open the command line and enter the "Journal" folder
8. Enter "java src.com.jou.main.Init"

Now, every time you want to use the Journal, you can just do the last two steps (or create an alias to do it for you).


Effective Use Guidelines:

- Make a list of tasks every morning, keeping in mind the week's goals. This will help you start the day with the right mindset and stay on task.
- "Complete" any tasks you finish to feel a sense of accomplishment and motivation.
- Reflect on what you learn at the end of every day to help you improve in the future.
- Always use "exit" or "quit" after editing the journal to save your data.

Important Notes:

- Normal tasks carry over to the next day you log in if not completed, so you do not need to worry about forgetting to complete a task and losing it. Daily tasks will not carry over. Ongoing tasks only apply to the given date range and do not carry over.
- All data is saved in the text file journalData.txt (in the Journal folder). This includes completed tasks; you will be reminded of the tasks you have completed in a week when writing a weekly reflection. Deleted data, however, is not saved.

Commmand Descriptions:

Week View
- new (task, ongoing task, goal, reflection): 
    Add a new item of that type to the week. An ongoing task will be added to every date in a range of dates (you will be prompted for 
    the date range).
- day (#): 
    Enter Day View on the given day number.
- delete goal: 
    Delete a weekly goal. You will later be prompted for the goal number to delete. The goal is not saved.
- today: 
    Enter Day View on the current date.
- go to day: 
    Enter Day View on any date. You will be prompted for the desired date. You may want to use this feature if you need to remember to 
    complete something in future weeks.
- delete (task #): 
    Delete the given task. The task is not saved.
- complete (task #): 
    Complete the given task.
- exit: 
    Exits the program and saves your data. Always use exit, unless you do not want to save your changes.

Day View
- new (task, ongoing task, daily task, note, reflection): 
    Add a new item of that type to the week. An ongoing task will be added to every date in a range of dates (you will be prompted for 
    the date range). A daily task will not carry to your next login date if not completed.
- week: 
    Enter Week View on the week that includes the day currently being viewed.
- delete note: 
    Delete a note. You will later be prompted for the note number to delete. The note is not saved.
- today: 
    Enter Day View on the current date.
- go to day: 
    Enter Day View on any date. You will be prompted for the desired date. You may want to use this feature if you need to remember to 
    complete something in future weeks.
- delete (task #): 
    Delete the given task. The task is not saved.
- complete (task #): 
    Complete the given task.
- exit: 
    Exits the program and saves your data. Always use exit, unless you do not want to save your changes.

Shortcuts and Advanced Notes:

- Backups of the last 5 saves are stored on a rotating basis. The third line of journalData.txt states the most recent backup save location. The five backup locations are: .0, .1, .2, .3, .4
- When entering dates, you will be prompted to use the format yyyy-[m]m-[d]d. You may simply use [m]m-[d]d if the desired date is the current year.
- Most commands can be invoked by using only the first letter of each word in the command. For example, nt runs new task, and gtd runs go to day.
