from collections import deque

w = "baadcb"

def reading_data(fileName):
    with open(f"{fileName}.txt", 'r', encoding='utf-8') as file:
        data = file.read()
    
    data_list = data.splitlines()  
    return data_list

def key_mapper(letter): 
    return ord(letter) - 97

def letter_mapper(key): 
    return chr(key + 97)

def data_processing(data): 
    data_map = {}

    for row in data: 
        row = row.replace(" ", "")
        key = key_mapper(row[1])
        data_map[key] = row[3:]
    
    return data_map

def get_literals_from_string(s): 
    return [char for char in s if key_mapper(char) in range(26)]

def find_relations(data_map): 
    dependencies = set()
    independencies = set()

    for key1 in data_map.keys(): 
        for key2 in data_map.keys(): 
            if data_map[key1][0] in get_literals_from_string(data_map[key2]): 
                dependencies.add((letter_mapper(key1), letter_mapper(key2)))
                dependencies.add((letter_mapper(key2), letter_mapper(key1)))
            else: 
                if (letter_mapper(key2), letter_mapper(key1)) not in independencies:
                    independencies.add((letter_mapper(key1), letter_mapper(key2)))
            
    return dependencies, independencies

def dieckert_graph(D, w): 
    n = len(w)
    graph = [[] for _ in range(n)]

    for i in range(n): 
        for j in range(i + 1, n): 
            if (w[i], w[j]) in D: 
                graph[i].append((w[j], j))

    for i in range(n): 
        for j in range(len(graph[i])):
            for k in range(j):
                if graph[i][j] in graph[graph[i][k][1]] and graph[i][k] != (0, 0): 
                    graph[i][j] = (0, 0)
                    break

    for row in graph: 
        while (0, 0) in row:
            row.remove((0,0))

    return graph

def foata_form(graph):
    n = len(graph)  
    in_degree = [0] * n 
    
    for i in range(n):
        for _, neighbor in graph[i]: 
            in_degree[neighbor] += 1

    foata_levels = []
    
    zero_in_degree = deque()

    for i, _ in enumerate(graph): 
        if in_degree[i] == 0: 
            zero_in_degree.append((w[i], i))
    
    while zero_in_degree:
        current_level = []
        
        for _ in range(len(zero_in_degree)):
            char, node = zero_in_degree.popleft()
            current_level.append(char)  
            
            for char, neighbor in graph[node]:  
                in_degree[neighbor] -= 1
                if in_degree[neighbor] == 0:
                    zero_in_degree.append((char, neighbor))
        
        foata_levels.append(sorted(current_level))  
    
    return foata_levels




data = reading_data("data")
data_map = data_processing(data)
D, I = find_relations(data_map)
graph = dieckert_graph(D, w)
print(foata_form(graph))
