# SpaceDistance
A simple java librry to calculate distance between celestial bodies.

Currently only has builtin clases for three celestial bodies: Earth, Sol (the sun), and Mars. Distances between any point on them can be calculated, provided you know the time since the last perihelion and the time since midnight of each Planet involved.

The base Planet class does all the heavy lifting, if you want to create others, you just extend it and implement the getter methods it needs.

Still to-do (in no particular order):
  * Distances between stars
  * Some kind of perihelion catalog, at least for Earth, to be able to use Date objects
  * Rest of the solar system
