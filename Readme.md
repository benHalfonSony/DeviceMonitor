Bugs I found:
- The list view wasn't refreshing every time we added a new item.
- New items wouldn't be affected by the removal event.

things I missed:
 - I can reuse the form of adding in order to edit.
 - I can create 2 buttons:
   - 1 - Upload that would trigger a function that basically would set the list with the list in the file.
   - 2 - save - that would take the current list serialized it and save it.
 - In initialization, I'll save the time and store it so I can use a new button that basically takes the current timestamp and calculates using the saved timestamp the last time update and the next one.  
