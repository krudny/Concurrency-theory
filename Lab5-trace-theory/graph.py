from util import get_literals_from_string, letter_mapper
from collections import deque
import networkx as nx
import matplotlib.pyplot as plt

def dieckert_graph(D, w): 
    n = len(w)
    graph = {i : [] for i in range(len(w))}

    for i in range(n): 
        for j in range(i + 1, n): 
            if (w[i], w[j]) in D: 
                graph[i].append(j)

    to_remove = set()

    for u in graph:
        for v in graph[u]: 
            for z in graph[u]: 
                if v in graph[z]:
                    to_remove.add((u, v))
                    break 

    for u, v in to_remove: 
        graph[u].remove(v)

    return graph

def foata_form(graph):
    n = len(graph)  
    in_degree = [0] * n 
    
    for i in range(n):
        for neighbor in graph[i]: 
            in_degree[neighbor] += 1

    foata_levels = []
    
    zero_in_degree = deque()

    for i in graph: 
        if in_degree[i] == 0: 
            zero_in_degree.append(i)
    
    while zero_in_degree:
        current_level = []
        
        for _ in range(len(zero_in_degree)):
            node = zero_in_degree.popleft()
            current_level.append(node)  
            
            for neighbor in graph[node]:  
                in_degree[neighbor] -= 1
                if in_degree[neighbor] == 0:
                    zero_in_degree.append(neighbor)
        
        foata_levels.append(sorted(current_level))  
    
    return foata_levels

def find_relations(data_map): 
    dependencies = set()
    independencies = set()

    for key1 in data_map.keys(): 
        for key2 in data_map.keys(): 
            if data_map[key1][0] in get_literals_from_string(data_map[key2]) or data_map[key2][0] in get_literals_from_string(data_map[key1]): 
                dependencies.add((letter_mapper(key1), letter_mapper(key2)))
                dependencies.add((letter_mapper(key2), letter_mapper(key1)))
            else: 
                letter1, letter2 = letter_mapper(key1), letter_mapper(key2)
                if (letter1, letter2) not in independencies and (letter2, letter1):
                    independencies.add((letter1, letter2))
            
    return dependencies, independencies

def print_graph(graph, w):
    G = nx.DiGraph()

    for u in graph: 
        for v in graph[u]: 
            G.add_edge(u, v)
    
    labels = {node: w[node] for node in G.nodes}

    pos = nx.circular_layout(G)
    nx.draw(G, pos, labels=labels, with_labels=True, node_size=700, node_color="skyblue", font_size=10, font_color="black", font_weight="bold", arrowsize=20)
    plt.show()