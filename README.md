# Dijkstra-Path-Finding
This is my final project of Data Structures and Algorithms course at AUT.  

 I used Java for implementing Dijkstra algorithm on a directed graph with weighted edges and Python for visualizing the chosen path for each passenger.  It considers the real time traffic of streets and estimates transportation time for each transportation.

![Capture](https://user-images.githubusercontent.com/60196448/109313860-ea1d1c00-785d-11eb-9752-a3e23b0fa916.JPG)

# Compiling and Running With JDK

Run this command in project root folder to compile the project: 

```
javac -d classes -sourcepath src src/*.java
```

And to run the program:

```
java -cp classes RouteFinder
```

# How to use

Once you run the program, you should select a map text file to create a graph based on that map. There are some examples in `Maps` folder and you can use `m?.txt` files as a map. maps text file template is like this:

```
<number of vertices> <number of edges>
<first vertex ID> <first vertex latitude> <first vertex longitude> 
...
<last vertex ID> <last vertex latitude> <last vertex longitude>
```

After choosing the map, you should determine the number of transportations and after that you can query transportations like this:

```
<start time> <source vertex> <destination vertex>
```

you can see some examples in `test.txt` files in `Maps` folder.