import matplotlib.pyplot as plt
import sys

def get_vertex_by_id(id):
	global vertices
	for vertex in vertices:
		if (vertex[0] == id):
			return vertex
	return None

commands = sys.argv
file_address = commands[1]

vertices = []
edges = []
index = 1
with open(file_address,'r') as file:
	graph_list = file.readlines()
	info = graph_list[0].rstrip().split()
	v = int(info[0])
	e = int(info[1])

	for i in range(v):
		numbers = graph_list[index].rstrip().split(" ")
		vertices.append((int(numbers[0]),float(numbers[1]),float(numbers[2])))
		index += 1
	for i in range(e):
		numbers = graph_list[index].rstrip().split(" ")
		edges.append((int(numbers[0]),int(numbers[1])))
		index += 1

path_len = int(commands[2])
path = []
#Getting ver
index = 3
for i in range(path_len):
	path.append((int(commands[index]),int(commands[index+1])))
	index += 2	

plt.figure();

lines = []
paths = []

for edge in edges:
	v0 = get_vertex_by_id(edge[0])
	v1 = get_vertex_by_id(edge[1])
	lines.append((v0[2],v0[1],v1[2],v1[1]))

for tup in lines:
	x,y = (tup[0],tup[2]),(tup[1],tup[3]) 
	plt.plot(x,y,'b');

for p in path:
	v0 = get_vertex_by_id(p[0])
	v1 = get_vertex_by_id(p[1])
	paths.append((v0[2],v0[1],v1[2],v1[1]))

for tup in paths:
	x,y = (tup[0],tup[2]),(tup[1],tup[3]) 
	plt.plot(x,y,'r')

latitudes =  [tup[1] for tup in vertices]
longitudes = [tup[2] for tup in vertices]

plt.plot(longitudes,latitudes,'k.')
if (len(vertices) < 50):
	for tup in vertices:
		plt.text(tup[2],tup[1] + 0.0001,str(tup[0]))
plt.show()