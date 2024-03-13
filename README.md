# Railway Network Simulator Coding Challenge

Imagine you are part of a team building a railway simulator (perhaps to be used to help build a new
railway).

- The simulator will allow trains to move through segments of track and stations.
- Segments of track can only have one train in them at a time
- Stations can have multiple trains in them at a time
- The train company has two types of trains: express (fast) and local (slow)
- Each track segment / station has a length: l. This should be in metres. A typical segment might be
1,000 m long. A station 100m.
- Each train has a speed: s. Fast trains travel at 500 metres per second (wow!). Slow ones at a mere 10
metres per second.
- The time a train is in any segment is l/s
- The time a train is in a staion is l/s + 5 (they stop for 5 seconds – people have to get on and off
quickly!)
- When the time is up (i.e. the train is at the end of the segment or has finished in a station), it can
only move into the next segment if there is space. Space is determined by capacity. I.e. a station with
capacity 3 can have 3 trains in it at once. Segments can only have one train in them at once.

Consider only tracks that have a start (left) and end (right). In other words, each station or piece of track
succeeds only one other station / track and precedes only one other. Trains only travel in one direction
(e.g. left to right). When the train has gone beyond the end of the last point on the network, 
it is removed from the network. 

- Train objects (each should run on its own thread)
- Track and station objects
- An object that creates trains and randomly puts them into the first bit of track / station (should be on
another thread and should continue indefinitely). (Use random delays between trains, and randomly
choose which type of train)
- An object that continually prints out the status of the railway (e.g. see screenshot below).
- You should use conditions and locks to implement the constraints on the number of trains in any
given section. E.g. a train can only enter a segment / station when there is space. This must be
enforced with Conditions, await and signal or signalAll

![Alt Text](example%20screenshot.jpg)
