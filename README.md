# DarkStoreApp_TestTask

Used technologies: 

Kotlin,
MVVM,
Dagger2,
Room,
Retrofit,
Coroutines,
Kotlin Flow,
Clean architecture.

Usage:
1. When you enter the application, you will see a dialog with a date selection.
2. The next step is validating the date. If the date satisfies the requirements (no more than the current one, not empty), 
a request is made to the network to obtain data on the Ukrain national exchange market for the selected day.
3. User can enter the amount in UAH and set the currency for which it want to make a calculation.
4. The calculation is performed and the result is displayed.
5. To select a new date, simply click on the calendar icon in the upper right corner and a calendar will open for selecting a date.
6. If you need to view the history of the last 10 operations, you can go to the viewing page by clicking on the icon under the calendar icon
7. To return from the History page, click on the arrow in the upper left corner or use the system function to return.
