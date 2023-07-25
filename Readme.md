Bugs I found:
- the list view wasn't refresh everytime we are adding new item.
- new items wouldn't be affected with the remove event.

things I missed:
 - I can reuse the form of adding inorder to do edit.
 - I can create 2 buttons:
   - 1 - upload that would trigger a function that basically would set the list with the list in the file.
   - 2 - save - that would take the current list serilized it and save it.
 - in initialization i'll save the time and store it so I could use a new button that basically takes the current times temp and calculate using the saved timestamp the lase time update and the next one.  
