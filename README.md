# TDDE43
Intro to android dev course at Linköping University
in the course we had to choose a project to work on. Here I have programmed two components that could be part of that project. The components can be used independently of each other. The first component is the EventCalendar which should display the data for each event. The idea is that a company should be able to enter events with date, time, URL, images, etc. I have also programmed a comparator that sorts the events by date before they are shown to the user.
The second component is a reaction pop up, i.e. something that shows emoji.
The components must then be able to insert logic as needed.

Since this was the first time I programmed in Android, there are of course a lot of improvements that can be made.

1. for the component with events, I used activities when displaying information about the event (ShowContent class). The problem with this is that if you want to add logic or custom components directly from main, it will not be possible to use an interface in a normal Java way.
When you switch to a new activity, the old data is "thrown away". You can get around this, but it should preferably be avoided.
Instead, segments should be used, which should be a better method and provide a cleaner code.

2. the reaction pop up component is easy to implement in other classes but here I happened to use a built-in class (Dialog) which is not updated by android anymore 
(what I read anyway) you could use others, more updated classes or build your own logic entirely.
